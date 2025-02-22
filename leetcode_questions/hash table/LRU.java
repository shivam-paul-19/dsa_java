import java.util.*;

public class LRU {
    static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value, Node node) {
            this.key = key;
            this.value = value;
            prev = node;
        }
    }

    public static void printLL(Node head) {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.key + " -> ");
            temp = temp.next;
        }
        System.out.println("Null");
    }

    static class LRUCache {
        HashMap<Integer, Node> map = new HashMap<>();
        Node head = null;
        Node tail;
        int capacity;
        int fill;
        public LRUCache(int capacity) {
            this.capacity = capacity;
            fill = 0;
        }

        public void swap(Node keyNode) {
            if(head == keyNode) {
                head = head.next;
                head.prev.next = null;
                head.prev = null;
                tail.next = keyNode;
                keyNode.next = null;
                keyNode.prev = tail;
                tail = tail.next;
                return;
            }

            keyNode.prev.next = keyNode.next;
            keyNode.next.prev = keyNode.prev;
            tail.next = keyNode;
            keyNode.prev = tail;
            tail = tail.next;
            tail.next = null;
        }
        
        public int get(int key) {
            if(map.containsKey(key)) {
                int val = map.get(key).value;
                if(tail.key != key) {
                    swap(map.get(key));
                }

                return val;
            }

            return -1;
        }
        
        public void put(int key, int value) {
            if(head == null) {
                head = new Node(key, value, null);
                map.put(key, head);
                tail = head;
                fill++;
                return;
            }

            if(map.containsKey(key)) {
                map.get(key).value = value;
                if(tail.key != key) {
                    swap(map.get(key));
                }
                return;
            }

            if(fill == capacity) {
                tail.next = new Node(key, value, tail);
                tail = tail.next;

                map.remove(head.key);
                map.put(key, tail);
                head = head.next;

                head.prev.next = null;
                head.prev = null;
                return;
            }

            tail.next = new Node(key, value, tail);
            tail = tail.next;
            map.put(key, tail);
            fill++;
        }
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(3);
        obj.put(1, 1);
        obj.put(2, 2);
        obj.put(3, 3);
        obj.put(4, 4);
        System.out.println(obj.get(4));
        System.out.println(obj.get(3));
        printLL(obj.head)
        System.out.println(obj.get(2));
        printLL(obj.head);
        System.out.println(obj.get(1));
        // printLL(obj.head);
        obj.put(5, 5);
        printLL(obj.head);
        System.out.println(obj.get(1));
        printLL(obj.head);
        System.out.println(obj.get(2));
        System.out.println(obj.get(3));
        System.out.println(obj.get(4));
        System.out.println(obj.get(5));
    }
}