package gurbirkalsi.blackjacksimulation;

/**
 * COMP 2071
 * Lab 3 - Data Structures
 * Due Date: Tuesday, February 23rd.
 * Group 13 - Josh Berger, Eli Kapetanopoulos, Giles Holmes, James Salvatore, Gurbir Kalsi
 */
public class Player {

    private String name;
    private Hand hand;
    private int money;

    public Player()
    {
        //default constructor
    }

    public Player(String newName)
    {
        //overloaded constructor
        this.name = newName;
        money = 0;
        hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void updateMoney() {
        Card newlyAdded = getHand().getTopCard();
        setMoney(getMoney() + newlyAdded.getFace().getCardValue());
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
                ", money = " + money +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("Test 1: Initialize Player\n");
        Player player1 = new Player("Rad");
        System.out.println(player1);
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 2: Add Cards & Update Money\n");
        player1.getHand().add(new Card(Card.Face.Ace, "Spades", null, null));
        player1.updateMoney();
        System.out.println(player1 + "\n");
        player1.getHand().add(new Card(Card.Face.Seven, "Hearts", null, null));
        player1.updateMoney();
        System.out.println(player1 + "\n");
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Testing 3: Testing getters...\n");
        System.out.println(player1);
        System.out.println("getName(): " + player1.getName() + "\n"
                            + "getMoney(): " +  player1.getMoney() + "\n"
                            + "getHand(): " + player1.getHand());
        System.out.println("");
        System.out.println("--------END TEST--------\n");

        System.out.println("Test 4: Testing setters...\n");
        System.out.println("Before setting...\n");
        System.out.println(player1);
        player1.setName("Joe");
        Hand testHand = new Hand();
        player1.setHand(testHand);
        player1.setMoney(0);
        System.out.println("");
        System.out.println("Reset hand & change name...\n");
        System.out.println(player1);
        System.out.println("");
        System.out.println("Add some new cards..\n");
        testHand.add(new Card(Card.Face.Ten, "Clovers", null, null));
        player1.updateMoney();
        System.out.println(player1);
        testHand.add(new Card(Card.Face.Five, "Hearts", null, null));
        player1.updateMoney();
        System.out.println(player1);
        testHand.add(new Card(Card.Face.Two, "Spades", null, null));
        player1.updateMoney();
        System.out.println(player1);
        System.out.println("");
        System.out.println("--------END TEST--------\n");
    }
}
