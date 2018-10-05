import java.io.IOException;
import java.util.Scanner;

public class BostonMetro
{
	
	private Multigraph graph;
	
    public BostonMetro() throws IOException
    {
	
	//Multigraph graph = new Multigraph();
	//MetroMapParser parser = new MetroMapParser("file.ext");
	
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
    }
    
    
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

}
