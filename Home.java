package com.example.game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    Spinner s1;
    Button b1;
    int flag = 0;
    MediaPlayer mobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mobj = new MediaPlayer();

        s1=(Spinner)findViewById(R.id.spinner1);
        b1 = (Button) findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = String.valueOf(s1.getSelectedItem());
                if(string.equalsIgnoreCase("Mellow")) {
                    playSong1();
                }

                else if(string.equalsIgnoreCase("Ambience")) {
                    playSong2();
                }

                else {
                    playSong3();
                }
            }
        });
    }

    public void playSong1() {
        mobj = MediaPlayer.create(this, R.raw.song);
        mobj.start();
    }

    public void playSong2() {
        mobj = MediaPlayer.create(this, R.raw.ambience);
        mobj.start();

    }

    public void playSong3() {
        mobj = MediaPlayer.create(this, R.raw.rock);
        mobj.start();

    }

    public void playGame(View view) {
        Intent obj = new Intent(this, Grid.class);
        startActivity(obj);
    }

    public void rulesopen(View view) {
        Intent obj2 = new Intent(this, Rules.class);
        startActivity(obj2);
    }

}
