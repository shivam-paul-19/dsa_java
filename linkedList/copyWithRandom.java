import java.util.HashMap;

public class copyWithRandom {
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node makeLL(int[] arr) {
        if(arr.length == 0) {
            return null;
        }
        Node head = new Node(0);
        Node tail;
        Node temp = tail = head;
        for(int i=0; i<arr.length; i++) {
            temp.val = arr[i];
            temp.next = new Node(0);
            tail = temp;
            temp = temp.next;
        }
        tail.next = null;
        return head;
    }

    public static void printLL(Node head) {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("Null");
        temp = head;
        while (temp != null) {
            System.out.println(temp.val + " => " + ((temp.random != null)? temp.random.val : temp.random));
            temp = temp.next;
        }
    }

    public static Node copyRandomList(Node head) {
        if(head == null) {
            return head;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node resHead = new Node(head.val);
        Node temp = resHead;
        Node tempOG = head;
        map.put(tempOG, temp);
        while(tempOG.next != null) {
            temp.next = new Node(tempOG.next.val);
            map.put(tempOG.next, temp.next);
            temp = temp.next;
            tempOG = tempOG.next;
        }

        temp = resHead;
        tempOG = head;
        resHead.random = (head.random == null)? null : map.get(head.random);
        while(tempOG.next != null) {
            temp.next.random = (tempOG.next.random == null)? null : map.get(tempOG.next.random);
            temp = temp.next;
            tempOG = tempOG.next;
        }

        return resHead;
    }

    public static void main(String[] args) {
        int[] arr = {7,13,11,10,1};
        Node root = makeLL(arr);
        root.random = null;
        root.next.random = root;
        root.next.next.random = root.next.next.next.next;
        root.next.next.next.random = root.next.next;
        root.next.next.next.next.random = root;
        int[] arr2 = {};
        Node newroot = makeLL(arr2);
        // printLL(newroot);
        printLL(copyRandomList(newroot));
    }
}
