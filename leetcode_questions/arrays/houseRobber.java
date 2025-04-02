// Leetcode Q: 2560. House Robber IV

import java.util.*;

public class houseRobber {
    private static boolean canRob(int[] nums, int k, int temp) {
        int i=0;
        int houses = 0;
        while(i<nums.length) {
            if(nums[i] <= temp) {
                houses++;
                i += 2;
            } else {
                i++;
            }
        }

        return houses >= k;
    }
    public static int minCapability(int[] nums, int k) {
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();

        while(min <= max) {
            int mid = min + (max-min)/2;
            if(canRob(nums, k, mid)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return min;
    }
}
