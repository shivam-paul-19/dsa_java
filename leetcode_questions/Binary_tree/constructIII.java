// Leetcode Q: 889. Construct Binary Tree from Preorder and Postorder Traversal

import java.util.*;

public class constructIII {
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

    public TreeNode buildTree(ArrayList<Integer> pre, ArrayList<Integer> post, TreeNode root) {
        if(pre.size() == 0 || post.size() == 0) {
            return null;
        }

        root = new TreeNode(pre.get(0));
        if(pre.size() == 1) {
            return root;
        }
        if(pre.size() > 1) {
            int root_left_idx = post.indexOf(pre.get(1));
            ArrayList<Integer> post_left = new ArrayList<>(post.subList(0, root_left_idx+1));
            ArrayList<Integer> post_right = new ArrayList<>(post.subList(root_left_idx+1, post.size()-1));

            ArrayList<Integer> pre_left = new ArrayList<>(pre.subList(1, post_left.size()+1));
            ArrayList<Integer> pre_right = new ArrayList<>(pre.subList(post_left.size()+1, pre.size()));

            root.right = buildTree(pre_right, post_right, root.right);
            root.left = buildTree(pre_left, post_left, root.left);
        } else {
            root.right = buildTree(pre, post, root.right);
            root.left = buildTree(pre, post, root.left);
        }

        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        ArrayList<Integer> post = new ArrayList<>();
        ArrayList<Integer> pre = new ArrayList<>();

        for(int i=0; i<postorder.length; i++) {
            post.add(i, postorder[i]);
        }

        for(int i=0; i<preorder.length; i++) {
            pre.add(i, preorder[i]);
        }

        return buildTree(pre, post, new TreeNode());
    }
}
