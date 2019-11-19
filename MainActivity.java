//working on the db functions

package com.example.android.iqomine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nameip;
    TextView scores;
    DBHandler db;
    Intent i;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= new DBHandler(this);
        nameip = (EditText) findViewById(R.id.newPlayerET);
        scores= (TextView) findViewById(R.id.highScoresTV);

        displayHighScores();
        i= getIntent();
    }

    public void playGame(View view)
    {
        Intent intent=new Intent(this, Grid.class);
        startActivity(intent);
    }

    public void addPlayerToDB(View view)
    {
        String name =  nameip.getText().toString();
        db.addPlayer(new PlayerDB(id, name, 0 ));
    }

    public void displayHighScores()
    {
        List<PlayerDB> players = db.getPlayers();
        for (PlayerDB i : players) {
            String s = i.getID() + "  " + i.getName() + "   " + i.getScore();
            scores.setText(s);
        }
    }
}

