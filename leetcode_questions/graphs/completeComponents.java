// Leetcode Q: 2685. Count the Number of Complete Components

import java.util.*;

public class completeComponents {
    // defining an edge class
    class Edge {
        int src;
        int dest;

        Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    private ArrayList<Edge>[] makeGraph(int[][] edges, int n) {
        ArrayList<Edge>[] graph = new ArrayList[n];

        // initialising the blank lists
        for(int i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }

        // making the graph
        for(int[] edge: edges) {
            graph[edge[0]].add(new Edge(edge[0], edge[1]));
            graph[edge[1]].add(new Edge(edge[1], edge[0]));
        }

        return graph;
    }

    // DFS
    private void dfs(ArrayList<Edge>[] graph, int v, boolean[] isVis, ArrayList<Integer> components) {
        isVis[v] = true;
        components.add(v);

        for(int i=0; i<graph[v].size(); i++) {
            Edge e = graph[v].get(i);
            if(!isVis[e.dest]) {
                dfs(graph, e.dest, isVis, components);
            }
        }
    }

    private int countEdge(ArrayList<Edge>[] graph, ArrayList<Integer> components) {
        int edge = 0;
        for(int v: components) {
            edge += graph[v].size();
        }

        // each edge is counted twice
        return edge / 2;
    }

    private boolean isComplete(ArrayList<Edge>[] graph, ArrayList<Integer> components) {
        int vertex = components.size();
        int edge = countEdge(graph, components);

        return (vertex*(vertex-1))/2 == edge;
    }

    public int countCompleteComponents(int n, int[][] edges) {
        ArrayList<Edge>[] graph = makeGraph(edges, n);
        boolean[] isVis = new boolean[n];
        int count = 0;
        
        for(int i=0; i<n; i++) {
            if(!isVis[i]) {
                ArrayList<Integer> components = new ArrayList<>();
                dfs(graph, i, isVis, components);

                if(isComplete(graph, components)) count++;
            }
        }

        return count;
    }
}
