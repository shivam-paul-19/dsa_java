// Leetcode Q: 106. Construct Binary Tree from Inorder and Postorder Traversal

import java.util.*;

public class constructII {
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

    public TreeNode buildTreeUtil(ArrayList<Integer> post, ArrayList<Integer> in, TreeNode root) {
        if(in.size() == 0 || post.size() == 0) {
            return null;
        }
        root = new TreeNode(post.get(post.size()-1));
        int root_idx = in.indexOf(root.val);
        ArrayList<Integer> pre_right = new ArrayList<>(post.subList(root_idx, post.size()-1));
        ArrayList<Integer> pre_left = new ArrayList<>(post.subList(0, root_idx));
        
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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        ArrayList<Integer> post = new ArrayList<>();
        ArrayList<Integer> in = new ArrayList<>();

        for(int i=0; i<postorder.length; i++) {
            post.add(i, postorder[i]);
        }

        for(int i=0; i<inorder.length; i++) {
            in.add(i, inorder[i]);
        }

        return buildTreeUtil(post, in, new TreeNode());
    }

}
