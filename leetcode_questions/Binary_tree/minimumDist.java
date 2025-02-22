// Leetcode Q: 783. Minimum Distance Between BST Nodes

import java.util.*;

public class minimumDist {
    public static class TreeNode {
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

    public static TreeNode insertBST(TreeNode root, int val) {
        if(root == null) {
            root = new TreeNode(val);
            return root;
        }

        if(root.val < val) {
            root.right = insertBST(root.right, val);
        }

        if(root.val > val) {
            root.left = insertBST(root.left, val);
        }

        return root;
    }

    public static void inorder(TreeNode root, ArrayList<Integer> list) {
        if(root == null) {
            return;
        }

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public static int minDiffInBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        inorder(root, list);
        for(int i=1; i<list.size(); i++) {
            min = Math.min(min, (list.get(i) - list.get(i-1)));
        }

        list.clear();
        return min;
    }

    public static void main(String[] args) {
        int[] nodes = {5, 1, 3, 4, 2, 7};
        TreeNode root = null;
        for(int i=0; i<nodes.length; i++) {
            root = insertBST(root, nodes[i]);
        }

        System.out.println(minDiffInBST(root));
    }
}
