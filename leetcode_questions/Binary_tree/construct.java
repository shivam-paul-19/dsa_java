// Leetcode Q: 105. Construct Binary Tree from Preorder and Inorder Traversal

import java.util.*;

public class construct {
    static class TreeNode {
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

    public TreeNode buildTreeUtil(ArrayList<Integer> pre, ArrayList<Integer> in, TreeNode root) {
        if(in.size() == 0 || pre.size() == 0) {
            return null;
        }
        root = new TreeNode(pre.get(0));
        ArrayList<Integer> pre_left = new ArrayList<>(pre.subList(1, pre.size()));
        int root_idx = in.indexOf(root.val);
        ArrayList<Integer> pre_right = new ArrayList<>(pre.subList(root_idx+1, pre.size()));

        ArrayList<Integer> in_left = new ArrayList<>();
        ArrayList<Integer> in_right = new ArrayList<>();

        if(root_idx >= 0) {
            in_left = new ArrayList<>(in.subList(0, root_idx));
            root.left = buildTreeUtil(pre_left, in_left, root.left);
        } 

        if(root_idx < in.size()) {
            in_right = new ArrayList<>(in.subList(root_idx+1, in.size()));
            root.right = buildTreeUtil(pre_right, in_right, root.right);
        } 

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> in = new ArrayList<>();

        for(int i=0; i<preorder.length; i++) {
            pre.add(i, preorder[i]);
        }

        for(int i=0; i<inorder.length; i++) {
            in.add(i, inorder[i]);
        }

        return buildTreeUtil(pre, in, new TreeNode());
    }
}
