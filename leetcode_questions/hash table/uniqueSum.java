// Leetcode Q: 1748. Sum of Unique Elements

import java.util.Arrays;

public class uniqueSum {
    public int sumOfUnique(int[] nums) {
        if(nums.length == 1) return nums[0];

        Arrays.sort(nums);
        int sum = nums[0];
        for(int i=1; i<nums.length-1; i++) {
            if(nums[i] != nums[i-1] && nums[i] != nums[i+1]) {
                sum += nums[i];
            }
        }

        if(nums.length > 1 && nums[0] != nums[1]) sum += nums[0];
        if(nums.length > 1 && nums[nums.length - 1] != nums[nums.length - 2]) sum += nums[nums.length - 1];

        return sum;
    }
}