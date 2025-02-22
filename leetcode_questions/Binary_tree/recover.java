// LeetCode Q: 1028. Recover a Tree From Preorder Traversal

public class recover {
    public static class TreeNode {
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

    public static TreeNode makeTree(String traversal, TreeNode root, int level) {
        if(traversal.length() == 0) {
            return null;
        }

        if(root == null) root = new TreeNode();

        int val = 0;
        int i = 0;
        while(i<traversal.length()) {
            char c = traversal.charAt(i);
            if (c == '-') break;
            val = val*10 + (int)c-48;
            i++;
        }
        if(traversal.length() > i) traversal = traversal.substring(i);
        root.val = val;

        String left = new String("");
        String right = new String("");
        int depth = 0;

        for(i=0; i<traversal.length(); i++) {
            char c = traversal.charAt(i);
            if(c == '-') depth++;
            else {
                if(depth == level) {
                    left = traversal.substring(i);
                    break;
                }
                depth = 0;
            }
        }

        depth = 0;
        for(i=0; i<left.length(); i++) {
            char c = left.charAt(i);
            if(c == '-') depth++;
            else {
                if(depth == level) {
                    right = left.substring(i);
                    left = left.substring(0, i-depth);
                    break;
                }
                depth = 0;
            }
        }
        
        root.left = makeTree(left, root.left, level+1);
        root.right = makeTree(right, root.right, level+1);

        return root;
    }

    public static TreeNode recoverFromPreorder(String traversal) {
        TreeNode root = new TreeNode();
        root = makeTree(traversal, root, 1);
        return root;
    }
}
