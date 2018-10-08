/**
 * An implementation of the node interface INode.
 * Has private fields to hold the necessary data to use in its methods.
 */
public class MGNode implements INode {
    private String label;
    private int id;

    /**
     * Constructor to build the node given the parameters for ID and label.
     *
     * @param id
     * @param label
     */
    public MGNode(int id, String label) {
        this.id = id;
        this.label = label;
    }

    /**
     * Returns the node ID as required by the specification.
     *
     * @return node ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the node label as required by the specification.
     *
     * @return node label
     */
    public String getLabel() {
        return label;
    }
}
