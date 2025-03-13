// Leetcode Q: 1749. Maximum Absolute Sum of Any Subarray

public class maxAbsSubArr {
    public static int maxAbsoluteSum(int[] nums) {
        int[] prefixArr = new int[nums.length];
        prefixArr[0] = nums[0];
        int min = prefixArr[0];
        int max = prefixArr[0];
        for(int i=1; i<nums.length; i++) {
            prefixArr[i] = nums[i] + prefixArr[i-1];
            min = (min>prefixArr[i])? prefixArr[i] : min;
            max = (max<prefixArr[i])? prefixArr[i] : max;
        }
        
        if(min >= 0) {
            return max;
        } else if(max<0) {
            return Math.abs(min);
        }
        return Math.abs(min - max);
    }
}
