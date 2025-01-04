// Leetcode: 147. Insertion Sort List

import java.util.*;

public class insertionSort {
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

    public static int[] makeArr(ListNode head) {
        int count = 0;
        ListNode temp = head;
        while(temp != null) {
            count++;
            temp = temp.next;
        }

        int[] arr = new int[count];
        temp = head;
        int i = 0;
        while(temp != null) {
            arr[i] = temp.val;
            temp = temp.next;
            i++;
        }

        return arr;
    }

    public static ListNode insertionSortList(ListNode head) {
        if(head.next == null) {
            return head;
        }

        int[] arr = makeArr(head);

        for(int i=1; i<arr.length; i++) {
            int c = arr[i];
            int p = i-1;
            while(p>=0 && arr[p]>c) {
                arr[p+1] = arr[p--];
            }

            arr[p+1] = c;
        }

        head = makeLL(arr);
        return head;
    }
}
