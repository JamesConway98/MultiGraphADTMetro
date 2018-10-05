import java.util.*;

public class Multigraph implements IGraph {
    private List<INode> nodes;
    private List<IEdge> edges;

    public Multigraph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addNode(int id, String label) {
        nodes.add(new MGNode(id, label));
    }

    public void addEdge(INode node1, INode node2, String label) {
        edges.add(new MGEdge(node1, node2, label));
    }

    public INode getNodeById(int id) throws NoSuchNodeException {
        for (INode n : nodes) {
            if (n.getId() == id) {
                return n;
            }
        }
        throw new NoSuchNodeException();
    }

    public INode getNodeByLabel(String label) throws NoSuchNodeException {
        for (INode n : nodes) {
            if (n.getLabel().equals(label)) {
                return n;
            }
        }
        throw new NoSuchNodeException();
    }

    private boolean isEdge(INode node1, INode node2) // might be needed for route planning :)
    {
        for (IEdge e : edges) {
            if (e.getNode1().equals(node1) && e.getNode2().equals(node2) || e.getNode1().equals(node2) && e.getNode2().equals(node1)) {
                return true;
            }
        }
        return false;
    }

    private Set<INode> getNeighbours(INode root) {
        Set<INode> neighbours = new HashSet<>();
        for (INode node: nodes) {
            if (isEdge(root, node)) {
                neighbours.add(node);
            }
        }
        return neighbours;
    }

    /**
     * Will comment I promise - Kyle
     * @param root
     * @param destination
     * @return
     */
    public List<IEdge> findPath(INode root, INode destination) {
        Set<INode> nodesToSearch = new HashSet<>();
        nodesToSearch.addAll(nodes);

        Map<INode, INode> previousNode = new HashMap<>();

        List<IEdge> path = new LinkedList<>();

        Queue<INode> currentPath = new LinkedList<>();

        INode current = root;

        currentPath.add(current);

        nodesToSearch.remove(root);

        while (!currentPath.isEmpty()) {
            current = currentPath.remove();
            if (current.equals(destination))
                break;
			else{
			    Set<INode> neighbours = getNeighbours(current);
			    for (INode n : neighbours){
			        if(nodesToSearch.contains(n)){
			            currentPath.add(n);
			            nodesToSearch.remove(n);
			            previousNode.put(n, current);
                    }
                }
            }
        }
        if (!current.equals(destination)){
            System.out.println("No Path");
        }
        for (INode node = destination; node != null; previousNode.get(node)){
            INode tempPrevious = previousNode.get(node);
            for (IEdge edge : edges){
                if (findEdge(edge, node, tempPrevious))
                        path.add(edge);
            }
        }
        Collections.reverse(path);
        return path;
    }

    private boolean findEdge(IEdge edge, INode first, INode second){
        if ((edge.getNode1().equals(first) && edge.getNode2().equals(second)) || (edge.getNode1().equals(second) && edge.getNode2().equals(first)))
            return true;
        else
            return false;
    }
}
