// Leetcode Q: 114. Flatten Binary Tree to Linked List

public class flatten {
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


    public static void flatten(TreeNode root) {
        if(root == null) {
            return;
        }

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;

        flatten(root.right);
        flatten(temp);

        TreeNode curr = root;
        while(curr.right != null) {
            curr = curr.right;
        }

        curr.right = temp;
    }
}