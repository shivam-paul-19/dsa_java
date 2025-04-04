public class LinkedList {

    // Making a node class
    static class Node {
        int val;
        Node next;

        Node() {
            this.val = 0;
            this.next = null;
        }

        Node(int val) {
            this.val = val;
            this.next = null;
        }

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    // making a Linked List with an array
    public static Node makeLL(int[] arr) {
        if(arr.length == 0) return null;
        
        Node head = new Node(arr[0]);
        Node temp = head;

        for(int i=1; i<arr.length; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }

        temp.next = null;
        return head;
    }

    // traversing a Linked List
    public static void printLL(Node head) {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    // Reversing a Linked List
    public static Node reverse(Node head) {
        if (head == null) return null;

        Node curr = head;
        Node prev = null;
        Node next;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
        return head;
    }

    // Inserting a element in a postion
    public static Node add(Node head, int val, int index) {
        if(index == 0) {
            return new Node(val, head);
        }

        int pos = 1;
        Node temp = head;

        while(temp != null && pos < index) {
            temp = temp.next;
            pos++;
        }

        if(temp == null) return head;

        temp.next = new Node(val, temp.next);
        return head;
    }

    // Deleting a node
    public static Node delete(Node head, int index) {
        if(index == 0) {
            return head.next;
        }
        int pos = 1;
        Node temp = head;

        while(temp != null && pos < index) {
            temp = temp.next;
            pos++;
        }

        if(temp.next == null) return head;

        temp.next = temp.next.next;
        return head;
    }

    // Finding mid of a linked list
    public static int findMid(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.val;
    }

    // Searching in a Linked List
    public static int search(Node head, int key) {
        Node temp = head;
        int index = 0;
        
        while(temp != null) {
            if(temp.val == key) return index;
            temp = temp.next;
            index++;
        }

        return -1;
    }

    // Detecting cycle in a Linked List (Flyod's cycle detection)
    public static boolean detectCycle(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }

        return false;
    }
}