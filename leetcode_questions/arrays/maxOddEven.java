// Leetcode Q: 2529. Maximum Count of Positive Integer and Negative Integer

public class maxOddEven {
    public int maximumCount(int[] nums) {
        int odd = 0;
        int zero = 0;
        int even = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] < 0) odd++;
            if(nums[i] == 0) zero++;
            if(nums[i] > 0) break;
        }

        even = nums.length - odd - zero;
        return Math.max(odd, even);
    }
}
