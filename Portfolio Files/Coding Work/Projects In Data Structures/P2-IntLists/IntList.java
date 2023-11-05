// Implements a linked lists of integers
// uses "functional" rather than object-oriented style
// i.e., an empty list is represented using null
// and the preferred way of manipulating lists is using the static
// methods below.

public class IntList {

    // Instance variables
    protected int head;
    protected IntList tail;

    // Constructor
    public IntList(int head, IntList tail) {
        this.head = head;
        this.tail = tail;
    }

    // Class Methods: these are the preferred way to manipulate IntLists

    public static IntList empty() {
        // returns a new empty list
        return null;
    }

    public static boolean isEmpty(IntList L) {
        return (L == null);
    }

    public static IntList prepend(int n, IntList L) {
        // adds a new node containing n at the front of the list
        return new IntList(n, L);
    }

    public static int head(IntList L) {
        // returns the head of L
        if (isEmpty(L)) {
            throw new ListException("Attempt to get the head of an empty int list");
        } else {
            return L.head;
        }
    }

    public static IntList tail(IntList L) {
        // returns the tail of L
        if (isEmpty(L)) {
            throw new ListException("Attempt to get the tail of an empty int list");
        } else {
            return L.tail;
        }
    }

    public static IntList arrayToIntList(int[] a) {
        // moves the contents of an array "a" into a linked list
        IntList result = empty();
        for (int i = a.length-1; i >= 0; i--) 
            result = prepend(a[i], result);
        return result;
    }

    public static String toString(IntList L) {
        if (isEmpty(L)) {
            return "[]";
        } else {
            String s = "[" + head(L);
            IntList toDo = tail(L);
            while (toDo != null) {
                s += ", " + head(toDo);
                toDo = tail(toDo);
            }
            s += "]";
            return s;
        }
    }

}
