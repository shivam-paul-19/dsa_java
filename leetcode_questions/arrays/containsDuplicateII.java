// Leetcode Q: 219. Contains Duplicate II

import java.util.*;

public class containsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(k == 0) return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<nums.length; i++) {
            if(map.containsKey(nums[i])) return true;
            map.put(nums[i], i);
            if(i >= k) map.remove(nums[i-k]);
        }

        return false;
    }
}
