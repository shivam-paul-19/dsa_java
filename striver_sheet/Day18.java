// Day 18: Binary tree part II

import java.util.*;
import java.util.LinkedList;

public class Day18 {
    // Definition of TreeNode 
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

    // Leetcode Q: 102. Binary Tree Level Order Traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        // edge case
        if(root == null) {
            return new ArrayList<>();
        }
        
        // in BFS, we use a Queue
        Queue<TreeNode> q = new LinkedList<>();

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> level = new ArrayList<>();

        // add root
        q.offer(root);
        q.offer(null);

        // perform BFS
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if(curr == null) {
                // one level is completed
                ans.add(new ArrayList<>(level));
                level.clear();

                if(q.isEmpty()) {
                    // traversal done
                    break;
                } else {
                    // add null
                    q.offer(null);
                }
            } else {
                // add the node value
                level.add(curr.val);

                // add children
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
        }

        return ans;
    }
}
