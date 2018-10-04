import java.util.ArrayList;

public interface iNode {


    public int addEdge(int node, String label);

    public void setNumNodes(int set);

    public int isEdge(int node);

    public int outDegree();

    public int getID();

    public String getName();
}
