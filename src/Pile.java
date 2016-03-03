import java.util.Collection;

/**
 * Created by jamessalvatore on 3/2/16.
 * asd
 */

public class Pile {

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
        Node currNode = headNode;
        Node prevNode = null;
        for (int i = 0; i < index; i++) {
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        newEntry.setNext(currNode);
        prevNode.setNext(newEntry);
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
        Node last = null;
        if (size > 0) {
            Node currNode = headNode;
            last = getLast();
            while (!currNode.getNext().equals(last)) {
                currNode = currNode.getNext();
            }
            currNode.setNext(null);
        }
        return last;
    }

    public boolean addAll(Collection<? extends Node> collection) {
        //appends all elements in the collection to the end of the list in order of collections specified iterator
        boolean addedCollection = false;
        if (collection.size() > 0) {
            for (Node n : collection) {
                this.add(n);
            }
            addedCollection = true;
        }
        return addedCollection;
    }

    public void clear() {
        //Removed all elements from the list
        headNode.setNext(null);
        size = 0;
    }

    public Node getLast() {
        Node currNode = headNode;
        while (!currNode.getNext().equals(null)) {
            currNode = currNode.getNext();
        }
        return currNode;
    }

    @Override
    public String toString() {
        //printable version of the 'Pile' object
        String pileString = "";
        Node currNode = headNode;
        while (!currNode.getNext().equals(null)) {
            pileString += " Node (Data: " + currNode.getData() + ", Next: " + currNode.getNext() + ")";
            currNode = currNode.getNext();
        }
        return pileString;
    }
}