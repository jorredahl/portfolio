// CS 201 Final exam programming question 2
// Name: Jorre Dahl

public class Final2 extends IntTreeOps {

    // Define your method `countLeaves` here
    public static int countLeaves(IntTree t) {
        if (isEmpty(t)) {
            return 0;
        }
        else if (isLeaf(t)) {
            return 1;
        }
        else {
            return countLeaves(right(t)) + countLeaves(left(t));
        }
    }


    public static void main(String[] args) {
        // create a sample tree for testing
        IntTree lt = node(1, node(4, empty(), empty()),
                             node(2, node(5, empty(), empty()), empty()));
        IntTree rt = node(3, empty(), node(7, empty(), empty()));
        IntTree t = node(6, lt, rt);
        System.out.println("Test tree:");
        printTree(t);
        System.out.print("leaves in left subtree = ");
        System.out.println(countLeaves(lt));
        System.out.print("leaves in right subtree = ");
        System.out.println(countLeaves(rt));
        System.out.print("leaves in full tree = ");
        System.out.println(countLeaves(t));
    }
}


