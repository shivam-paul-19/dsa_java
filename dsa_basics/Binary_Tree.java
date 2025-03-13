import java.util.*;

public class Binary_Tree {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        
    }

    static int idx = -1;
    public static class BinaryTree {
        public static Node buildTree(int[] nodes) {
            idx++;
            if(nodes[idx] == -1) {
                return null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }

        //^ these are DFS: depth first search
        public static void preTravesre(Node root) {
            if (root == null) {
                System.out.print("-1 ");
                return;
            }
            System.out.print(root.data+" ");
            preTravesre(root.left);
            preTravesre(root.right);
        }

        public static void inTravesre(Node root) {
            if (root == null) {
                return;
            }
            inTravesre(root.left);
            System.out.print(root.data+" ");
            inTravesre(root.right);
        }

        public static void postTravesre(Node root) {
            if (root == null) {
                return;
            }
            postTravesre(root.left);
            postTravesre(root.right);
            System.out.print(root.data+" ");
        }

        //^ This is BFS: breadth first search
        public static void levelOrderTraverse(Node root) {
            Queue<Node> que = new LinkedList<>();
            que.add(root);
            que.add(null);

            while(!que.isEmpty()) {
                Node currNode = que.remove();
                if(currNode == null) {
                    System.out.println();
                    if (que.isEmpty()) {
                        break;
                    } else {
                        que.add(null);
                    }
                } else {
                    System.out.print(currNode.data+" ");
                    if(currNode.left != null) {
                        que.add(currNode.left);
                    }

                    if(currNode.right != null) {
                        que.add(currNode.right);
                    }
                }
            }
        }

        // Hieght of the tree
        public static int treeHeight(Node root) {
            if (root == null) {
                return 0;
            }

            return 1 + Math.max(treeHeight(root.left), treeHeight(root.right));
        }
        
        // Number of nodes
        public static int count(Node root) {
            if (root == null) {
                return 0;
            }

            return 1 + count(root.left) + count(root.right);
        }

        // sum of nodes
        public static int sumNodes(Node root) {
            if (root == null) {
                return 0;
            }

            return root.data + sumNodes(root.left) + sumNodes(root.right);
        }

        // search for a node
        public static boolean searchNode(Node root, int key) {
            if(root == null) {
                return false;
            }

            if(root.data == key) {
                return true;
            }

            return searchNode(root.left, key) || searchNode(root.right, key);
        }

        // Diameter of the tree
        public static int diameter(Node root) {
            if (root == null) {
                return 0;
            }
    
            int d = treeHeight(root.left) + treeHeight(root.right) + 1;
            int diameter = Math.max(d, Math.max(diameter(root.left), diameter(root.right)));
            return diameter;
        }

        public static class Info {
            int diam;
            int height;

            Info(int d, int h) {
                this.diam = d;
                this.height = h;
            }
        }

        // Diameter of the tree (optimised)
        public static Info diameterOpt(Node root) {
            if (root == null) {
                return new Info(0, 0);
            }

            Info rightInfo = diameterOpt(root.right);
            Info leftInfo = diameterOpt(root.left);

            int ht = Math.max(leftInfo.height, rightInfo.height) + 1;
            int diameter = Math.max(leftInfo.height + rightInfo.height + 1, Math.max(leftInfo.diam, rightInfo.diam));

            return new Info(diameter, ht);
        }

        public static class ViewInfo {
            int hd;
            Node node;

            ViewInfo(Node n, int h) {
                this.hd = h;
                this.node = n;
            }
        }

        // Top view
        public static void topView(Node root) {
            Queue<ViewInfo> que = new LinkedList<>();
            HashMap<Integer, Node> map = new HashMap<>();

            que.add(new ViewInfo(root, 0));
            que.add(null);

            int min = 0, max = 0;

            while(!que.isEmpty()) {
                ViewInfo curr = que.remove();
                if(curr == null) {
                    if(que.isEmpty()) {
                        break;
                    }
                    else {
                        que.add(null);
                    }
                }
                else {
                    if(!map.containsKey(curr.hd)) {
                        map.put(curr.hd, curr.node);
                    }

                    if(curr.node.left != null) {
                        que.add(new ViewInfo(curr.node.left, curr.hd-1));
                        min = Math.min(min, curr.hd-1);
                    }

                    if(curr.node.right != null) {
                        que.add(new ViewInfo(curr.node.right, curr.hd+1));
                        max = Math.max(max, curr.hd+1);
                    }
                }
            }

            for (int i=min; i<=max; i++) {
                System.out.print(map.get(i).data + " ");
            }
        }
    }

    // is Identical
    public static boolean isIdentical(Node root, Node subRoot) {
        if (root==null && subRoot == null) {
            return true;
        }
        
        else if (root == null || subRoot == null || root.data != subRoot.data) {
            return false;
        }

        return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
    }

    // is subtree
    public static boolean isSubtree(Node root, Node subRoot) {
        if (root == null) {
            return false;
        }

        if (root.data == subRoot.data) {
            if(isIdentical(root, subRoot)) {
                return true;
            }
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // getting k-level nodes (usinf DFS)
    public static void kLevel(Node root, int l, int k) {
        if(root == null) {
            return;
        }

        if(l==k) {
            System.out.print(root.data + " ");
        }

        kLevel(root.left, l+1, k);
        kLevel(root.right, l+1, k);
    }

    // getting k-level nodes (usinf BFS)
    public static void kLevel2(Node root, int l, int k) {
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        que.add(null);
        while(!que.isEmpty()) {
            Node currNode = que.remove();
            if(currNode == null) {
                if(que.isEmpty()) {
                    break;
                }
                else {
                    que.add(null);
                    l++;
                }
            }
            else {
                if(l == k) {
                    System.out.print(currNode.data + " ");
                }
                else {
                    if(currNode.left != null) {
                        que.add(currNode.left);
                    }
                    if(currNode.right != null) {
                        que.add(currNode.right);
                    }
                }
            }
        }
    }

    // root to dest path
    public static boolean getPath(Node root, int n, ArrayList<Node> path) {
        if(root == null) {
            return false;
        }

        path.add(root);

        if(root.data == n) {
            return true;
        }

        boolean right = getPath(root.right, n, path);
        boolean left = getPath(root.left, n, path);

        if(right || left) {
            return true;
        }

        path.remove(path.size()-1);
        return false;
    }

    // Lowest common anscestor
    public static Node lowestCommonAns(Node root, int n1, int n2) {
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        getPath(root, n1, path1);
        getPath(root, n2, path2);

        int i=0;
        while(i < path1.size() && i < path2.size()) {
            if(path1.get(i) != path2.get(i)) {
                break;
            }
            i++;
        }

        Node low = path1.get(i-1);
        return low;
    }

    public static int lcaDistance(Node root, int n) {
        if(root == null) {
            return -1;
        }

        if(root.data == n) {
            return 0;
        }
        
        int leftDist = lcaDistance(root.left, n);
        int rightDist = lcaDistance(root.right, n);

        if(leftDist == -1 && rightDist == -1) {
            return -1;
        }

        else if(leftDist == -1) {
            return rightDist+1;
        }

        else {
            return leftDist+1;
        }
    }

    public static int minDistance(Node root, int n1, int n2) {
        Node lca = lowestCommonAns(root, n1, n2);
        int right = lcaDistance(lca, n1);
        int left = lcaDistance(lca, n2);

        return right+left;
    }

    public static int kAnc(Node root, int n, int k) {
        if(root == null) {
            return -1;
        }
        if (root.data == n) {
            return 0;
        }

        int leftDist = kAnc(root.left, n, k);
        int rightDist = kAnc(root.right, n, k);

        if(leftDist == -1 && rightDist == -1) {
            return -1;
        }

        int maxDist = Math.max(leftDist, rightDist);
        if(maxDist+1 == k) {
            System.out.println(root.data);
        }
        return maxDist+1;
    }

    public static Node sumTree(Node root) {
        if(root.left == null && root.right == null) {
            return root;
        }

        else if(root.left == null) {
            root.data = root.right.data;
            return root;
        }

        else if(root.right == null) {
            root.data = root.right.data;
            return root;
        }

        root.left = sumTree(root.left);
        root.right = sumTree(root.right);
        root.data = root.left.data + root.right.data;
        return root;
    }

    public static int transform(Node root) {
        if(root == null) {
            return 0;
        }

        int leftChild = transform(root.left);
        int rightChild = transform(root.right);

        int data = root.data;

        int newLeft =  root.left == null ? 0 : root.left.data;
        int newRight =  root.right == null ? 0 : root.right.data;

        root.data = leftChild + newRight + rightChild + newLeft;
        return data;
    }
}
