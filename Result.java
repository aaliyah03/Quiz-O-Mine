package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Result extends AppCompatActivity {

    String s;
    int gotresult;
    DatabaseHelper mydb;
    TextView t1;
    TextView topscoresTV;
    ImageView i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mydb =  new DatabaseHelper(this);
        t1 = (TextView) findViewById(R.id.ress);
        i1 = (ImageView) findViewById(R.id.ressult);
        topscoresTV = (TextView) findViewById(R.id.topscoresTV);

        Intent i = getIntent();
        //gotresult = i.getIntExtra("result",0);
        s = i.getStringExtra("result");

        if(s.equalsIgnoreCase("YOU HAVE WON!")) {
            i1.setImageResource(R.drawable.sad);
        }

        if(s.equalsIgnoreCase("YOU HAVE LOST!")) {
            i1.setImageResource(R.drawable.sad);
        }

        /*if(gotresult == 0) {
            s = "YOU HAVE LOST!";
            i1.setImageResource(R.drawable.sad);
        }
        else if (gotresult == 1){
            s = "YOU HAVE WON";
            i1.setImageResource(R.drawable.happy);
        }
        else if(gotresult == -1) {
            s = ":o";
        }*/
        t1.setText(s);
        getScores();
    }

    public void gotohomeagain(View view) {
        Intent obj = new Intent(this, MainActivity.class);
        startActivity(obj);
    }

    public void replay(View view) {
        Intent obj2 = new Intent(this, Grid.class);
        startActivity(obj2);
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
    }

}
