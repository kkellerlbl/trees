#!/usr/bin/env perl
use strict;
use warnings;
use Getopt::Long;
use Data::Dumper;

use Bio::KBase::Tree::Client;
use Bio::KBase::Tree::Util qw(get_tree_client);


my $DESCRIPTION =
"
NAME
      tree-get-tree -- retrieve tree or tree meta data from the CDS

SYNOPSIS
      tree-get-tree [OPTIONS] [TREE_ID]

DESCRIPTION
      Retrieve the specified tree or meta information associated with the tree.  The
      raw tree is returned in newick format by default with leaf node labels in an
      arbitrary internal id that is unique only within the given tree.  By default, the
      raw tree stored in KBase is returned.  To return the tree with node labels replaced
      with KBase protein sequence IDs or cannonical feature IDs, use the options below.  To
      provide a list of tree IDs, pipe in the list through standard-in or specify an input
      file to read.  In either case, each tree id should appear on a separate line.  In the
      case of a list, results are returned in the same order they were provided as input.
      
      -p, --protein-sequence
                        set this flag to return the tree with node labels replaced with
                        protein sequence IDs
                        
      -f, --feature
                        set this flag to return the tree with node labels replaced with KBase
                        feature IDs.  Note that some trees may not have assigned cannonical
                        feature IDs for each node, in which case blank labels will be returned.
                        
      -b, --bootstrap-remove
                        set this flag to return the tree with bootstrap values removed
                        
      -d, --distance-remove
                        set this flag to return the tree without distance values removed
                        
      -m, --meta
                        set this flag to return meta data instead of the tree itself
                        
      -i, --input [FILENAME]
                        set this flag to specify the input file to read
                        
      -h, --help
                        diplay this help message, ignore all arguments
                        
                        

EXAMPLES
      Retrieve the raw tree newick string
      > tree-get-tree 'kb|tree.25'
      
      Retrieve meta data about a tree
      > tree-get-tree -m 'kb|tree.25'
      
      

SEE ALSO
      tree-find-tree-ids
      
AUTHORS
      Michael Sneddon (mwsneddon\@lbl.gov)
      
";

my $help = '';
my $metaFlag = '';
my $replaceFeature='';
my $replaceSequence='';
my $noBootstrap='';
my $noDist='';
my $inputFile='';
my $opt = GetOptions (
        "help" => \$help,
        "meta" => \$metaFlag,
        "feature" => \$replaceFeature,
        "protein-sequence" => \$replaceSequence,
        "bootstrap-remove" => \$noBootstrap,
        "distance-remove" => \$noDist,
        "input" => \$inputFile
        );

if($help) {
     print $DESCRIPTION;
     exit 0;
}

my $n_args = $#ARGV+1;

my $id_list=[];
# if we have specified an input file, then read the file
if($inputFile) {
     my $inputFileHandle;
     open($inputFileHandle, "<", $inputFile);
     if(!$inputFileHandle) {
          print "FAILURE - cannot open '$inputFile' \n$!\n";
          exit 1;
     }
     eval {
          while (my $line = <$inputFileHandle>) {
               chomp($line);
               push @$id_list,$line;
          }
          close $inputFileHandle;
     };
}

# if we have a single argument, then accept it as the treeString
elsif($n_args==1) {
     my $id = $ARGV[0];
     chomp($id);
     push @$id_list,$id;
}

# if we have no arguments, then read the tree from standard-in
elsif($n_args == 0) {
     while(my $line = <STDIN>) {
          chomp($line);
          push @$id_list,$line;
     }
} else {
     print "Invalid number of arguments.  Run with --help for usage.\n";
     exit 1;
}

foreach my $treeId (@$id_list) {
    #create client
    my $treeClient;
    eval{ $treeClient = get_tree_client(); };
    if(!$treeClient) {
        print "FAILURE - unable to create tree service client.  Is you tree URL correct? see tree-url.\n";
        exit 1;
    }
    if($metaFlag) {
        #get meta data
        my $tree_data;
        # eval {   #add eval block so errors don't crash execution, should really handle exceptions here.
            ($tree_data) = $treeClient->get_tree_data([$treeId]);
        # };
        if(exists $tree_data->{$treeId}) {
            my $metaData = $tree_data->{$treeId};
            print "[tree_id]:\t".$treeId."\n";
            foreach my $label (keys %$metaData) {
                print "[".$label."]:\t".$metaData->{$label}."\n";
            }
        }
    } else {
        #get actual tree
        my $tree;
        # eval {   #add eval block so errors don't crash execution, should really handle exceptions here.
            my $options = {};
            if($replaceFeature && $replaceSequence) { print "FAILURE - cannot use -p option with -f; choose one or the other.\n"; exit 1; }
            if($replaceFeature) {$options->{newick_label}='feature_id';}
            if($replaceSequence) {$options->{newick_label}='protein_sequence_id';}
            if($noBootstrap) {$options->{newick_bootstrap}='none';}
            if($noDist) {$options->{newick_distance}='none';}
            ($tree) = $treeClient->get_tree($treeId,$options);
        # };
        if($tree) { print $tree."\n"; }
    }
        
}

exit 0;

