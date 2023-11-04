// Linked lists of linked lists of integers

public class IntListList {

    // Instance variables
    protected IntList head;
    protected IntListList tail;

    // Constructor
    public IntListList(IntList head, IntListList tail) {
        this.head = head;
        this.tail = tail;
    }

    // Class Methods: these are the preferred way to manipulate IntListLists

    public static IntListList empty() {
        // returns an empty list of integer lists
        return null;
    }

    public static boolean isEmpty(IntListList L) {
        return (L == null);
    }

    public static IntListList prepend(IntList il, IntListList L) {
        // returns a new list with a new node added to the front of L,
        // containing the list "il" in the head
        return new IntListList(il, L);
    }

    public static IntList head(IntListList L) {
        // returns the head of L
        if (isEmpty(L)) {
            throw new ListException("Attempt to get head of an empty int list list");
        } else {
            return L.head;
        }
    }

    public static IntListList tail(IntListList L) {
        // returns the tail of L
        if (isEmpty(L)) {
            throw new ListException("Attempt to get tail of an empty int list list");
        } else {
            return L.tail;
        }
    }

    public static IntListList arrayToIntListList(int[][] a) {
        // creates a list of integer lists from the contents of a 2-D array
        IntListList result = empty();
        for (int i = a.length-1; i >= 0; i--) {
            result = prepend(IntList.arrayToIntList(a[i]), result);
        }
        return result;
    }

    public static String toString(IntListList L) {
        if (isEmpty(L)) {
            return "[]";
        } else {
            String s = "[" + IntList.toString(head(L));
            IntListList toDo = tail(L);
            while (toDo != null) {
                s += ", " + IntList.toString(head(toDo));
                toDo = tail(toDo);
            }
            s += "]";
            return s;
        }
    }

}
