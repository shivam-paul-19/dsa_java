// Leetcode Q: 3392. Count Subarrays of Length Three With a Condition

public class counttttttt {
    public int countSubarrays(int[] nums) {
        int count = 0;
        for(int i=1; i<nums.length-1; i++) {
            if(nums[i] == (nums[i-1] + nums[i+1])*2) count++;
        }

        return count;
    }
}