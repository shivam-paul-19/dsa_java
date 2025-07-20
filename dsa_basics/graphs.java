import java.util.*;

public class graphs {
    // Making an Edge class
    static class Edge {
        int src;
        int des;
        int weight;

        Edge(int s, int d, int w) {
            this.src = s;
            this.des = d;
            this.weight = w;
        }
    }

    //* The Graph
    /*
     ^ It is undirected (With cycle)
     ^ For breaking the cycle, just break edge between 1 and 3 
     *
     *          (5)
     *      0 ------- 1
     *               /  \
     *          (1) /    \ (3)
     *             /      \
     *           2 ------- 3
     *           |   (1)
     *       (2) |
     *           |
     *           4 
     * 
     ^ It is directed (without cycle)
     ^ For making it cycle, just convert 1 -> 3 int 3 -> 1
     * 
     *          (5)
     *      0 ---->-- 1
     *               /  \
     *          (1) V    V (3)
     *             /      \
     *           2 --->--- 3
     *           |   (1)
     *       (2) V
     *           |
     *           4 
     *
     */

    // BFS traversal
    public static void breadthFirstSearch(ArrayList<Edge>[] graph) {
        boolean[] isVis = new boolean[graph.length];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        for(int i=0; i<graph.length; i++) {
            while(!q.isEmpty()) {
                int curr = q.remove();
                if(!isVis[curr]){
                    System.out.print(curr + " ");
                    isVis[curr] = true;
                    for(int j=0; j<graph[curr].size(); j++) {
                        Edge e = graph[curr].get(j);
                        q.add(e.des);
                    }
                }
            }
        }
    }

    // DFS traversal
    public static void depthFirstSearch(ArrayList<Edge>[] graph, int curr, boolean[] isVis) {
        System.out.print(curr + " ");
        isVis[curr] = true;

        for(int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if(!isVis[e.des]) {
                depthFirstSearch(graph, e.des, isVis);
            }
        }
    }

