// Leetcode Q: 416. Partition Equal Subset Sum

public class sumPartition {
    public boolean canPartition(int[] nums) {
        int total = nums[0];

        for(int i=1; i<nums.length; i++) {
            total += nums[i];
        }

        if(total%2 == 1) return false;

        boolean[] dp = new boolean[total/2 + 1];
        dp[0] = true;

        for(int i=0; i<nums.length; i++) {
            for(int j=total/2; j>=nums[i]; j--) {
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }

        return dp[total/2];
    }
}
