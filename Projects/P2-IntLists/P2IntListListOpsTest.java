public class P2IntListListOpsTest {
    // Provides examples and methods to test the methods implemented in
    // P2IntListListOps.java

    // class variables for list examples 

    private static IntList L1, L3, L5, L12;
    private static IntListList ILL1, ILL2, ILL3, ILL4;

    public static void testFilterSorted () {
        IntListList[] lists = {ILL1, ILL2, ILL3, ILL4};
        for (int i = 0; i < lists.length; i++) {
            IntListList L = lists[i]; 
            try {
                System.out.println("filterSorted(" + toString(L) +
                                   ") \n  = " + toString(P2IntListListOps.filterSorted(L)));
            } catch (ListException e) {
                System.out.println("LIST EXCEPTION: " + e.getMessage());
            } 
        }
    }

    public static void testMapPrepend () {
        IntListList[] lists = {ILL1, ILL2, ILL3, ILL4};
        for (int i = 0; i < lists.length; i++) {
            IntListList L = lists[i]; 
            try {
                System.out.println("mapPrepend("+ (i + 5) + ", " + toString(L) +
                                   ") \n  = " + toString(P2IntListListOps.mapPrepend(i+5, L)));
            } catch (ListException e) {
                System.out.println("LIST EXCEPTION: " + e.getMessage());
            } 
        }
    }

    public static void testSubsequences () {
        IntList[] lists = {L1, L3, L5, L12};
        for (int i = 0; i < lists.length; i++) {
            IntList L = lists[i]; 
            try {
                System.out.println("subsequences(" + toString(L) + ") \n  = " +
                                   toString(P2IntListListOps.subsequences(L)));
            } catch (ListException e) {
                System.out.println("LIST EXCEPTION: " + e.getMessage());
            } 
        }
    }

    public static void testLongest () {
        IntListList[] lists = {ILL1, ILL2, ILL3, ILL4};
        for (int i = 0; i < lists.length; i++) {
            IntListList L = lists[i]; 
            try {
                System.out.println("longest("+ toString(L) + ") \n  = " +
                                   toString(P2IntListListOps.longest(L)));
            } catch (ListException e) {
                System.out.println("LIST EXCEPTION: " + e.getMessage());
            } 
        }

    }

    public static String toString(IntList L) {
        return IntList.toString(L);
    }

    public static String toString(IntListList L) {
        return IntListList.toString(L);
    }

    public static void main(String[] args) {
        // Initialize lists for testing
        int[] a1 = {};
        int[] a2 = {1};
        int[] a3 = {1,2};
        int[] a4 = {2,1};
        int[] a5 = {1,2,3};
        int[] a6 = {1,3,2};
        int[] a7 = {2,1,3};
        int[] a8 = {2,3,1};
        int[] a9 = {3,1,2};
        int[] a10 = {3,2,1};            
        int[] a11 = {1, 2, 3, 4};
        int[] a12 = {3, 1, 4, 2};       

        L1 = IntList.arrayToIntList(a1);
        L3 = IntList.arrayToIntList(a2);
        L5 = IntList.arrayToIntList(a5);
        L12 = IntList.arrayToIntList(a12);

        int[][] aa1 = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10};
        int[][] aa2 = {a4, a8, a12, a1, a11, a7, a5, a4, a2};

        ILL1 = IntListList.empty();
        ILL2 = IntListList.prepend(L1, IntListList.empty());
        ILL3 = IntListList.arrayToIntListList(aa1);
        ILL4 = IntListList.arrayToIntListList(aa2);

        System.out.println("--------------------------------------------------");
        testFilterSorted();     
        System.out.println("--------------------------------------------------");
        testMapPrepend();       
        System.out.println("--------------------------------------------------");
        testSubsequences();     
        System.out.println("--------------------------------------------------");
        testLongest();  
        System.out.println("--------------------------------------------------");
    }
    
}

