// LeetCode Q: 2799. Count Complete Subarrays in an Array

import java.util.*;
import java.util.Arrays;

public class completeSubArr {
    public static int countCompleteSubarrays(int[] nums) {
        int unique = (int)Arrays.stream(nums).boxed().distinct().count();
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        while(right<nums.length) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while(map.size() == unique) {
                count += nums.length - right;
                if(map.get(nums[left]) == 1) map.remove(nums[left]);
                else map.put(nums[left], map.get(nums[left]) - 1);

                left++;
            }

            right++;
        }

        return count;
    }
}