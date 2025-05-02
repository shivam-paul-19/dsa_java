// LeetCode Q: 560. Subarray Sum Equals K

import java.util.*;

public class sumIsK {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int currSum = 0;

        for(int i=0; i<nums.length; i++) {
            currSum += nums[i];
            count += map.getOrDefault(currSum - k, 0);
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }
}