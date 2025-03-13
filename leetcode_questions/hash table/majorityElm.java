// Leetcode Q: 229. Majority Element II

import java.util.*;

public class majorityElm {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        int threshold = nums.length/3;
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if(map.get(nums[i]) > threshold && !list.contains(nums[i])) {
                list.add(nums[i]);
            }
        }
        return list;
    }
}