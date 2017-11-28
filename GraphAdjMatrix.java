/**
 * 
 * @author geromeesperacion
 *
 */

/*
 * Graph data type using topological sort.
 */
public class GraphAdjMatrix implements Graph {
	
	private int[][] edges;
	
	//constructor
	public GraphAdjMatrix(int vertices) {
		this.edges = new int[vertices][vertices];
	}

	/*
	 * Adds a directed edge between two vertices from src to tar
	 */
	public void addEdge(int src, int tar) {
		edges[src][tar] = 1;
		
	}

	/*
	 * Prints (to console) one ordering of vertices such that each directed edge (v1, v2) from vertex v1 
	 * to vertex v2, v1 comes before v2 in the ordering. If such an ordering is not possible 
	 * (due to cycles, for example), this function must indicate so, though it may print a partial 
	 * solution in so doing. 
	 */
	public void topologicalSort() {
		
		boolean visited[] = new boolean[edges.length];
		
		for(int i = 0; i < visited.length; i++) {
			if(!visited[i]) {
				topologicalSort(i, visited);
			}
		}
		
	}
	
	private void topologicalSort(int vertex, boolean[] visited) {
		
		if(!visited[vertex]) {
			visited[vertex] = true;
			System.out.println(vertex);
			
			for(int i = 0; i < neighbors(vertex).length; i++) {
				topologicalSort(i, visited);
			}
			
		} else {
			System.out.println("Ordering not possible. Possibly due to cycles."
					+ " Partial solution may be printed.");
		}
	}

	/*
	 * Returns the number of outgoing degrees of a vertex
	 */
	public int outdegree(int vertex) {
		
		int degree = 0;
		
		for(int i = 0; i < edges.length; i++) {
			if(edges[vertex][i] != -1 && edges[vertex][i] != 0) {
				degree++;
			}
		}

		return degree;
	}
	
	/*
	 * Returns an array of vertex IDs such that each ID represents a vertex which is 
	 * the destination of the edge origination from the argument.
	 */
	public int[] neighbors(int vertex) {
		
		int[] neighbors = new int[outdegree(vertex)];
		int index = 0;
		
		for(int i = 0; i < edges.length; i++) {
			if(edges[vertex][i] == 1) {
				neighbors[index] = i;
				index++;
			}
		}
		
		return neighbors;
	}

}
