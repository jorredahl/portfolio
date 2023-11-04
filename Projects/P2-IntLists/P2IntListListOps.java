// CS 201 Project 2 Problem 2
// Name: Jorre Dahl

public class P2IntListListOps extends IntListListOps {

    // Tackle one at a time! Test using
    // "java P2IntListOpsTest"

    public static IntListList filterSorted (IntListList L) {
        // Returns a list of all of the lists of L that are sorted. 
        // To use a method from P2IntListOps.java, use the prefix
        // "P2IntListOps."
        if (L == null) {
            return empty();
        }
        else if (!P2IntListOps.isSorted(head(L))) {
            return filterSorted(tail(L));
        }
        else {
            return prepend(head(L), filterSorted(tail(L)));
        }
    }

    public static IntListList mapPrepend (int i, IntListList L) {
        // Returns a new list that contains list elements in which i is
        // prepended to every corresponding element of the input list.
        // Note that this method uses both IntList and IntListList
        // methods. In the body, IntList methods must contain an explicit
        // "IntList." prefix, but no prefix is necessary for IntListList
        // methods.
        if(L == null) {
            return empty();
        }
        return prepend(IntList.prepend(i, head(L)), mapPrepend(i, tail(L)));
    }

    public static IntListList subsequences (IntList L) {
        // Given an integer list L, return a list of all the subsequences
        // of L.  Think carefully about the base case!  Note that this
        // method uses both IntList and IntListList methods. In the body,
        // IntList methods must contain an explicit "IntList." prefix, but
        // no prefix is necessary for IntListList methods.
        if (L == null) {
            return prepend(IntList.empty(), empty());
        }
        return append(subsequences(IntList.tail(L)), mapPrepend(IntList.head(L), subsequences(IntList.tail(L))));
    } 

    // Optional (extra credit):
    // Please don't remove this method even if you don't tackle it so our test code runs

    public static IntListList longest (IntListList L) {

        // Returns a list of all of the lists of L that have the longest length. 
        // If L is empty, returns the empty list. 
        // Think carefully about base cases here!
        if (L == null) {
            return empty();
        }
        else if (tail(L) == null) {
            return prepend(head(L), empty());
        }
        else if (IntListOps.length(head(L)) > IntListOps.length(head(longest(tail(L))))) {
            return prepend(head(L), empty());
        }
        else if (IntListOps.length(head(L)) == IntListOps.length(head(longest(tail(L))))) {
            return prepend(head(L), longest(tail(L)));
        }
        else {
            return longest(tail(L));
        }
    }
}
