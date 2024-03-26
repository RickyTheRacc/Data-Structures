package misc;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"unused"})
public class LinkedPositionalList<E> {
    private static class Node<E> implements Position<E> {
        private E element;               // reference to the element stored at this node
        private Node<E> prev;            // reference to the previous node in the list
        private Node<E> next;            // reference to the subsequent node in the list

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() throws IllegalStateException {
            // Convention for defunct node
            if (next == null) throw new IllegalStateException("Position no longer valid");
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private final Node<E> header;           // header sentinel
    private final Node<E> trailer;          // trailer sentinel
    private int size = 0;             // number of elements

    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (p instanceof Node) {
            Node<E> node = (Node<E>) p;
            if (node.getNext() == null) throw new IllegalArgumentException("p is no longer in the list");
            return node;
        } else {
            throw new IllegalArgumentException("Invalid p");
        }
    }

    private Position<E> position(Node<E> node) {
        // Don't expose sentinels to the user
        if (node == header || node == trailer) return null;
        return node;
    }

    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    public Position<E> first() {
        return position(header.getNext());
    }

    public Position<E> last() {
        return position(trailer.getPrev());
    }

    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);  // create and link a new node
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());       // just after the header
    }

    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);     // just before the trailer
    }

    public Position<E> addBefore(Position<E> p, E e)
        throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    public Position<E> addAfter(Position<E> p, E e)
        throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);           // help with garbage collection
        node.setNext(null);              // and convention for defunct node
        node.setPrev(null);
        return answer;
    }

    /**
     * A (nonstatic) inner class. Note well that each instance
     * contains an implicit reference to the containing list,
     * allowing us to call the list's methods directly.
     */
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first();   // position of the next element to report
        private Position<E> recent = null;       // position of last reported element

        public boolean hasNext() {
            return cursor != null;
        }

        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;           // element at this position might later be removed
            cursor = after(cursor);
            return recent;
        }

        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            LinkedPositionalList.this.remove(recent);         // remove from outer list
            recent = null;               // do not allow remove again until next is called
        }
    }

    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    public Iterable<Position<E>> positions() {
        return new PositionIterable();       // create a new instance of the inner class
    }


    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();
        public boolean hasNext() { return posIterator.hasNext(); }
        public E next() { return posIterator.next().getElement(); } // return element!
        public void remove() { posIterator.remove(); }
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = header.getNext();
        while (walk != trailer) {
            sb.append(walk.getElement());
            walk = walk.getNext();
            if (walk != trailer)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }
}
