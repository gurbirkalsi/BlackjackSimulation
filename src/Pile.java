import java.util.Collection;

/**
 * Created by jamessalvatore on 3/2/16.
 * asd
 */

public class Pile {

    private int numCards;
    private Card topCard, bottomCard;

    public Pile() {
        topCard = null;
        bottomCard = null;
        numCards = 0;
    }

    public boolean addFirst(Card newCard) {
        boolean successfulAdd = false;
        if (topCard == null) {
            topCard = newCard;
            bottomCard = topCard;
            topCard.setNext(bottomCard);
            bottomCard.setPrevious(topCard);
            numCards++;
            successfulAdd = true;
        }
        return successfulAdd;
    }

    public boolean add(Card newCard) {

        if (topCard == null) {
            topCard = newCard;
            bottomCard = topCard;
            topCard.setNext(bottomCard);
            bottomCard.setPrevious(topCard);
            numCards++;
        }
        else {
            Card lastCard = bottomCard;
            lastCard.setNext(newCard);
            newCard.setPrevious(lastCard);
            bottomCard = newCard;
            numCards++;
        }
        return true;
    }

    public Card remove(Card cardToRemove) {
        Card prev = cardToRemove.getPrevious();
        Card next = cardToRemove.getNext();
        prev.setNext(next);
        next.setPrevious(prev);
        numCards--;
        return cardToRemove;
    }

    public boolean addAll(Collection<? extends Card> collection) {
        //appends all elements in the collection to the end of the list in order of collections specified iterator
        boolean addedCollection = false;
        if (collection.size() > 0) {
            for (Card c : collection) {
                this.add(c);
            }
            addedCollection = true;
        }
        return addedCollection;
    }

    public void clear() {
        //Removed all elements from the list
        topCard = null;
        bottomCard = null;
    }

    @Override
    public String toString() {
        //printable version of the 'Pile' object
        String pileString = "Pile:\n";
        Card currentCard = topCard;
        while (currentCard.getNext() != null) {
            pileString += " Card (Face: " + currentCard.getFace() +
                    ", Suit: " + currentCard.getSuit() + ")";
            currentCard = currentCard.getNext();
        }
        return pileString;
    }
}