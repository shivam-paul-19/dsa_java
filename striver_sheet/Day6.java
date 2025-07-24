// Day 6: Linked list part II

import java.util.*;

public class Day6 {
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

    // Leetcode Q: 160. Intersection of Two Linked Lists
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // the two linked Lists have different ending but may be they have same ending
        // we can add the whole headA list into a set

        HashSet<ListNode> set = new HashSet<>();
        ListNode temp = headA;
        while(temp != null) {
            set.add(temp);
            temp = temp.next;
        }

        // now we will traverse through headB, and find the first common node
        temp = headB;
        while(temp != null) {
            if(set.contains(temp)) return temp;
            temp = temp.next;
        }

        return null;    // if no intersection is there
    }

    // Leetcode Q: 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        // we can use slow and fast pointer here.
        // there is a cycle then for sure both the pointers will meet
        // if they don't meet then there is no cycle (fast will reach null in that case)

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) return true;
        }

        return false;   // fast reached end, no cycle
    }

    // Leetcode Q: 

    // helper function of reverse
    private ListNode reverse(ListNode head) {
        if(head == null) return head;   // edge case

        // for reversing a LL, just point the next pointer towards the previous one
        ListNode prev = null;   // to track the previous
        ListNode curr = head;   // to track the current
        ListNode next = new ListNode();     // to track the next

        while(curr != null) {
            next = curr.next;   // store the next
            curr.next = prev;   // point the next pointer to the prev
            prev = curr;        // move the prev pointer
            curr = next;        // move the curr point
        }

        head = prev;    // make prev the new head
        return head;
    }

    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;  // edge case

        // just make a copy of the Linked list and reverse that copy
        // if the reversed copy is same as original then it's a palindrome

        ListNode copy = new ListNode();
        ListNode copyTemp = copy;
        ListNode temp = head;
        while(temp != null) {
            copyTemp.val = temp.val;
            copyTemp.next = new ListNode();
            copyTemp = copyTemp.next;
            temp = temp.next;
        }

        // reverse this copy now
        copy = reverse(copy);
        copy = copy.next;

        // now check if they are same or not
        temp = head;
        copyTemp = copy;
        while(temp != null) {
            if(temp.val != copyTemp.val) return false;
            temp = temp.next;
            copyTemp = copyTemp.next;
        } 

        return true;
    }

    // Leetcode Q: 
    public ListNode detectCycle(ListNode head) {
        // first let us check if there's any cycle in it
        ListNode slow = head;
        ListNode fast = head;
        boolean isCyle = false;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                isCyle = true;
                break;
            }
        }

        // now if there is a cycle then moce the slow pointer to the head and keep fast where it was
        if(isCyle) {
            slow = head;
            while(slow != fast) {
                // move them one by one, they will meet at the cycle point only
                slow = slow.next;
                fast = fast.next;
            }

            return slow;
        }

        return null;
    }
}
