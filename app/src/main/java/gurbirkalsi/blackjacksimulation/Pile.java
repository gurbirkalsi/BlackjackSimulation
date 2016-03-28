package gurbirkalsi.blackjacksimulation;

import java.util.Iterator;
import java.util.NoSuchElementException;

import gurbirkalsi.blackjacksimulation.Card;

/**
 * COMP 2071
 * Lab 3 - Data Structures
 * Due Date: Tuesday, February 23rd.
 * Group 13 - Josh Berger, Eli Kapetanopoulos, Giles Holmes, James Salvatore, Gurbir Kalsi
 */

public class Pile implements ListInterface{

    private int numCards;

    private Card topCard;

    public Card getBottomCard() {
        return bottomCard;
    }

    public Card getTopCard() {
        return topCard;
    }

    private Card bottomCard;

//    private DoublyLinkedIterator iterator;
//
//    private class DoublyLinkedIterator implements Iterator {
//
//        private Card currentCard;
//
//        private DoublyLinkedIterator() {
//            currentCard = topCard;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return (currentCard != null);
//        }
//
//        @Override
//        public Object next() {
//            if (hasNext()) {
//                Card resultantCard = currentCard;
//                currentCard = currentCard.getNext();
//                return resultantCard;
//            }
//            throw new NoSuchElementException("Linked list.next");
//        }
//    }

    public Pile() {
        topCard = null;
        bottomCard = null;
        numCards = 0;
        //iterator = new DoublyLinkedIterator();
    }

    public Pile(int numCards) {
        topCard = null;
        bottomCard = null;
        this.numCards = numCards;
    }


    @Override
    public boolean add(Object newEntry) {
        Card newCard = (Card)newEntry;
        if (isEmpty()) {
            // add initial card
            topCard = newCard;
            bottomCard = topCard;
            topCard.setNext(null);
            topCard.setPrevious(bottomCard);
            bottomCard.setNext(topCard);
            bottomCard.setPrevious(null);
            numCards++;
        }
        else {
            // general case: add new card to the top of the pile
            Card top = topCard;
            top.setNext(newCard);
            newCard.setPrevious(topCard);
            newCard.setNext(null);
            topCard = newCard;
            numCards++;
        }
        return true;
    }


    @Override
    public boolean add(int newPosition, Object newEntry) {
        boolean added = false;
        Card newCard = (Card)newEntry;
        Card cardAtPosition = (Card)getEntry(newPosition);

        if (validIndex(newPosition)) {

            if (newPosition == numCards) {
                // want to add to top of pile (new top card)
                add(newEntry);
            }

            else if (cardAtPosition.equals(bottomCard)) {
                // want to add to bottom
                Card bot = bottomCard;
                newCard.setNext(bot);
                bot.setPrevious(newCard);
                bottomCard = newCard;
                numCards++;
            }

            else {
                // want to add anywhere else in the pile
                Card prev = cardAtPosition.getPrevious();
                prev.setNext(newCard);
                newCard.setNext(cardAtPosition);
                newCard.setPrevious(prev);
                cardAtPosition.setPrevious(newCard);
                numCards++;
            }
            added = true;
        }

        return added;
    }

    public boolean validIndex(int givenPosition) {
        return (givenPosition < numCards && givenPosition >= 0);

    }


    @Override
    public Object remove(int givenPosition) {
        Card desiredCard = (Card)getEntry(givenPosition);

        if (numCards == 1) {
            // would remove the final card in pile
            this.clear();
        }

        else {

            if (desiredCard.equals(topCard)) {
                // want to remove top card
                desiredCard = (Card)removeTop();
            }

            else if (desiredCard.equals(bottomCard)) {
                // want to remove bottom card
                desiredCard = (Card)removeBottom();
            }

            else {
                // want to remove from anywhere else in pile
                Card prev = desiredCard.getPrevious();
                Card next = desiredCard.getNext();
                prev.setNext(next);
                next.setPrevious(prev);
                numCards--;
            }
        }
        return desiredCard;
    }

