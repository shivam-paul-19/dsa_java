// Leetcode Q: 215. Kth Largest Element in an Array

import java.util.*;

public class kthLargest {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);
        for(int i=0; i<nums.length; i++) {
            pq.add(nums[i]);
        }

        int i=1;
        int ans = 0;
        while(i<=k) {
            ans = pq.poll();
            i++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,1,5,6,4};
        System.out.println(findKthLargest(arr, 2));
    }
}
