import java.io.IOException;
import java.util.*;

public class BostonMetro {
    private ArrayList<ArrayList<String>> nodes;
    private String fileLocation;
    HashMap<Integer, String> orange, blue, green, red, mattapan;
    private IGraph graph = new Multigraph();

    public BostonMetro() {
        fileLocation = "src/Stations.txt";
        orange = new HashMap<>();
        blue = new HashMap<>();
        green = new HashMap<>();
        red = new HashMap<>();
        mattapan = new HashMap<>();

        try {
            read();
        } catch (BadFileException e) {
            System.out.println("Fatal Error: Input file has wrong format!");
            System.exit(0);
        }
        mapLines();
        addAllNodes();
        addAllEdges();
        init();
    }

    /**
     * Parse user input and obtain node ID for a given station name
     *
     * @param scanner
     * @return node ID of station if successful, -1 otherwise
     */
    private int getStation(Scanner scanner) {
        int ID = 0;
        String name = scanner.nextLine().toLowerCase();
        if (name.toLowerCase().equals("exit")) {
            System.out.println("Application Closing...");
            System.exit(0);
        }

        try {
            // retrieve the station ID from the user input
            if (graph.getNodeByLabel(name).size() == 1) {
                ID = graph.getNodeByLabel(name).get(0).getId();
            } else {
                System.out.println("Which station do you wish to select?");
                int i = 0;
                for (INode n : graph.getNodeByLabel(name)) {
                    System.out.print(name + " with neighbours");
                    for (INode neighbour : graph.getNeighbours(n)) {
                        System.out.print(" " + neighbour.getLabel());
                    }
                    System.out.println(" (" + i + ")");
                    i++;
                }
                int userSelection = Integer.parseInt(scanner.nextLine());
                ID = graph.getNodeByLabel(name).get(userSelection).getId();
            }
        } catch (NoSuchNodeException e) {
            System.out.println("A station with the name " + name + " doesn't exist.");
            return -1;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Station index out of bounds!");
            return -1;
        }
        return ID;
    }

    private void init() {

        String startStation = "";
        String endStation = "";
        int startStationID;
        int endStationID;

        try {
            // input the nodes and edges from map
            read();
        } catch (BadFileException e) {
            System.out.println("Fatal Error: Input file has wrong format!");
            System.exit(0);
        }

        System.out.println("---------------------------------------------------------------------");
        System.out.println("*********************************************************************");
        System.out.println("** ___________   ___________________________________________       **");
        System.out.println("**  ___   ___ |||  ___   ___   ___    ___ ___  |   __  ,----\\      **");
        System.out.println("** |   | |   |||| |   | |   | |   |  |   |   | |  |  | |_____\\     **");
        System.out.println("** |___| |___|||| |___| |___| |___|  |   |   | |  |  |        \\    **");
        System.out.println("**            |||                    |___|___| |  |__|         )   **");
        System.out.println("** ___________|||______________________________|______________/    **");
        System.out.println("**            |||                                        /         **");
        System.out.println("** -----------'''---------------------------------------'          **");
        System.out.println("*********************************************************************");
        System.out.println("**   ____            _                __  __      _                **");
        System.out.println("**  |  _ \\          | |              |  \\/  |    | |               **");
        System.out.println("**  | |_) | ___  ___| |_ ___  _ __   | \\  / | ___| |_ _ __ ___     **");
        System.out.println("**  |  _ < / _ \\/ __| __/ _ \\| \'_ \\  | |\\/| |/ _ \\ __| \'__/ _ \\    **");
        System.out.println("**  | |_) | (_) \\__ \\ || (_) | | | | | |  | |  __/ |_| | | (_) |   **");
        System.out.println("**  |____/ \\___/|___/\\__\\___/|_| |_| |_|  |_|\\___|\\__|_|  \\___/    **");
        System.out.println("**                                                                 **");
        System.out.println("*********************************************************************");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");
        System.out.println();
        System.out.println("Enter your start and destination stations when prompted.");
        System.out.println("To quit type \'exit\'.");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        // while user input is not a valid station
        while (startStation.toLowerCase() != "exit") {

            System.out.println("Please enter your starting station: ");

            startStationID = getStation(scanner);
            if (startStationID == -1)
                continue;

            // if there is a station with the same name as the inputed value
            if (startStationID > 0) {
                System.out.println("Starting Station " + startStationID + " " + startStation);
            }
            // if there is no station matching the input value; the
            // getNodeByLabel method doesn't currently return a
            // number if argument doesn't match, so this will never be called
            else {
                System.out.println("There is no Station matching " + startStation);
            }

            System.out.println("Please enter your ending station: ");

            endStationID = getStation(scanner);
            if (endStationID == -1)
                continue;
            // if there is a station with the same name as the inputed value
            if (endStationID > 0) {
                System.out.println("Ending Station " + endStationID + " " + endStation);
            }
            // if there is no station matching the input value
            else {
                System.out.println("There is no Station matching " + endStation);
            }
            try {
                printPath(graph.findPath(graph.getNodeById(startStationID), graph.getNodeById(endStationID)));
            } catch (NoSuchNodeException e) {   // should never happen
            System.out.println("Fatal Error: Input node to findPath not found!");
            System.exit(0);
        }
    }

}

