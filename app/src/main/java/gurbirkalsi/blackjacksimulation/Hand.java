package gurbirkalsi.blackjacksimulation;

/**
 * COMP 2071
 * Lab 3 - Data Structures
 * Due Date: Tuesday, February 23rd.
 * Group 13 - Josh Berger, Eli Kapetanopoulos, Giles Holmes, James Salvatore, Gurbir Kalsi
 */

public class Hand extends Pile {

    public Hand() {
        //default constructor
    }

    @Override
    public String toString() {
        String handString = "Hand: ";
        if (this.isEmpty()) {
            handString = "Empty Hand";
        }

        else if (this.getLength() == 1) {
            handString += this.getTopCard();
        }

        else {
            Card currentCard = this.getBottomCard();
            while (currentCard != null) {
                handString+= " " + currentCard;
                currentCard = currentCard.getNext();
            }
        }
        return handString;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: Init Hand\n");
        Hand testHand = new Hand();
        System.out.println(testHand);
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 1: Add a Card to Hand\n");
        Card testCard = new Card(Card.Face.Four, "Hearts", null, null);
        System.out.println("Before add...\n");
        System.out.println(testHand);
        System.out.println("");
        System.out.println("Card to add: " + testCard);
        testHand.add(testCard);
        System.out.println("");
        System.out.println("After add...\n");
        System.out.println(testHand+"\n");
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 2: Add another Card to Hand\n");
        Card testCard2 = new Card(Card.Face.Nine, "Spades", null, null);
        System.out.println("Before add...\n");
        System.out.println(testHand);
        System.out.println("");
        System.out.println("Card to add: " + testCard2);
        testHand.add(testCard2);
        System.out.println("");
        System.out.println("After add...\n");
        System.out.println(testHand+"\n");
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 3: Remove card from Hand\n");
        System.out.println("Before remove..\n");
        System.out.println(testHand+"\n");
        testHand.removeTop();
        System.out.println("After remove..\n");
        System.out.println(testHand+"\n");
        System.out.println("");
        System.out.println("--------END TEST--------\n");

    }
}
