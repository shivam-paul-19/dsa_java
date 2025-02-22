// Leetcode Q: 547. Number of Provinces

public class provinces {
    private static void dfs(int[][] graph, boolean[] isVis, int curr) {
        isVis[curr] = true;
        for(int i=0; i<graph[curr].length; i++) {
            if(i==curr) {
                continue;
            }
            if(graph[curr][i] == 1 && !isVis[i]) {
                dfs(graph, isVis, i);
            }
        }
    }

    public static int findCircleNum(int[][] isConnected) {
        int n = 0;
        boolean[] isVis = new boolean[isConnected.length];
        for(int i=0; i<isConnected.length; i++) {
            if(!isVis[i]) {
                n++;
                dfs(isConnected, isVis, i);
            }
        }
        return n;
    }
}
