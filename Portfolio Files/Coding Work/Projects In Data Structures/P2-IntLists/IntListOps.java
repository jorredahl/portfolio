// Operations built on top of the IntList class

public class IntListOps {

    // Generators

    public static IntList fromTo(int lo, int hi) {
        // returns a list of integers increasing from lo to hi, inclusive
        if (lo > hi) {
            return empty();
        } else {
            return prepend(lo, fromTo(lo + 1, hi));
        }
    }

    // Mappers

    public static IntList mapDouble(IntList L) {
        // returns a new list whose elements are doubled versions of
        // the elements of L
        if (isEmpty(L)) {
            return empty();
        } else {
            return prepend(2*head(L), mapDouble(tail(L)));
        }
    }

    public static IntList mapSquare(IntList L) {
        // returns a new list whose elements are squared versions of
        // the elements of L
        if (isEmpty(L)) {
            return empty();
        } else {
            return prepend(head(L) * head(L), mapSquare(tail(L)));
        }
    }

    // Filters

    public static IntList filterEven(IntList L) {
        // returns a new list that contains only the even integers in L
        if (isEmpty(L)) {
            return empty();
        } else if ((head(L) % 2) == 0) {
            return prepend(head(L), filterEven(tail(L)));
        } else {
            return filterEven(tail(L));
        }
    }

    public static IntList filterPositive(IntList L) {
        // returns a new list that contains only the positive integers in L
        if (isEmpty(L)) {
            return empty();
        } else if (head(L) > 0)  {
            return prepend(head(L), filterPositive(tail(L)));
        } else {
            return filterPositive(tail(L));
        }
    }

    // Accumulators

    public static int length(IntList L) {
        // returns the length of the list L
        if (isEmpty(L)) {
            return 0;
        } else {
            return 1 + length(tail(L));
        }
    }

    public static int sum(IntList L) {
        // returns the sum of the integers contained in the list L
        if (isEmpty(L)) {
            return 0;
        } else {
            return head(L) + sum(tail(L));
        }
    }

    public static int prod(IntList L) {
        // returns the product of the integers contained in the list L
        if (isEmpty(L)) {
            return 1;
        } else {
            return head(L) * prod(tail(L));
        }
    }

    // Programs written in the signal processing style. The list returned by
    // one method can immediately processed by another, to create very
    // compact definitions:

    public static int factorial(int n) {
        return prod(fromTo(1,n));
    }

    public static int sumSquareEvens(int lo, int hi) {
        return sum(mapSquare(filterEven(fromTo(lo,hi))));
    }

    // Transformers

    public static IntList append(IntList L1, IntList L2) {
        // creates a new list by appending the elements of L2 onto
        // the end of L1
        if (isEmpty(L1)) {
            return L2;
        } else {
            return prepend(head(L1), append(tail(L1), L2));
        }
    }

    public static IntList postpend(IntList L, int i) {
        // adds a new node containing i to the end of list L
        return append(L, prepend(i, empty()));
    }

    // straight-forward, but inefficient version - O(n^2)
    public static IntList reverseSlow(IntList L) {
        // returns a new list with the elements of L in reverse order
        if (isEmpty(L)) {
            return empty();
        } else {
            return postpend(reverseSlow(tail(L)), head(L));
        }
    }

    // tail-recursive version - O(n)
    public static IntList reverse(IntList L) {
        return reverseTail(L, empty());
    }

    public static IntList reverseTail(IntList L, IntList result) {
        if (isEmpty(L)) {
            return result;
        } else {
            return reverseTail(tail(L), prepend(head(L), result));
        }
    }

    // ----------------------------------------------------------
    // Local abbreviations, allowing you to avoid having to type the
    // IntList class name when calling the list methods

    public static IntList empty() {
        return IntList.empty();
    }

    public static boolean isEmpty(IntList L) {
        return IntList.isEmpty(L);
    }

    public static IntList prepend(int n, IntList L) {
        return IntList.prepend(n, L);
    }

    public static int head(IntList L) {
        return IntList.head(L);
    }

    public static IntList tail(IntList L) {
        return IntList.tail(L);
    }

    public static String toString(IntList L) {
        return IntList.toString(L);
    }

}
