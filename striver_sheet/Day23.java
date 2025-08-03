// Day 23: graphs

import java.util.*;
import java.util.LinkedList;

public class Day23 {
    // DFS
    private void dfs(int curr, List<List<Integer>> adj, List<Integer> ans, boolean[] vis) {
        // first add the curr in the list
        ans.add(curr);
        // mark as visited
        vis[curr] = true;

        // recursive calls for neighbours
        for(int i=0; i<adj.get(curr).size(); i++) {
            int next = adj.get(curr).get(i);
            if(vis[next] == false) {
                // to prevent stack overflow
                dfs(next, adj, ans, vis);
            }
        }
    }

    public List<Integer> dfsOfGraph(int V, List<List<Integer>> adj) {
        // first make a visited array
        boolean[] vis = new boolean[V];

        List<Integer> ans = new ArrayList<>();
        dfs(0, adj, ans, vis);
        return ans;
    }

    // BFS
    public List<Integer> bfsOfGraph(int V, List<List<Integer>> adj) {
        // first make a visited array
        boolean[] vis = new boolean[V];
        List<Integer> ans = new ArrayList<>();
        
        // make a Queue to store nodes
        Queue<Integer> q = new LinkedList<>();

        // add 0 to queue
        q.add(0);

        // perform BFS
        while(!q.isEmpty()) {
            // get the front node/value and add it to list
            int curr = q.poll();
            ans.add(curr);

            // mark as visited
            vis[curr] = true;

            // add it's neighbours if they aren't visited
            for(int i=0; i<adj.get(curr).size(); i++) {
                int neighbour = adj.get(curr).get(i);

                if(vis[neighbour] == false) {
                    q.offer(neighbour);
                }
            }
        }

        return ans;
    }

    // cycle detection: undirected graph
    private boolean isCycleDFS(List<Integer>[] adj, boolean[] vis, int curr, int parent) {
        // mark as visited
        vis[curr] = true;

        // check for neighbours
        for(int i=0; i<adj[curr].size(); i++) {
            int neighbour = adj[curr].get(i);

            // if it's neighbour is not visited then visit it
            if(vis[neighbour] == false) {
                return isCycleDFS(adj, vis, neighbour, curr);
            }

            // if the nieghbour is visted and is not parent then it is a cycle
            if(vis[neighbour] && parent != neighbour) {
                return true;
            }
        }

        return false;
    }

    public boolean isCycle(int V, List<Integer>[] adj) {
        return isCycleDFS(adj, new boolean[V], 0, -1);
    }

    // cycle detection: directed graph
    private boolean isCycleDFS_dir(List<Integer>[] adj, boolean[] vis, boolean[] rec, int curr) {
        // mark as visited in both
        vis[curr] = true;
        rec[curr] = true;

        // check for neighbours
        for(int i=0; i<adj[curr].size(); i++) {
            int neighbour = adj[curr].get(i);

            // if it is in current recursion stack then there is a cycle
            if(rec[neighbour]) return true;

            // if it's neighbour is not visited then visit it
            if(vis[neighbour] == false) {
                return isCycleDFS_dir(adj, vis, rec, neighbour);
            }
        }

        // remove from recursion stack
        rec[curr] = false;

        return false;
    }

    public boolean isCycle_dir(int V, List<Integer>[] adj) {
        return isCycleDFS_dir(adj, new boolean[V], new boolean[V], 0);
    }

    // Topological sort
    // Note: for doing topological, the pre requisite is that the graph should be directed and no cycle in it

    // Topological sort using DFS
    private void popilateStack(Stack<Integer> s, List<List<Integer>> adj, boolean[] vis, int curr) {
        // mark as visited
        vis[curr] = true;

        // call for neighbours
        for(int i=0; i<adj.get(curr).size(); i++) {
            int neighbour = adj.get(curr).get(i);

            // visit if not visited yet
            if(vis[neighbour] == false) {
                popilateStack(s, adj, vis, neighbour);
            }
        }

        // at the end push the curr in stack
        s.push(curr);
    }

    public int[] topoSort(int V, List<List<Integer>> adj) {
        // create a stack, and a visited array
        Stack<Integer> s = new Stack<>();
        boolean[] vis = new boolean[V];

        for(int i=0; i<adj.size(); i++) {
            // loop is used as it is possible that the graph isn't a single connected component
            if(vis[i] == false) {
                popilateStack(s, adj, vis, i);
            } 
        }

        // empty the stack
        int[] ans = new int[V];
        int i=0;
        while(!s.isEmpty()) {
            ans[i++] = s.pop();
        }

        return ans;
    }

    // Leetcode Q: 200. Number of Islands
    public int numIslands(char[][] grid) {
        // make a count variable
        int count = 0;

        

        return count;
    }

    // Leetcode Q: 785. Is Graph Bipartite?
    public boolean isBipartite(int[][] graph) {
        int V = graph.length;

        // make a visited array and a color array, as we are using graph coloring method
        int[] color = new int[V];

        // colors: 0 -> no color, 1 -> color-1, 2 -> color-2

        // make a Queue
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<graph.length; i++) {
            // in case the graph is not a single connected component
            if(color[i] == 0) {
                // color it with 1 and add to queue
                color[i] = 1;
                q.offer(i);
                while(!q.isEmpty()) {
                    // get the curr node
                    int curr = q.poll();

                    // check for the neighbours
                    for(int j=0; j<graph[curr].length; j++) {
                        // if the neighbour isn't colored thne color with opposite one
                        int neighbour = graph[curr][j];
                        if(color[neighbour] == 0) {
                            color[neighbour] = (color[curr] == 1)? 2 : 1;
                            q.offer(neighbour);     // add to queue
                        }

                        // if the neighbour is colored with same then return false
                        else if(color[neighbour] == color[curr]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
