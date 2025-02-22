// Leetcode Q: 80. Remove Duplicates from Sorted Array II

import java.util.*;

public class removeDupII {
    public static int removeDuplicates(int[] nums) {
        int[] newNums = new int[nums.length];
        HashMap<Integer, Integer> map = new HashMap<>();

        newNums = Arrays.copyOf(nums, nums.length);
        Arrays.fill(nums, 0);
        int j=0;
        for(int i=0; i<newNums.length; i++) {
            if(map.containsKey(newNums[i]) && (map.get(newNums[i]) == 2)) {
                continue;
            }
            nums[j++] = newNums[i];
            map.put(newNums[i], map.getOrDefault(newNums[i], 0) + 1);
        }

        return j;
    }
}
