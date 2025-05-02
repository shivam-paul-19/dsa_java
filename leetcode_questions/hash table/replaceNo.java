// Leetcode Q: 2295. Replace Elements in an Array

import java.util.*;

public class replaceNo {
    public int[] arrayChange(int[] nums, int[][] operations) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], i);
        }

        for(int[] op: operations) {
            int idx = map.get(op[0]);
            int newVal = op[1];
            nums[idx] = newVal;
            map.put(newVal, idx);
        }

        return nums;
    }
}
