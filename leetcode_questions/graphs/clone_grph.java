// Leetcode Q: 133. Clone Graph

import java.util.*;

public class clone_grph {
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private static void copyToMap(Node root, HashMap<Node, Node> map, HashSet<Node> isVis) {
        Node newNode = new Node(root.val);
        map.put(root, newNode);
        isVis.add(root);

        for(Node n: root.neighbors) {
            if(!isVis.contains(n)) {
                copyToMap(n, map, isVis);
            }
        }
    }

    private static Node copyToGraph(Node root, Node newRoot, HashMap<Node, Node> map, HashSet<Node> isVis) {
        newRoot = map.get(root);
        isVis.add(root);

        for(Node n: root.neighbors) {
            newRoot.neighbors.add(map.get(n));
            if(!isVis.contains(n)) {
                Node neighbour = map.get(n);
                neighbour = copyToGraph(n, map.get(n), map, isVis);
            }
        }

        return newRoot;
    }

    public static Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }

        if(node.neighbors.size() == 0) {
            return new Node(node.val);
        }

        HashMap<Node, Node> map = new HashMap<>();
        HashSet<Node> isVis = new HashSet<>();

        copyToMap(node, map, isVis);
        isVis.clear();

        Node newRoot = new Node();
        newRoot = copyToGraph(node, newRoot, map, isVis);

        return newRoot;
    }

    public static void depthFirstSearchUtil(Node curr, HashSet<Node> isVis) {
        System.out.print(curr.val + " ");
        isVis.add(curr);

        for(Node n: curr.neighbors) {
            if(!isVis.contains(n)) {
                depthFirstSearchUtil(n, isVis);
            }
        }
    }

    public static void depthFirstSearch(Node root) {
        HashSet<Node> isVis = new HashSet<>();
        depthFirstSearchUtil(root, isVis);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);

        root.neighbors.add(two);
        root.neighbors.add(four);

        two.neighbors.add(three);
        two.neighbors.add(root);

        three.neighbors.add(two);
        three.neighbors.add(four);

        four.neighbors.add(root);
        four.neighbors.add(three);

        depthFirstSearch(cloneGraph(root));
    }
}
