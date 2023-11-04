// A partial linked list implementation
// 
// CS 201 Final exam programming question 3
// This file should not be changed or submitted

 public class LinkedList<E> { 
	protected Node head; 

	public LinkedList() { 
		head = null; 
	} 

	public void addLast(E value) {
		if (head == null) {
			head = new Node(value);
		} else {
			Node finger = head;
			
			while (finger.next() != null) {
				finger = finger.next();
			}
			
			finger.setNext(new Node(value));
		}
	}

    public String toString() {
        String s;
        if (head == null) {
            return "[]";
        }
        else {
            s = "[" + head.value();
            Node finger = head;
            while (finger.next() != null) {
                s += ", " + finger.next().value();
                finger = finger.next();
            }
            s += "]";
        }
        return s;
    }
	
 	protected class Node {
		private E data; 
		private Node next; 
		
		public Node(E v, Node next) { 
			data = v; 
			this.next = next; 
		} 
		
		public Node(E v) { 
			this(v,null); 
		}
		
		public Node next() { 
			return next;
		} 
		
		public void setNext(Node next) { 
			this.next = next; 
		} 
		
		public E value() { 
			return data; 
		}  
		
		public String toString() { 
			return "<Node: "+value()+">"; 
		} 
	}

}