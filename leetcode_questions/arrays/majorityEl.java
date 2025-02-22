// Leetcode Q: 169. Majority Element

import java.util.*;

public class majorityEl {
    public int majorityElement(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        HashMap <Integer, Integer> map = new HashMap<>();
        for(int e: nums) {
            if(map.get(e) == null) {
                map.put(e, 1);
            } else {
                map.put(e, map.get(e) + 1);
                if(map.get(e) > nums.length/2) {
                    return e;
                }
            }
        }

        return 0;
    }
}
