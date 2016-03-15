package gurbirkalsi.blackjacksimulation;

import java.util.Random;

import gurbirkalsi.blackjacksimulation.Card;
import gurbirkalsi.blackjacksimulation.Pile;

/**
 * Created by jamessalvatore on 3/3/16.
 * asd
 */
public class Deck extends Pile {

    private String[] cardSuits = {"Hearts", "Diamonds", "Spades", "Clovers"}; // Card suits for a traditional deck

    public Deck() {
        generatePile();
    }

    public void generatePile() {
        // Fills the deck with the traditional 52 cards
        for (int i = 0; i < cardSuits.length; i++) {
            for (Card.Face f : Card.Face.values()) {
                Card newCard = new Card(f, cardSuits[i], null, null);
                this.add(newCard);
            }
        }
    }

    public void shuffleDeck() {
        //generic shuffling (randomization)
        Random r = new Random();

        for (int i = 0; i < getLength(); i++) {
            int result = r.nextInt(getLength());

            Card currentCard = (Card)getEntry(i); // current card
            Card randomCard = (Card)getEntry(result); // will move to current index location

            Card newCurrentCard = new Card(randomCard.face,randomCard.suit,randomCard.getNext(),randomCard.getPrevious());
            Card newRandomCard = new Card(currentCard.face,currentCard.suit,currentCard.getNext(),currentCard.getPrevious());

            replace(i, newCurrentCard);
            replace(result, newRandomCard);
        }
    }

    public void deal(Player[] players) {
        //initial deal method, called once when the game starts, just gives each player card from top of deck..

        for (Player player : players) {
            Card currentTopOfDeck = (Card)this.removeTop();
            Hand playerHand = player.getHand();
            playerHand.add(currentTopOfDeck);
            player.updateScore();
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1: Initialize Deck");
        Deck testDeck = new Deck();
        testDeck.display();
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 2: Shuffle the deck (random shuffle)");
        testDeck.shuffleDeck();
        testDeck.display();
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 3: Deal Cards to players");
        Player[] testPlayers = {new Player("James"), new Player("Rob"), new Player("Tom")};
        System.out.println("\n" + "Before deal...");
        for (Player player : testPlayers) {
            System.out.println(player);
        }
        System.out.println("\n" + "After deal...");
        testDeck.deal(testPlayers);
        for (Player player : testPlayers) {
            System.out.println(player);
        }
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 4: Deal again");
        System.out.println("\n" + "Before deal...");
        for (Player player : testPlayers) {
            System.out.println(player);
        }
        System.out.println("\n" + "After deal...");
        testDeck.deal(testPlayers);
        for (Player player : testPlayers) {
            System.out.println(player);
        }
        System.out.println("");
        System.out.println("--------END TEST--------\n");
    }

}
