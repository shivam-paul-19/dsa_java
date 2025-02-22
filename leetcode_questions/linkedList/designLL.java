// Leetcode Q: 707. Design Linked List

public class designLL {
    public static class MyLinkedList {
        private static class Node {
            int val;
            Node next;

            public Node() {}

            public Node(int val, Node next) {
                this.val = val;
                this.next = null;
            }
        }

        Node head = null;

        public MyLinkedList() { }
        
        public int get(int index) {
            Node temp = head;
            int i=0;
            while(temp != null) {
                if(i == index) {
                    return temp.val;
                }
                temp = temp.next;
                i++;
            }

            return -1;
        }
        
        public void addAtHead(int val) {
            if(head == null) {
                head = new Node();
                head.val = val;
                head.next = null;
                return;
            }

            Node temp = new Node();
            temp.val = val;
            temp.next = head;
            head = temp;
        }
        
        public void addAtTail(int val) {
            if(head == null) {
                head = new Node(); 
                head.val = val;
                head.next = null;
                return;
            }
            Node temp = head;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(val, null);
        }
        
        public void addAtIndex(int index, int val) {
            if(index == 0) {
                addAtHead(val);
                return;
            }

            if(head == null && index > 0) {
                return;
            }

            Node temp = head;
            int i = 0;
            while(temp.next != null && i<index-1) {
                temp = temp.next;
                i++;
            }

            if(i == index-1) {
                Node newNode = new Node();
                newNode.val = val;
                newNode.next = temp.next;
                temp.next = newNode;
            }
        }
        
        public void deleteAtIndex(int index) {
            if(head == null) {
                return;
            }

            if(index == 0) {
                head = head.next;
                return;
            }

            Node temp = head;
            int i = 0;
            while(temp.next != null && i<index-1) {
                temp = temp.next;
                i++;
            }

            if(temp.next != null) {
                temp.next = temp.next.next;
            }
        }

        public void printLL() {
            Node temp = head;
            while(temp != null) {
                System.out.print(temp.val + " -> ");
                temp = temp.next;
            }
            System.out.println("null");
        }
    }
}
