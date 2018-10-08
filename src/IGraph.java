/**
 * This interface acts as the specification for a multigraph
 * It should make use of implemented nodes and edges interfaces which act as specifications for node and edge functionality.
 * The graph provides functionality for adding nodes and edges to the graph,finding the shortest path between two nodes and finding nodes by IDs and names.
 */

import java.util.List;

public interface IGraph {
    /**
     * Allows a node to be added to the graph using the required data to build it.
     * @param id
     * @param label
     */
    public void addNode(int id, String label);

    /**
     * Allows an edge to be added to the graph using the nodes it connects and a label.
     * @param node1
     * @param node2
     * @param label
     */
    public void addEdge(INode node1, INode node2, String label);

    /**
     * Traverses the graph to find a path from one given node to another.
     * Returns a list of edges that describe this connection.
     * @param root
     * @param destination
     * @return
     */
    public List<IEdge> findPath(INode root, INode destination);

    /**
     * Allows a node in the graph to be found based on its ID
     * @param id
     * @return
     * @throws NoSuchNodeException
     */
    public INode getNodeById(int id) throws NoSuchNodeException;

    public INode getNodeByLabel(String label) throws NoSuchNodeException;
}
