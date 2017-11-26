package search.search;

import java.util.Iterator;
import java.util.LinkedList;

public class DFSsearch {
	
    static void dfsUtil(int v,boolean visited[],LinkedList<Integer> adj[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v+" ");
 
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                dfsUtil(n, visited,adj);
        }
    }
 
    // The function to do DFS traversal. It uses recursive DFSUtil()
    public static void dfsSearch(int v,Graph g)   {
    	int vertices = g.getVertices();
    	LinkedList<Integer> adj[] = g.getAdjacencyList();
    	
    	
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[vertices];
        
 
        // Call the recursive helper function to print DFS traversal
        dfsUtil(v, visited,adj);
    }
}
