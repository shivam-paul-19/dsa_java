// Day 20: Binary Search Tree

import java.util.*;
import java.util.LinkedList;

import javax.swing.tree.TreeNode;

public class Day20 {
    // definition of Node
    class Node {
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
    };

    // defintion of treenode
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Leetcode Q: 116. Populating Next Right Pointers in Each Node
    public Node connect(Node root) {
        // edge case
        if(root == null) return null;

        // this question will be done using BFS, For that we will need a Queue
        Queue<Node> q = new LinkedList<>();

        // add the root in it
        q.offer(root);
        q.offer(null);  // null to mark end of the level

        // make a prev node
        Node prev = new Node(-1);

        while(!q.isEmpty()) {
            // get the curr node (front of queue)
            Node curr = q.poll();

            if(curr == null) {
                // one level is ending 
                if(q.isEmpty()) {
                    // then this was the last level
                    prev.next = curr;
                    break;
                } else {
                    // further levels are there
                    q.offer(null);
                    prev.next = curr;
                    prev = new Node(-1);
                }
            } else {
                prev.next = curr;
                prev = curr;

                // add chilren node to queue
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
        }

        return root;
    }

    // Leetcode Q: 700. Search in a binary search tree
    public TreeNode searchBST(TreeNode root, int val) {
        // base case (reached end, or reached key)
        if(root == null || root.val == val) return root;

        // if the key is smaller than root value, it must be in left subtree
        if(val < root.val) return searchBST(root.left, val);
        // else, the key is bigger than root value, it must be in right subtree
        else return searchBST(root.right, val);
    }

    // Leetcode Q: 108. Convert Sorted Array to Binary Search Tree
    private TreeNode sortedArrayToBST_util(TreeNode root, int[] nums, int start, int end) {
        // base case
        if(start > end) return null;

        // add value to root
        int mid = start + (end-start)/2;
        root = new TreeNode(nums[mid]);

        // call for children nodes
        root.left = sortedArrayToBST_util(root.left, nums, start, mid-1);
        root.right = sortedArrayToBST_util(root.right, nums, mid+1, end);

        return root;
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST_util(new TreeNode(), nums, 0, nums.length-1);
    }

    // Leetcode Q: 1008. Construct Binary Search Tree from Preorder Traversal

    // helper function to construct tree
    static int idx;    // a global variable
    private TreeNode bstFromPreorderUtil(int[] preorder, int upperbound) {
        // base case
        if(idx >= preorder.length || preorder[idx] > upperbound) return null;

        // if the idx satisfies the condition then make it a node
        TreeNode root = new TreeNode(preorder[idx++]);

        // call for children
        root.left = bstFromPreorderUtil(preorder, root.val);
        root.right = bstFromPreorderUtil(preorder, upperbound);

        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        idx = 0;
        return bstFromPreorderUtil(preorder, Integer.MAX_VALUE);
    }

    // Leetcode Q: 98. Validate Binary Search Tree
    private boolean isValid(TreeNode root, long min, long max) {
        if(root == null) return true;   // reached the end

        // voilating the BST rules
        if(root.val <= min || root.val >= max) return false;

        // both subtree should follow the same rule
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Leetcode Q: 235. Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if(root == null) return null;

        if(root.val < p.val && root.val < q.val) {
            // the LCA is in right subtree
            return lowestCommonAncestor(root.right, p, q);
        } else if(root.val > p.val && root.val > q.val) {
            // The LCA is in left subtree
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // got the LCA, as it is first node which is greater than one, and smaller than other
            return root;
        }
    }

    // Not in Leetcode: Inorder successor and predecessor in BST
    List<Integer> succPredBST(TreeNode root, int key) {
        List<Integer> ans = new ArrayList<>();

        // first find the node with value key
        TreeNode node = searchBST(root, key);

        // for predecessor, we need the right-most node of left subtree
        TreeNode temp = node;
        if(temp.left != null) {
            temp = temp.left;
            while(temp.right != null) temp = temp.right;
            ans.add(temp.val);
        } else {
            // if no left subtree is there
            ans.add(-1);
        }

        // for successor, we need the left-most noe of right subtree
        temp = node;
        if(temp.right != null) {
            temp = temp.right;
            while(temp.left != null) temp = temp.left;
            ans.add(temp.val);
        } else {
            // if no right subtree is there
            ans.add(-1);
        }

        return ans;
    }
}
