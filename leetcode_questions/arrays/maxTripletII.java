// Leetcode Q: 2874. Maximum Value of an Ordered Triplet II

public class maxTripletII {
    public long maximumTripletValue(int[] nums) {
        int[] maxLeft = new int[nums.length];
        int[] maxRight = new int[nums.length];

        maxLeft[0] = nums[0];
        maxRight[maxRight.length - 1] = nums[nums.length - 1];

        for(int i=1; i<nums.length; i++) {
            maxLeft[i] = Math.max(maxLeft[i-1], nums[i]);
        }

        for(int i=nums.length-2; i>=0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], nums[i]);
        }

        long ans = 0l;
        for(int i=0; i<nums.length-2; i++) {
            ans = Math.max(ans, (long)(maxLeft[i] - nums[i+1]) * maxRight[i+2]);
        }

        return ans;
    }
}