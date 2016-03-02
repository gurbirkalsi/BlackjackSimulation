import java.util.Collection;

/**
 * Created by jamessalvatore on 3/2/16.
 * asd
 */

public class Pile<T> {

    private int size;
    private Node headNode;



    public Pile() {
        headNode = null;
        size = 0;
    }


    public boolean add(Node newEntry) {
        boolean nodeAdded = false;
        if (size == 0) {
            headNode = newEntry;
            size++;
        }

        else {
            Node lastNode = getLast();
            lastNode.setNext(newEntry);
            size++;
            nodeAdded = true;
        }
        return nodeAdded;
    }

    public boolean add(Node newEntry, int index) {
        //adds to specific index
        return true;
    }

    public Node remove(Node entry) {
        //removes the head
        Node currNode = headNode;
        Node prevNode = null;
        while (!currNode.equals(entry)) {
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        prevNode.setNext(currNode.getNext());
        return currNode;
    }

    public Node remove() {
        //remove from end LOL420
        if (size == 1) {
            Node entry = headNode;
            headNode.setNext(null);
            size--;
        }
        return null;
    }

    public boolean addAll(Collection<? extends Node> collection) {
        //appends all elements in the collection to the end of the list in order of collections specified iterator
        return true;
    }

    public void clear() {
        //Removed all elements from the list
    }

    public Node getLast() {
        Node currNode = new Node(headNode, null);
        while (!currNode.getNext().equals(null)) {
            currNode = currNode.getNext();
        }
        return currNode;
    }

    @Override
    public String toString() {
        //printable version of the 'Pile' object
        return null;
    }

}
