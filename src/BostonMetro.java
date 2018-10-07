import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BostonMetro
{
	private ArrayList<ArrayList<String>> nodes;
    private String fileLocation;
    HashMap<Integer, String> orange, blue, green, red;
    private IGraph graph = new Multigraph();

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
    public BostonMetro() throws BadFileException, NumberFormatException, NoSuchNodeException {
    	fileLocation = "src/Stations.txt";
    	orange = new HashMap<Integer, String>(32);
    	blue  = new HashMap<Integer, String>(32);
    	green  = new HashMap<Integer, String>(64);
    	red  = new HashMap<Integer, String>(32);
    	
    	try {
			read();
		} catch (BadFileException e) {
			// TODO Auto-generated catch block
			throw new BadFileException("Incorrect File Source");
		}
    	mapLines();
		addAllNodes();
    	//addAllEdges();
    	
    }
    
public void findPath() throws NoSuchNodeException, BadFileException {
    	
    	String startStation = "";
    	String endStation = "";
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	//adding stations for test whilst I dont have mapParser connected
    	//graph.addNode(1, "Jorge");
    	//graph.addNode(2, "Don");
    	
    	read();
    	
    	
    
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
	    		System.out.println("Ending Station " + graph.getNodeByLabel(endStation).getId());
	    	}
	    	//if there is no station matching the input value
	    	else {
	    		System.out.println("There is no Station matching " + endStation);
	    	}
	    	
	    	System.out.println(graph.findPath(graph.getNodeByLabel(startStation), graph.getNodeByLabel(endStation)));
	    	
	    	
    	}	
    	
    }

    	
    private void addAllNodes() throws NoSuchNodeException{
    	
    	for(int i = 1; i <= nodes.size(); i++){
	    		if(orange.containsKey(i)){
	    				graph.addNode(i,orange.get(i));
	    		}else if(blue.containsKey(i)){
	    			graph.addNode(i,blue.get(i));
	    		}else if(green.containsKey(i)){
	    			graph.addNode(i,green.get(i));
	    		}else if(red.containsKey(i)){
	    			graph.addNode(i, red.get(i));
	    		}
	    			    		
    			try{// use this for testing all nodes have been added!
    			System.out.print(graph.getNodeById(i) + " was successfully added to graph, and i: " + i + "\n");
    			System.out.print(graph.getLabelByNode(graph.getNodeById(i)) + " was added, id: " + i+ "\n");
    			}catch(NoSuchNodeException e){
    				throw new NoSuchNodeException();
    			}
    		}
    	}
    				
    		//green.forEach((k,v)->graph.addEdge(graph.getNodeById(), node2, label););	
    	
    /*This function is not working entirely correctly!! 
     * it will correctly add some green lines, but will add some weird ones as well
     * haven't managed to figure it out yet, but good luck /)(0.0)/)
     * */
    private void addAllEdges() throws NumberFormatException, NoSuchNodeException{
    for(int i =0; i< nodes.size(); i++){
    	for(int k = 0; k <nodes.size(); k++){
    	System.out.print("does it contain i? " + green.containsKey(i) + " i: " + i + " outbound val: "+ nodes.get(i).get(4) + "\n");
    			if(green.containsKey(i) && (Integer.parseInt(nodes.get(k).get(4)) == i)){
    				graph.addEdge(graph.getNodeById(i),graph.getNodeById(Integer.parseInt(nodes.get(i).get(4))), "green");
    				//System.out.print(graph.getLabelByNode(graph.getNodeById(i)) +" was successfully added to: " + nodes.get(i).get(1) + " as a node \n");
    			}else{
    				System.out.print("false false false");
    			}
    			}
    		}
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
    private void mapLines(){
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
			}
		}
		//green!!	
    	for(int i = 0; i < nodes.size(); i++){
			if((nodes.get(i).contains("Green") 
				|| nodes.get(i).contains("GreenB") 
				|| nodes.get(i).contains("GreenC") 
				|| nodes.get(i).contains("GreenD") 
				|| nodes.get(i).contains("GreenE")) && Integer.parseInt(nodes.get(i).get(0)) == i + 1){
				green.put(i + 1, nodes.get(i).get(1));	
			}
		}
    	//red!!
    	for(int i = 0; i < nodes.size(); i++){
			if((nodes.get(i).contains("Red")
				|| nodes.get(i).contains("RedA")
				|| nodes.get(i).contains("RedB")
				|| nodes.get(i).contains("Mattapan")) && Integer.parseInt(nodes.get(i).get(0)) == i + 1){
				red.put(i + 1, nodes.get(i).get(1));
			}
		}//System.out.print(red.values());
	}
    
}
