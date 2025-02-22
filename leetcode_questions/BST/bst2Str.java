// Leetcode Q: 606. Construct String from Binary Tree

public class bst2Str {
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

    public static void makeStr(TreeNode root, StringBuilder sb) {
        if(root == null) {
            return;
        }

        sb.append(root.val);
        if(root.left == null) {
            if(root.right != null) {
                sb.append("()");
            }
        } else {
            sb.append('(');
            makeStr(root.left, sb);
        }
        if(root.right != null) {
            sb.append('(');
            makeStr(root.right, sb);
        }
        sb.append(')');
    }

    public static String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        makeStr(root, sb);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
