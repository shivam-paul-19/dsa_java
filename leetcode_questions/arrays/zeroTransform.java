// Leetcode Q: 3355. Zero Array Transformation I

public class zeroTransform {
    public static boolean isZeroArray(int[] nums, int[][] queries) {
        int[] prefix = new int[nums.length+1];
        for(int[] q: queries) {
            prefix[q[0]]++;
            prefix[q[1]+1]--;
        }

        for(int i=1; i<prefix.length; i++) {
            prefix[i] += prefix[i-1];
        }

        for(int i=0; i<nums.length; i++) {
            if(nums[i] > prefix[i]) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1};
        int[][] queries = {{0,2}};
        System.out.println(isZeroArray(nums, queries));
    }
}
