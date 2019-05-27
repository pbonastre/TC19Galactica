import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class BattlestarGalactica {
    private static final String INFILENAME = "C:\\workSpaceTuenti\\TC19Galactica\\input\\galacticaInTL.txt";
    private static String OUTFILENAME = "C:\\workSpaceTuenti\\TC19Galactica\\output\\outputChallenge2.txt";
    private static Scanner in;
    private static PrintWriter out;
    private static final String START = "Galactica";
    private static final String END = "New Earth";
    private static Graph graph = new Graph();
    private static int pathAmount;



    public static void main(String[] args) throws FileNotFoundException {
        in = new Scanner(new FileReader(INFILENAME));
        out = new PrintWriter(OUTFILENAME);


        int cases = in.nextInt();

        for (int i = 1; i <= cases; i++) {
            int graphSize = in.nextInt();
            createPath(graphSize);
            inspectGraph();

           out.println("Case #" + i + ": "+pathAmount);
            //System.out.println("path number = " + pathAmount);
            pathAmount = 0;
            graph.clearGraph();

      }
        in.close();
        out.close();
    }

    private static void createPath(int graphSize) {
        String line;
        for(int size=0;size <=graphSize; size++){
            line = in.nextLine();
            if(line.length()==0) {continue;}
            createGraph(graph, line);
        }

    }

    private static void inspectGraph() {
        LinkedList<String> visited = new LinkedList();
        visited.add(START);
        new BattlestarGalactica().depthFirst(graph, visited);
    }

    private static void createGraph(Graph actualGraph, String inLine){
        StringTokenizer token = new StringTokenizer(inLine);
        String initialNode = token.nextToken(":");
        inLine = inLine.substring(initialNode.length()+1,inLine.length());

        StringTokenizer tokenizer = new StringTokenizer(inLine, ",");
        while (tokenizer.hasMoreElements()) {
            graph.addEdge(initialNode,tokenizer.nextToken());
        }
    }

    private void depthFirst(Graph graph, LinkedList<String> visited) {
        LinkedList<String> nodes = graph.adjacentNodes(visited.getLast());

        // examine adjacent nodes
        for (String node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(END)) {
                visited.add(node);
                //printPath(visited);
                pathAmount++;
                visited.removeLast();
                break;
            }
        }
        for (String node : nodes) {
            if (visited.contains(node) || node.equals(END)) {
                continue;
            }
            visited.addLast(node);
            depthFirst(graph, visited);
            visited.removeLast();
        }
    }

    private void printPath(LinkedList<String> visited) {
        for (String node : visited) {
            System.out.print(node);
            System.out.print(" ");
        }
        System.out.println();
    }

}
