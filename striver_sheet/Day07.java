// Day 7: Linked List and Arrays

import java.util.*;

public class Day07 {
    // defining ListNode class for Linked Lists
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { 
            this.val = val; 
        }
        ListNode(int val, ListNode next) { 
            this.val = val; 
            this.next = next; 
        }
    }
    public class Node {
        int val;
        Node next;
        Node random;
        
        Node(int val) { 
            this.val = val; 
            this.next = null;
            this.random = null;
        }
    }

    // Leetcode Q: 61. rotate
    public ListNode rotateRight(ListNode head, int k) {
        // edge case
        if(head == null) return head;
        // first of all, count the number of nodes
        int count = 0;
        ListNode temp = head;
        while(temp != null) {
            temp = temp.next;
            count++;
        }

        k %= count;     // mod k with count to save the iterations
        for(int i=0; i<k; i++) {
            temp = head;
            // reach the second last node
            for(int j=1; j<count-1; j++) {
                temp = temp.next;
            }

            temp.next.next = head;  // make the last node point to head
            head = temp.next;       // make that last node as head
            temp.next = null;       // make the second last node last by pointing it to null
        }

        return head;
    }

    // Leetcode Q: 138. Copy List with Random Pointer
    public Node copyRandomList(Node head) {
        // edge case
        if(head == null) return null;

        // we will maintain a hashMap of orginal and copied nodes
        HashMap<Node, Node> map = new HashMap<>();
        Node copy = new Node(head.val);
        map.put(head, copy);
        Node copyTemp = copy;
        Node temp = head;

        while(temp.next != null) {
            copyTemp.next = new Node(temp.next.val);  // copy the value
            map.put(temp.next, copyTemp.next);        // put them in map
            copyTemp = copyTemp.next;
            temp = temp.next;
        }

        // again take them to their respective heads
        temp = head;
        copyTemp = copy;
        copyTemp.random = temp.random != null? map.get(temp.random) : null;
        while(temp.next != null) {
            // if there exists a random
            copyTemp.next.random = temp.next.random != null? map.get(temp.next.random) : null;     // the copied one's address
            copyTemp = copyTemp.next;
            temp = temp.next;
        }

        return copy;
    }

    // Leetcode Q: 15. 3Sum
    public List<List<Integer>> threeSum(int[] nums) {
        // lets make a set of List, as we need unique
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();     // to store one triplet at a time

        // sort the array
        Arrays.sort(nums);

        int n = nums.length;
        for(int i=0; i<n-2; i++) {
            int j = i+1;
            int k = n-1;

            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0) {
                    // in this case, we have got one triplet
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    Collections.sort(list);
                    set.add(new ArrayList<>(list));
                    list.clear();   // clear the list
                    j++;
                    k--;
                } else if(sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        // return the set in the form of arraylist
        return new ArrayList<>(set);
    }

    // Leetcode Q: 42. Trapping Rain Water
    public int trap(int[] height) {
        int n = height.length;
        if(n == 0 || n == 1) return 0;  // in this case, no water will be trapped

        // we will make two arrays
        int[] right = new int[n];   // to track highest in right
        int[] left = new int[n];    // to track highest in left

        left[0] = height[0];
        for(int i=1; i<n; i++) {
            left[i] = Math.max(height[i], left[i-1]);
        }

        right[n-1] = height[n-1];
        for(int i=n-2; i>=0; i--) {
            right[i] = Math.max(height[i], right[i+1]);
        }

        int trapped = 0;
        
        // at each point, the trapped water above the height will be => min(right[i], left[i]) - height[i]
        for(int i=0; i<n; i++) {
            trapped += Math.min(right[i], left[i]) - height[i];
        }

        return trapped;
    }

    // Leetcode Q: 26. Remove Duplicates from Sorted Array
    public int removeDuplicates(int[] nums) {
        // first make the array sorted
        Arrays.sort(nums);

        int n = nums.length;
        int d = 0;  // to track duplicacy
        for(int i=1; i<n; i++) {
            if(nums[i] == nums[i-1]) {
                // in this case there is duplicacy
                nums[i-1] = Integer.MAX_VALUE;
                d++;
            }
        }

        // sort the nums again, by this all the discarded elements will be at end as they are the max
        Arrays.sort(nums);
        return n-d;     // unique elements
    }

    // Leetcode Q: 485. Max Consecutive Ones
    public int findMaxConsecutiveOnes(int[] nums) {
        // we have to find the most consecutive 1s, just traverse the array
        int maxLen = 0;
        int len = 0;

        for(int n: nums) {
            if(n == 0) {
                maxLen = Math.max(len, maxLen);
                len = 0;    // reset len
            } else {
                len++;  // count the 1s
            }
        }
        
        maxLen = Math.max(len, maxLen);
        return maxLen;
    }
}

