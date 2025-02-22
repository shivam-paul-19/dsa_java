// Leetcode Q: 18. 4Sum

import java.util.*;

public class four_sum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        HashSet<List<Integer>> set = new HashSet<>();
        List<Integer> no = new ArrayList<>();

        for(int i=0; i<nums.length-3; i++) {
            for(int j=i+1; j<nums.length-2; j++) {
                int left = j+1, right = nums.length-1;
                while(left<right) {
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum > target) {
                        right--;
                    } else if(sum < target) {
                        left++;
                    } else if(sum == target) {
                        System.out.println(sum);
                        no.add(nums[i]);
                        no.add(nums[j]);
                        no.add(nums[left]);
                        no.add(nums[right]);
                        Collections.sort(no);
                        set.add(new ArrayList<>(no));
                        no.clear();
                        left++;
                        right--;
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        int[] arr = {1000000000,1000000000,1000000000,1000000000};
        System.out.println(fourSum(arr, -294967296));
    }
}
