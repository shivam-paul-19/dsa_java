// Leetcode Q: 2873. Maximum Value of an Ordered Triplet I

public class maxTriplet {
    public long maximumTripletValue(int[] nums) {
        long res = 0l;
        for(int i=0; i<nums.length-2; i++) {
            for(int j=i+1; j<nums.length-1; j++) {
                for(int k=j+1; k<nums.length; k++) {
                    res = Math.max(res, (nums[i] - nums[j])*(long)nums[k]);
                }
            }
        }

        return res;
    }
}