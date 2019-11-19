//image stuff left will do today

package com.example.android.game;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //image= (ImageView) findViewById(R.id.picIV);
    }

    public void goHome(View view)
    {
        Intent i= new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void displayPic(View view)
    {

    }
}
