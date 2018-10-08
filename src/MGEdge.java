/**
 * An implementation of the edge interface IEdge.
 * Has private fields to hold the necessary data to use in its methods.
 * Represents an undirected edge.
 */
public class MGEdge implements IEdge {
    private String label;
    private INode node1, node2;

    /**
     * Constructor to build the edge by setting the nodes it refers to and its label as given by parameters.
     *
     * @param node1
     * @param node2
     * @param label
     */
    public MGEdge(INode node1, INode node2, String label) {
        this.label = label;
        this.node1 = node1;
        this.node2 = node2;
    }

    /**
     * Returns its label, as required by the specification.
     *
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the first of the two nodes it connects, as required by the specification.
     *
     * @return first node
     */
    public INode getNode1() {
        return node1;
    }

    /**
     * Returns the second of the two nodes it connects, as required by the specification.
     *
     * @return second node
     */
    public INode getNode2() {
        return node2;
    }
}