    public Object removeTop() {
        Card top = topCard;
        topCard = topCard.getPrevious();
        topCard.setNext(null);
        numCards--;
        return top;
    }

    public Object removeBottom() {
        Card bottom = bottomCard;
        Card next = bottom.getNext();
        next.setPrevious(null);
        bottomCard = next;
        numCards--;
        return bottom;
    }

    public void clear() {
        //Removed all elements from the list
        topCard = null;
        bottomCard = null;
        numCards = 0;
    }


    @Override
    public boolean replace(int givenPosition, Object newEntry) {
        boolean replaced = false;
        Card cardToAdd = (Card)newEntry;
        Card cardToReplace = (Card)getEntry(givenPosition);
        if (validIndex(givenPosition)) {
            cardToAdd.setNext(cardToReplace.getNext());
            cardToAdd.setPrevious(cardToReplace.getPrevious());
            cardToReplace.setFace(cardToAdd.face);
            cardToReplace.setSuit(cardToAdd.suit);
            replaced = true;
        }
        return replaced;
    }


    @Override
    public Object getEntry(int givenPosition) {
//        while (iterator.hasNext()) {
//            if (pileIndex == givenPosition) {
//                desiredCard = iterator.currentCard;
//            }
//        }
        Card currentCard = bottomCard;
        int index = 0;
        while (index < givenPosition) {
            currentCard = currentCard.getNext();
            index++;
        }
        return currentCard;
    }


    @Override
    public boolean contains(Object anEntry) {
        boolean found = false;
        Card desiredCard = (Card)anEntry;
        Card currentCard = bottomCard;
//        while (iterator.hasNext()) {
//            if (iterator.currentCard.equals((desiredCard))) {
//                found = true;
//                break;
//            }
//        }
        while (currentCard != null) {
            if (currentCard.getFace().equals(desiredCard.face) &&
                    currentCard.getSuit().equals(desiredCard.suit)) {
                found = true;
                break;
            }
            currentCard = currentCard.getNext();
        }
        return found;
    }


    @Override
    public int getLength() {
        return numCards;
    }


    @Override
    public boolean isEmpty() {
        return (getLength() == 0);
    }


    @Override
    public boolean isFull() {
        return (numCards == 52);
    }


    @Override
    public void display() {
//        while (iterator.hasNext()) {
//            System.out.println(iterator.currentCard);
//        }
        System.out.println("Pile: ");
        if (isEmpty()) {
            System.out.print("Empty Pile\n");
        }
        else {
            Card currCard = topCard;
            while (!currCard.equals(bottomCard)) {
                System.out.println(currCard);
                currCard = currCard.getPrevious();
            }
            System.out.println(bottomCard);
        }
    }


    public boolean addAll(Card[] collection) {
        // Adds collection of card objects to the pile
        boolean added = false;
        if (collection !=  null) {
            for (Card card : collection) {
                this.add(card);
            }
            added = true;
        }
        return added;
    }

