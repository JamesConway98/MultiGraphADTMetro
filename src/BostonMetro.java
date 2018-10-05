import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BostonMetro
{
	private ArrayList<ArrayList<String>> nodes;
    private String fileLocation;
    HashMap<Integer, String> orange, blue, green, red;

    public static void main(String[] args)
    {

    }

	/*
	IGraph graph = new Multigraph();
	MetroParser parser = new MetroParser("file.ext");
	parser.parse(); // creates HashMaps stations, greenLine, redLine ...
	stations.forEach((id, station) -> {
            graph.addNode(id, station);
        });
        greenLine.forEach((id1, id2) -> {
            graph.addEdge(graph.getNodeById(id1), graph.getNodeById(id2), "green");
        });
        ...
        something like that :)
	*/
    public BostonMetro() throws BadFileException {
    	fileLocation = "H:/cis/windows/workspace/boston_metro/src/Stations.txt";
    	orange = new HashMap<Integer, String>(32);
    	blue  = new HashMap<Integer, String>(32);
    	green  = new HashMap<Integer, String>(32);
    	red  = new HashMap<Integer, String>(32);
    	
    	
    	try {
			read();
		} catch (BadFileException e) {
			// TODO Auto-generated catch block
			throw new BadFileException("Incorrect File Source");
		}
    	lineMaps();
    }
    
    private void read() throws BadFileException{
    	try {
			MetroMapParser reader = new MetroMapParser(fileLocation);
			nodes = reader.generateGraphFromFile();
		} catch (IOException | BadFileException e) {
			throw new BadFileException("Incorrect File Source");
			
		}
    	//System.out.print(nodes);  	
    }
    
    /* for each colour
     * attach hashset with <stationID,stationLabel>
     */
    private void lineMaps(){
    	// Orange!
    	for(int i = 0; i < nodes.size(); i++){
    			if(nodes.get(i).contains("Orange") && Integer.parseInt(nodes.get(i).get(0)) == i + 1){
    				orange.put(i + 1, nodes.get(i).get(1));
    				//System.out.print(nodes.get(i).get(1) + " was successfully added to hashmap orange," + " size: " + orange.size() + "\n");
    		}
    	}//blue!
    	for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).contains("Blue") && Integer.parseInt(nodes.get(i).get(0)) == i + 1){
				blue.put(i + 1, nodes.get(i).get(1));
				System.out.print(nodes.get(i).get(1) + " was successfully added to hashmap blue," + " size: " + blue.size() + "\n");
			}
		}/*
		//green!!	
    	for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).contains("green") && Integer.parseInt(nodes.get(i).get(0)) == i + 1){
				green.put(i + 1, nodes.get(i).get(1));
			}
		}*/
    	/*red!!
    	for(int i = 0; i < nodes.size(); i++){
			if(nodes.get(i).contains("Red") && Integer.parseInt(nodes.get(i).get(0)) == i + 1){
				red.put(i + 1, nodes.get(i).get(1));
			}
		}*/
	}
    
}
