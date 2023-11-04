// CS 201 Final exam programming question 3
// Name: Jorre Dahl

public class Final3<E> extends LinkedList<E> {

    // Define your method `everyOther` here
    public void everyOther() {
        LinkedList<E>.Node finger = head;
        while (finger != null && finger.next() != null) {
            finger.setNext(finger.next().next());
            finger = finger.next();
        }
    }

    public static void main(String[] args) {
        Final3<Integer> list = new Final3<Integer>(); 
        list.addLast(2);
        list.addLast(5);
        list.addLast(3);
        list.addLast(4);
        list.addLast(1);
        list.addLast(2);
        list.addLast(7);
        System.out.println(list);
        list.everyOther();
        System.out.println(list);
    }

}


