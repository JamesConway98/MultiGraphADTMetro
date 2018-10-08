import java.io.IOException;
import java.util.*;

public class BostonMetro {
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
        blue = new HashMap<Integer, String>(32);
        green = new HashMap<Integer, String>(64);
        red = new HashMap<Integer, String>(32);

        try {
            read();
        } catch (BadFileException e) {
            // TODO Auto-generated catch block
            throw new BadFileException("Incorrect File Source");
        }
        mapLines();
        addAllNodes();
        addAllEdges();

    }

    public void init() throws NoSuchNodeException, BadFileException {

        String startStation = "";
        String endStation = "";
        int startStationID;
        int endStationID;

        Scanner scanner = new Scanner(System.in);

        //input the nodes and edges from map
        read();


        //while user input is not a valid station
        while(startStation.toLowerCase() != "exit") {

            System.out.println("Please enter your starting station: ");

            startStation = scanner.nextLine();

            if(startStation.toLowerCase().equals("exit")) {
                System.out.println("Application Closing...");
                System.exit(0);
            }
            
            //retrieve the station ID from the user input
            startStationID = graph.getNodeByLabel(startStation).getId();
            
            if(startStation.toLowerCase().equals("st.paulstreet")) {
                
            	System.out.println("Would you like the St.Paulstreet on line Green B or Green C? (Type 'B' or 'C')");
                
            	String input = scanner.nextLine().toLowerCase();
            	
                if(input.equals("b")) {
                	startStationID = 38;
                }else if(input.equals("c")) {
                	startStationID = 61;
                }else {
                	startStationID = 0;	//If user inputs incorrect value then return id 0 to show incorrect input
                }
                
            }

            //if there is a station with the same name as the inputed value
            if(startStationID > 0) {
                System.out.println("Starting Station " + startStationID + " " + startStation);
            }
            //if there is no station matching the input value; the getNodeByLabel method doesn't currently return a
            //number if argument doesnt match, so this will never be called
            else {
                System.out.println("There is no Station matching " + startStation);
            }
            
            System.out.println("Please enter your ending station: ");

            endStation = scanner.nextLine();

            if (endStation.toLowerCase().equals("exit")) {
                System.out.println("Application Closing...");
                System.exit(0);
            } 
            
            //retrieve the station ID from the user input
            endStationID = graph.getNodeByLabel(endStation).getId();
            
            if (endStation.toLowerCase().equals("st.paulstreet")) {
            	
            	System.out.println("Would you like the St.Paulstreet on line Green B or Green C? (Type 'B' or 'C')");
                
            	String input = scanner.nextLine().toLowerCase();
            	
                if(input.equals("b")) {
                	endStationID = 38;
                }else if(input.equals("c")) {
                	endStationID = 61;
                }else {
                	endStationID = 0;	//If user inputs incorrect value then return id 0 to show incorrect input
                }
            }

            //if there is a station with the same name as the inputed value
            if (endStationID > 0) {
                System.out.println("Ending Station " + endStationID + " " + endStation);
            }
            //if there is no station matching the input value
            else {
                System.out.println("There is no Station matching " + endStation);
            }
            printPath(graph.findPath(graph.getNodeById(startStationID), graph.getNodeById(endStationID)));
        }

    }

    private void printPath(List<IEdge> path){
        for (IEdge e : path) {
            System.out.println(e.getNode1().getLabel() + " connects to " + e.getNode2().getLabel() + " by " + e.getLabel());
        }
    }

    private void addAllNodes() throws NoSuchNodeException {

        for (int i = 1; i <= nodes.size(); i++) {
            if (orange.containsKey(i)) {
                graph.addNode(i, orange.get(i));
            } else if (blue.containsKey(i)) {
                graph.addNode(i, blue.get(i));
            } else if (green.containsKey(i)) {
                graph.addNode(i, green.get(i));
            } else if (red.containsKey(i)) {
                graph.addNode(i, red.get(i));
            }

            try {// use this for testing all nodes have been added!
                System.out.print(graph.getNodeById(i) + " was successfully added to graph, and i: " + i + "\n");
                System.out.print(graph.getLabelByNode(graph.getNodeById(i)) + " was added, id: " + i + "\n");
            } catch (NoSuchNodeException e) {
                throw new NoSuchNodeException();
            }
        }
    }

    /**
     * Adds all the edges read in from the text file.
     * Based on the set formatting outlined in the text file (ID Name n(Line Outbound Inbound)) where n is the number of lines the station is on.
     * Checks for the outbound and inbound station IDs not being 0 ie no station.
     *
     * @throws NumberFormatException
     * @throws NoSuchNodeException
     */
    private void addAllEdges() throws NumberFormatException, NoSuchNodeException {
        try {
            for (int i = 0; i < nodes.size(); i++) {
                String label;
                for (int j = 3; j < nodes.get(i).size(); j = j + 3) {
                    label = nodes.get(i).get(j - 1);
                    if (Integer.parseInt(nodes.get(i).get(j)) != 0) {
                        graph.addEdge(graph.getNodeById(Integer.parseInt(nodes.get(i).get(j))), graph.getNodeById(Integer.parseInt(nodes.get(i).get(0))), label);
                    }
                    if (Integer.parseInt(nodes.get(i).get(j + 1)) != 0) {
                        graph.addEdge(graph.getNodeById(Integer.parseInt(nodes.get(i).get(0))), graph.getNodeById(Integer.parseInt(nodes.get(i).get(j + 1))), label);
                    }
                }
            }
        } catch (NoSuchNodeException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }


    private void read() throws BadFileException {
        try {
            MetroMapParser reader = new MetroMapParser(fileLocation);
            nodes = reader.generateGraphFromFile();
        } catch (IOException | BadFileException e) {
            throw new BadFileException("Incorrect File Source");
        }
    }

    /* for each colour
     * attach hashset with <stationID,stationLabel>
     */
    private void mapLines() {
        // Orange!
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).contains("Orange") && Integer.parseInt(nodes.get(i).get(0)) == i + 1) {
                orange.put(i + 1, nodes.get(i).get(1));
                //System.out.print(nodes.get(i).get(1) + " was successfully added to hashmap orange," + " size: " + orange.size() + "\n");
            }
        }//blue!
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).contains("Blue") && Integer.parseInt(nodes.get(i).get(0)) == i + 1) {
                blue.put(i + 1, nodes.get(i).get(1));
            }
        }
        //green!!
        for (int i = 0; i < nodes.size(); i++) {
            if ((nodes.get(i).contains("Green")
                    || nodes.get(i).contains("GreenB")
                    || nodes.get(i).contains("GreenC")
                    || nodes.get(i).contains("GreenD")
                    || nodes.get(i).contains("GreenE")) && Integer.parseInt(nodes.get(i).get(0)) == i + 1) {
                green.put(i + 1, nodes.get(i).get(1));
            }
        }
        //red!!
        for (int i = 0; i < nodes.size(); i++) {
            if ((nodes.get(i).contains("Red")
                    || nodes.get(i).contains("RedA")
                    || nodes.get(i).contains("RedB")
                    || nodes.get(i).contains("Mattapan")) && Integer.parseInt(nodes.get(i).get(0)) == i + 1) {
                red.put(i + 1, nodes.get(i).get(1));
            }
        }//System.out.print(red.values());
    }

}
