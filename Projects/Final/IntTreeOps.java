// Operations built on top of the IntTree class
//
// CS 201 Final exam programming question 2
// This file should not be changed or submitted

public class IntTreeOps {
       
    // Local abbreviations
        
    public static IntTree empty() {
        return IntTree.empty();
    }
        
    public static boolean isEmpty(IntTree T) {
        return IntTree.isEmpty(T);
    }
        
    public static boolean isLeaf(IntTree T) {
        return IntTree.isLeaf(T);
    }
        
    public static IntTree node(int val, IntTree lt, IntTree rt) {
        return IntTree.node(val, lt, rt);
    }
        
    public static int value(IntTree T) {
        return IntTree.value(T);
    }
        
    public static IntTree left(IntTree T) {
        return IntTree.left(T);
    }
        
    public static IntTree right(IntTree T) {
        return IntTree.right(T);
    }

    public static void printTree(IntTree T) {
        IntTree.printTree(T);
    }
        
}
