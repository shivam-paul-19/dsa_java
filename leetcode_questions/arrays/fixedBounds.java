// Leetcode Q: 2444. Count Subarrays With Fixed Bounds

public class fixedBounds {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int lastIdx = -1;
        int minIdx = -1;
        int maxIdx = -1;
        long count = 0L;

        for(int i=0; i<nums.length; i++) {
            if(nums[i] > maxK || nums[i] < minK) {
                lastIdx = i;
                minIdx = -1;
                maxIdx = -1;
                continue;
            }

            if(nums[i] == maxK) maxIdx = i;
            if(nums[i] == minK) minIdx = i;

            if(maxIdx != -1 && minIdx != -1) {
                count += Math.max(0, Math.min(maxIdx, minIdx) - lastIdx);
            }
        }

        return count;
    }
}