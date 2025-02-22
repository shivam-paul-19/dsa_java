// Leetcode Q: 1372. Longest ZigZag Path in a Binary Tree

public class longestZigZag {
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

    private static int longestZigZagUtil(TreeNode root, boolean isLeft, int level) {
        if(root == null) {
            return level;
        }

        if(isLeft) {
            return Math.max(longestZigZagUtil(root.right, false, level+1), longestZigZagUtil(root.left, true, 0));
        } else {
            return Math.max(longestZigZagUtil(root.left, true, level+1), longestZigZagUtil(root.right, false, 0));
        }
    }

    public static int longestZigZag(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = longestZigZagUtil(root, true, 0);
        int right = longestZigZagUtil(root, false, 0);

        return Math.max(left, right);
    }
}
