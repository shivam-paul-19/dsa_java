// Day 21: Binary search tree part II

import java.util.ArrayList;
import java.util.Stack;

public class Day21 {
    // definition of TreeNode 
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

    // not in leetcode: Floor in a BST
    private int findFloorutil(TreeNode root, int key, long prevKey) {
        // base case (not found the exact key)
        if(root == null) {
            if(prevKey == Long.MAX_VALUE) return -1;    // we didn't find any floor
            return (int)prevKey;
        }
        
        // key found
        if(root.val == key) {
            return root.val;
        }
        
        if(root.val > key) {
            // in this case, the floor will be left subtree
            return findFloorutil(root.left, key, prevKey);
        } else {
            // in this case, the floor will be in the right subtree
            // we are updating prevFloor, as this is potential floor value
            return findFloorutil(root.right, key, root.val);
        }
    }
    
    public int findFloor(TreeNode root, int key) {
        // floor -> greatest data lesser than or equal to the key value
        return findFloorutil(root, key, Long.MAX_VALUE);
    }

    // not in LeetCode: Ciel in a BST
    private int findCeilUtil(TreeNode root, int key, long prevCeil) {
        // base case, ceil value not found
        if(root == null) {
            if(prevCeil == Long.MIN_VALUE) return -1;
            else return (int)prevCeil;
        }

        // key found
        if(root.val == key) return root.val;

        if(root.val > key) {
            // in this case, the floor will be left subtree
            // we are updating prevFloor, as this is potential ciel value
            return findCeilUtil(root.left, key, root.val);
        } else {
            // in this case, the floor will be in the right subtree
            return findCeilUtil(root.right, key, prevCeil);
        }
    }

    public int findCeil(TreeNode root, int key) {
        // Ceil -> smallest data larger than or equal to the key value
        return findCeilUtil(root, key, Long.MIN_VALUE);
    }

    // Leetcode Q: 230. Kth Smallest Element in a BST
    int seq = 0;
    private int traverseWithSeq(TreeNode root, int k) {
        // base case, nothing found
        if(root == null) return -1;

        // traverse the left part
        int res = -1;
        if(root.left != null) {
            res = traverseWithSeq(root.left, k);
        }

        // if left subtree gave us an answer then no need to go further
        if(res != -1) return res;

        // check for the root
        seq++;
        if(seq == k) return root.val;

        // now check for right subtree
        if(root.right != null) {
            return traverseWithSeq(root.right, k);
        }

        return -1;  // nothing is found
    }

    public int kthSmallest(TreeNode root, int k) {
        return traverseWithSeq(root, k);
    }

    // Leetcode Q: 173. Binary Search Tree Iterator
    class BSTIterator {
        // initialise a stack
        Stack<TreeNode> s;

        public BSTIterator(TreeNode root) {
            this.s = new Stack<>();
            // push all the left most nodes
            pushLefts(root);
        }

        private void pushLefts(TreeNode root) {
            TreeNode temp = root;
            while(temp != null) {
                s.push(temp);
                temp = temp.left;
            }
        }

        public int next() {
            // get the top of the stack
            TreeNode curr = s.pop();
            // if right node is there, then push it along with it's left most ones
            pushLefts(curr.right);

            return curr.val;
        }
        
        public boolean hasNext() {
            // if stack is empty then no next is there
            return !s.isEmpty();
        }
    }

    // Leetcode Q: 1373. Maximum Sum BST in Binary Tree

    // we need a helper class
    private class Info {
        long min;
        long max;
        int sum;

        Info(long min, long max, int sum) {
            this.max = max;
            this.min = min;
            this.sum = sum;
        } 
    }

    // helper recursive function 
    private Info maxSumBSTUtil(TreeNode root) {
        // base case
        if(root == null) {
            return new Info(Long.MAX_VALUE, Long.MIN_VALUE, 0);
        }

        // call for left and right subtrees
        Info left = maxSumBSTUtil(root.left);
        Info right = maxSumBSTUtil(root.right);

        // if the node is satisfying BST rules
        if(root.val > left.max && root.val < right.min) {
            int currSum = left.sum + right.sum + root.val;
            long currMin = Math.min(root.val, left.min);
            long currMax = Math.max(root.val, right.max);

            return new Info(currMin, currMax, currSum);
        }

        // if not satisfying the rules
        int currSum = Math.max(left.sum, right.sum);
        return new Info(Long.MIN_VALUE, Long.MAX_VALUE, currSum);
        
    }

    public int maxSumBST(TreeNode root) {
        Info info = maxSumBSTUtil(root);
        return info.sum;
    }
}
