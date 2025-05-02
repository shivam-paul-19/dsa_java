// Leetcode Q: 164. Maximum Gap

import java.util.Arrays;

public class maxGap {
    public int maximumGap(int[] nums) {
        if(nums.length == 1) return 0;

        int max = 0;
        Arrays.sort(nums);
        for(int i=1; i<nums.length; i++) {
            max = Math.min(max, nums[i]-nums[i-1]);
        }

        return max;
    }
}