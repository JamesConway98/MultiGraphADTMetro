import java.util.List;

public interface iGraph {

    public void addNode( int ID, String name);

    public void addEdge(int root, int destination, String label);

    public int numNodes();

    public List<Integer> findPath(int root, int destination);
}
