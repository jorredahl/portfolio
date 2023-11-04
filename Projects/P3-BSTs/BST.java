import java.util.ArrayList;

// BST class for Project 3 BSTs and Anagrams
// Name: Jorre Dahl

public class BST<E extends Comparable<E>> {
    protected TreeNode root;

    public BST()     {
        root = null;
    }
    
    public boolean isEmpty()     {
        return root == null;
    }

    @Override
    public boolean equals(Object tree) {
        if (this == tree) return true;
        if (tree == null || !(tree instanceof BST<?>)) return false;
        BST<?> otherTree = BST.class.cast(tree);
        return equals(this.root, otherTree.root);
    }

    private boolean equals(TreeNode root1, BST<?>.TreeNode root2) {
        if (root2 == null && root1 == null) {
            return true;
        }
        else if (root2 == null || root1 == null) {
            return false;
        }
        else if (root2.data.compareTo(root1.data) == 0) {
            return equals(root1.left, root2.left) && equals(root1.right, root2.right);
        }
        else {
            return false;
        }
    }
    
    public void add(E value) {
        root = addHelper(root, value);
    }

    public void add(E cannon, E original) {
        root = addHelperDupes(root, cannon, original);
    }
    
    private TreeNode addHelper(TreeNode rt, E value) {
        if (rt == null)
            return new TreeNode(value, null, null, null);
        
        if (value.compareTo(rt.data) < 0)
            rt.left = addHelper(rt.left, value);
        else if (value.compareTo(rt.data) > 0)
            rt.right = addHelper(rt.right, value);
        else
            throw new IllegalStateException("Duplicate value in tree " + value);
        
        return rt;  
    }

    private TreeNode addHelperDupes(TreeNode rt, E value, E listVal) {
        if (rt == null)
            return new TreeNode(value, null, null, listVal);
        if (value.compareTo(rt.data) < 0)
            rt.left = addHelperDupes(rt.left, value, listVal);
        else if (value.compareTo(rt.data) > 0)
            rt.right = addHelperDupes(rt.right, value, listVal);
        else
            rt.addToList(listVal);
        return rt;  
    }
            
    public void inOrder() {
        inOrder(root);
    }
    
    private void inOrder(TreeNode rt) {
        if (rt != null) {
            inOrder(rt.left);
            System.out.print(rt.data + " ");
            inOrder(rt.right);
        }
    }
    
    public void preOrder() {
        preOrder(root);
    }
    
    private void preOrder(TreeNode rt) {
        if (rt != null) {
            System.out.print(rt.data + " ");
            preOrder(rt.left);
            preOrder(rt.right);
        }
    }
    
    public int size() {
        return size(root);
    }
    
    private int size(TreeNode rt) {
        if (rt == null)
            return 0;
        return 1 + size(rt.left) + size(rt.right);
    }
    
    public boolean contains(E value) {
        TreeNode rt = root;
        while (rt != null) {
            if (value.compareTo(rt.data) == 0)
                return true;
            else if (value.compareTo(rt.data) < 0)
                rt = rt.left;
            else
                rt = rt.right;
        }
        return false;
    }

    public int height() {
        return height(root);
    }
     
    private int height(TreeNode rt) {
        if (rt == null)
             return -1;
        int h1 = height(rt.left);
        int h2 = height(rt.right);
        if (h1 > h2) {
           return 1 + h1;
        }
        else {
           return 1 + h2;
        }
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(TreeNode rt) {
        if (rt == null) {
            return true;
        }
        int x = height(rt.left) - height(rt.right);
        if ((2 > x) && (-2 < x)) {
            return isBalanced(rt.left) && isBalanced(rt.right);
        }
        return false;
    }

    public ArrayList<E> find(E cannon) {
        return find(root, cannon);
    }

    private ArrayList<E> find(TreeNode rt, E cannon) {
        if (rt == null) {
            return null;
        }
        else if (rt.data.compareTo(cannon) == 0) {
            return rt.list;
        }
        else if (rt.data.compareTo(cannon) > 0) {
            return find(rt.left, cannon);
        }
        else {
            return find(rt.right, cannon);
        }
    }

    // returns a String that prints tree top to bottom, right to left in a 90-degree rotated level view
    public String toString() {
        StringBuilder result =  new StringBuilder();
        return toString(result, -1, root).toString();
    }
    
    public StringBuilder toString(StringBuilder res, int height, TreeNode rt) {
        if (rt != null) {
            height++;
            res = toString(res, height, rt.right);
            for (int i = 0; i < height; i++)
                res.append("\t");
            res.append(rt.data + "\n");
            res = toString(res, height, rt.left);
        }
        return res;
    }
    
    // The TreeNode class is a private inner class used (only) by the BST class
    private class TreeNode {
        private E data;
        private TreeNode left, right;
        private ArrayList<E> list;
        
        private TreeNode(E data, TreeNode left, TreeNode right, E listVal) {
            this.data = data;
            this.left = left;
            this.right = right;
            addToList(listVal);
        }

        private void addToList(E value) {
            if (this.list == null) {
                this.list = new ArrayList<E>();
            }
            this.list.add(value);
        }
    }
    
    public static void main(String[] args) {
        BST<Integer> treeTest = new BST<Integer>();
        treeTest.add(7);
        treeTest.add(5);
        treeTest.add(4);
        treeTest.add(10);
        treeTest.add(6);
        treeTest.add(8);
        BST<Integer> treeTest2 = new BST<Integer>();
        treeTest2.add(7);
        treeTest2.add(5);
        treeTest2.add(4);
        treeTest2.add(10);
        treeTest2.add(6);
        treeTest2.add(8);
        treeTest.inOrder();
        System.out.println();
        treeTest.preOrder();
        System.out.println(treeTest.size());
        System.out.println(treeTest.height());
        System.out.println(treeTest2.height());
        System.out.println(treeTest.isBalanced());
        System.out.println(treeTest2.isBalanced());
        System.out.println(treeTest.equals(treeTest2));
        System.out.println();
        System.out.println(treeTest.contains(6));
        System.out.println(treeTest.contains(112));
        System.out.println(treeTest.contains(7));
        System.out.println(treeTest.contains(10));
        System.out.println();
        System.out.println(treeTest);
        System.out.println();
    }
    
}
