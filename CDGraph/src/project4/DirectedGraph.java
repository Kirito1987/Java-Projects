package project4;


/* Filename: DirectedGraph.java
    Author: Laudro Pineda
    Date: July 14, 2019
    Program Description: The program creates a graph of vertices from a a text (.txt) file.  It follows a depth-first search(DFS)
    approach after a vertex is input from the GUI.  The output is the topological order of said sub-graph based on its
    position in the text file.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DirectedGraph <T> {

    // Variables
    private Map<T, Integer> mapToInteger;
    private ArrayList<LinkedList<Integer>> adjLists;
    private int v = 0;
    private List<Integer> neighbors;
    private StringBuilder output;


    /**
     * Default Constructor for DirectedGraph
     */
    DirectedGraph() {
        adjLists = new ArrayList<>();
        mapToInteger = new HashMap<>();
    }


    void buildDirectedGraphFromFile(ArrayList<T[]> tokensArray) {
        for (T[] tokensLine : tokensArray) {
            for (int i = 0; i < tokensLine.length; i++) {
                addVertex(tokensLine[i]);
                if (i != 0) {
                    addEdge(tokensLine[0], tokensLine[i]);
                }
            }
        }
    }


    ArrayList<String[]> tokenizeFile(String fileName) throws IOException {
        String filePath = new File("res/" + fileName).getAbsolutePath();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<String[]> fileArray = new ArrayList<>();
        String line;
        int index = 0;

        while ((line = br.readLine()) != null) {
            String[] lineArray = line.split("\\s");
            fileArray.add(index,lineArray);
            index++;
        }

        return fileArray;
    }


    private void addVertex(T className) {
        if (!mapToInteger.containsKey(className)) {
            mapToInteger.put(className, v);
            LinkedList<Integer> adj = new LinkedList<>();
            adjLists.add(v, adj);
            v++;
        }
    }


    private void addEdge(T vertexFrom, T vertexTo) {
        int from = mapToInteger.get(vertexFrom);
        int to = mapToInteger.get(vertexTo);
        adjLists.get(from).add(to);
    }


    String topOrdGeneration(T startVertex) throws InvalidClassNameException, ContainsCycleException {
        if (mapToInteger.get(startVertex) == null) {
            throw new InvalidClassNameException();
        }

        output = new StringBuilder();
        neighbors = new ArrayList<>();
        dfs(mapToInteger.get(startVertex));

        return output.toString();
    }

    private String getNameFromIndex(int vertex) {
        for (T k : mapToInteger.keySet()) {
            if (mapToInteger.get(k).equals(vertex)) {
                return k.toString();
            }
        }
        return "";
    }

    private void dfs(int v) throws ContainsCycleException {
        output.append(getNameFromIndex(v)).append(" ");
        for (Integer x : adjLists.get(v)) {
            if (neighbors.contains(x)) {
                throw new ContainsCycleException();
            }
            neighbors.add(x);
            dfs(x);
        }
    }
}
