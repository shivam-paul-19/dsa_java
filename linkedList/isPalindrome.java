public class isPalindrome {
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

    public static ListNode reverse(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode prev = new ListNode();
        ListNode curr = new ListNode();
        ListNode next = new ListNode();
        prev = null;
        curr = head;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
        return head;
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode new_head = new ListNode();
        ListNode temp = head;
        ListNode temp2 = new_head;
        while(temp != null) {
            temp2.val = temp.val;
            temp = temp.next;
            temp2.next = new ListNode();
            temp2 = temp2.next;
        }

        new_head = reverse(new_head);
        new_head = new_head.next;
        temp = head;
        temp2 = new_head;
        while(temp != null) {
            if(temp.val != temp2.val) {
                return false;
            }
            temp = temp.next;
            temp2 = temp2.next;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {};
        ListNode root = makeLL(arr);
        printLL(root);
        System.out.println(isPalindrome(root));
    }
}
