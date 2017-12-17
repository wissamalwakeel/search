package search.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

public class ShortestPath {

	private int vertices;

	int minmumDistance(int dist[], Boolean sptSet[]) {
		int minmum = Integer.MAX_VALUE, minmumIndex = -1;

		for (int v = 0; v < vertices; v++)
			if (sptSet[v] == false && dist[v] <= minmum) {
				minmum = dist[v];
				minmumIndex = v;
			}

		return minmumIndex;
	}

	void printSolution(int dist[], int n) {
		System.out.println("Vertex   Distance from Source");
		for (int i = 0; i < vertices; i++)
			System.out.println(i + "        " + dist[i]);
	}

	void dijkstra(int graph[][], int src) {
		int distance[] = new int[vertices]; 
		Boolean sptSet[] = new Boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			distance[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}
		distance[src] = 0;

		for (int i = 0; i < vertices - 1; i++) {
			int u = minmumDistance(distance, sptSet);
			sptSet[u] = true;
			for (int v = 0; v < vertices; v++)
				if (!sptSet[v] && graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v])
					distance[v] = distance[u] + graph[u][v];
		}
		printSolution(distance, vertices);
	}

	public static void dijkstrasShortestPath() {

		ShortestPath shortestPath = new ShortestPath();
		int graph[][] = shortestPath.buildGraph();
		shortestPath.dijkstra(graph, 0);
	}
	
	private int[][] buildGraph() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int graph[][] =null;
		try {
			System.out.println("Enter the number of vertices (integer expected): ");
			vertices = Integer.parseInt(reader.readLine());
		    graph = new int[vertices][];
			for (int i = 0; i < vertices; i++) { 
				System.out.println("Enter Graph data in format i,i1,i2,...in " + (i+1));
				reader = new BufferedReader(new InputStreamReader(System.in));
				String graphData = reader.readLine();
				graph[i] = parseData(graphData);
			}			
		} catch (IOException e) {
			System.out.println("Unable to build graph duo to wrong input");
		}		
		return graph;
	}

	private int[] parseData(String graphData) {
		graphData = StringUtils.remove(graphData, " ");
		String graphDataArray[] = graphData.split(",");
		int[] edgeData = new int[vertices];
		for (int x = 0; x < vertices; x++) {
			edgeData[x] = Integer.parseInt(graphDataArray[x]); 
		}
		return edgeData;
	}
	
	
}