// Leetcode Q: 1800. Maximum Ascending Subarray Sum

public class ascArr {
    public static int maxAscendingSum(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }

        int max = nums[0];
        int currMax = nums[0];
        for(int i=1; i<nums.length; i++) {
            if(nums[i] > nums[i-1]) {
                currMax += nums[i];
            } else {
                max = Math.max(currMax, max);
                currMax = nums[i];
            }
        }

        max = Math.max(currMax, max);
        return max;
    }
}