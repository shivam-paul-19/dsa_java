// leetocde Q: 2503. Maximum Number of Points From Grid Queries

import java.util.*;

public class maxPoints {
    public int[] maxPoints_(int[][] grid, int[] queries) {
        int[] ans = new int[queries.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int[][] directions = {{-1,0}, {1,0}, {0,1}, {0,-1}};    // defining directions
        pq.add(new int[] {grid[0][0], 0, 0});    // adding first element
        int[][] seq = new int[queries.length][2];
        boolean[][] isVis = new boolean[grid.length][grid[0].length];
        isVis[0][0] = true;

        for(int i=0; i<queries.length; i++) {
            seq[i][0] = queries[i];
            seq[i][1] = i;
        }
        
        Arrays.sort(seq, (a, b) -> a[0] - b[0]);
        int point = 0;
        for(int q[]: seq) {
            int ogIdx = q[1];
            int query = q[0];
            while(!pq.isEmpty() && pq.peek()[0] < query) {
                int[] cell = pq.poll();
                point++;
                for(int[] d: directions) {
                    int newRow = cell[1] + d[0];
                    int newCol = cell[2] + d[1];
                    if(newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && !isVis[newRow][newCol]) {
                        isVis[newRow][newCol] = true;
                        pq.add(new int[] {grid[newRow][newCol], newRow, newCol});
                    }
                }
            } 
            ans[ogIdx] = point;
        }
        
        return ans;
    }
}
