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

    /**
     * This method and its associated auxiliary methods find the shortest path from a given node, to another.
     * The search method used is a breadth first search, traversing the graph by looking at every node out from the root, one layer deeper at a time.
     * The search looks at every node at each layer it visits before probing deeper into the graph.
     * The first path found to the destination is therefor the shortest, however not the fastest.
     * This method takes two INode objects as its parameters, the root and destination.
     * <p>
     * To keep track of what nodes have been visited, each node visited is placed in a set of INodes.
     * The path is built once the search has found the destination node. This is done by linking each node to the node it came from with a HashMap.
     * The route is generated by going from the destination, to the node the search got to it from, and so on until it reaches the root, where it cannot go back further in the map.
     * The edges describing these links (as IEdge objects) are placed in a list (backwards do to the reverse traversal) and reversed to describe root -> destination.
     *
     * @param root
     * @param destination
     * @return A list of IEdge objects
     */
    public List<IEdge> findPath(INode root, INode destination) {

        /*
            Declare fields used for searching the shortest route between two given nodes within the graph.

            nodesSearched:   A set of INode objects that keeps a record of visited nodes so the graph does not search in loops.
                             Implemented as a HashSet as ordering of objects does not matter so the efficiency of this data type can be utilised.
            previousNode:    A map that links a recently visited node, to the node that the graph visited before when looking at its neighbours.
                             Allows for backtracking and building the path.
            path:            The list used for holding the generated route and returning to the method caller.
            nextNodes:       A queue that is used to hold the nodes to next visit and check if it is the destination and if not, check its neighbours.
                             By using the 'First in-First out' approach of a queue, it ensures the depth of searching remains consistent when looking at neighbours of nodes.
            current:         The node currently being compared to the destination and who's neighbours will be added to nextNodes.
         */
        Set<INode> nodesSearched = new HashSet<>();
        Map<INode, INode> previousNode = new HashMap<>();
        List<IEdge> path = new LinkedList<>();
        Queue<INode> nextNodes = new LinkedList<>();
        INode current = root;

        /*  Sets the root to be the first node checked. Also adds it to the checked set fo nodes.   */
        nextNodes.add(current);
        nodesSearched.add(root);

        while (!nextNodes.isEmpty()) { /* The search will continue to traverse the graph looking for the destination node as long as there are nodes that have been reached and not visited. */
            current = nextNodes.remove();   /* Takes the head node off of the queue (FIFO)and first checks if it is the destination. */
            if (current.equals(destination)) {
                break;
            } else {
                Set<INode> neighbours = getNeighbours(current); /* Gets all neighbouring nodes of currently checked node using the auxiliary method getNeighbours(INode root) */
                for (INode n : neighbours) {    /* For each neighbouring node, if it has not been visited, it gets added to the queue to be checked, added to the set of checked nodes and a record of its predecessor is kept.*/
                    if (!nodesSearched.contains(n)) {
                        nextNodes.add(n);
                        nodesSearched.add(n);
                        previousNode.put(n, current);
                    }
                }
            }
        }
        if (!current.equals(destination)) { /* If the search ends and the currently checked node is not the destination, then all connected nodes have been checked and the destination was not found, so a path does not exist and the method returns the empty list. */
            return null;
        }
        for (INode node = destination; node != null; node = previousNode.get(node)) {   /* Reaches this block if the destination was found. This block backtracks through the HashMap to build a list of edges that connect the destination and root. */
            INode tempPrevious = previousNode.get(node);
            for (IEdge edge : edges) {
                if (findEdge(edge, node, tempPrevious))
                    path.add(edge);
            }
        }
        Collections.reverse(path); /* Reverses the list of edges as it will read from destination to root rather than root to destination due to backtracking. */
        return path; /* The shortest path has been found and is returned to method caller. */
    }

    /**
     * Auxiliary method used to find the edge in the list of edges to connect one node to another, to build the shortest path (list of edges)
     *
     * @param edge
     * @param first
     * @param second
     * @return True or false if the given edge from the edges list is the desired edge between the given nodes.
     */
    private boolean findEdge(IEdge edge, INode first, INode second) {
        if ((edge.getNode1().equals(first) && edge.getNode2().equals(second)) || (edge.getNode1().equals(second) && edge.getNode2().equals(first)))
            return true;
        else
            return false;
    }

    /**
     * Auxiliary method to collect a set of neighbouring nodes to the given node.
     *
     * @param root
     * @return A set of nodes that neighbour the given node
     */
    private Set<INode> getNeighbours(INode root) {
        Set<INode> neighbours = new HashSet<>();
        for (INode node : nodes) {
            if (isEdge(root, node)) {
                neighbours.add(node);
            }
        }
        return neighbours;
    }

    /**
     * Auxiliary method used to check if an edge exists between two given nodes.
     *
     * @param node1
     * @param node2
     * @return True or false if an edge does or does not exist between the given nodes.
     */
    private boolean isEdge(INode node1, INode node2) {
        for (IEdge e : edges) {
            if (e.getNode1().equals(node1) && e.getNode2().equals(node2) || e.getNode1().equals(node2) && e.getNode2().equals(node1)) { /* Checks both directions that the edge could be interpreted as going based on its node fields */
                return true;
            }
        }
        return false;
    }
}
