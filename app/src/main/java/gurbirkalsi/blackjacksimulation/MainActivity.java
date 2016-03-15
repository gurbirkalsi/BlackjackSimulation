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
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, ViewSwitcher.ViewFactory {

    //Initialize layout elements and visuals.
    public static LinearLayout layout;
    public static TextView money, highestScore, dealerScore, yourScore, bet;
    private ImageSwitcher dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5;
    private ImageSwitcher playerCard1, playerCard2, playerCard3, playerCard4, playerCard5;
    private ImageSwitcher splitCard1, splitCard2, splitCard3, splitCard4, splitCard5;
    private Button placeBet, hit, stand, surrender, challenge;
    private SeekBar betAmount;
    static final String[] challengeString = new String[3];

    private int betCoinAmount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeVariables();
        setGameImages();

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

        playerCard1 = (ImageSwitcher) findViewById(R.id.ivYourCard1);
        playerCard2 = (ImageSwitcher) findViewById(R.id.ivYourCard2);
        playerCard3 = (ImageSwitcher) findViewById(R.id.ivYourCard3);
        playerCard4 = (ImageSwitcher) findViewById(R.id.ivYourCard4);
        playerCard5 = (ImageSwitcher) findViewById(R.id.ivYourCard5);

        splitCard1 = (ImageSwitcher) findViewById(R.id.ivSplitCard1);
        splitCard2 = (ImageSwitcher) findViewById(R.id.ivSplitCard2);
        splitCard3 = (ImageSwitcher) findViewById(R.id.ivSplitCard3);
        splitCard4 = (ImageSwitcher) findViewById(R.id.ivSplitCard4);
        splitCard5 = (ImageSwitcher) findViewById(R.id.ivSplitCard5);

        hit = (Button) findViewById(R.id.btnHit);
        stand = (Button) findViewById(R.id.btnStand);
        surrender = (Button) findViewById(R.id.btnSurrender);
        placeBet = (Button) findViewById(R.id.btnPlaceBet);
        betAmount = (SeekBar) findViewById(R.id.sbBetAmount);
        betAmount.setOnSeekBarChangeListener(this);

    }

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

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {

        }

        betCoinAmount = i;
        bet.setText("Bet: $ " + betCoinAmount);

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
}
