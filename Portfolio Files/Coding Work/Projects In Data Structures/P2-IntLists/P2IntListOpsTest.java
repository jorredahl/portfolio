public class P2IntListOpsTest {

    public static void main (String[] args) {
        // Prints out the results of testing the methods "isSorted",
        // "remove" and "removeDuplicates" defined in P2IntListOps.java
        System.out.println("------------------------------------------------");
        testIsSorted(); 
        System.out.println("------------------------------------------------");
        testRemove();   
        System.out.println("------------------------------------------------");
        testRemoveDuplicates(); 
        System.out.println("------------------------------------------------");
    }

    public static void testIsSorted () {
        int[] a1 = {};
        int[] a2 = {1};
        int[] a3 = {1,2};
        int[] a4 = {1,1};
        int[] a5 = {2,1};
        int[] a6 = {1,2,3,4,5};
        int[] a7 = {1,2,3,4,0};
        int[] a8 = {1,5,3,4,2};
        IntList L1 = IntList.arrayToIntList(a1);
        IntList L2 = IntList.arrayToIntList(a2);
        IntList L3 = IntList.arrayToIntList(a3);
        IntList L4 = IntList.arrayToIntList(a4);
        IntList L5 = IntList.arrayToIntList(a5);
        IntList L6 = IntList.arrayToIntList(a6);
        IntList L7 = IntList.arrayToIntList(a7);
        IntList L8 = IntList.arrayToIntList(a8);
        try {
            System.out.println("Testing isSorted");
            System.out.println("L1 " + toString(L1) + " : " +
                               P2IntListOps.isSorted(L1));
            System.out.println("L2 " + toString(L2) + " : " +
                               P2IntListOps.isSorted(L2));
            System.out.println("L3 " + toString(L3) + " : " +
                               P2IntListOps.isSorted(L3));
            System.out.println("L4 " + toString(L4) + " : " +
                               P2IntListOps.isSorted(L4));
            System.out.println("L5 " + toString(L5) + " : " +
                               P2IntListOps.isSorted(L5));
            System.out.println("L6 " + toString(L6) + " : " +
                               P2IntListOps.isSorted(L6));
            System.out.println("L7 " + toString(L7) + " : " +
                               P2IntListOps.isSorted(L7));
            System.out.println("L8 " + toString(L8) + " : " +
                               P2IntListOps.isSorted(L8));
        } catch (ListException e) {
            System.out.println("LIST EXCEPTION: " + e.getMessage());
        } 
    }

    public static void testRemove () {
        int[] a = {4, 2, 3, 4, 1, 3, 4, 2, 4, 3};
        IntList L = IntList.arrayToIntList(a);
        System.out.println("Testing remove with list " + toString(L));
        for (int i = 0; i <= 4; i++) {
            try {
                System.out.print("remove(" + i + ") = ");
                System.out.println(toString(P2IntListOps.remove(i, L)));
            } catch (ListException e) {
                System.out.println("LIST EXCEPTION: " + e.getMessage());
            } 
        }
    }

    public static void testRemoveDuplicates () {
        int[] a1 = {};
        int[] a2 = {3, 5, 2, 1, 4};
        int[] a3 = {4, 2, 3, 4, 1, 3, 4, 2, 4, 3};
        IntList L1 = IntList.arrayToIntList(a1);
        IntList L2 = IntList.arrayToIntList(a2);
        IntList L3 = IntList.arrayToIntList(a3);
        try {
            System.out.println("Testing removeDuplicates");
            System.out.println("L1 " + toString(L1) + " : "
                               + toString(P2IntListOps.removeDuplicates(L1)));
            System.out.println("L2 " + toString(L2) + " : "
                               + toString(P2IntListOps.removeDuplicates(L2)));
            System.out.println("L3 " + toString(L3) + " : "
                               + toString(P2IntListOps.removeDuplicates(L3)));
        } catch (ListException e) {
            System.out.println("LIST EXCEPTION: " + e.getMessage());
        } 
    }

    public static String toString(IntList L) {
        return IntList.toString(L);
    }

}

