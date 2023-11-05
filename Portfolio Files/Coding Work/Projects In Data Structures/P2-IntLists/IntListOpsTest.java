public class IntListOpsTest {

    // main method for testing and printing results of IntListOps methods

    public static void main(String[] args) {
        // create some initial lists of integers (from arrays) to use
        // as test cases

        int[] a1 = {3, 7};
        int[] a2 = {5, 1, 4};
        int[] a3 = {5, -3, -2, 7, 1, 6, 2, 8};
        IntList L0 = IntList.empty();
        IntList L1 = IntList.arrayToIntList(a1);
        IntList L2 = IntList.arrayToIntList(a2);
        IntList L3 = IntList.arrayToIntList(a3);
        System.out.println("Testing List Operations...");
        System.out.println("L0: " + toString(L0));
        System.out.println("L1: " + toString(L1));
        System.out.println("L2: " + toString(L2));
        System.out.println("L3: " + toString(L3));
        System.out.println("Length of L0: " + IntListOps.length(L0));
        System.out.println("Length of L1: " + IntListOps.length(L1));
        System.out.println("Length of L2: " + IntListOps.length(L2));
        System.out.println("Length of L3: " + IntListOps.length(L3));
        System.out.println("Sum of L0: " + IntListOps.sum(L0));
        System.out.println("Sum of L1: " + IntListOps.sum(L1));
        System.out.println("Sum of L2: " + IntListOps.sum(L2));
        System.out.println("Sum of L3: " + IntListOps.sum(L3));
        System.out.println("Product of L0: " + IntListOps.prod(L0));
        System.out.println("Product of L1: " + IntListOps.prod(L1));
        System.out.println("Product of L2: " + IntListOps.prod(L2));
        System.out.println("Product of L3: " + IntListOps.prod(L3));
        System.out.println("--------------------------------------------------");
        System.out.println("Testing mapDouble...");
        System.out.println("L1: " + toString(IntListOps.mapDouble(L1)));
        System.out.println("L2: " + toString(IntListOps.mapDouble(L2)));
        System.out.println("L3: " + toString(IntListOps.mapDouble(L3)));
        System.out.println("--------------------------------------------------");
        System.out.println("Testing mapSquare...");
        System.out.println("L1: " + toString(IntListOps.mapSquare(L1)));
        System.out.println("L2: " + toString(IntListOps.mapSquare(L2)));
        System.out.println("L3: " + toString(IntListOps.mapSquare(L3)));
        System.out.println("--------------------------------------------------");
        System.out.println("Testing filterEven...");
        System.out.println("L1: " + toString(IntListOps.filterEven(L1)));
        System.out.println("L2: " + toString(IntListOps.filterEven(L2)));
        System.out.println("L3: " + toString(IntListOps.filterEven(L3)));
        System.out.println("--------------------------------------------------");
        System.out.println("Testing filterPositive...");
        System.out.println("L1: " + toString(IntListOps.filterPositive(L1)));
        System.out.println("L2: " + toString(IntListOps.filterPositive(L2)));
        System.out.println("L3: " + toString(IntListOps.filterPositive(L3)));
        System.out.println("--------------------------------------------------");
        System.out.println("Testing append...");
        System.out.println("L1 + L2: " + toString(IntListOps.append(L1,L2)));
        System.out.println("L2 + L3: " + toString(IntListOps.append(L2,L3)));
        System.out.println("--------------------------------------------------");
        System.out.println("Testing reverse...");
        System.out.println("L1: " + toString(IntListOps.reverse(L1)));
        System.out.println("L2: " + toString(IntListOps.reverse(L2)));
        System.out.println("L3: " + toString(IntListOps.reverse(L3)));
        System.out.println("--------------------------------------------------");
        System.out.println("Testing reverseSlow...");
        System.out.println("L1: " + toString(IntListOps.reverseSlow(L1)));
        System.out.println("L2: " + toString(IntListOps.reverseSlow(L2)));
        System.out.println("L3: " + toString(IntListOps.reverseSlow(L3)));
        System.out.println("--------------------------------------------------");
        System.out.println("Factorial of 5: " + IntListOps.factorial(5));
        System.out.print("sumSquareEvens 1 to 10: ");
        System.out.println(IntListOps.sumSquareEvens(1, 10));
        System.out.println("--------------------------------------------------");
    }

    public static String toString(IntList L) {
        return IntList.toString(L);
    }
}
