/**
 * Created by KapetanopoulosEli on 3/3/2016.
 */
public class Player {

    private String name;
    private Hand hand;
    private int score;

    public Player()
    {
        //default constructor
    }

    public Player(String newName)
    {
        //overloaded constructor
        this.name = newName;
        score = 0;
        hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore() {
        Card newlyAdded = getHand().getTopCard();
        setScore(getScore()+newlyAdded.getFace().getCardValue());
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name = '" + name + '\'' +
                ", hand = " + hand +
                ", score = " + score +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("Test 1: Initialize Player\n");
        Player player1 = new Player("Rad");
        System.out.println(player1);
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 2: Add Cards & Update Score\n");
        player1.getHand().add(new Card(Card.Face.Ace, "Spades", null, null));
        player1.updateScore();
        System.out.println(player1 + "\n");
        player1.getHand().add(new Card(Card.Face.Seven, "Hearts", null, null));
        player1.updateScore();
        System.out.println(player1 + "\n");
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Testing 3: Testing getters...\n");
        System.out.println(player1);
        System.out.println("getName(): " + player1.getName() + "\n"
                            + "getScore(): " +  player1.getScore() + "\n"
                            + "getHand(): " + player1.getHand());
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 4: Testing setters...\n");
        System.out.println("Before setting...\n");
        System.out.println(player1);
        player1.setName("Joe");
        Hand testHand = new Hand();
        player1.setHand(testHand);
        player1.setScore(0);
        System.out.println("");
        System.out.println("Reset hand & change name...\n");
        System.out.println(player1);
        System.out.println("");
        System.out.println("Add some new cards..\n");
        testHand.add(new Card(Card.Face.Ten, "Clovers", null, null));
        player1.updateScore();
        System.out.println(player1);
        testHand.add(new Card(Card.Face.Five, "Hearts", null, null));
        player1.updateScore();
        System.out.println(player1);
        testHand.add(new Card(Card.Face.Two, "Spades", null, null));
        player1.updateScore();
        System.out.println(player1);
        System.out.println("");
        System.out.println("--------END TEST--------\n");
    }
}
