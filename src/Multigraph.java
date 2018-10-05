import java.util.*;

public class Multigraph implements IGraph
{
    private List<MGNode> nodes;
    private List<MGEdge> edges;

    public Multigraph()
    {
	nodes = new ArrayList<>();
	edges = new ArrayList<>();
    }
    
    public void addNode(int id, String label)
    {
	nodes.add(new MGNode(id, label));
    }
    
    public void addEdge(INode node1, INode node2, String label)
    {
	edges.add(new MGEdge(node1, node2, label));
    }
    
    public INode getNodeById(int id) throws NoSuchNodeException
    {
	for(INode n : nodes)
	{
	    if(n.getId() == id)
	    {
		return n;
	    }
	}
	throw new NoSuchNodeException();
    }
    
    public INode getNodeByLabel(String label) throws NoSuchNodeException
    {
	for(INode n : nodes)
	{
	    if(n.getLabel().equals(label))
	    {
		return n;
	    }
	}
	throw new NoSuchNodeException();
    }
    
    private boolean isEdge(INode node1, INode node2) // might be needed for route planning :)
    {
	for(MGEdge e : edges)
	{
	    if(e.getNode1().equals(node1) && e.getNode2().equals(node2) || e.getNode1().equals(node2) && e.getNode2().equals(node1))
	    {
		return true;
	    }
	}
	return false;
    }
    

    public List<IEdge> findPath(INode root, INode destination)
    {
	// TODO implement path planning
	isEdge(null, null);
	return null;
    }
}
