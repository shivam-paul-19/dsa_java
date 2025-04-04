// Leetcode Q: 1123. Lowest Common Ancestor of Deepest Leaves

public class LCAofDeepestLeaf {
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int data) {
            this.data = data;
        }

        TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        
    }

    
    TreeNode res = new TreeNode();
    int maxDepth = -1;
    private int DFS(TreeNode root, int depth) {
        if(root == null) {
            maxDepth = Math.max(depth, maxDepth);
            return depth;
        }

        int left = DFS(root.left, depth+1);
        int right = DFS(root.right, depth+1);
        if(left == right && left == maxDepth) res = root;
        return Math.max(left, right);
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        DFS(root, 0);
        maxDepth = -1;
        return res;
    }
}