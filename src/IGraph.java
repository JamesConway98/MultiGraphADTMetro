import java.util.List;

public interface IGraph
{
    public void addNode(int id, String label);
    public void addEdge(INode node1, INode node2, String label);
    public List<IEdge> findPath(INode root, INode destination);
    public INode getNodeById(int id) throws NoSuchNodeException;
    public INode getNodeByLabel(String label) throws NoSuchNodeException;
    public String getLabelByNode(INode node);
}
