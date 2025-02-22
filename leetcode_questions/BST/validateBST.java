

public class validateBST {
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

    public static boolean isValid(TreeNode root, TreeNode min, TreeNode max) {
        if(root == null) {
            return true;
        }

        if((min != null && root.val <= min.val) || (max != null && root.val >= max.val)) {
            return false;
        }

        return isValid(root.left, min, root) && isValid(root.right, root, max);
    }

    public static boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }
}
