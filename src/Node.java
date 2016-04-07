/**
 * Lab 1 - Data Structures
 * Due Date - Tuesday, January 19th.
 * @author COMP 2071 Group 4
 *
 * The Node class implements the Node construct by storing
 * information in it's 'data' field, and points to another
 * Node (the next node in the Linked List). This Node class
 * also incorporates the use of Generic Types in order for a
 * Node to contain data of any type.
 *
 * NOTE: Ch.3 In Data Structures Book used to construct this class
 */

public class Node<T> {

    private T data;//data for the node
    private Node next;//pointer for the Node

    /**
     * Default constructor (set initial data and pointer to null)
     * @param dataPortion
     */
    private Node(T dataPortion) {
        this(dataPortion, null);
    }

    /**
     * Actual constructor used to immediately assign data and next values.
     * @param dataPortion
     * @param nextNode
     */
    public Node(T dataPortion, Node nextNode) {
        this.data = dataPortion;
        this.next = nextNode;
    }

    /**
     * Returns the data of the node
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * Allows for the data of the Node to be changed
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns the pointer of the current Node
     * @return
     */
    public Node getNext() {
        return next;
    }

    /**
     * Allows for the Node's pointer to be changed
     * @param next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * toString that returns a printable version of the Node
     * @return
     */
    public String toString() {
        String printNode = "";
        printNode += this.data + " " + this.next;

        return printNode;
    }
}
