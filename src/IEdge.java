/**
 * This interface acts as the specification for the edge of a multigraph.
 * It should provide access to a label and the 2 nodes it connects.
 */
public interface IEdge {
    /**
     * Provides access to the label of the edge
     * @return label
     */
    String getLabel();

    /**
     * Provides access to the first of the two nodes that the edge connects.
     * @return first node
     */
    INode getNode1();

    /**
     * Provides access to the second of the two nodes that the edge connects.
     * @return
     */
    INode getNode2();
}
