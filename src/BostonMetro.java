import java.io.IOException;
<<<<<<< HEAD
import java.util.Scanner;

public class BostonMetro
{
	
	private Multigraph graph;
	
    public BostonMetro() throws IOException
    {
	
	//Multigraph graph = new Multigraph();
	//MetroMapParser parser = new MetroMapParser("file.ext");
	
=======
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

>>>>>>> b96dea26e38f72302391a58c7e65a20cab04a229
	/*
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
    
<<<<<<< HEAD
    
    public String findPath() throws NoSuchNodeException {
    	
    	String startStation = "s";
    	String endStation = "e";
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	Multigraph graph = new Multigraph();
    	
    	//adding stations for test whilst I dont have mapParser connected
    	graph.addNode(1, "Jorge");
    	graph.addNode(2, "Don");
    	
    	
    
    	//while user input is not a valid station
    	while(startStation.toLowerCase() != "exit") {
    		
	    	System.out.println("Please enter your starting station: ");
	    	
	    	startStation = scanner.nextLine();
	    	
	    	if(startStation.toLowerCase() == "exit") {
	    		System.out.println("Application Closing...");
	    		System.exit(0);
	    	}
	    	else if(startStation.toLowerCase() == "st.paulstreet") {
	    		//deal with duplicate station
	    	}
	    	
	    	//if there is a station with the same name as the inputed value
	    	if(graph.getNodeByLabel(startStation).getId() > 0) {
	    		System.out.println("Starting Station " + graph.getNodeByLabel(startStation).getId());
	    	}
	    	//if there is no station matching the input value
	    	else {
	    		System.out.println("There is no Station matching " + startStation);
	    	}
    		
	    	System.out.println("Please enter your final station: ");
	    	
	    	endStation = scanner.nextLine();
	    	
	    	if(endStation.toLowerCase() == "exit") {
	    		System.out.println("Application Closing...");
	    		System.exit(0);
	    	}
	    	else if(endStation.toLowerCase() == "st.paulstreet") {
	    		//deal with duplicate station
	    	}
	    	
	    	//if there is a station with the same name as the inputed value
	    	if(graph.getNodeByLabel(endStation).getId() > 0) {
	    		
	    	}
	    	//if there is no station matching the input value
	    	else {
	    		System.out.println("There is no Station matching " + endStation);
	    	}
	    	
	    	graph.findPath(graph.getNodeByLabel(startStation), graph.getNodeByLabel(endStation));
	    	
    	}	
    	
    	
    	return null;
    	
    }

=======
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
    
>>>>>>> b96dea26e38f72302391a58c7e65a20cab04a229
}
