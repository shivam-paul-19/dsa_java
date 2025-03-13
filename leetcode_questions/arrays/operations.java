// LeetCode Q: 2460. Apply Operations to an Array

public class operations {
    public static int[] applyOperations(int[] nums) {
        int[] res = new int[nums.length];
        int resIdx = 0;
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i] == nums[i+1] && nums[i] != 0) {
                res[resIdx++] = nums[i]*2;
                nums[i+1] = 0;
            } else if(nums[i] != 0) {
                res[resIdx++] = nums[i];
            }
        }

        res[resIdx] = nums[nums.length-1];

        return res;
    }
}
