package us.kbase.tree;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.UnauthorizedException;
import us.kbase.kbasetrees.AbundanceParams;
import us.kbase.kbasetrees.AbundanceResult;
import us.kbase.kbasetrees.AlignmentMetaData;
import us.kbase.kbasetrees.FilterParams;
import us.kbase.kbasetrees.TreeMetaData;

/**
 * <p>Original spec-file module name: Tree</p>
 * <pre>
 * Phylogenetic Tree and Multiple Sequence Alignment Services
 * This service provides a set of methods for querying, manipulating, and analyzing multiple
 * sequence alignments and phylogenetic trees.
 * Authors
 * ---------
 * Michael Sneddon, LBL (mwsneddon@lbl.gov)
 * Fangfang Xia, ANL (fangfang.xia@gmail.com)
 * Keith Keller, LBL (kkeller@lbl.gov)
 * Matt Henderson, LBL (mhenderson@lbl.gov)
 * Dylan Chivian, LBL (dcchivian@lbl.gov)
 * </pre>
 */
public class TreeClient {
    private JsonClientCaller caller;

    public TreeClient(URL url) {
        caller = new JsonClientCaller(url);
    }

    public TreeClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, token);
    }

    public TreeClient(URL url, String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password);
    }

	public void setConnectionReadTimeOut(Integer milliseconds) {
		this.caller.setConnectionReadTimeOut(milliseconds);
	}

    public boolean isAuthAllowedForHttp() {
        return caller.isAuthAllowedForHttp();
    }

    public void setAuthAllowedForHttp(boolean isAuthAllowedForHttp) {
        caller.setAuthAllowedForHttp(isAuthAllowedForHttp);
    }

	public void _setFileForNextRpcResponse(File f) {
		caller.setFileForNextRpcResponse(f);
	}

    /**
     * <p>Original spec-file function name: replace_node_names</p>
     * <pre>
     * Given a tree in newick format, replace the node names indicated as keys in the 'replacements' mapping
     * with new node names indicated as values in the 'replacements' mapping.  Matching is EXACT and will not handle
     * regular expression patterns.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @param   replacements   instance of mapping from original type "node_name" (The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note that this is not the full, raw label in a newick_tree (which may include comments).) to original type "node_name" (The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note that this is not the full, raw label in a newick_tree (which may include comments).)
     * @return   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String replaceNodeNames(String tree, Map<String,String> replacements) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        args.add(replacements);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("Tree.replace_node_names", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: remove_node_names_and_simplify</p>
     * <pre>
     * Given a tree in newick format, remove the nodes with the given names indicated in the list, and
     * simplify the tree.  Simplifying a tree involves removing unnamed internal nodes that have only one
     * child, and removing unnamed leaf nodes.  During the removal process, edge lengths (if they exist) are
     * conserved so that the summed end to end distance between any two nodes left in the tree will remain the same.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @param   removalList   instance of list of original type "node_name" (The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note that this is not the full, raw label in a newick_tree (which may include comments).)
     * @return   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String removeNodeNamesAndSimplify(String tree, List<String> removalList) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        args.add(removalList);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("Tree.remove_node_names_and_simplify", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: merge_zero_distance_leaves</p>
     * <pre>
     * Some KBase trees keep information on canonical feature ids, even if they have the same protien sequence
     * in an alignment.  In these cases, some leaves with identical sequences will have zero distance so that
     * information on canonical features is maintained.  Often this information is not useful, and a single
     * example feature or genome is sufficient.  This method will accept a tree in newick format (with distances)
     * and merge all leaves that have zero distance between them (due to identical sequences), and keep arbitrarily
     * only one of these leaves.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String mergeZeroDistanceLeaves(String tree) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("Tree.merge_zero_distance_leaves", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: extract_leaf_node_names</p>
     * <pre>
     * Given a tree in newick format, list the names of the leaf nodes.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of list of original type "node_name" (The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note that this is not the full, raw label in a newick_tree (which may include comments).)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> extractLeafNodeNames(String tree) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("Tree.extract_leaf_node_names", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: extract_node_names</p>
     * <pre>
     * Given a tree in newick format, list the names of ALL the nodes.  Note that for some trees, such as
     * those originating from MicrobesOnline, the names of internal nodes may be bootstrap values, but will still
     * be returned by this function.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of list of original type "node_name" (The string representation of the parsed node name (may be a kbase_id, but does not have to be).  Note that this is not the full, raw label in a newick_tree (which may include comments).)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> extractNodeNames(String tree) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("Tree.extract_node_names", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_node_count</p>
     * <pre>
     * Given a tree, return the total number of nodes, including internal nodes and the root node.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of Long
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long getNodeCount(String tree) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("Tree.get_node_count", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_leaf_count</p>
     * <pre>
     * Given a tree, return the total number of leaf nodes, (internal and root nodes are ignored).  When the
     * tree was based on a multiple sequence alignment, the number of leaves will match the number of sequences
     * that were aligned.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @return   instance of Long
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Long getLeafCount(String tree) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        TypeReference<List<Long>> retType = new TypeReference<List<Long>>() {};
        List<Long> res = caller.jsonrpcCall("Tree.get_leaf_count", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_tree</p>
     * <pre>
     * Returns the specified tree in the specified format, or an empty string if the tree does not exist.
     * The options hash provides a way to return the tree with different labels replaced or with different attached meta
     * information.  Currently, the available flags and understood options are listed below. 
     *     options = [
     *         format => 'newick',
     *         newick_label => 'none' || 'raw' || 'feature_id' || 'protein_sequence_id' ||
     *                         'contig_sequence_id' || 'best_feature_id' || 'best_genome_id',
     *         newick_bootstrap => 'none' || 'internal_node_labels'
     *         newick_distance => 'none' || 'raw'
     *     ];
     *  
     * The 'format' key indicates what string format the tree should be returned in.  Currently, there is only
     * support for 'newick'. The default value if not specified is 'newick'.
     * The 'newick_label' key only affects trees returned as newick format, and specifies what should be
     * placed in the label of each leaf.  'none' indicates that no label is added, so you get the structure
     * of the tree only.  'raw' indicates that the raw label mapping the leaf to an alignement row is used.
     * 'feature_id' indicates that the label will have an examplar feature_id in each label (typically the
     * feature that was originally used to define the sequence). Note that exemplar feature_ids are not
     * defined for all trees, so this may result in an empty tree! 'protein_sequence_id' indicates that the
     * kbase id of the protein sequence used in the alignment is used.  'contig_sequence_id' indicates that
     * the contig sequence id is added.  Note that trees are typically built with protein sequences OR
     * contig sequences. If you select one type of sequence, but the tree was built with the other type, then
     * no labels will be added.  'best_feature_id' is used in the frequent case where a protein sequence has
     * been mapped to multiple feature ids, and an example feature_id is used.  Similarly, 'best_genome_id'
     * replaces the labels with the best example genome_id.  The default value if none is specified is 'raw'.
     * The 'newick_bootstrap' key allows control over whether bootstrap values are returned if they exist, and
     * how they are returned.  'none' indicates that no bootstrap values are returned. 'internal_node_labels'
     * indicates that bootstrap values are returned as internal node labels.  Default value is 'internal_node_labels';
     * The 'newick_distance' key allows control over whether distance labels are generated or not.  If set to
     * 'none', no distances will be output. Default is 'raw', which outputs the distances exactly as they appeared
     * when loaded into KBase.
     * </pre>
     * @param   treeId   instance of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @param   options   instance of mapping from String to String
     * @return   instance of original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String getTree(String treeId, Map<String,String> options) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(treeId);
        args.add(options);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("Tree.get_tree", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_alignment</p>
     * <pre>
     * Returns the specified alignment in the specified format, or an empty string if the alignment does not exist.
     * The options hash provides a way to return the alignment with different labels replaced or with different attached meta
     * information.  Currently, the available flags and understood options are listed below. 
     *     options = [
     *         format => 'fasta',
     *         sequence_label => 'none' || 'raw' || 'feature_id' || 'protein_sequence_id' || 'contig_sequence_id',
     *     ];
     *  
     * The 'format' key indicates what string format the alignment should be returned in.  Currently, there is only
     * support for 'fasta'. The default value if not specified is 'fasta'.
     * The 'sequence_label' specifies what should be placed in the label of each sequence.  'none' indicates that
     * no label is added, so you get the sequence only.  'raw' indicates that the raw label of the alignement row
     * is used. 'feature_id' indicates that the label will have an examplar feature_id in each label (typically the
     * feature that was originally used to define the sequence). Note that exemplar feature_ids are not
     * defined for all alignments, so this may result in an unlabeled alignment.  'protein_sequence_id' indicates
     * that the kbase id of the protein sequence used in the alignment is used.  'contig_sequence_id' indicates that
     * the contig sequence id is used.  Note that trees are typically built with protein sequences OR
     * contig sequences. If you select one type of sequence, but the alignment was built with the other type, then
     * no labels will be added.  The default value if none is specified is 'raw'.
     * </pre>
     * @param   alignmentId   instance of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @param   options   instance of mapping from String to String
     * @return   instance of original type "alignment" (String representation of a sequence alignment, the format of which may be different depending on input options for retrieving the alignment.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String getAlignment(String alignmentId, Map<String,String> options) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(alignmentId);
        args.add(options);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("Tree.get_alignment", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_tree_data</p>
     * <pre>
     * Get meta data associated with each of the trees indicated in the list by tree id.  Note that some meta
     * data may not be available for trees which are not built from alignments.  Also note that this method
     * computes the number of nodes and leaves for each tree, so may be slow for very large trees or very long
     * lists.  If you do not need this full meta information structure, it may be faster to directly query the
     * CDS for just the field you need using the CDMI.
     * </pre>
     * @param   treeIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @return   instance of mapping from original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".) to type {@link us.kbase.tree.TreeMetaData TreeMetaData}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,TreeMetaData> getTreeData(List<String> treeIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(treeIds);
        TypeReference<List<Map<String,TreeMetaData>>> retType = new TypeReference<List<Map<String,TreeMetaData>>>() {};
        List<Map<String,TreeMetaData>> res = caller.jsonrpcCall("Tree.get_tree_data", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_alignment_data</p>
     * <pre>
     * Get meta data associated with each of the trees indicated in the list by tree id.  Note that some meta
     * data may not be available for trees which are not built from alignments.  Also note that this method
     * computes the number of nodes and leaves for each tree, so may be slow for very large trees or very long
     * lists.  If you do not need this full meta information structure, it may be faster to directly query the
     * CDS for just the field you need using the CDMI.
     * </pre>
     * @param   alignmentIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @return   instance of mapping from original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".) to type {@link us.kbase.tree.AlignmentMetaData AlignmentMetaData}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,AlignmentMetaData> getAlignmentData(List<String> alignmentIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(alignmentIds);
        TypeReference<List<Map<String,AlignmentMetaData>>> retType = new TypeReference<List<Map<String,AlignmentMetaData>>>() {};
        List<Map<String,AlignmentMetaData>> res = caller.jsonrpcCall("Tree.get_alignment_data", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_tree_ids_by_feature</p>
     * <pre>
     * Given a list of feature ids in kbase, the protein sequence of each feature (if the sequence exists)
     * is identified and used to retrieve all trees by ID that were built using the given protein sequence.
     * </pre>
     * @param   featureIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @return   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getTreeIdsByFeature(List<String> featureIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(featureIds);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("Tree.get_tree_ids_by_feature", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_tree_ids_by_protein_sequence</p>
     * <pre>
     * Given a list of kbase ids of a protein sequences (their MD5s), retrieve the tree ids of trees that
     * were built based on these sequences.
     * </pre>
     * @param   proteinSequenceIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @return   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getTreeIdsByProteinSequence(List<String> proteinSequenceIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(proteinSequenceIds);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("Tree.get_tree_ids_by_protein_sequence", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_alignment_ids_by_feature</p>
     * <pre>
     * Given a list of feature ids in kbase, the protein sequence of each feature (if the sequence exists)
     * is identified and used to retrieve all alignments by ID that were built using the given protein sequence.
     * </pre>
     * @param   featureIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @return   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getAlignmentIdsByFeature(List<String> featureIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(featureIds);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("Tree.get_alignment_ids_by_feature", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_alignment_ids_by_protein_sequence</p>
     * <pre>
     * Given a list of kbase ids of a protein sequences (their MD5s), retrieve the alignment ids of trees that
     * were built based on these sequences.
     * </pre>
     * @param   proteinSequenceIds   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @return   instance of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<String> getAlignmentIdsByProteinSequence(List<String> proteinSequenceIds) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(proteinSequenceIds);
        TypeReference<List<List<String>>> retType = new TypeReference<List<List<String>>>() {};
        List<List<String>> res = caller.jsonrpcCall("Tree.get_alignment_ids_by_protein_sequence", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_tree_ids_by_source_id_pattern</p>
     * <pre>
     * This method searches for a tree having a source ID that matches the input pattern.  This method accepts
     * one argument, which is the pattern.  The pattern is very simple and includes only two special characters,
     * wildcard character, '*', and a match-once character, '.'  The wildcard character matches any number (including
     * 0) of any character, the '.' matches exactly one of any character.  These special characters can be escaped
     * with a backslash.  To match a blackslash literally, you must also escape it.  Note that source IDs are
     * generally defined by the gene family model which was used to identifiy the sequences to be included in
     * the tree.  Therefore, matching a source ID is a convenient way to find trees for a specific set of gene
     * families.
     * </pre>
     * @param   pattern   instance of String
     * @return   instance of list of list of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<List<String>> getTreeIdsBySourceIdPattern(String pattern) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(pattern);
        TypeReference<List<List<List<String>>>> retType = new TypeReference<List<List<List<String>>>>() {};
        List<List<List<String>>> res = caller.jsonrpcCall("Tree.get_tree_ids_by_source_id_pattern", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_leaf_to_protein_map</p>
     * <pre>
     * Given a tree id, this method returns a mapping from a tree's unique internal ID to
     * a protein sequence ID.
     * </pre>
     * @param   treeId   instance of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @return   instance of mapping from original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".) to original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getLeafToProteinMap(String treeId) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(treeId);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("Tree.get_leaf_to_protein_map", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_leaf_to_feature_map</p>
     * <pre>
     * Given a tree id, this method returns a mapping from a tree's unique internal ID to
     * a KBase feature ID if and only if a cannonical feature id exists.
     * </pre>
     * @param   treeId   instance of original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @return   instance of mapping from original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".) to original type "kbase_id" (A KBase ID is a string starting with the characters "kb|".  KBase IDs are typed. The types are designated using a short string. For instance," g" denotes a genome, "tree" denotes a Tree, and "aln" denotes a sequence alignment. KBase IDs may be hierarchical.  For example, if a KBase genome identifier is "kb|g.1234", a protein encoding gene within that genome may be represented as "kb|g.1234.peg.771".)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,String> getLeafToFeatureMap(String treeId) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(treeId);
        TypeReference<List<Map<String,String>>> retType = new TypeReference<List<Map<String,String>>>() {};
        List<Map<String,String>> res = caller.jsonrpcCall("Tree.get_leaf_to_feature_map", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: compute_abundance_profile</p>
     * <pre>
     * Given an input KBase tree built from a sequence alignment, a metagenomic sample, and a protein family, this method
     * will tabulate the number of reads that match to every leaf of the input tree.  First, a set of assembled reads from
     * a metagenomic sample are pulled from the KBase communities service which have been determined to be a likely hit
     * to the specified protein family.  Second, the sequences aligned to generate the tree are retrieved.  Third, UCLUST [1]
     * is used to map reads to target sequences of the tree.  Finally, for each leaf in the tree, the number of hits matching
     * the input search criteria is tabulated and returned.  See the defined objects 'abundance_params' and 'abundance_result'
     * for additional details on specifying the input parameters and handling the results.
     * [1] Edgar, R.C. (2010) Search and clustering orders of magnitude faster than BLAST, Bioinformatics 26(19), 2460-2461.
     * </pre>
     * @param   abundanceParams   instance of type {@link us.kbase.tree.AbundanceParams AbundanceParams}
     * @return   parameter "abundance_result" of type {@link us.kbase.tree.AbundanceResult AbundanceResult}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public AbundanceResult computeAbundanceProfile(AbundanceParams abundanceParams) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(abundanceParams);
        TypeReference<List<AbundanceResult>> retType = new TypeReference<List<AbundanceResult>>() {};
        List<AbundanceResult> res = caller.jsonrpcCall("Tree.compute_abundance_profile", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: filter_abundance_profile</p>
     * <pre>
     * ORDER OF OPERATIONS:
     * 1) using normalization scope, defines whether process should occur per column or globally over every column
     * 2) using normalization type, normalize by dividing values by the option indicated
     * 3) apply normalization post process if set (ie take log of the result)
     * 4) apply the cutoff_value threshold to all records, eliminating any that are not above the specified threshold
     * 5) apply the cutoff_number_of_records (always applies per_column!!!), discarding any record that are not in the top N record values for that column
     * - if a value is not a valid number, it is ignored
     * </pre>
     * @param   abundanceData   instance of original type "abundance_data" (map the name of the profile with the profile data) &rarr; mapping from String to original type "abundance_profile" (map an id to a number (e.g. feature_id mapped to a log2 normalized abundance value)) &rarr; mapping from String to Double
     * @param   filterParams   instance of type {@link us.kbase.tree.FilterParams FilterParams}
     * @return   parameter "abundance_data_processed" of original type "abundance_data" (map the name of the profile with the profile data) &rarr; mapping from String to original type "abundance_profile" (map an id to a number (e.g. feature_id mapped to a log2 normalized abundance value)) &rarr; mapping from String to Double
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public Map<String,Map<String,Double>> filterAbundanceProfile(Map<String,Map<String,Double>> abundanceData, FilterParams filterParams) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(abundanceData);
        args.add(filterParams);
        TypeReference<List<Map<String,Map<String,Double>>>> retType = new TypeReference<List<Map<String,Map<String,Double>>>>() {};
        List<Map<String,Map<String,Double>>> res = caller.jsonrpcCall("Tree.filter_abundance_profile", args, retType, true, false);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: draw_html_tree</p>
     * <pre>
     * Given a tree structure in newick, render it in HTML/JAVASCRIPT and return the page as a string. display_options
     * provides a way to pass parameters to the tree rendering algorithm, but currently no options are recognized.
     * </pre>
     * @param   tree   instance of original type "newick_tree" (Trees are represented in KBase by default in newick format (http://en.wikipedia.org/wiki/Newick_format) and are returned to you in this format by default.) &rarr; original type "tree" (A string representation of a phylogenetic tree.  The format/syntax of the string is specified by using one of the available typedefs declaring a particular format, such as 'newick_tree', 'phylo_xml_tree' or 'json_tree'.  When a format is not explictily specified, it is possible to return trees in different formats depending on addtional parameters. Regardless of format, all leaf nodes in trees built from MSAs are indexed to a specific MSA row.  You can use the appropriate functionality of the API to replace these IDs with other KBase Ids instead. Internal nodes may or may not be named. Nodes, depending on the format, may also be annotated with structured data such as bootstrap values and distances.)
     * @param   displayOptions   instance of mapping from String to String
     * @return   instance of original type "html_file" (String in HTML format, used in the KBase Tree library for returning rendered trees.)
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public String drawHtmlTree(String tree, Map<String,String> displayOptions) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(tree);
        args.add(displayOptions);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("Tree.draw_html_tree", args, retType, true, false);
        return res.get(0);
    }
}
