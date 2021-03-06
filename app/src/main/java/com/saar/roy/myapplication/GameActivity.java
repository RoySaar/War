package com.saar.roy.myapplication;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener  {

    Game game;
    Button hitButton;
    ImageView playerCardImage;
    ImageView cpuCardImage;
    ImageView warImage;
    Animation slideDown;
    Animation slideUp;
    public final int[] IMAGES = {R.drawable.ace_of_clubs,R.drawable.two_of_clubs,R.drawable.three_of_clubs,R.drawable.four_of_clubs,R.drawable.five_of_clubs,R.drawable.six_of_clubs,R.drawable.seven_of_clubs,R.drawable.eight_of_clubs,R.drawable.nine_of_clubs,
            R.drawable.ten_of_clubs,R.drawable.jack_of_clubs,R.drawable.queen_of_clubs,R.drawable.king_of_clubs,R.drawable.ace_of_diamonds,R.drawable.two_of_diamonds,R.drawable.three_of_diamonds,R.drawable.four_of_diamonds,R.drawable.five_of_diamonds,R.drawable.six_of_diamonds,R.drawable.seven_of_diamonds,R.drawable.eight_of_diamonds,R.drawable.nine_of_diamonds,
            R.drawable.ten_of_diamonds,R.drawable.jack_of_diamonds,R.drawable.queen_of_diamonds,R.drawable.king_of_diamonds,R.drawable.ace_of_hearts,R.drawable.two_of_hearts,R.drawable.three_of_hearts,R.drawable.four_of_hearts,R.drawable.five_of_hearts,R.drawable.six_of_hearts,R.drawable.seven_of_hearts,R.drawable.eight_of_hearts,R.drawable.nine_of_hearts,
            R.drawable.ten_of_hearts,R.drawable.jack_of_hearts,R.drawable.queen_of_hearts,R.drawable.king_of_hearts,R.drawable.ace_of_hearts,R.drawable.two_of_spades,R.drawable.three_of_spades,R.drawable.four_of_spades,R.drawable.five_of_spades,R.drawable.six_of_spades,R.drawable.seven_of_spades,R.drawable.eight_of_spades,R.drawable.nine_of_spades,
            R.drawable.ten_of_spades,R.drawable.jack_of_spades,R.drawable.queen_of_spades,R.drawable.king_of_spades
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        game = new Game();
        hitButton = (Button)findViewById(R.id.player1Button);
        hitButton.setOnClickListener(this);
        slideDown  = AnimationUtils.loadAnimation(this,R.anim.slide_down);
        slideDown.setAnimationListener(this);
        slideUp  = AnimationUtils.loadAnimation(this,R.anim.slide_up);
        slideUp.setAnimationListener(this);
        playerCardImage = (ImageView)findViewById(R.id.playerCard);
        cpuCardImage = (ImageView)findViewById(R.id.cpuCard);
        warImage = (ImageView)findViewById(R.id.warImage);
        TextView playerCardText = (TextView)findViewById(R.id.playerCardCount);
        TextView cpuCardText = (TextView)findViewById(R.id.cpuCardCount);
        Typeface ptRegular = Typeface.createFromAsset(getAssets(),"fonts/pt_sans_regular.ttf");
        playerCardText.setTypeface(ptRegular);
        cpuCardText.setTypeface(ptRegular);

    }

    @Override
    public void onClick(View view) {
        if (view == hitButton) {
            turnHandler();
        }
    }

    public void turnHandler() {
        hitButton.setEnabled(false);
        game.playEach();
        playerCardImage.setImageResource(IMAGES[game.getTopPlaye1rCard()]);
        cpuCardImage.setImageResource(IMAGES[game.getTopPlayer2Card()]);
        int turnResult = game.handlePlay();
        final Animation slide;
        switch (turnResult) {
            case Game.PLAYER2_WON:
                slide = slideUp;
                playerCardImage.startAnimation(slide);
                cpuCardImage.startAnimation(slide);
                break;
            case Game.PLAYER1_WON:
                slide = slideDown;
                playerCardImage.startAnimation(slide);
                cpuCardImage.startAnimation(slide);
                break;
            case Game.TIE:
                final TextView warText = (TextView)findViewById(R.id.warText);
                warText.setAlpha(1);
                MediaPlayer mp = MediaPlayer.create(this,R.raw.horn);
                mp.start();
                CountDownTimer timer = new CountDownTimer(2000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        warText.setAlpha(0);
                        hitButton.setEnabled(true);
                    }
                }.start();
                break;
        }
        TextView playerCardsText = (TextView)findViewById(R.id.playerCardCount);
        TextView cpuCardsText = (TextView)findViewById(R.id.cpuCardCount);
        playerCardsText.setText(String.valueOf(game.getPlayer1CardCount()));
        cpuCardsText.setText(String.valueOf(game.getPlayer2CardCount()));
        int gameResult = game.gameResult();
        switch (gameResult) {
            case Game.PLAYER2_WON:
                game = new Game();
                playerCardsText.setText("26");
                cpuCardsText.setText("26");
                Toast.makeText(this,"CPU Won!",Toast.LENGTH_LONG).show();
                break;
            case Game.PLAYER1_WON:
                game = new Game();
                playerCardsText.setText("26");
                cpuCardsText.setText("26");
                Toast.makeText(this,"Player Won!",Toast.LENGTH_LONG).show();
                break;
            case Game.NOT_OVER:
                break;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        playerCardImage.setImageResource(0);
        cpuCardImage.setImageResource(0);
        animation.reset();
        hitButton.setEnabled(true);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
