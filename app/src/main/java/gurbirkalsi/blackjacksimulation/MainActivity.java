package gurbirkalsi.blackjacksimulation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, ViewSwitcher.ViewFactory {

    //Initialize layout elements and visuals.
    public static LinearLayout layout;
    public static TextView money, highestScore, dealerScore, yourScore, bet;
    private ImageSwitcher dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5;
    private ImageSwitcher playerCard1, playerCard2, playerCard3, playerCard4, playerCard5;
    private ImageSwitcher splitCard1, splitCard2, splitCard3, splitCard4, splitCard5;
    private Button placeBet, hit, stand;
    private SeekBar betAmount;

    private ArrayList<ImageSwitcher> dealerHandImages;
    private ArrayList<ImageSwitcher> playerHandImages;

    //Game variables
    Deck gameDeck;
    Player player;
    Hand playerHand;
    Player dealer;
    Hand dealerHand;
    static int nMoney = 0;
    int nBet = 0;
    int nDealerScore = 0, nPlayerScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initializeVariables();
        setGameImages();
        nMoney = 500;
        money.setText("$ " + nMoney);
        betAmount.setMax(nMoney);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeVariables() {

        layout = (LinearLayout) findViewById(R.id.parentLayout);

        money = (TextView) findViewById(R.id.tvMoney);
        highestScore = (TextView) findViewById(R.id.tvHighest);
        dealerScore = (TextView) findViewById(R.id.tvDealer);
        yourScore = (TextView) findViewById(R.id.tvYou);
        bet = (TextView) findViewById(R.id.tvBet);

        dealerCard1 = (ImageSwitcher) findViewById(R.id.ivDealerCard1);
        dealerCard2 = (ImageSwitcher) findViewById(R.id.ivDealerCard2);
        dealerCard3 = (ImageSwitcher) findViewById(R.id.ivDealerCard3);
        dealerCard4 = (ImageSwitcher) findViewById(R.id.ivDealerCard4);
        dealerCard5 = (ImageSwitcher) findViewById(R.id.ivDealerCard5);

        dealerHandImages = new ArrayList<ImageSwitcher>() {{

            add(dealerCard1);
            add(dealerCard2);
            add(dealerCard3);
            add(dealerCard4);
            add(dealerCard5);

        }};

        playerCard1 = (ImageSwitcher) findViewById(R.id.ivYourCard1);
        playerCard2 = (ImageSwitcher) findViewById(R.id.ivYourCard2);
        playerCard3 = (ImageSwitcher) findViewById(R.id.ivYourCard3);
        playerCard4 = (ImageSwitcher) findViewById(R.id.ivYourCard4);
        playerCard5 = (ImageSwitcher) findViewById(R.id.ivYourCard5);

        playerHandImages = new ArrayList<ImageSwitcher>() {{

            add(playerCard1);
            add(playerCard2);
            add(playerCard3);
            add(playerCard4);
            add(playerCard5);

        }};

        splitCard1 = (ImageSwitcher) findViewById(R.id.ivSplitCard1);
        splitCard2 = (ImageSwitcher) findViewById(R.id.ivSplitCard2);
        splitCard3 = (ImageSwitcher) findViewById(R.id.ivSplitCard3);
        splitCard4 = (ImageSwitcher) findViewById(R.id.ivSplitCard4);
        splitCard5 = (ImageSwitcher) findViewById(R.id.ivSplitCard5);

        hit = (Button) findViewById(R.id.btnHit);
        stand = (Button) findViewById(R.id.btnStand);
        placeBet = (Button) findViewById(R.id.btnPlaceBet);
        betAmount = (SeekBar) findViewById(R.id.sbBetAmount);
        betAmount.setOnSeekBarChangeListener(this);

        placeBet.setOnClickListener(this);
        hit.setOnClickListener(this);
        stand.setOnClickListener(this);

        gameDeck = new Deck();
        gameDeck.shuffleDeck();
        player = new Player("Player");
        playerHand = new Hand();
        dealer = new Player("Dealer");
        dealerHand = new Hand();

    }

    /**
     * Initializes all ImageSwitcher elements and places default card images inside.
     */
    private void setGameImages() {

        dealerCard1.setFactory(this);
        dealerCard2.setFactory(this);
        dealerCard3.setFactory(this);
        dealerCard4.setFactory(this);
        dealerCard5.setFactory(this);

        playerCard1.setFactory(this);
        playerCard2.setFactory(this);
        playerCard3.setFactory(this);
        playerCard4.setFactory(this);
        playerCard5.setFactory(this);

        splitCard1.setFactory(this);
        splitCard2.setFactory(this);
        splitCard3.setFactory(this);
        splitCard4.setFactory(this);
        splitCard5.setFactory(this);

        dealerCard1.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        dealerCard1.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        dealerCard2.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        dealerCard2.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        dealerCard3.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        dealerCard3.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        dealerCard4.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        dealerCard4.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        dealerCard5.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        dealerCard5.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        playerCard1.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        playerCard1.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        playerCard2.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        playerCard2.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        playerCard3.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        playerCard3.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        playerCard4.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        playerCard4.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        playerCard5.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left));
        playerCard5.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right));

        dealerCard1.setImageResource(R.drawable.default_red);
        dealerCard2.setImageResource(R.drawable.default_red);
        dealerCard3.setImageResource(R.drawable.default_red);
        dealerCard4.setImageResource(R.drawable.default_red);
        dealerCard5.setImageResource(R.drawable.default_red);

        playerCard1.setImageResource(R.drawable.default_blue);
        playerCard2.setImageResource(R.drawable.default_blue);
        playerCard3.setImageResource(R.drawable.default_blue);
        playerCard4.setImageResource(R.drawable.default_blue);
        playerCard5.setImageResource(R.drawable.default_blue);

        dealerCard1.setImageResource(R.drawable.default_red);
        dealerCard2.setImageResource(R.drawable.default_red);
        dealerCard3.setImageResource(R.drawable.default_red);
        dealerCard4.setImageResource(R.drawable.default_red);
        dealerCard5.setImageResource(R.drawable.default_red);

        playerCard1.setImageResource(R.drawable.default_blue);
        playerCard2.setImageResource(R.drawable.default_blue);
        playerCard3.setImageResource(R.drawable.default_blue);
        playerCard4.setImageResource(R.drawable.default_blue);
        playerCard5.setImageResource(R.drawable.default_blue);
        
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPlaceBet:

                if (nBet > 0) {
                    nMoney -= nBet;
                    money.setText(String.valueOf(nMoney));
                    gameStart();
                }

                break;

            case R.id.btnHit:

                playerTurn();

                if (nPlayerScore > 21) {
                    Toast.makeText(MainActivity.this, "You Lose!", Toast.LENGTH_SHORT).show();
                    if (thread.getState() == Thread.State.NEW)
                    {
                        thread.start();
                    }
                }

                if (playerHand.getTopCard().getFace().getCardValue() >= 5 && nPlayerScore < 22) {
                    Toast.makeText(MainActivity.this, "You Lose!", Toast.LENGTH_SHORT).show();
                    if (thread.getState() == Thread.State.NEW)
                    {
                        thread.start();
                    }
                }

                break;

            case R.id.btnStand:

                while (nDealerScore < 17 && nDealerScore <= nPlayerScore &&  dealerHand.getTopCard().getFace().getCardValue() < 5) {
                    dealerTurn();
                }

                if (nDealerScore > 21) {
                    Toast.makeText(MainActivity.this, "You Won!", Toast.LENGTH_SHORT).show();
                    if (thread.getState() == Thread.State.NEW)
                    {
                        thread.start();
                    }

                } else {

                    checkWin();

                }

                dealerTurn();

                if (dealerHand.getTopCard().getFace().getCardValue() >= 5) {
                    checkWin();
                }

                break;

            default:

                break;

        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {

        }

        nBet = i;
        bet.setText("Bet: $ " + nBet);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public View makeView() {
        ImageView iView = new ImageView(this);
        iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iView.setLayoutParams(new ImageSwitcher.LayoutParams(
                RadioGroup.LayoutParams.FILL_PARENT, RadioGroup.LayoutParams.FILL_PARENT));
        return iView;
    }

    /**
     * First round of the blackjack game.
     */
    private void gameStart() {

        //Dealer actions
        dealerTurn();

        //Player actions
        playerTurn();
        playerTurn();

        //Check for blackjack
        if (nPlayerScore == 21) {
            if ((playerHand.getEntry(0).equals('A') &&  playerHand.getEntry(1).equals('J'))
                    || ((playerHand.getEntry(0).equals('J') &&  playerHand.getEntry(1).equals('A')))) {
                Toast.makeText(MainActivity.this, "You hit BLACKJACK!",
                        Toast.LENGTH_SHORT).show();
                if (thread.getState() == Thread.State.NEW)
                {
                    thread.start();
                }
            }
        }
    }

    /**
     * Dealer turn involving dealer drawing from deck, and game images switching.
     */
    private void dealerTurn() {

        //Dealer actions
        Card dealerDrawnCard = (Card) gameDeck.removeTop();
        dealerHand.add(dealerDrawnCard);
        switchGameImages(dealerDrawnCard.getFace().getCardValue(), dealerDrawnCard.getSuit(), dealerHandImages.get(dealerHand.getLength() - 1));
        nDealerScore += dealerDrawnCard.getFace().getCardValue();
        dealerScore.setText(String.valueOf(nDealerScore));

    }

    /**
     * Player turn involving player drawing from deck, and game images switching.
     */
    private void playerTurn() {
        Card playerDrawnCard = (Card) gameDeck.removeTop();
        playerHand.add(playerDrawnCard);
        switchGameImages(playerDrawnCard.getFace().getCardValue(), playerDrawnCard.getSuit(), playerHandImages.get(playerHand.getLength() - 1));
        nPlayerScore += playerDrawnCard.getFace().getCardValue();
        yourScore.setText(String.valueOf(nPlayerScore));

    }

    /**
     * Handlers to check current score to see if there is a win.
     */
    public void checkWin() {
        if (nDealerScore > nPlayerScore) {
            Toast.makeText(MainActivity.this, "You Lose!", Toast.LENGTH_SHORT).show();
            if (thread.getState() == Thread.State.NEW)
            {
                thread.start();
            }

        } else if (nPlayerScore > nDealerScore) {
            Toast.makeText(MainActivity.this, "You Win!", Toast.LENGTH_SHORT).show();
            if (thread.getState() == Thread.State.NEW)
            {
                thread.start();
            }

        } else {
            nMoney += nBet;
            nBet = 0;
            Toast.makeText(MainActivity.this, "Draw!",
                    Toast.LENGTH_SHORT).show();
            if (thread.getState() == Thread.State.NEW)
            {
                thread.start();
            }
        }
    }

    /**
     * Switch game images with new cardNumber and cardSuit information.
     */
    private void switchGameImages(int cardNumber, String cardSuit, ImageSwitcher imageView) {

        if (cardSuit.equals("Hearts")) {

            switch (cardNumber) {
                case 2: imageView.setImageResource(R.drawable.h2); break;
                case 3: imageView.setImageResource(R.drawable.h3); break;
                case 4: imageView.setImageResource(R.drawable.h4); break;
                case 5: imageView.setImageResource(R.drawable.h5); break;
                case 6: imageView.setImageResource(R.drawable.h6); break;
                case 7: imageView.setImageResource(R.drawable.h7); break;
                case 8: imageView.setImageResource(R.drawable.h8); break;
                case 9: imageView.setImageResource(R.drawable.h9); break;
                case 10: imageView.setImageResource(R.drawable.h10); break;
                case 11: imageView.setImageResource(R.drawable.hj); break;
                case 12: imageView.setImageResource(R.drawable.hq); break;
                case 13: imageView.setImageResource(R.drawable.hk); break;
                case 14: imageView.setImageResource(R.drawable.h1); break;
                default: break;
            }

        } else if (cardSuit.equals("Diamonds")) {

            switch (cardNumber) {
                case 2: imageView.setImageResource(R.drawable.d2); break;
                case 3: imageView.setImageResource(R.drawable.d3); break;
                case 4: imageView.setImageResource(R.drawable.d4); break;
                case 5: imageView.setImageResource(R.drawable.d5); break;
                case 6: imageView.setImageResource(R.drawable.d6); break;
                case 7: imageView.setImageResource(R.drawable.d7); break;
                case 8: imageView.setImageResource(R.drawable.d8); break;
                case 9: imageView.setImageResource(R.drawable.d9); break;
                case 10: imageView.setImageResource(R.drawable.d10); break;
                case 11: imageView.setImageResource(R.drawable.dj); break;
                case 12: imageView.setImageResource(R.drawable.dq); break;
                case 13: imageView.setImageResource(R.drawable.dk); break;
                case 14: imageView.setImageResource(R.drawable.d1); break;
                default: break;
            }

        } else if (cardSuit.equals("Spades")) {
            
            switch (cardNumber) {
                case 2: imageView.setImageResource(R.drawable.s2); break;
                case 3: imageView.setImageResource(R.drawable.s3); break;
                case 4: imageView.setImageResource(R.drawable.s4); break;
                case 5: imageView.setImageResource(R.drawable.s5); break;
                case 6: imageView.setImageResource(R.drawable.s6); break;
                case 7: imageView.setImageResource(R.drawable.s7); break;
                case 8: imageView.setImageResource(R.drawable.s8); break;
                case 9: imageView.setImageResource(R.drawable.s9); break;
                case 10: imageView.setImageResource(R.drawable.s10); break;
                case 11: imageView.setImageResource(R.drawable.sj); break;
                case 12: imageView.setImageResource(R.drawable.sq); break;
                case 13: imageView.setImageResource(R.drawable.sk); break;
                case 14: imageView.setImageResource(R.drawable.s1); break;
                default: break;
            }

        } else if (cardSuit.equals("Clovers")) {

            switch (cardNumber) {
                case 2: imageView.setImageResource(R.drawable.c2); break;
                case 3: imageView.setImageResource(R.drawable.c3); break;
                case 4: imageView.setImageResource(R.drawable.c4); break;
                case 5: imageView.setImageResource(R.drawable.c5); break;
                case 6: imageView.setImageResource(R.drawable.c6); break;
                case 7: imageView.setImageResource(R.drawable.c7); break;
                case 8: imageView.setImageResource(R.drawable.c8); break;
                case 9: imageView.setImageResource(R.drawable.c9); break;
                case 10: imageView.setImageResource(R.drawable.c10); break;
                case 11: imageView.setImageResource(R.drawable.cj); break;
                case 12: imageView.setImageResource(R.drawable.cq); break;
                case 13: imageView.setImageResource(R.drawable.ck); break;
                case 14: imageView.setImageResource(R.drawable.c1); break;
                default: break;
            }

        }

    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(3500);
                finish();
                startActivity(getIntent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
