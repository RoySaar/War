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

import java.util.Stack;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener  {

    Player player;
    Player cpu;
    Card playerCard;
    Card cpuCard;
    Game game;
    Button hitButton;
    ImageView playerCardImage;
    ImageView cpuCardImage;
    ImageView warImage;
    Animation slideDown;
    Animation slideUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        game = new Game();
        player = new Player(game.generatePack());
        cpu = new Player(game.generatePack());
        hitButton = (Button)findViewById(R.id.hitButton);
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
        game.playEach();
        playerCardImage.setImageResource(IMAGES[game.getTopPlayerCard()]);
        cpuCardImage.setImageResource(IMAGES[game.getTopCpuCard()]);
        int turnResult = game.handlePlay();
        switch (turnResult) {
            case Game.CPU_WON:
                //Animation
                break;
            case Game.PLAYER_WON:
                //Animation
                break;
            case Game.TIE:
                //War Animation
                break;
        }
        int gameResult = game.gameResult();
        switch (gameResult) {
            case Game.CPU_WON:
                //
                break;
            case Game.PLAYER_WON:
                //
                break;
            case Game.NOT_OVER:
                //
                break;
        }
    }

    public void boardHandler(boolean playerWon) {
        TextView playerCardsText = (TextView)findViewById(R.id.playerCardCount);
        TextView cpuCardsText = (TextView)findViewById(R.id.cpuCardCount);
        playerCardsText.setText(String.valueOf(player.getCards().size()));
        cpuCardsText.setText(String.valueOf(cpu.getCards().size()));

        final Animation slide;

        if (playerWon) {
            slide = slideDown;
        }
        else {
            slide = slideUp;
        }
        playerCardImage.startAnimation(slide);
        cpuCardImage.startAnimation(slide);
    }

    public void resetGame(int winner) {
        String name;
        if (winner == 0)
            name = "CPU";
        else
            name = "Player";
        playerCardImage.setImageResource(0);
        cpuCardImage.setImageResource(0);
        game = new Game();
        player = new Player(game.generatePack());
        cpu = new Player(game.generatePack());
        TextView playerCardsText = (TextView)findViewById(R.id.playerCardCount);
        TextView cpuCardsText = (TextView)findViewById(R.id.cpuCardCount);
        playerCardsText.setText("27");
        cpuCardsText.setText("27");
        Toast.makeText(this,name + " Won!",Toast.LENGTH_LONG).show();
        hitButton.setEnabled(true);
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
