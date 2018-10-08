/**
 * This interface acts as the specification for the node of a multigraph.
 * It should provide access to a label and an ID
 */
public interface INode {
    /**
     * Allows access to a node's ID
     *
     * @return ID
     */
    int getId();

    /**
     * Allows access to a node's label
     *
     * @return label
     */
    String getLabel();
}
