package gurbirkalsi.blackjacksimulation;

/**
 * Created by jamessalvatore on 3/2/16.
 * asd
 */
public class Card implements Comparable{
    String suit;
    Card next, previous;
    Face face;
    enum Face {
        Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8),
        Nine(9), Ten(10), Jack(10), Queen(10), King(10), Ace(11);

        public int getCardValue() {
            return cardValue;
        }

        private int cardValue;

        Face(int value) {
            cardValue = value;
        }
    }

    public Card(Face face, String suit, Card next, Card previous) {
        this.face = face;
        this.suit = suit;
        this.next = next;
        this.previous = previous;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
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
        cardString += "(" + this.face + " of " + this.suit + ")";
        return cardString;
    }

    /** @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object o) {
        Card comparingCard = (Card)o;
        switch (comparingCard.face) {
            case Two:
                return 2;
            case Three:
                return 3;
            case Four:
                return 4;
            case Five:
                return 5;
            case Six:
                return 6;
            case Seven:
                return 7;
            case Eight:
                return 8;
            case Nine:
                return 9;
            case Ten:
                return 10;
            case Jack:
                return 11;
            case Queen:
                return 12;
            case King:
                return 13;
            case Ace:
                return 14;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1: Make a Card..\n");
        Card testCard1 = new Card(Face.Four, "Spades", null, null);
        System.out.println(testCard1);
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 2: Link 2 Cards together (testing setNext, setPrevious, getNext, getPrevious)\n");
        Card testCard2 = new Card(Face.Ten, "Hearts", null, null);
        System.out.println("Original card: " + testCard1 + "\nNew card to link: " + testCard2);
        testCard1.setNext(testCard2);
        testCard2.setPrevious(testCard1);
        System.out.println("Showing 'next' relation in chain of original card: " + testCard1 + "->" + testCard1.getNext());
        System.out.println("Showing 'previous' relation in chain of new card: " + testCard2.getPrevious() + "<-" + testCard2);
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 3: Show Card Info (getter)\n");
        System.out.println("Card to test: " + testCard1 +"\n");
        System.out.println("Value: " + testCard1.getFace().cardValue);
        System.out.println("Suit: " + testCard1.getSuit());
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 4: Set Card info to new Card (setter)\n");
        System.out.println("Before change...\n");
        System.out.println("Card: " + testCard1 +"\n");
        System.out.println("Changing card to 'Ace of Spades'..\n");
        testCard1.setFace(Face.Ace);
        testCard1.setSuit("Spades");
        System.out.println("Card: "+ testCard1);
        System.out.println("");
        System.out.println("--------END TEST--------\n");



    }
}
