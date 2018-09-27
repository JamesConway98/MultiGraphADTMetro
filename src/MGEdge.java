public class MGEdge implements iEdge {

    private String label;

    public MGEdge(String label){
        setLabel(label);
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }


}
