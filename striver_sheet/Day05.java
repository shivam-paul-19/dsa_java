// Day 5: Linked Lists

public class Day05 {
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

    // Leetcode Q: 206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
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

    // Leetcode Q: 876. Middle of the Linked List
    public ListNode middleNode(ListNode head) {
        // we will use the slow and fast pointer approach in it
        // slow pointer: moves one step
        // fast pointer: moves two step

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;       // moving one step
            fast = fast.next.next;  // moving two steps
        }

        // when the fast pointer stops, the slow pointer is always at the middle as it was travesing in the half speed of the fast pointer
        return slow;
    }

    // Leetcode Q: 21. Merge Two Sorted Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // as the two lists are sorted, we can merge them just like we do in merge sort
        ListNode temp1 = list1;
        ListNode temp2 = list2;
        ListNode merged = new ListNode(0);
        ListNode temp = merged;    // the merged linkedList

        while(temp1 != null && temp2 != null) {
            if(temp1.val < temp2.val) {
                temp.next = new ListNode(temp1.val);
                temp1 = temp1.next;
            } else {
                temp.next = new ListNode(temp2.val);
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        
        // if any nodes are left
        while(temp1 != null) {
            temp.next = new ListNode(temp1.val);
            temp1 = temp1.next;
            temp = temp.next;
        }
        while(temp2 != null) {
            temp.next = new ListNode(temp2.val);
            temp2 = temp2.next;
            temp = temp.next;
        }

        // return the second node as head as first one is 0
        return merged.next;
    }

    // Leetcode Q: 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // if we want to delete the nth node then we should be at (n-1)th node
        // maintaining two pointer can be helpful
        // when the first pointer reaches (n+1) from the starting, then start the second pointer

        ListNode newHead = new ListNode(0, head);
        ListNode first = head;
        ListNode second = newHead;      // staying one step back before head
        int node = 1;

        while(first != null) {
            if(node > n) {
                second = second.next;   // now you can move the second pointer
            }
            first = first.next;
            node++;
        }

        // now when first is at null, so second is at (n-1)th node from back
        second.next = second.next.next; 
        return newHead.next;    // as first node was a dummy
    }

    // Leetcode Q: 2. Add Two Numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // the numbers are in reversed order, which is an advantage
        // so will add one by one and keep track of carry

        int carry = 0;
        ListNode answer = new ListNode(0);
        ListNode ans = answer;
        ListNode temp1 = l1;
        ListNode temp2 = l2;

        while(temp1 != null && temp2 != null) {
            int sum = temp1.val + temp2.val + carry;
            carry = sum/10;
            ans.next = new ListNode(sum%10);
            temp1 = temp1.next;
            temp2 = temp2.next;
            ans = ans.next;
        }

        // if numbers are remaining in any of them
        while(temp1 != null) {
            int sum = temp1.val + carry;
            carry = sum/10;
            ans.next = new ListNode(sum%10);
            temp1 = temp1.next;
            ans = ans.next;
        }

        while(temp2 != null) {
            int sum = temp2.val + carry;
            carry = sum/10;
            ans.next = new ListNode(sum%10);
            temp2 = temp2.next;
            ans = ans.next;
        }

        // in case the carry is left 
        if(carry != 0) {
            ans.next = new ListNode(carry);
        }
        return answer.next;
    }

    // Leetcode Q: 237. Delete Node in a Linked List
    public void deleteNode(ListNode node) {
        // as we have the node in parameter then, it is the easiest problem to do
        
        // copy the value of next node
        node.val = node.next.val;
        // delete the next node
        node.next = node.next.next;

        // the constraints are saying the deleted node is never the tail, if it could be a tail then we would add extra code to handle that too
    }
}
