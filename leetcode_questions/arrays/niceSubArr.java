// Leetcode Q: 2401. Longest Nice Subarray

public class niceSubArr {
    private static boolean check(int[] nums, int start, int end) {
        for(int i=start; i<end; i++) {
            if((nums[i] & nums[end]) != 0) return false;
        }

        return true;
    }

    public static int longestNiceSubarray(int[] nums) {        
        int res = 1;
        int start = 0;
        for(int i=1; i<nums.length; i++) {
            if((nums[i] & nums[i-1]) == 0) {
                while(!check(nums, start, i)) {
                    start++;
                }

                res = Math.max(res, i - start + 1);
            } 
            else {
                start = i;
            }
        }

        return res;
    }
}