    /**
     * Prints the shortest path that was found between two nodes. Handles
     * printing the edges that are between same stations but with different
     * labels. Prints the stations in a linked format, not just in the arbitrary
     * order the undirected edges hold.
     *
     * @param path
     */
    private void printPath(List<IEdge> path) {
        IEdge prev = null;
        for (IEdge e : path) {
            if (prev != null && e.getNode1().equals(prev.getNode1()) && e.getNode2().equals(prev.getNode2())) {
                System.out.print(", " + e.getLabel());
            } else if (prev != null) {
                if (prev.getNode2().getLabel().equals(e.getNode1().getLabel())) {
                    System.out.print("\n" + "Go from " + e.getNode1().getLabel() + " to " + e.getNode2().getLabel()
                            + " along line(s) " + e.getLabel());
                } else {
                    System.out.print("\n" + "Go from " + e.getNode2().getLabel() + " to " + e.getNode1().getLabel()
                            + " along line(s) " + e.getLabel());
                }
            } else {
                System.out.print("\n" + "Go from " + e.getNode1().getLabel() + " to " + e.getNode2().getLabel()
                        + " along line(s) " + e.getLabel());
            }
            prev = e;
        }
        System.out.print("\nYou have arrived at your destination");
        System.out.println();
    }

    /**
     * adds all the nodes read in from our nodes arraylist increments the key
     * starting from 1 add to our instance of multigraph the new node loop until
     * we reach the end of the nodes arraylist
     */

    private void addAllNodes() {
        for (int i = 1; i <= nodes.size(); i++) {
            if (orange.containsKey(i)) {
                graph.addNode(i, orange.get(i));
            } else if (blue.containsKey(i)) {
                graph.addNode(i, blue.get(i));
            } else if (green.containsKey(i)) {
                graph.addNode(i, green.get(i));
            } else if (red.containsKey(i)) {
                graph.addNode(i, red.get(i));
            } else if (mattapan.containsKey(i)) {
                graph.addNode(i, mattapan.get(i));
            }
        }
    }

    /**
     * Adds all the edges read in from the text file. Based on the set
     * formatting outlined in the text file (ID Name n(Line Outbound Inbound))
     * where n is the number of lines the station is on. Checks for the outbound
     * and inbound station IDs not being 0 ie no station.
     */
    private void addAllEdges() {
        try {
            for (int i = 0; i < nodes.size(); i++) {
                String label;
                for (int j = 3; j < nodes.get(i).size(); j = j + 3) {
                    label = nodes.get(i).get(j - 1);
                    if (Integer.parseInt(nodes.get(i).get(j)) != 0) {
                        graph.addEdge(graph.getNodeById(Integer.parseInt(nodes.get(i).get(j))),
                                graph.getNodeById(Integer.parseInt(nodes.get(i).get(0))), label);
                    }
                    if (Integer.parseInt(nodes.get(i).get(j + 1)) != 0) {
                        graph.addEdge(graph.getNodeById(Integer.parseInt(nodes.get(i).get(0))),
                                graph.getNodeById(Integer.parseInt(nodes.get(i).get(j + 1))), label);
                    }
                }
            }
        } catch (NoSuchNodeException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    /*
     * creates an instance of MetroMapParser calls generateGraphFromFile to add
     * an arraylist of all node information
     */
    private void read() throws BadFileException {
        try {
            MetroMapParser reader = new MetroMapParser(fileLocation);
            nodes = reader.generateGraphFromFile();
        } catch (IOException | BadFileException e) {
            throw new BadFileException("Incorrect File Source");
        }
    }

    /*
     * for each colour attach hashset with <stationID,stationLabel> will check
     * from arraylist of nodes to designate its particular colour to its unique
     * hashset
     */
    private void mapLines() {
        // Orange!
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).contains("Orange") && Integer.parseInt(nodes.get(i).get(0)) == i + 1) {
                orange.put(i + 1, nodes.get(i).get(1));
            }
            // blue!
            else if (nodes.get(i).contains("Blue") && Integer.parseInt(nodes.get(i).get(0)) == i + 1) {
                blue.put(i + 1, nodes.get(i).get(1));
            }
            // green!!
            else if ((nodes.get(i).contains("Green") || nodes.get(i).contains("GreenB")
                    || nodes.get(i).contains("GreenC") || nodes.get(i).contains("GreenD")
                    || nodes.get(i).contains("GreenE")) && Integer.parseInt(nodes.get(i).get(0)) == i + 1) {
                green.put(i + 1, nodes.get(i).get(1));
            }

            // red!!
            else if ((nodes.get(i).contains("Red") || nodes.get(i).contains("RedA")
                    || nodes.get(i).contains("RedB")) && Integer.parseInt(nodes.get(i).get(0)) == i + 1) {
                red.put(i + 1, nodes.get(i).get(1));

                // mattapan
            } else if (nodes.get(i).contains("Mattapan")) {
                mattapan.put(i + 1, nodes.get(i).get(1));
            }
        }
    }

}
