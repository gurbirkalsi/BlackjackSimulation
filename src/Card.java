/**
 * Created by jamessalvatore on 3/2/16.
 * asd
 */
public class Card implements Comparable{
    String face;
    String suit;
    int value; //to be added...
    Card next, previous;


    public Card(String face, String suit, Card next, Card previous) {
        this.face = face;
        this.suit = suit;
        this.next = next;
        this.previous = previous;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Card getNext() {
        return next;
    }

    public void setNext(Card next) {
        this.next = next;
    }

    public Card getPrevious() {
        return previous;
    }

    public void setPrevious(Card previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        String cardString = "";
        cardString += "(" + this.face + " " + this.suit + ")";
        return cardString;
    }

    /** @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object o) {
        Card comparingCard;
        comparingCard = (Card)o;
        switch (comparingCard.face) {
            case "One":
                return 1;
            case "Two":
                return 2;
            case "Three":
                return 3;
            case "Four":
                return 4;
            case "Five":
                return 5;
            case "Six":
                return 6;
            case "Seven":
                return 7;
            case "Eight":
                return 8;
            case "Nine":
                return 9;
            case "Ten":
                return 10;
            case "Jack":
                return 11;
            case "Queen":
                return 12;
            case "King":
                return 13;
            case "Ace":
                return 14;
            default:
                return 0;
        }
    }
}
