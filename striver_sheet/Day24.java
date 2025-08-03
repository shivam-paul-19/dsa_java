// Day 24: graph part II

import java.util.*;

public class Day24 {
    // defintion of Edge class
    public class Edge {
        int src;
        int dest;
        int wt;

        Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt = wt;
        }
    }

    // KosaRaju algorithm for finding no of strongly connected components
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        // Step 1: Do Topological Sort and store in stack
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topoDFS(i, visited, adj, stack);
            }
        }

        // Step 2: Transpose the graph
        ArrayList<ArrayList<Integer>> transposed = transposeGraph(V, adj);

        // Step 3: Do DFS on transposed graph in the order of the stack
        Arrays.fill(visited, false);
        int sccCount = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                reverseDFS(node, visited, transposed);
                sccCount++; // Each DFS call means one SCC
            }
        }

        return sccCount;
    }

    // Topological sort using DFS
    private void topoDFS(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                topoDFS(neighbor, visited, adj, stack);
            }
        }
        stack.push(node); // Push after visiting all neighbors
    }

    // Transpose the graph
    private ArrayList<ArrayList<Integer>> transposeGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> transposed = new ArrayList<>();

        // Initialize empty list for each vertex
        for (int i = 0; i < V; i++) {
            transposed.add(new ArrayList<>());
        }

        // Reverse the direction of edges
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                transposed.get(v).add(u);
            }
        }

        return transposed;
    }

    // DFS for the transposed graph
    private void reverseDFS(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                reverseDFS(neighbor, visited, adj);
            }
        }
    }

    // Djikstra's Algorithm: Used to find the shortest route from source to destination
    public class Pair {
        int n;
        int path;

        public Pair(int n, int p) {
            this.n = n;
            this.path = p;
        }
    }

    public int[] djikstra(ArrayList<Edge>[] graph, int src) {
        int dist[] = new int[graph.length];

        // all distances as infinite, except src itself
        for(int i=0; i<dist.length; i++) {
            if(i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        // vis array
        boolean isVis[] = new boolean[graph.length];

        // make a priority queue on the basis of paths
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.path - b.path);

        // add the src with distance 0
        pq.add(new Pair(src, 0));

        // perform BFS
        while(!pq.isEmpty()) {
            Pair curr = pq.remove();
            if(!isVis[curr.n]) {
                isVis[curr.n] = true;   // mark as visited
                for(Edge e: graph[curr.n]) {
                    int u = e.src;
                    int v = e.dest;
                    int w = e.wt;

                    // relaxation
                    if(dist[v] > dist[u]+w) { 
                        dist[v] = dist[u] + w;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        return dist;
    }

    // Bellman Ford algorithm: Used to find the shortest route from source to destination, when the graph has -ve weights too.
    public int[] bellmanFord(ArrayList<Edge>[] graph, int src) {
        int[] dist = new int[graph.length];
        for(int i=0; i<dist.length; i++) {
            if(i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        for(int i=0; i<graph.length; i++) {
            for(int j=0; j<graph.length; j++) {
                for(int k=0; k<graph[j].size(); k++) {
                    Edge e = graph[j].get(k);

                    // relaxation 
                    if(dist[e.src] != Integer.MAX_VALUE && dist[e.dest] > dist[e.src] + e.wt) {
                        dist[e.dest] = dist[e.src] + e.wt;
                    }
                }
            }
        }

        return dist;
    }

    // Prim's algorithm: used to find minimum spanning tree from a graph
    public int primsAlgo(ArrayList<Edge>[] graph) {
        boolean[] isVis = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.path - b.path);

        pq.add(new Pair(0, 0));
        int finalCost = 0;

        while(!pq.isEmpty()) {
            Pair curr = pq.remove();
            if(!isVis[curr.n]) {
                isVis[curr.n] = true;
                finalCost += curr.path;

                // get all the neighbours
                for(int i=0; i<graph[curr.n].size(); i++) {
                    Edge e = graph[curr.n].get(i);
                    pq.add(new Pair(e.dest, e.wt));
                }
            }
        }

        return finalCost;
    }
}

