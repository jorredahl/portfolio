public class IntListListOps {

    // Operations built on top of the IntListList class

    // Transformers 

    public static IntListList append(IntListList L1, IntListList L2) {
        // returns a new list of integer lists by concatenating the
        // contents of L1 and contents of L2
        if (isEmpty(L1)) {
            return L2;
        } else {
            return prepend(head(L1), append(tail(L1), L2));
        }
    }

    public static IntListList postpend(IntListList L, IntList il) {
        // returns a new list of integer lists, with a new node at the end
        // with the list "il" in the head
        return append(L, prepend(il, empty()));
    }

    public static IntListList reverse(IntListList L) {
        // returns a new list of integer lists with the contents reversed
        if (isEmpty(L)) {
            return empty();
        } else {
            return postpend(reverse(tail(L)), head(L));
        }
    }   

    // ----------------------------------------------------------
    // Local abbreviations

    public static IntListList empty() {
        return IntListList.empty();
    }

    public static boolean isEmpty(IntListList L) {
        return IntListList.isEmpty(L);
    }

    public static IntListList prepend(IntList il, IntListList L) {
        return IntListList.prepend(il, L);
    }

    public static IntList head(IntListList L) {
        return IntListList.head(L);
    }

    public static IntListList tail(IntListList L) {
        return IntListList.tail(L);
    }

    public static String toString(IntListList L) {
        return IntListList.toString(L);
    }

}

