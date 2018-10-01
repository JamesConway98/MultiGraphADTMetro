import java.util.ArrayList;

public class MGNode implements iNode {

    //private String ID, name;

    private ArrayList<ArrayList<iEdge>> node;


    public MGNode(int numNodes){ //}, String ID, String name) {
        node = new ArrayList<>();
        //this.ID = ID;
        //this.name = name;
    }

    @Override
    public void setNumNodes(int numNodes) {
        for (int i = 0; i < (numNodes - node.size()); i++) {
            if (node.get(i).equals(null))
                node.add(new ArrayList<>());
        }
    }

    @Override
    public int addEdge(int nodeIndex, String label) {
        //if (edges.get(node).size() == 0){
            node.get(nodeIndex).add(new MGEdge(label));
            return node.get(nodeIndex).size();
        //}
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
