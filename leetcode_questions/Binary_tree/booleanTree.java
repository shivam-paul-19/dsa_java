// Leetcode Q: 2331. Evaluate Boolean Binary Tree

public class booleanTree {
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

    public boolean evaluateTree(TreeNode root) {
        if(root.left == null && root.right == null) {
            return (root.val == 1)? true : false;
        }

        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);

        boolean ans = false;
        if(root.val == 2) {
            ans = left || right;
        } else if (root.val == 3) {
            ans = left && right;
        }

        return ans;
    }
}
