// Leetcode Q: 160. Intersection of Two Linked Lists

import java.util.*;

public class intersection {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int x) {
            val = x;
            next = null;
        }
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

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode temp = headA;
        while(temp != null) {
            list.add(temp);
            temp = temp.next;
        }

        temp = headB;
        while(temp != null) {
            if(list.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }

        return null;
    }
}
