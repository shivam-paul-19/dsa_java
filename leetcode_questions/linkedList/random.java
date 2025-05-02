// leetcode Q: 382. Linked List Random Node

import java.util.*;

public class random {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    class Solution {
        List<Integer> list;
        public Solution(ListNode head) {
            this.list = new ArrayList<>();
            ListNode temp = head;
            while(temp != null) {
                list.add(temp.val);
                temp = temp.next;
            }
        }
        
        public int getRandom() {
            int n = this.list.size();
            int idx = (int)(Math.random()*n);
            return this.list.get(idx);
        }
    }
}
