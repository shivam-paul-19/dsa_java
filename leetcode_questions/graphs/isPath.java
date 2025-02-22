import java.util.*;

public class isPath {
    static class Edge {
        int src;
        int dest;

        Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static void makeGraph(int[][] edges, ArrayList<Edge>[] graph) {
        for(int[] edge: edges) {
            int src = edge[0];
            int dest = edge[1];

            graph[src].add(new Edge(src, dest));
            graph[dest].add(new Edge(dest, src));
        }
    }

    public static boolean dfs(boolean[] isVis, ArrayList<Edge>[] graph, int curr, int dest) {
        isVis[curr] = true;

        for(int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if(e.dest == dest) {
                return true;
            }
            if(!isVis[e.dest]) {
                return dfs(isVis, graph, e.dest, dest);
            }
        }

        return false;
    }

    public static boolean validPath(int n, int[][] edges, int source, int destination) {
        boolean[] isVis = new boolean[n];
        ArrayList<Edge>[] graph = new ArrayList[n];

        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        makeGraph(edges, graph);
        for(ArrayList<Edge> e: graph) {
            for(Edge ed: e) {
                System.out.println(ed.src + " " + ed.dest);
            }
            System.out.println("---");
        }

        return dfs(isVis, graph, source, destination);
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1}, {0,2}, {3,5}, {5,4}, {4,3}};
        System.out.println(validPath(6, edges, 0, 5));
    }
}