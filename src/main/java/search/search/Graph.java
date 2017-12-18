package search.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Graph {

	public static void main(String[] args) throws IOException {
		int exit = 0;
		while (exit == 0) {		
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
			switch (choice.toUpperCase()) {
			case "DFS":
				System.out.println("Enter starting vertix");
				v = Integer.parseInt(reader.readLine());
				graph = creatGraph();
				DFSsearch.dfsSearch(v, graph);
				break;
			case "BFS":
				System.out.println("Enter starting vertix");
				v = Integer.parseInt(reader.readLine());
				graph = creatGraph();
				BFSsearch.bfsSearch(v, graph);
				break;
			case "TOPO":
				System.out.println("Enter starting vertix");
				v = Integer.parseInt(reader.readLine());
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
				KMSsearch.ksearch();
			}
			System.out.println("Do you wish to do another search y/n? ");
			exit = ("y".equalsIgnoreCase(reader.readLine())) ? 0 : 1 ;
		}
	}

	private static Graph creatGraph() {
		System.out.println("Enter number of vertices");
		scanner = new Scanner(System.in);
		int vertices = scanner.nextInt();
		System.out.println("Enter number of edges");
		int noEdges = scanner.nextInt();
		Graph graph = new Graph(vertices);
		while (noEdges > 0) {
			noEdges--;
			System.out.println("Enter edge starting vertix ");
			int strartingVertix = scanner.nextInt();
			System.out.println("Enter edge ending vertix");
			int endingVertix = scanner.nextInt();
			graph.addEdge(strartingVertix, endingVertix);
		}
		return graph;
	}

	private int vertices;
	private LinkedList<Integer> adj[];
	private static Scanner scanner;


	Graph(int v) {
		vertices = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	void addEdge(int v, int w) {
		adj[v].add(w);
	}

	public int getVertices() {
		return this.vertices;
	}

	public LinkedList<Integer>[] getAdjacencyList() {
		return this.adj;

	}
}
