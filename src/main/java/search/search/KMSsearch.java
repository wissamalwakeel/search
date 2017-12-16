package search.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class KMSsearch {
	class Edge implements Comparable<Edge> {
		int src, dest, weight;

		public int compareTo(Edge compareEdge) {
			return this.weight - compareEdge.weight;
		}
	};

	class subset {
		int parent, rank;
	};

	int vertices, edges;
	Edge edge[];

	KMSsearch(int v, int e) {
		vertices = v;
		edges = e;
		edge = new Edge[edges];
		for (int i = 0; i < e; ++i)
			edge[i] = new Edge();
	}

	int find(subset subsets[], int i) {

		if (subsets[i].parent != i)
			subsets[i].parent = find(subsets, subsets[i].parent);

		return subsets[i].parent;
	}

	void Union(subset subsets[], int x, int y) {
		int xroot = find(subsets, x);
		int yroot = find(subsets, y);

		if (subsets[xroot].rank < subsets[yroot].rank)
			subsets[xroot].parent = yroot;
		else if (subsets[xroot].rank > subsets[yroot].rank)
			subsets[yroot].parent = xroot;
		else {
			subsets[yroot].parent = xroot;
			subsets[xroot].rank++;
		}
	}

	void KruskalMST() {
		Edge result[] = new Edge[vertices];
		int e = 0;
		int i = 0;
		for (i = 0; i < vertices; ++i)
			result[i] = new Edge();

		Arrays.sort(edge);

		subset subsets[] = new subset[vertices];
		for (i = 0; i < vertices; ++i)
			subsets[i] = new subset();

		for (int v = 0; v < vertices; ++v) {
			subsets[v].parent = v;
			subsets[v].rank = 0;
		}

		i = 0;

		while (e < vertices - 1) {
			Edge next_edge = new Edge();
			next_edge = edge[i++];

			int x = find(subsets, next_edge.src);
			int y = find(subsets, next_edge.dest);

			if (x != y) {
				result[e++] = next_edge;
				Union(subsets, x, y);
			}
		}
		System.out.println("Following are the edges in " + "the constructed MST");
		for (i = 0; i < e; ++i)
			System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
	}

	public static void ksearch() {
		int vertices;
		int edges;
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(in);
		System.out.println("Please enter graph Data \n");

		try {
			System.out.println("Enter number of Vertices: ");
			vertices = Integer.parseInt(reader.readLine());
			System.out.println("Enter number of Edges: ");
			edges = Integer.parseInt(reader.readLine());
			KMSsearch graph = new KMSsearch(vertices, edges);

			for (int i = 0; i < edges; i++) {
				reader = new BufferedReader(in);
				System.out.println("Enter edge source Vertix for Edge number "+ (i+1) + "acceptable value is between 0-" + (vertices - 1) + ": ");
				graph.edge[i].src = Integer.parseInt(reader.readLine());
				System.out.println("Enter edge destination Vertix Edge number "+ (i+1) + "acceptable value is between 0-" + (vertices - 1) + ": ");
				graph.edge[i].dest = Integer.parseInt(reader.readLine());
				System.out.println("Enter edge  weight for edge number "+ (i+1) +": ");
				graph.edge[i].weight = Integer.parseInt(reader.readLine());
			}
			graph.KruskalMST();

		} catch (NumberFormatException | IOException e) {
			System.out.println("invaled input data");
		}

	}
}
