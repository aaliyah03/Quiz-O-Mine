//still working on countdown timer
package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import java.math.*;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Grid extends AppCompatActivity {

    ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    TextView t1, t2;
    int arr[];
    int nooflives;
    public int counter1 = 9;
    public int counter2 = 59;

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
        t1 = (TextView) findViewById(R.id.lives);

        final TextView counttime1 = findViewById(R.id.timermin);
        final TextView counttime2 = (TextView) findViewById(R.id.timersec);

        new CountDownTimer(600000,60000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counttime1.setText(String.valueOf(counter1));
                counter1 --;
            }
            @Override
            public void onFinish() {
                counttime1.setText("Finished");
                timeup();
            }
        }.start();

        while(counter1!=0) {
            new CountDownTimer(59000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    counttime2.setText(String.valueOf(counter2));
                    counter2 --;
                }
                @Override
                public void onFinish() {

                }
            }.start();

        }

        Intent in = getIntent();
        mine();     //mines have been generated
        nooflives = 3;
    }

    public void timeup() {
        Toast.makeText(this, "Your time is up!", Toast.LENGTH_SHORT).show(); //add intent to result activity
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

    public void ifmine() {
        Toast.makeText(this, "You have clicked on a mine!", Toast.LENGTH_SHORT).show(); //add intent to quiz activity
        nooflives--; //lives decreased
        if(nooflives == 0) {
            Toast.makeText(this, "You have lost all lives!", Toast.LENGTH_SHORT).show(); //add intent to result activity
        }
        else {
            String s = String.valueOf(nooflives);
            t1.setText(s);
        }
    }

    public void f1(View view) {
        if(arr[0] == 1) { //clicked on mine
            b1.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b1.setImageResource(android.R.drawable.presence_online);
    }

    public void f2(View view) {
        if(arr[1] == 1) { //clicked on mine
            b2.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b2.setImageResource(android.R.drawable.presence_online);

    }

    public void f3(View view) {
        if(arr[2] == 1) { //clicked on mine
            b3.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b3.setImageResource(android.R.drawable.presence_online);
    }

    public void f4(View view) {
        if(arr[3] == 1) { //clicked on mine
            b4.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b4.setImageResource(android.R.drawable.presence_online);
    }

    public void f5(View view) {
        if(arr[4] == 1) { //clicked on mine
            b5.setImageResource(android.R.drawable.ic_delete);
           ifmine();
        }
        else
            b5.setImageResource(android.R.drawable.presence_online);
    }

    public void f6(View view) {
        if(arr[5] == 1) { //clicked on mine
            b6.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b6.setImageResource(android.R.drawable.presence_online);
    }

    public void f7(View view) {
        if(arr[6] == 1) { //clicked on mine
            b7.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b7.setImageResource(android.R.drawable.presence_online);
    }

    public void f8(View view) {
        if(arr[7] == 1) { //clicked on mine
            b8.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b8.setImageResource(android.R.drawable.presence_online);
    }

    public void f9(View view) {
        if(arr[8] == 1) { //clicked on mine
            b9.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b9.setImageResource(android.R.drawable.presence_online);
    }

    public void f10(View view) {
        if(arr[9] == 1) { //clicked on mine
            b10.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b10.setImageResource(android.R.drawable.presence_online);
    }

    public void f11(View view) {
        if(arr[10] == 1) { //clicked on mine
            b11.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b11.setImageResource(android.R.drawable.presence_online);
    }

    public void f12(View view) {
        if(arr[11] == 1) { //clicked on mine
            b12.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b12.setImageResource(android.R.drawable.presence_online);
    }

    public void f13(View view) {
        if(arr[12] == 1) { //clicked on mine
            b13.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b13.setImageResource(android.R.drawable.presence_online);
    }

    public void f14(View view) {
        if(arr[13] == 1) { //clicked on mine
            b14.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b14.setImageResource(android.R.drawable.presence_online);
    }

    public void f15(View view) {
        if(arr[14] == 1) { //clicked on mine
            b15.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b15.setImageResource(android.R.drawable.presence_online);
    }

    public void f16(View view) {
        if(arr[15] == 1) { //clicked on mine
            b16.setImageResource(android.R.drawable.ic_delete);
            ifmine();
        }
        else
            b16.setImageResource(android.R.drawable.presence_online);
    }
}
