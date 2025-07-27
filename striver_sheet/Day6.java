// Day 6: Linked list part II

import java.util.*;

public class Day6 {
    // defining ListNode class for Linked Lists
    public class ListNode {
        int val;
        ListNode next;
        ListNode child;     // for last question only
        ListNode() {}

        ListNode(int val) { 
            this.val = val; 
            this.next = null;
            this.child = null;
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
    
    // leetcode Q: 25. Reverse Nodes in k-Group
    public ListNode reverseKGroup(ListNode head, int k) {
        // first let us count the number of nodes
        int count = 0;
        ListNode temp = head;
        while(temp != null) {
            count++;
            temp = temp.next;
        }
        
        // now reverse every group of k nodes
        // but first make dummy node before head
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = dummy;
        ListNode next = dummy;
        
        while(count >= k) {
            curr = prev.next;
            next = curr.next;
            
            for(int i=1; i<k; i++) {
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = curr.next;
            }
            
            prev = curr;
            count -= k;     // decrement count by k
        }
        
        return dummy.next;  // discard the dummy node
    }
    
    // Leetcode Q: 234. Palindrome Linked List

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

    // Not in Leetcode: Flattening of LL
    
    private ListNode merge(ListNode a, ListNode b) {
        ListNode res = new ListNode(0);
        ListNode temp = res;

        while(a != null && b != null) {
            if(a.val < b.val) {
                temp.child = new ListNode(a.val);
                a = a.child;
            } else {
                temp.child = new ListNode(b.val);
                b = b.child;
            }
            temp = temp.child;
        }

        if (a != null) temp.child = a;
        else temp.child = b;

        return res.child;
    }

    public ListNode flattenLinkedList(ListNode head) {
        // all the child lists are sorted, we can perform merge to them

        // base case
        if(head == null || head.next == null) return head;

        // recursive call
        head.next = flattenLinkedList(head.next);

        // now merge them
        head = merge(head, head.next);

        return head;
    }
}