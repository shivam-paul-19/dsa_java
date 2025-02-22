// leetcode Q: 86. Partition List

import java.util.*;

public class partitionList {
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

    public static ListNode partition(ListNode head, int x) {
        ListNode root1 = new ListNode(0);
        ListNode root2 = new ListNode(0);
        ListNode temp = head;

        ListNode temp_1 = root1;
        ListNode temp_2 = root2;

        while(temp != null) {
            if(temp.val < x) {
                temp_1.next = new ListNode(temp.val);
                temp_1 = temp_1.next; 
            } else {
                temp_2.next = new ListNode(temp.val);
                temp_2 = temp_2.next;
            }

            temp = temp.next;
        }

        root1 = root1.next;
        root2 = root2.next;

        temp_2.next = null;

        if(root1 == null) {
            return root2;
        }

        temp_1.next = root2;
    
        return root1;
    }
}