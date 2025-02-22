// Leetcode Q: 111. Minimum Depth of Binary Tree

public class MinDepth {
    class TreeNode {
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

    public static int minDepth(TreeNode root) {
        if(root ==  null) {
            return 0;
        }

        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        if(root.left == null) {
            leftDepth = Integer.MAX_VALUE;
        } else if(root.right == null) {
            rightDepth = Integer.MAX_VALUE;
        }

        int depth = Math.min(leftDepth, rightDepth) + 1;
        return depth;
    }
}
