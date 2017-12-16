package search.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

public class MSTsearch {
	private static int vertices = 5;

	int getMinmumKey(int key[], Boolean mstSet[]) {
		int minimum = Integer.MAX_VALUE, minmumIndex = -1;
		for (int v = 0; v < vertices; v++)
			if (mstSet[v] == false && key[v] < minimum) {
				minimum = key[v];
				minmumIndex = v;
			}
		return minmumIndex;
	}

	void printMST(int parent[], int n, int graph[][]) {
		System.out.println("Edge   Weight");
		for (int i = 1; i < vertices; i++)
			System.out.println(parent[i] + " - " + i + "    " + graph[i][parent[i]]);
	}

	void primMST(int graph[][]) {
		int parent[] = new int[vertices];
		int key[] = new int[vertices];
		Boolean mstSet[] = new Boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}

		key[0] = 0; 
		parent[0] = -1; 
		for (int count = 0; count < vertices - 1; count++) {
			int u = getMinmumKey(key, mstSet);
			mstSet[u] = true;
			for (int v = 0; v < vertices; v++)
				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
					parent[v] = u;
					key[v] = graph[u][v];
				}
		}
		printMST(parent, vertices, graph);
	}

	public static void mst() { 
		MSTsearch t = new MSTsearch();
		int graph[][] = t.buildGraph();
// Test Data = { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 }, { 0, 3, 0, 0, 7 }, { 6, 8, 0, 0, 9 },{ 0, 5, 7, 9, 0 }
		t.primMST(graph);
	}
	
	private int[][] buildGraph() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int graph[][] = new int[vertices][];
		try {
			System.out.println("Enter the number of vertices (integer expected): ");
			vertices = Integer.parseInt(reader.readLine());
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
