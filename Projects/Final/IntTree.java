// Binary trees of nodes that contain integers
//
// CS 201 Final exam programming question 2
// This file should not be changed or submitted

public class IntTree {
        
    // Instance variables
        
    protected int value;
    protected IntTree left;
    protected IntTree right;
        
    // Constructor
                
    public IntTree(int val, IntTree lt, IntTree rt) {
        value = val;
        left = lt;
        right = rt;
    }
        
    // Instance methods: 
        
    public int value() {
        // returns the integer stored in the root node of the tree
        return value;
    }
        
    public IntTree left() {
        // returns the left subtree of the tree
        return left;
    }
        
    public IntTree right() {
        // returns the right subtree of the tree
        return right;
    }
        
    // Class Methods: 
        
    public static IntTree empty() {
        // returns an empty tree (i.e., null)
        return null;
    }
        
    public static boolean isEmpty(IntTree t) {
        // returns true if the tree is empty
        return t == null;
    }
        
    public static boolean isLeaf(IntTree t) {
        // returns true if the tree is a leaf (has no children)
        return t.left() == null && t.right() == null;
    }
        
    public static IntTree node(int val, IntTree lt, IntTree rt) {
        // creates a new tree with the integer "val" in the root node
        // and with the left subtree "lt" and right subtree "rt"
        return new IntTree(val, lt, rt);
    }
        
    public static int value(IntTree t) {
        // returns the integer stored in the root node of the tree
        return t.value();
    }
        
    public static IntTree left(IntTree t) {
        // returns the left subtree of the tree t
        return t.left();
    }
        
    public static IntTree right(IntTree t) {
        // returns the right subtree of the tree t
        return t.right();
    }
        
    public static void printTree(IntTree t) {
        // displays a printed representation of tree t using printTree1
        printTree1(t, 0, true);
    }
        
    public static void printTree1(IntTree t, int level, boolean indent) {
        if (indent) {
            for (int i=0; i<level; i++)
                System.out.print("    ");
        } else {
            System.out.print(" ");
        }
        if (isEmpty(t)) {
            System.out.println("  .");
        } else {
            System.out.printf("%3d", value(t));
            if (isLeaf(t)) {
                System.out.println();
            } else {
                printTree1(right(t), level+1, false);
                printTree1(left(t),  level+1, true);
            }
        }
    }

}
