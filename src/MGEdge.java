public class MGEdge implements IEdge {
    private String label;
    private INode node1, node2;

    public MGEdge(INode node1, INode node2, String label) {
        this.label = label;
        this.node1 = node1;
        this.node2 = node2;
    }

    public String getLabel() {
        return label;
    }

    public INode getNode1() { return node1; }

    public INode getNode2() {
        return node2;
    }
}
