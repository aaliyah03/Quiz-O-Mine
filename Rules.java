package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        Intent i = new Intent();
    }

    public void gotohome(View view) {
        Intent obj = new Intent(this, Home.class);
        startActivity(obj);
    }

    public void gotogame(View view) {
        Intent obj2 = new Intent(this, Grid.class);
        startActivity(obj2);
    }
}
