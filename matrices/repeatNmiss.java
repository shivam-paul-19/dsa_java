// 2965. Find Missing and Repeated Values

import java.util.*;

public class repeatNmiss {
    public static int[] findMissingAndRepeatedValues(int[][] grid) {
        HashMap <Integer, Integer> map = new HashMap<>();
        for (int[] row: grid) {
            for (int i: row) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }

        int n = grid.length;
        int[] res = new int[2];
        for(int i=1; i<=n*n; i++) {
            if(map.containsKey(i)) {
                if(map.get(i) > 1) {
                    res[0] = i;
                }
            } else {
                res[1] = i;
            }
        }

        return res;
}
