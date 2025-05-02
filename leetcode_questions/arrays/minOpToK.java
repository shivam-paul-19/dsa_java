// Leetcode Q: 3375. Minimum Operations to Make Array Values Equal to K

import java.util.*;

public class minOpToK {
    public int minOperations(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int op = 0;
        for(int n: nums) {
            if(n < k) return -1;
            if(!set.contains(n)) {
                set.add(n);
                if(n>k) op++;
            }
        }

        return op;
    }
}