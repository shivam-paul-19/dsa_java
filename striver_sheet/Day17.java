// Day 17: Binary tree

import java.util.*;
import java.util.LinkedList;

public class Day17 {
    // Definition of TreeNode 
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Leetcode Q: 94. Binary Tree Inorder Traversal
    List<Integer> in = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) {
            return in;
        }
        inorderTraversal(root.left);
        in.add(root.val);
        inorderTraversal(root.right);

        return in;
    }

    // Leetcode Q: 144. Binary Tree Preorder Traversal
    List<Integer> pre = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) {
            return pre;
        }
        pre.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        
        return pre;
    }
    
    // Leetcode Q: 145. Binary Tree Postorder Traversal
    List<Integer> post = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) {
            return post;
        }
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        post.add(root.val);

        return post;
    }

    // Not in leetcode: Leftview of Tree
    
    private void leftSideViewUtil(TreeNode root, int level, List<Integer> list) {
        // base case
        if(root == null) return;
        
        // if the size of list and level is same then it is the first node of this level
        // add the node
        if(level == list.size()) {
            list.add(root.val);
        }
        
        // recursive call for child
        leftSideViewUtil(root.left, level+1, list);
        leftSideViewUtil(root.right, level+1, list);
    }
    
    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        leftSideViewUtil(root, 0, ans);
        return ans;
    }
    
    // Not in leetcode: Rightview of Tree
    
    // helper function to traverse tree
    private void rightSideViewUtil(TreeNode root, int level, List<Integer> list) {
        // base case
        if(root == null) return;

        // if the size of list and level is same then it is the first node of this level
        // add the node
        if(level == list.size()) {
            list.add(root.val);
        }

        // recursive call for child
        rightSideViewUtil(root.right, level+1, list);
        rightSideViewUtil(root.left, level+1, list);
    }
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        rightSideViewUtil(root, 0, ans);
        return ans;
    }

    // Not in Leetcode: Top view of binary tree
    
    // helper fn to populate the map
    private void populateMap(TreeNode root, int level, TreeMap<Integer, Integer> map) {
        // base case
        if(root == null) {
            return;
        }

        // if this level is already in the map, then ignore
        // other wise put in the map
        if(!map.containsKey(level)) {
            map.put(level, root.val);
        }

        // call for children nodes
        populateMap(root.left, level-1, map);   // left is one level lower than root (vertically)
        populateMap(root.right, level+1, map);  // right is one level greater than root (vertically)
    }

    public List<Integer> topView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        // make a map to store vertical level nodes (top nodes)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        populateMap(root, 0, map);
        // traverse the map
        for(int key: map.keySet()) {
            ans.add(map.get(key));
        }

        return ans;
    }

    // Not in Leetcode: Bottom View of tree

    // helper fn to populate the map
    private void populateMapBottom(TreeNode root, int level, Map<Integer, Integer> map) {
        // base case
        if(root == null) {
            return;
        }

        // we don't care if the level was there or not, simply add
        // even if it was there, we will have to override the value
        map.put(level, root.val);

        // call for childer nodes
        populateMapBottom(root.left, level-1, map);
        populateMapBottom(root.right, level+1, map);
    }

    public List<Integer> bottomView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        // make a map to store vertical level nodes (top nodes)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        populateMapBottom(root, 0, map);
        // traverse the map
        for(int key: map.keySet()) {
            ans.add(map.get(key));
        }

        return ans;
    }

    // Not in Leetcode: Print root to node path in BT

    // helper recursive function to get paths
    private void getPaths(TreeNode root, List<Integer> path, List<List<Integer>> ans) {
        // base case
        if(root == null) {
            return;
        }

        // add the value
        path.add(root.val);

        if(root.left == null && root.right == null) {
            // we have reached the leaf node so one path is completed
            ans.add(new ArrayList<>(path));
        } else {
            // traverse the left sub tree
            getPaths(root.left, path, ans);
            // traverse the right sub tree
            getPaths(root.right, path, ans);
        }

        // remove the last added node
        path.removeLast();
    }

    public List<List<Integer>> allRootToLeaf(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        getPaths(root, new ArrayList<>(), ans);
        return ans;
    }

    // Not in Leetcode: Pre, Post, Inorder in one traversal
    // pair class to maintain nodes and state
    private class Pair2 {
        TreeNode root;
        int state;

        Pair2(TreeNode root) {
            this.root = root;
            this.state = 1;
        }

        // to update the state
        void updateState() {
            this.state++;
        }
    }

    List<List<Integer>> treeTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        // stack to store nodes and state
        Stack<Pair2> s = new Stack<>();

        // add the root
        if(root != null) s.push(new Pair2(root));

        // iterate
        while(!s.isEmpty()) {
            // peek the top element
            Pair2 curr = s.peek();
            int state = curr.state;

            if(state == 1) {
                // add the value to pre order
                pre.add(curr.root.val);
                // update it's state
                curr.updateState();
                // push the left in stack
                if(curr.root.left != null) s.push(new Pair2(curr.root.left));

            } else if(state == 2) {
                // add to inorder
                in.add(curr.root.val);
                // update the state
                curr.updateState();
                // push the right in stack
                if(curr.root.right != null) s.push(new Pair2(curr.root.right));

            } else {
                // add to post order
                post.add(curr.root.val);
                // pop from the stack
                s.pop();
            }
        }

        // add them all in the main list
        ans.add(in);
        ans.add(pre);
        ans.add(post);
        return ans;
    }

    // Leetcode Q: 987. Vertical Order Traversal of a Binary Tree

    // helper function to populate map
    private void populateMapII(TreeNode root, int level, Map<Integer, List<int[]>> map, int horLevel) {
        // base condition
        if(root == null) {
            return;
        }

        // if this level is already in the map, then simply add the node
        if(map.containsKey(level)) {
            map.get(level).add(new int[]{root.val, horLevel});
        } else {
            // if not then initialise it
            map.put(level, new ArrayList<>());
            map.get(level).add(new int[]{root.val, horLevel});
        }

        // calls for childer
        populateMapII(root.left, level-1, map, horLevel + 1);
        populateMapII(root.right, level+1, map, horLevel + 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<int[]>> map = new TreeMap<>();

        // we will maintain a map to track the nodes in each vertical level
        populateMapII(root, 0, map, 0);
        // copy the nodes value in list
        for(int key: map.keySet()) {
            List<int[]> curr = map.get(key);
            curr.sort((a, b) -> {
                // if horizontal level is same then sort in terms of value
                if(a[1] == b[1]) return a[0] - b[0];
                // else in terms in level
                else return a[1] - b[1];
            });

            // get the values now
            List<Integer> list = new ArrayList<>();
            for(int[] c: curr) {
                list.add(c[0]);
            }

            // add the list in the main list
            ans.add(new ArrayList<>(list));
        }

        return ans;
    }

    // Leetcode Q: 662. Maximum Width of Binary Tree

    // we have to make a pair class
    private class Pair {
        TreeNode root;
        int idx;

        Pair(TreeNode root, int idx) {
            this.root = root;
            this.idx = idx;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        // base case
        if(root == null) return 0;

        // in this we will use a BFS appraoch
        // make a de-que of int[2] to store value and index each time
        Deque<Pair> q = new LinkedList<>();

        // add root
        q.offer(new Pair(root, 0));
        int maxWidth = 0;

        // implement BFS
        while(!q.isEmpty()) {
            // see the current level's first and last node
            int start = q.peekFirst().idx;
            int end = q.peekLast().idx;

            maxWidth = Math.max(maxWidth, end - start + 1); // track the max width

            // add the next level with their index
            int size = q.size();
            for(int i=0; i<size; i++) {
                // pop curr
                Pair curr = q.poll();

                // add children
                if(curr.root.left != null) q.offer(new Pair(curr.root.left, 2*curr.idx+1));
                if(curr.root.right != null) q.offer(new Pair(curr.root.right, 2*curr.idx+2));
            }

        }

        return maxWidth;
    }
}