// Leetcode Q: 2537. Count the Number of Good Subarrays

import java.util.*;

public class goodNo {
    public long countGood(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int same = 0;
        int right = -1;
        long ans = 0L;
        for(int left = 0; left < nums.length; left++) {
            while(same < k && right + 1 < nums.length) {
                right++;
                same += map.getOrDefault(nums[right], 0);
                map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            }

            if(same >= k) {
                ans += nums.length - right;
            }

            map.put(nums[left], map.get(nums[left]) - 1);
            same -= map.get(nums[left]);
        }

        return ans;
    }
}