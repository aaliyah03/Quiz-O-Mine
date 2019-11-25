package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;

public class Home extends AppCompatActivity
{
    Spinner s1;
    Button b1;
    int flag = 0;
    MediaPlayer mobj;
    Button addUser;
    DatabaseHelper mydb;
    EditText etname;
    Spinner allPlayers;
    String playerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mobj = new MediaPlayer();
        s1 = (Spinner) findViewById(R.id.spinner1);
        b1 = (Button) findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = String.valueOf(s1.getSelectedItem());
                if (string.equalsIgnoreCase("Mellow")) {
                    playSong1();
                } else if (string.equalsIgnoreCase("Ambience")) {
                    playSong2();
                } else {
                    playSong3();
                }
            }
        });

        mydb = new DatabaseHelper(this);

        addUser = (Button) findViewById(R.id.addbutton);
        etname = (EditText) findViewById(R.id.insertET);
        allPlayers = (Spinner) findViewById(R.id.spinner);
        loadSpinner();
        addPlayer();
    }

    public void addPlayer()
    {
        addUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playerName = etname.getText().toString();
                        boolean isInserted= mydb.insertPlayer(playerName,
                                "0");
                        if(isInserted ==true)
                            Toast.makeText(Home.this, "Player added", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Home.this, "Player not added", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        loadSpinner(); //with new data
    }

    public void loadSpinner() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<String> names = db.getNames();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, names);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        allPlayers.setAdapter(dataAdapter);
        allPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                playerName = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "You selected: " + playerName,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        obj.putExtra("playerName", playerName);
        startActivity(obj);
    }

    public void rulesopen(View view) {
        Intent obj2 = new Intent(this, Rules.class);
        startActivity(obj2);
    }

}
