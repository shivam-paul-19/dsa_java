// Leetocde Q: 2574. Left and Right Sum Differences

public class leftRight {
    public int[] leftRightDifference(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = 0;
        right[right.length - 1] = 0;

        for(int i=1; i<nums.length; i++) {
            left[i] = left[i-1] + nums[i-1];
            right[right.length-i-1] = right[right.length-i] + nums[nums.length-i];
        }

        int[] ans = new int[nums.length];
        for(int i=0; i<left.length; i++) {
            ans[i] = Math.abs(left[i] - right[i]);
        }

        return ans;
    }
}
