// Leetcode Q: 113. Path Sum II

import java.util.*;

public class pathsumII {
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

    static List<List<Integer>> ansList = new ArrayList<>();

    private static void pathSumUtil(TreeNode root, int restSum, List<Integer> ans) {
        if(root == null) {
            return;
        }

        ans.add(root.val);
        if((root.left == null && root.right == null) && root.val == restSum) {
            ansList.add(new ArrayList<>(ans));
        } else {
            pathSumUtil(root.left, restSum-root.val, ans);
            pathSumUtil(root.right, restSum-root.val, ans);
        }

        if(!ans.isEmpty()){
            ans.remove(ans.size()-1);
        }
    }
}
