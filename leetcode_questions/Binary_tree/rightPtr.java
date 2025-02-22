// Leetcode Q: 116. Populating Next Right Pointers in Each Node

import java.util.*;

public class rightPtr {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    static int idx = -1;
    public static Node buildTree(int[] nodes) {
        idx++;
        if(nodes[idx] == -1) {
            return null;
        }

        Node newNode = new Node(nodes[idx]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);

        return newNode;
    }

    public static void connect(Node root) {
        if(root == null) {
            return null;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        Node temp = new Node(-1);
        while(!q.isEmpty()) {
            Node curr = q.remove();
            if(curr == null) {
                if(q.isEmpty()) {
                    temp.next = null;
                    System.out.println("NULL");
                    break;
                } else {
                    temp.next = null;
                    temp = q.peek();
                    System.out.println("NULL");
                    q.add(null);
                }
            } else {
                System.out.print(curr.val + " -> ");
                temp.next = curr;
                temp = temp.next;
                if(curr.left != null) {
                    q.add(curr.left);
                }
                if(curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        Node tree = new Node();
        tree = buildTree(nodes);
        connect(tree);
    }
}