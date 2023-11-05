// CS 201 Project 2 Problem 1
// Name: Jorre Dahl

public class P2IntListOps extends IntListOps {

    // Tackle one at a time! Test using
    // "java P2IntListOpsTest"

    public static boolean isSorted(IntList L) {
        if(L == null || tail(L) == null) {
            return true;
        }
        else if (head(L) > head(tail(L))) {
            return false;
        }
        else {
            return isSorted(tail(L));
        }
    }

    public static IntList remove(int i, IntList L) {
        if(L == null) {
            return empty();
        }
        else if (head(L) == i) {
            return remove(i, tail(L));
        }
        else {
            return prepend(head(L), remove(i, tail(L)));
        }
    }

    public static IntList removeDuplicates(IntList L) {
        if(L == null) {
            return empty();
        }
        return prepend(head(L), removeDuplicates(remove(head(L), tail(L))));
    }

}
