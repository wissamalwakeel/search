package search.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.commons.lang3.StringUtils;

public class Graph {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Enter number of vertices");
		Scanner scanner = new Scanner(System.in);
		int vertices = scanner.nextInt();
		System.out.println("Enter number of edges");
		int noEdges = scanner.nextInt();
		Graph graph = new Graph(vertices);
		while (noEdges > 0 ) {
			noEdges--;
			System.out.println( "Enter edge starting vertix ");
			int strartingVertix = scanner.nextInt();
			System.out.println("Enter edge ending vertix");
			int endingVertix = scanner.nextInt();
			graph.addEdge(strartingVertix, endingVertix);	
		}
		String choice = "";
		
		while (StringUtils.isBlank(choice)) {
			System.out.println("Please enter a choice from the menue");
			System.out.println("-DFS");
			System.out.println("-BFS");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			choice = reader.readLine();
		}
		
		System.out.println("Enter starting vertix");
		int v = scanner.nextInt();
		
		switch(choice.toUpperCase()) {
		case "DFS":
			DFSsearch.dfsSearch(v, graph);
			break;
		case "BFS":
			BFSsearch.bfsSearch(v, graph);
			break;
		}		

	}
	
	

	private int vertices;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists
 
    // Constructor
    Graph(int v)
    {
        vertices = v;
        adj = new LinkedList[v];																							
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
 
    // Function to add an edge into the graph
    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }
    
    public int getVertices()  {
    	return this.vertices;
    }
    
    public LinkedList<Integer>[] getAdjacencyList() {
    	return this.adj; 
    	
    }
}
