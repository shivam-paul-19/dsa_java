// Leetcode Q: 2352. Equal Row and Column Pairs

import java.util.*;

public class equalRowNCol {
    public static int equalPairs(int[][] grid) {
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        int[][] grid_t = new int[grid.length][grid.length];
        int unique = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                grid_t[i][j] = grid[j][i];
            }
        }

        for(int[] row: grid) {
            String s = Arrays.toString(row);
            map1.put(s, map1.getOrDefault(s, 0) + 1);
        }

        for(int[] row: grid_t) {
            String s = Arrays.toString(row);
            map2.put(s, map2.getOrDefault(s, 0) + 1);
        }

        for(String s: map1.keySet()) {
            if(map2.containsKey(s)) {
                int m = map1.get(s);
                int n = map2.get(s);
                unique += n*m;
            }
        }

        return unique;
    }
    
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        int[][] array = {
            {3, 1, 2, 2},
            {1, 4, 4, 5},
            {2, 4, 2, 2},
            {2, 4, 2, 2}
        };
        
        System.out.println(equalPairs(array));
    }
}
