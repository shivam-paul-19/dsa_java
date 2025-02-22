// Leetcode Q: 3255. Find the Power of K-Size Subarrays II

public class powerOfSubArr {
    public int[] resultsArray(int[] nums, int k) {
        if(k==1) {
            return nums;
        }

        int n = nums.length;
        int[] ans = new int[n-k+1];
        int count = 1;
        int index = 0;

        for(int i=0; i<nums.length-1; i++) {
            if(nums[i]+1 == nums[i+1]) {
                count++;
            } else {
                count=1;
            }

            if(i+1>=k) {
                if(count>=k) {
                    ans[index++] = nums[i];
                } else {
                    ans[index++] = -1;
                }
            }
        }

        return ans;
    }
}
