// Leetcode Q: 2962. Count Subarrays Where Max Element Appears at Least K Times

import java.util.Arrays;

public class maxKTimes {
    public long countSubarrays(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        long count = 0L;
        int left = 0;
        int maxCount = 0;

        for(int right=0; right<nums.length; right++) {
            if(nums[right] == max) maxCount++;

            while(maxCount == k) {
                if(nums[left] == max) maxCount--;
                left++;
            }

            count += left;
        }

        return count;
    }
}