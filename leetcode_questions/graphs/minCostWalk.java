// Leetcode Q: 3108. Minimum Cost Walk in Weighted Graph

import java.util.*;

public class minCostWalk {
    private int find(int n, int[] parent) {
        if(parent[n] == -1) return n;
        parent[n] = find(parent[n], parent);
        return parent[n];
    }

    private void union(int n1, int n2, int[] parent, int[] depth) {
        int root1 = find(n1, parent);
        int root2 = find(n2, parent);

        if(root1 == root2) return;

        if(depth[root1] < depth[root2]) {
            int temp = root1;
            root1 = root2;
            root2 = temp;
        }

        parent[root2] = root1;

        if (depth[root1] == depth[root2]) depth[root1]++;
    }

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] cost = new int[n];

        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        for(int[] edge: edges) {
            union(edge[0], edge[1], parent, depth);
        }

        for(int[] edge: edges) {
            int root = find(edge[0], parent);
            cost[root] &= edge[2];
        }

        int[] ans = new int[query.length];
        for(int i=0; i<query.length; i++) {
            int s = query[i][0];
            int e = query[i][1];

            if(find(s, parent) != find(e, parent)) {
                ans[i] = -1;
            } else {
                ans[i] = cost[find(s, parent)];
            }
        }

        return ans;
    }
}
