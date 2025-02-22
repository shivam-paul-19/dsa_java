// Leetcode Q: 1261. Find Elements in a Contaminated Binary Tree

import java.util.*;

public class contaminatedTree {
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

    class FindElements {
        HashSet<Integer> set = new HashSet<>();

        public FindElements(TreeNode root) {
            root = recoverTree(root, 0);
        }

        public TreeNode recoverTree(TreeNode root, int el) {
            if(root == null) {
                return null;
            }

            root.val = el;
            set.add(el);
            if(root.left != null) {
                root.left = recoverTree(root.left, el*2+1);
            }
            if(root.right != null) {
                root.right = recoverTree(root.right, el*2+2);
            }

            return root;
        }
        
        public boolean find(int target) {
            return set.contains(target);
        }
    }
}
