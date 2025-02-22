// Leetcode Q: 230. Kth Smallest Element in a BST

import java.util.*;

public class kthSamllest {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void inOrder(TreeNode root, ArrayList<Integer> list) {
        if(root == null) {
            return;
        }

        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        inOrder(root, list);
        int ans = list.get(k-1);
        list.clear();
        return ans;
    }
}
