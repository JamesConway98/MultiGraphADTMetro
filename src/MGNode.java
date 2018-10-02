import java.util.ArrayList;

public class MGNode implements iNode {

    private String name;
    private int ID;

    private ArrayList<ArrayList<iEdge>> node;


    public MGNode(int numNodes, int ID, String name) {
        node = new ArrayList<>();						//give the node a list of edges
        this.ID = ID;									//give the node an ID
        this.name = name;								//give the node a name
    }

    @Override
    public void setNumNodes(int numNodes) {
        for (int i = 0; i < (numNodes - node.size()); i++) {
            if (node.get(i).equals(null))
                node.add(new ArrayList<>());
        }
    }

    @Override
    public int addEdge(int nodeIndex, String label) {		//add an edge for a given node index
        if (node.get(nodeIndex).size() == 0){				//add the edge if the nodes aren't already connected
            node.get(nodeIndex).add(new MGEdge(label));
            return node.get(nodeIndex).size();
        }
    }

    @Override
    public int isEdge(int nodeIndex) {
        return node.get(nodeIndex).size();
    }

    @Override
    public int outDegree() {
        int deg = 0;
        for (int i = 0; i < node.size(); i++) {
             deg = deg + node.get(i).size();
        }
        return deg;
    }
}