    public static void main(String[] args) {
        Pile testPile = new Pile();
        Card[] newCards = {new Card(Card.Face.Ace, "Diamonds", null, null),
                new Card(Card.Face.King, "Hearts", null, null),
                new Card(Card.Face.Seven, "Spades", null, null),
                new Card(Card.Face.Nine, "Diamonds", null, null),
                new Card(Card.Face.Jack, "Clubs", null, null)};

        // Test init
        System.out.println("Test 1: Init Pile..\n");
        testPile.display();
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        //Test add function
        System.out.println("Test 2: Add (general case: top of pile)\n");
        System.out.println("Adding new card...");
        testPile.add(new Card(Card.Face.Four, "Hearts", null, null));
        System.out.println("");
        testPile.display();
        System.out.println("");
        System.out.println("Adding new card...");
        testPile.add(new Card(Card.Face.Two, "Spades", null, null));
        System.out.println("");
        testPile.display();
        System.out.println("");
        System.out.println("--------END TEST-------\n");

        System.out.println("Test 3: Add (at index: 1)\n");
        System.out.println("Adding new card...");
        testPile.add(1, new Card(Card.Face.Five, "Clovers" , null, null));
        System.out.println("");
        testPile.display();
        System.out.println("");
        System.out.println("--------END TEST--------\n");


        //Test clear function
        System.out.println("Test 4: Clear Pile:\n");
        System.out.println("Before clear...\n");
        testPile.display();
        System.out.println("");
        System.out.println("After clear...\n");
        testPile.clear();
        testPile.display();
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        //Test addAll function
        System.out.println("Test 5: Add All cards from given collection:\n");
        System.out.print("Collection of Cards: ");
        for (int i = 0; i < newCards.length; i++) {
            System.out.print(newCards[i] + " ");
        }
        System.out.println("\n");
        System.out.println("Adding collection of cards...");
        testPile.addAll(newCards);
        System.out.println("");
        testPile.display();
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        //Test remove function (from top, bottom, and middle of deck)
        System.out.println("Test 6: Remove top card in Pile\n");
        System.out.println("Before remove...\n");
        testPile.display();
        System.out.println("");
        System.out.println("After remove...\n");
        testPile.remove(testPile.getLength()-1);
        testPile.display();
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 7: Remove bottom card in Pile\n");
        System.out.println("Before remove...\n");
        testPile.display();
        System.out.println("");
        System.out.println("After remove...\n");
        testPile.remove(0);
        testPile.display();
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 8: Remove from middle of Pile\n");
        System.out.println("Before remove...\n");
        testPile.display();
        System.out.println("");
        System.out.println("After remove...\n");
        testPile.remove(1);
        testPile.display();
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        //Test replace function
        System.out.println("Test 9: Replace a card\n");
        Card replacementCard = new Card(Card.Face.Ten, "Clovers", null, null);
        System.out.println("Replacing " + testPile.getEntry(1) + " with " + replacementCard + "...\n");
        System.out.println("Before replacement...\n");
        testPile.display();
        System.out.println("");
        testPile.replace(1, replacementCard);
        System.out.println("After replacement...\n");
        testPile.display();
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        //Test getEntry(index)
        System.out.println("Test 10: Get entry at index 1\n");
        testPile.display();
        System.out.println("");
        System.out.println("Card at index 1: " + testPile.getEntry(1));
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        //Test contains(Card newEntry)
        System.out.println("Test 11: Contains\n");
        testPile.display();
        System.out.println("");
        Card testCard1 = new Card(Card.Face.Ace, "Spades",  null, null);
        Card testCard2 = new Card(Card.Face.King, "Hearts", null, null);
        System.out.println("Does the pile contain: " + testCard1 + " ?\n");
        System.out.println(testPile.contains(testCard1));
        System.out.println("");
        System.out.println("Does the pile contain: " + testCard2 + " ?\n");
        System.out.println(testPile.contains(testCard2));
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        //Test getLength(), isEmpty(), isFull()
        System.out.println("Test 12: getLength, isEmpty, isFull\n");
        testPile.display();
        System.out.println("");
        System.out.println("Number of cards in pile: " + testPile.getLength() + "\n");
        System.out.println("Is the pile full (52 cards in pile) ?\n");
        System.out.println(testPile.isFull());
        System.out.println("");
        System.out.println("Is the pile empty (0 cards in pile) ?\n");
        System.out.println(testPile.isEmpty());
        System.out.println("");
        System.out.println("Removing all cards (clear)...\n");
        testPile.clear();
        testPile.display();
        System.out.println("");
        System.out.println("Is the pile empty (0 cards in pile) ?\n");
        System.out.println(testPile.isEmpty());
    }
}