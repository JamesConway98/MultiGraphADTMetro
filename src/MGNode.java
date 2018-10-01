import java.util.ArrayList;

public class MGNode implements iNode {

    //private String ID, name;

    private ArrayList<ArrayList<iEdge>> edges;


    public MGNode(int numNodes){ //}, String ID, String name) {
        edges = new ArrayList<>();
        //this.ID = ID;
        //this.name = name;
    }

    @Override
    public void setNumNodes(int numNodes) {
        for (int i = 0; i < (numNodes - edges.size()); i++) {
            if (edges.get(i).equals(null))
                edges.add(new ArrayList<>());
        }
    }

    @Override
    public int addEdge(int node, String label) {
        //if (edges.get(node).size() == 0){
            edges.get(node).add(new MGEdge(label));
            return edges.get(node).size();
        //}
    }

    @Override
    public int isEdge(int node) {
        return edges.get(node).size();
    }

    @Override
    public int outDegree() {
        int deg = 0;
        for (int i = 0; i < edges.size(); i++) {
             deg = deg + edges.get(i).size();
        }
        return deg;
    }
}
