// Leetcode Q: 2033. Minimum Operations to Make a Uni-Value Grid

import java.util.*;

public class minOperationForUni {
    public int minOperations(int[][] grid, int x) {
        List<Integer> list = new ArrayList<>();

        for(int[] row: grid) {
            for(int n: row) list.add(n);
        }

        Collections.sort(list);
        int mid = list.get(list.size()/2);
        int ans = 0;

        for(int el: list) {
            if (el%x != mid%x) return -1;
            ans += Math.abs(mid - el)/x;
        }

        return ans;
    }
}
