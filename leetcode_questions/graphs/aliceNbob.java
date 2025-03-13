// Leetcode Q: 2467. Most Profitable Path in a Tree

import java.util.*;

public class aliceNbob {
    static class Edge {
        int src;
        int dest;

        Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static ArrayList<Edge>[] makeGraph(int[][] edges, int[] amount) {
        ArrayList<Edge>[] graph = new ArrayList[edges.length+1];
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<edges.length; i++) {
            int src = edges[i][0];
            int dest = edges[i][1];
            
            graph[src].add(new Edge(src, dest));
            graph[dest].add(new Edge(dest, src));
        }
        
        return graph;
    }

    public static boolean bobPath(ArrayList<Edge>[] graph, int init, ArrayList<Integer> path, boolean[] isVis) {
        isVis[init] = true;
        path.add(init);
        if(init == 0) {
            // path.add(init);
            return true;
        }
        
        for(int i=0; i<graph[init].size(); i++) {
            Edge e = graph[init].get(i);
            if(!isVis[e.dest]) {
                if(bobPath(graph, e.dest, path, isVis)) {
                    return true;
                }
            }
        }

        path.remove(path.size()-1);
        return false;
    }

    public static int findAlicePath(ArrayList<Edge>[] graph, int[] time, int[] amount, int curr, int aliceTime, int maxProfit, boolean[] isVis) {
        int currProfit = Integer.MIN_VALUE;
        isVis[curr] = true;
        for(int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if(isVis[e.dest]) {
                continue;
            }
            
            currProfit = Math.max(currProfit, findAlicePath(graph, time, amount, e.dest, aliceTime+1, 0, isVis));
            maxProfit = currProfit;
        }

        if(time[curr] == aliceTime) {
            maxProfit += amount[curr]/2;
        } else if(time[curr] == -1 || time[curr] > aliceTime) {
            maxProfit += amount[curr];
        } 
        
        return maxProfit;
    }

    public static int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int[] time_taken = new int[edges.length+1];
        Arrays.fill(time_taken, -1);
        ArrayList<Edge>[] graph = makeGraph(edges, amount);
        ArrayList<Integer> bobPath = new ArrayList<>();

        // finding Bob's path
        bobPath(graph, bob, bobPath, new boolean[edges.length+1]);
        
        for(int i=0; i<bobPath.size(); i++) {
            time_taken[bobPath.get(i)] = i;
        }
        
        return findAlicePath(graph, time_taken, amount, 0, 0, 0, new boolean[edges.length+1]);
    }
    
    public static void main(String[] args) {
        int[][] edges = {{0,1}, {1,2}, {1,3}, {3,4}};
        int[] amount = {-2,4,2,-4,6};
        // int[][] edges = {{0,1}};
        // int[] amount = {-7280,2350};
        
        int p = mostProfitablePath(edges, 3, amount);
        System.out.println(p);
    }
}