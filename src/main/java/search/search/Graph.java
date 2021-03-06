package search.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Graph {

	public static void main(String[] args) throws IOException {
		
		Graph graph;
		String choice = "";
		int v;
		
		while (StringUtils.isBlank(choice)) {
			System.out.println("Please enter a choice from the menue");
			System.out.println("-DFS");
			System.out.println("-BFS");
			System.out.println("-TOPO (Topological sort");
			System.out.println("-DIJK (Dijkstra's Shortest Path)");
			System.out.println("-MST (prim)");
			System.out.println("-KMS (Kruskal’s Minimum Spanning Tree) ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			choice = reader.readLine();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		switch(choice.toUpperCase()) {
		case "DFS":
			System.out.println("Enter starting vertix");
			v = reader.read();
			graph = creatGraph();
			DFSsearch.dfsSearch(v, graph);
			break;
		case "BFS":
			System.out.println("Enter starting vertix");
			v = reader.read();
			graph = creatGraph();
			BFSsearch.bfsSearch(v, graph);
			break;
		case "TOPO":
			System.out.println("Enter starting vertix");
			v = reader.read();
			graph = creatGraph();
			Topological topologicalSort = new Topological(graph);
			topologicalSort.topologicalSort();
			break;
		case "DIJK":
			ShortestPath.dijkstrasShortestPath();
			break;
		case "MST":
			MSTsearch.mst(); 
			break;
		case "KMS":
			Ksearch.ksearch();
		}

	}

	private static Graph creatGraph() {
		System.out.println("Enter number of vertices");
		scanner = new Scanner(System.in);
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
		return graph;
	}
	
	

	private int vertices;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists
	private static Scanner scanner;
 
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
