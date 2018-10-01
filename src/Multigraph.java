import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

public class Multigraph implements iGraph {

    private ArrayList<iNode> graph;

    public Multigraph() {
        graph = new ArrayList<>();
    }

    @Override
    public void addNode() {
        graph.add(new MGNode(numNodes()+1));
        for (int i = 0; i < numNodes()-1; i++) {
            graph.get(i).setNumNodes(numNodes());
        }
    }

    @Override
    public int numNodes() {
        return graph.size();
    }

    @Override
    public void addEdge(int root, int destination, String label) {
        graph.get(root).addEdge(destination, label);
    }

    @Override
    public ArrayList<Integer> findPath(int root, int destination) {
        return null;
    }
}
