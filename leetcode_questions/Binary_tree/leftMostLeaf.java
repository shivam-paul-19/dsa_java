// Leetcode Q: 513. Find Bottom Left Tree Value

public class leftMostLeaf {
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

    static int maxLvl = 0;
    static TreeNode curr = new TreeNode(0);
    public static TreeNode findBottomLeft(TreeNode root, int level) {
        if(root.left == null && root.right == null) {
            if(level > maxLvl) {
                maxLvl = level;
                return root;
            }
        }

        if(root.left != null) {
            curr = findBottomLeft(root.left, level+1);
        }

        if(root.right != null) {
            curr = findBottomLeft(root.right, level+1);
        }

        return curr;
    }

    public int findBottomLeftValue(TreeNode root) {
        TreeNode bottomLeft = findBottomLeft(root, 0);
        return bottomLeft.val;
    }
    
}