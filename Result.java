package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;

import org.w3c.dom.Text;

public class Result extends AppCompatActivity {

    String s, playerName;
    int gotresult, score;

    TextView t1;
    ImageView i1;
    DatabaseHelper mydb;
    TextView topscoresTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        t1 = (TextView) findViewById(R.id.ress);
        i1 = (ImageView) findViewById(R.id.ressult);

        mydb =  new DatabaseHelper(this);
        topscoresTV = (TextView) findViewById(R.id.topscoresTV);

        Intent i = getIntent();
        gotresult = i.getIntExtra("result",3);
        score = i.getIntExtra("score", 0);
        playerName = i.getStringExtra("playerName");
        String sc = "Score: "+ String.valueOf(score);
        Toast.makeText(this, sc, Toast.LENGTH_SHORT).show();
        updateScore();
        if(gotresult == 1) {
            s = "YOU HAVE WON!";
            i1.setImageResource(R.drawable.happy);
        }
        else if (gotresult == 0){
            s = "YOU HAVE LOST!";
            i1.setImageResource(R.drawable.sad);
        }
        else if (gotresult == 2){
            s = "YOU HAVE QUIT!";
            i1.setImageResource(R.drawable.sad);
        }
        t1.setText(s);

        getScores();
    }

    public void gotohomeagain(View view) {
        Intent obj = new Intent(this, Home.class);
        startActivity(obj);
    }

    public void replay(View view) {
        Intent obj2 = new Intent(this, Grid.class);
        startActivity(obj2);
    }

    public void updateScore()
    {
        Cursor res = mydb.getPlayerScore(playerName);
        res.moveToFirst();

        //getting the value from score column
        String s = res.getString(res.getColumnIndex("SCORE"));
        int sc = Integer.parseInt(s);
        score = score + sc;
        s = Integer.toString(score);
        boolean b = mydb.updatePlayer(playerName, s);
        if(b)
        {
            Toast.makeText(this,"Your score has been updated", Toast.LENGTH_SHORT).show();
        }
        res.close();
    }

    public void getScores()
    {
        Cursor res = mydb.getTopScores();
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append(res.getString(0)+ "  ");
            buffer.append(res.getString(1)+"  ");
            buffer.append(res.getString(2)+"\n");

        }
        String s = buffer.toString();
        topscoresTV.setText(s);
        res.close();
    }

}
