// Leetcode Q: 2563. Count the Number of Fair Pairs

import java.util.*;

public class fairPair {
    private long count(int[] nums, int value) {
        int start = 0;
        int end = nums.length-1;
        long count = 0L;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum < value) {
                count += (end - start);
                start++;
            } else {
                end--;
            }
        }

        return count;
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return count(nums, upper + 1) - count(nums, lower);
    }
}