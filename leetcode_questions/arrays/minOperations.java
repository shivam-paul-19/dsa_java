// Leetcode Q: 3396. Minimum Number of Operations to Make Elements in Array Distinct

import java.util.*;

public class minOperations {
    private boolean isDistinct(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n: nums) {
            set.add(n);
        }

        return set.size() == nums.length;
    }

    public int minimumOperations(int[] nums) {
        if(nums.length == 0) {
            return 0;
        } 

        int idx = 0;
        int op = 0;
        while(idx < nums.length) {
            if(isDistinct(Arrays.copyOfRange(nums, idx, nums.length))) {
                return op;
            }

            op++;
            idx += 3;
        }

        return op;
    }
}