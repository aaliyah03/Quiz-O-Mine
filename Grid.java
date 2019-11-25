/*goal: click on all green buttons within specified amt of time. if red button clicked - then question will appear and time reduced by 50 s. once all green buttons are clicked, then game over. if time is still left then you won, else you've lost*/

package com.example.game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.math.*;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Grid extends AppCompatActivity {

    //initializations
    ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    TextView t1, counttime1, livest;

    CountDownTimer cdt;

    //for quiz
    int arr[];
    int nooflives = 3;
    public int counter1 = 100;
    String questions[] = {"Amartya Sen was awarded the Nobel prize for his contribution to Literature","William Hewlett and David Packard set up a small company called apple","Pacific Ocean is the largest ocean on Earth","The National Anthem of Spain has no words","In Greek mythology, Hades, Zeus and Poseidon are all brothers.\n" +
            "\n","Angel Falls in Venezuela is the world's largest waterfall","The Earthworm has no eyes","It's colder at the Arctic than at the Antarctic","Bananas grow on trees","Carrots help you see in the dark","If a piece of paper was folded 45 times, it would reach to the moon","It rains diamonds on Saturn and Jupiter"};
    String answers[] = {"False", "False","True","True","True","True","True","False","False","False","True","True"};
    int used[] = {0,0,0,0,0,0,0,0,0,0,0,0};
    int ques = 0;

    //for result
    int flag = 0;
    int green = 10; //to keep a check on green buttons clicked
    int score = 0;

    //for storing in DB
    String playerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        //initialization
        b1 = (ImageButton) findViewById(R.id.b1);
        b2 = (ImageButton) findViewById(R.id.b2);
        b3 = (ImageButton) findViewById(R.id.b3);
        b4 = (ImageButton) findViewById(R.id.b4);
        b5 = (ImageButton) findViewById(R.id.b5);
        b6 = (ImageButton) findViewById(R.id.b6);
        b7 = (ImageButton) findViewById(R.id.b7);
        b8 = (ImageButton) findViewById(R.id.b8);
        b9 = (ImageButton) findViewById(R.id.b9);
        b10 = (ImageButton) findViewById(R.id.b10);
        b11 = (ImageButton) findViewById(R.id.b11);
        b12 = (ImageButton) findViewById(R.id.b12);
        b13 = (ImageButton) findViewById(R.id.b13);
        b14 = (ImageButton) findViewById(R.id.b14);
        b15 = (ImageButton) findViewById(R.id.b15);
        b16 = (ImageButton) findViewById(R.id.b16);
        livest = (TextView) findViewById(R.id.textView);

        counttime1 = findViewById(R.id.timer);

        cdt = new CountDownTimer(100000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counttime1.setText(String.valueOf(counter1));
                counter1 --;
            }

            @Override
            public void onFinish() {
                thisone();
            }
        }.start();

        Intent in = getIntent();
        playerName=in.getStringExtra("playerName");
        mine();     //mines have been generated
        nooflives = 3;
    }

    public void thisone() {
        if(flag == 1) {
            Intent intent = new Intent(Grid.this, Result.class);
            intent.putExtra("result", 1);
            intent.putExtra("score", score);
            intent.putExtra("playerName", playerName);
            startActivity(intent);
        }
        else if(flag == 0) {
            Intent intent = new Intent(Grid.this, Result.class);
            intent.putExtra("result", 0);
            intent.putExtra("score", 0);
            intent.putExtra("playerName", playerName);
            startActivity(intent);
        }
    }

    public void restart(View view) {
        Intent obj2 = new Intent(this, Grid.class);
        startActivity(obj2);
    }

    public void quit(View view) {
        Intent obj3 = new Intent(this, Home.class);
        //obj3.putExtra("result", 2);
        startActivity(obj3);
    }

    public void openDialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        // Set Custom Title
        TextView title = new TextView(this);
        // Title Properties
        title.setText("Penalty Question! Save your life!");
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        alertDialog.setCustomTitle(title);

        // Set Message
        TextView msg = new TextView(this);
        // choosing question
        int set = 0;
        String s = "";
        while (set != 1) {
            double m = (Math.random() * ((13 - 1) + 1)) + 1; //since higher limit excluded
            ques = (int) m;
            ques = ques % 12;
            if (used[ques] == 0) {
                s = questions[ques];
                used[ques] = 1;
                set = 1;
            }
        }

        msg.setText(s);
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);

        // Set Button you can more buttons
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "True", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //if answer matches then lives ++
                if(answers[ques] == "True") {
                    Toast.makeText(getApplicationContext(),"Correct answer!",Toast.LENGTH_SHORT).show();
                    increasescore();
                    dialog.cancel();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong answer! Life Lost",Toast.LENGTH_SHORT).show();
                    sendanswer();
                }

            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "False", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if(answers[ques] == "False") {
                    Toast.makeText(getApplicationContext(),"Correct answer! Scored",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong answer! Life Lost",Toast.LENGTH_SHORT).show();
                    sendanswer();
                }

            }
        });

        new Dialog(getApplicationContext());
        alertDialog.show();

        // Set Properties for OK Button
        final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        okBT.setPadding(50, 10, 10, 10);   // Set Position
        okBT.setTextColor(Color.BLUE);

        final Button cancelBT = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        cancelBT.setTextColor(Color.BLUE);
    }

    //to increase score in case of correct answer
    public void increasescore() {
        score++;
    }

    //to decrease lives in case of wrong answer
    public void sendanswer() {
        nooflives--;
        if(nooflives == 0) {
            thisone();
        }
        String setnow = String.valueOf(nooflives);
        livest.setText(setnow);
    }

    public void mine() { //will randomly generate 6 distinct random numbers between 1-16, which will act as mines
        arr = new int[16]; //to ensure no repetition
        for (int i =0; i<16; i++) { //initialization
            arr[i] = 0;
        }
        int total = 0;
        while(total != 6) {
            double m = (Math.random() * ((17 - 1) + 1)) + 1; //since higher limit excluded
            int mine = (int) m;
            mine = mine % 16;
            if (arr[mine] == 0)
            {
                arr[mine] = 1;  //i.e. it is now a mine
                total ++;
            }
        }
    }

    //if mine, question is asked and time decreased
    public void ifmine() {
        Toast.makeText(this, "You have clicked on a mine! Answer the penalty question", Toast.LENGTH_SHORT).show(); //quiz dialog box open
        openDialog();
    }

    //if not mine, then counter decreased and if all green done then result displayed
    public void ifnotmine() {
        green --;
        if(green == 0) {
            flag = 1;
            thisone();
        }
    }

    public void f1(View view) {
        if(arr[0] == 1) { //clicked on mine
            b1.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b1.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f2(View view) {
        if(arr[1] == 1) { //clicked on mine
            b2.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b2.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f3(View view) {
        if(arr[2] == 1) { //clicked on mine
            b3.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b3.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f4(View view) {
        if(arr[3] == 1) { //clicked on mine
            b4.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b4.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f5(View view) {
        if(arr[4] == 1) { //clicked on mine
            b5.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b5.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f6(View view) {
        if(arr[5] == 1) { //clicked on mine
            b6.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b6.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f7(View view) {
        if(arr[6] == 1) { //clicked on mine
            b7.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b7.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f8(View view) {
        if(arr[7] == 1) { //clicked on mine
            b8.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b8.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f9(View view) {
        if(arr[8] == 1) { //clicked on mine
            b9.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b9.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f10(View view) {
        if(arr[9] == 1) { //clicked on mine
            b10.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b10.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f11(View view) {
        if(arr[10] == 1) { //clicked on mine
            b11.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b11.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f12(View view) {
        if(arr[11] == 1) { //clicked on mine
            b12.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b12.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f13(View view) {
        if(arr[12] == 1) { //clicked on mine
            b13.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b13.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f14(View view) {
        if(arr[13] == 1) { //clicked on mine
            b14.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b14.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f15(View view) {
        if(arr[14] == 1) { //clicked on mine
            b15.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b15.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }

    public void f16(View view) {
        if(arr[15] == 1) { //clicked on mine
            b16.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else {
            b16.setImageResource(android.R.drawable.presence_online);
            ifnotmine();
        }
    }
}
