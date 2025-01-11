

public class reverseKGrp {
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

    public static ListNode reverseGrp(ListNode head, int k, ListNode further) {
        ListNode prev = further;
        ListNode curr = head;
        ListNode next = new ListNode();
        int i=0;
        while(curr != further) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
        }
        
        head = prev;
        return head;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if(k==1) {
            return head;
        }
        ListNode temp = head;
        ListNode prev = new ListNode();
        int i=0;
        ListNode tail = head;
        while(tail != null && i<k) {
            tail = tail.next;
            i++;
        }
        i=0;
        while(true) {
            if(i%k == 0) {
                temp = reverseGrp(temp, k, tail);
            }
            
            if(i==0) {
                head = temp;
            } else {
                prev.next = temp;
            }

            if(tail == null) {
                break;
            }
            prev = temp;
            temp = temp.next;
            tail = tail.next;
            i++;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1,2};
        ListNode root = makeLL(arr);
        printLL(root);
        root = reverseKGroup(root, 2);
        printLL(root);
    }
}
