// Leetcode Q: 2161. Partition Array According to Given Pivot

import java.util.*;

public class partition {
    public static int[] pivotArray(int[] nums, int pivot) {
        if(nums.length == 1) {
            return nums;
        }

        ArrayList<Integer> less = new ArrayList<>();
        ArrayList<Integer> equal = new ArrayList<>();
        ArrayList<Integer> great = new ArrayList<>();

        for(int i=0; i<nums.length; i++) {
            if(nums[i] < pivot) {
                less.add(nums[i]);
            } else if(nums[i] > pivot) {
                great.add(nums[i]);
            } else {
                equal.add(nums[i]);
            }
        }

        int i=0;
        int[] res = new int[nums.length];
        while(i<nums.length) {
            if(i<less.size()) {
                res[i] = less.get(i);
            } else if(i<less.size()+equal.size()) {
                res[i] = equal.get(i-less.size());
            } else {
                res[i] = great.get(i-(less.size()+equal.size()));
            }

            i++;
        }

        return res;
    }
}
