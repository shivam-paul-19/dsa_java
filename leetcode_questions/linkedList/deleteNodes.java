//! pending 

import java.util.*;

public class deleteNodes {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode makeLL(int[] arr) {
        if(arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode();
        ListNode tail;
        ListNode temp = tail = head;
        for(int i=0; i<arr.length; i++) {
            temp.val = arr[i];
            temp.next = new ListNode();
            tail = temp;
            temp = temp.next;
        }
        tail.next = null;
        return head;
    }

    public static void printLL(ListNode head) {
        ListNode temp = head;
        while(temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("Null");
    }

    public static ListNode modifiedList(int[] nums, ListNode head) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<nums.length; i++) {
            list.add(nums[i]);
        }
        ListNode newhead = new ListNode(-1);
        ListNode tempNew = newhead;
        ListNode temp = head;
        while(temp != null) {
            if(!list.contains(temp.val)) {
                tempNew.next = new ListNode(temp.val);
                tempNew = tempNew.next;
            }
            temp = temp.next;
        }

        newhead = newhead.next;
        return newhead;
    }

    public static void main(String[] args) {
        int[] head = {1,2,1,2,1,2};
        int[] nums = {1};

        ListNode root = makeLL(head);
        printLL(modifiedList(nums, root));
    }
}
