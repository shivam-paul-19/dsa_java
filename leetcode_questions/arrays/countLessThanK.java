// Leetcode Q: 713. Subarray Product Less Than K

public class countLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int product = 1;
        int left = 0;

        for(int right = 0; right<nums.length; right++) {
            product *= nums[right];
            while(left <= right && product >= k) {
                product /= nums[left];
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }
}