    // HasPath function
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] isVis) {
        if(src == dest) {
            return true;
        }
        
        isVis[src] = true;

        for(int i=0; i<graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            if(!isVis[e.des] && hasPath(graph, e.des, dest, isVis)) {
                return true;
            }
        }

        return false;
    }

    // Cycle detection

    /* The working 
        
        Normally traverse the graph but this time maintain another paramter : 'parent'
        parent will show from where the function has reached the current vertex

        Go through all the destination of the edges it has, there will be three possible conditions:
        * Case 1:
        ~ The destination vertex is not visited earlier
        What to do? => visit it! 

        * Case 2:
        ~ The destination vertex is visited earlier but it the parent
        What to do? => ignore it

        * Case 3:
        ~ The destination vertex is visted again and it is not the parent
        What to do? => return true, because there is a cycle in the graph in this case
     */
    public static boolean detectCycleUtil(ArrayList<Edge>[] graph, int curr, boolean[] isVis, int parent) {
        isVis[curr] = true;

        for(int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if(isVis[e.des] && parent != e.des) {
                return true;
            } else if(!isVis[e.des] && detectCycleUtil(graph, e.des, isVis, curr)) {
                return true;
            }
        }

        return false;
    }

    public static boolean detectCycle(ArrayList<Edge>[] graph) {
        boolean[] isVis = new boolean[graph.length];

        for(int i=0; i<graph.length; i++) {
            if(!isVis[i] && detectCycleUtil(graph, i, isVis, -1)) {
                return true;
            }
        }

        return false;
    }

    // is the graph Bipartite
    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        int[] color = new int[graph.length];
        Arrays.fill(color, -1);

        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<graph.length; i++) {
            if(color[i] == -1) {
                q.add(i);
                color[i] = 0;
                while(!q.isEmpty()) {
                    int curr = q.remove();
                    for(int j=0; j<graph[curr].size(); j++) {
                        Edge e = graph[curr].get(j);
                        if(color[e.des] == -1) {
                            color[e.des] = 1 - color[curr]; 
                            /*
                            {
                                0 if color[curr] == 1,
                                1 if color[curr] == 0
                            }
                            */
                        } else if(color[e.des] == color[curr]) {
                            return false;
                        }
                    }
                }
            }
        }

        return false;
    }

    /*
    Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge u-v, 
    vertex u comes before v in the ordering.
     */
    public static void topologicalSort(ArrayList<Edge>[] graph) {
        boolean[] isVis = new boolean[graph.length];
        Stack<Integer> st = new Stack<>();

        for(int i=0; i<graph.length; i++) {
            if(!isVis[i]) {
                topologicalSortUtil(graph, i, isVis, st);
            }
        }

        while(!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
    }

    public static void topologicalSortUtil(ArrayList<Edge>[] graph, int curr, boolean[] isVis, Stack<Integer> st) {
        isVis[curr] = true;

        for(int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if(!isVis[e.des]) {
                topologicalSortUtil(graph, e.des, isVis, st);
            }
        }

        st.push(curr);
    }

    // Kahn's Algorithm (BFS topological sort)
    public static void kahnsAlgo(ArrayList<Edge>[] graph) {
        int[] inDeg = new int[graph.length];
        fillInDeg(graph, inDeg);
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<inDeg.length; i++) {
            if(inDeg[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + " ");

            for(int i=0; i<graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                inDeg[e.des]--;
                if(inDeg[e.des] == 0) {
                    q.add(e.des);
                }
            }
        }
    }

    public static void fillInDeg(ArrayList<Edge>[] graph, int[] inDeg) {
        for(int i=0; i<graph.length; i++) {
            for(int j=0; j<graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                inDeg[e.des]++;
            }
        }
    }

    // Getting all paths
    public static List<List<Integer>> allPath(ArrayList<Edge>[] graph, int src, int des) {
        List<List<Integer>> pathList = new ArrayList<>();
        allPathUtil(graph, src, des, new ArrayList<>(), pathList);
        return pathList;
    }

    public static void allPathUtil(ArrayList<Edge>[] graph, int src, int des, ArrayList<Integer> path, List<List<Integer>> pathList) {
        path.add(src);
        if(src == des) {
            pathList.add(new ArrayList<>(path));
            return;
        }

        for(int i=0; i<graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            allPathUtil(graph, e.des, des, path, pathList);
            path.remove(path.size()-1);
        }
    }

    // Djikstra's Algorithm: Used to find the shortest route from source to destination
    public static class Pair implements Comparable<Pair> {
        int n;
        int path;

        public Pair(int n, int p) {
            this.n = n;
            this.path = p;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;
        }
    }

    public static void djikstra(ArrayList<Edge>[] graph, int src) {
        int dist[] = new int[graph.length];
        for(int i=0; i<dist.length; i++) {
            if(i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        boolean isVis[] = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));
        while(!pq.isEmpty()) {
            Pair curr = pq.remove();
            if(!isVis[curr.n]) {
                isVis[curr.n] = true;
                for(Edge e: graph[curr.n]) {
                    int u = e.src;
                    int v = e.des;
                    int w = e.weight;
                    if(dist[v] > dist[u]+w) { 
                        dist[v] = dist[u] + w;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        for(int i=0; i<dist.length; i++) {
            System.out.println(i + " : " + dist[i]);
        }
    }

    // Bellman Ford algorithm: Used to find the shortest route from source to destination, when the graph has -ve weights too.
    public static void bellmanFord(ArrayList<Edge>[] graph, int src) {
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
                    if(dist[e.src] != Integer.MAX_VALUE && dist[e.des] > dist[e.src] + e.weight) {
                        dist[e.des] = dist[e.src] + e.weight;
                    }
                }
            }
        }
    }

    // Prim's algorithm: used to find minimum spanning tree from a graph

    /*  => We will again use this Pair class the same way, It is commented here as it's already defined in the file.
    public static class Pair implements Comparable<Pair> {
        int n;
        int path;

        public Pair(int n, int p) {
            this.n = n;
            this.path = p;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;
        }
    }
     */

    public static void primsAlgo(ArrayList<Edge>[] graph) {
        boolean[] isVis = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(0, 0));
        int finalCost = 0;

        while(!pq.isEmpty()) {
            Pair curr = pq.remove();
            if(!isVis[curr.n]) {
                isVis[curr.n] = true;
                finalCost += curr.path;

                for(int i=0; i<graph[curr.n].size(); i++) {
                    Edge e = graph[curr.n].get(i);
                    pq.add(new Pair(e.des, e.weight));
                }
            }
        }

        System.out.println(finalCost);
    }
    
    public static void main(String[] args) {
        int V = 5;
        ArrayList<Edge>[] graph = new ArrayList[V];
        for(int i=0; i<V; i++) {
            graph[i] = new ArrayList<>();
        }

        //~ 0 vertex 
        graph[0].add(new Edge(0, 1, 5));

        //~ 1 vertex
        graph[1].add(new Edge(1, 2, 1));
        graph[1].add(new Edge(1, 3, 3));
        // graph[1].add(new Edge(1, 0, 5));

        //~ 2 vertex
        graph[2].add(new Edge(2, 3, 1));
        graph[2].add(new Edge(2, 4, 2));
        // graph[2].add(new Edge(2, 1, 1));

        //~ 3 vertex
        // graph[3].add(new Edge(3, 1, 3));
        // graph[3].add(new Edge(3, 2, 1));

        //~ 4 vertex 
        // graph[4].add(new Edge(4, 2, 2));

        breadthFirstSearch(graph);
        System.out.println();
        depthFirstSearch(graph, 0, new boolean[5]);
        System.out.println();
        System.out.println(detectCycle(graph));
        System.out.println(isBipartite(graph));
        topologicalSort(graph);
        kahnsAlgo(graph);
        System.out.println(allPath(graph, 1, 3));;
    }
}