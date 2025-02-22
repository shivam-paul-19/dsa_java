// Leetcode Q:  437. Path Sum III

public class pathSumIII {
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

    static int res = 0;
    public static int pathSumUtil(TreeNode root, int sum, int target) {
        if(root == null) {
            return 0;
        }
        
        sum += root.val;
        int count = (sum == target)? 1 : 0;

        count += pathSumUtil(root.left, sum, target);
        count += pathSumUtil(root.right, sum, target);

        return count;
    }

    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return 0;
        }
        return pathSumUtil(root, 0, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }
}
