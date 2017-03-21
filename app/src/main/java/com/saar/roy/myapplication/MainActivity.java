package com.saar.roy.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button pvpButton;
    Button pveButton;
    Button rulesButton;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Typeface ptBold = Typeface.createFromAsset(getAssets(),"fonts/pt_sans_bold.ttf");
        pvpButton = (Button)findViewById(R.id.pvpButton);
        pvpButton.setOnClickListener(this);
        pvpButton.setTypeface(ptBold);
        pveButton = (Button)findViewById(R.id.pveButton);
        pveButton.setOnClickListener(this);
        pveButton.setTypeface(ptBold);
        rulesButton = (Button)findViewById(R.id.rulesButton);
        rulesButton.setOnClickListener(this);
        rulesButton.setTypeface(ptBold);
    }


    @Override
    public void onClick(View view) {
        // Play 1v1
        if (view == pvpButton) {
            Intent intent = new Intent(this,PvpGameActivity.class);
            startActivity(intent);
        }
        // PLay vs CPU
        if (view == pveButton) {
            Intent intent = new Intent(this,GameActivity.class);
            startActivity(intent);
        }
        // Show Rules
        if (view == rulesButton) {
            createDialog();
        }
        // Dismiss
        if (view.getId() == R.id.dismiss) {
            dialog.dismiss();
        }
    }

    public void createDialog() {
        dialog = new Dialog(this);
        dialog.setTitle("RULES:");
        dialog.setContentView(R.layout.dialog_layout);
        Button dismiss = (Button)dialog.findViewById(R.id.dismiss);
        dismiss.setOnClickListener(this);
        dialog.show();
    }
}
