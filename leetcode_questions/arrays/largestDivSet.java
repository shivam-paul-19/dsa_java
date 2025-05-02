// Leetocde Q: 368. Largest Divisible Subset

import java.util.*;

public class largestDivSet {
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums.length == 1) {
            return Arrays.asList(nums[0]);
        }

        int[] dp = new int[nums.length];
        int[] prev = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        Arrays.sort(nums);

        int maxLen = 1;
        int maxIdx = 0;

        for(int i=0; i<nums.length; i++) {
            for(int j=0; j<i; j++) {
                if(nums[i]%nums[j] == 0 && dp[i] < dp[j]+1) {
                    dp[i] = dp[j]+1;
                    prev[i] = j;
                }

                if(dp[i] > maxLen) {
                    maxLen = dp[i];
                    maxIdx = i;
                }
            }
        }

        List<Integer> ans = new ArrayList<>(); 
        while(maxIdx != -1) {
            ans.add(nums[maxIdx]);
            maxIdx = prev[maxIdx];
        }
        
        return ans.reversed();
    }
}