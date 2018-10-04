public class MGNode implements INode
{
    private String label;
    private int id;
    
    public MGNode(int id, String label)
    {
	this.id = id;
	this.label = label;
    }
    
    public int getId()
    {
	return id;
    }
    
    public String getLabel()
    {
	return label;
    }
}
