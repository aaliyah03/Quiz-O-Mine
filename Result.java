//lots of errors cuz the latest version doesnt support basic stuff?! this should ideally work.. let me know if there are any errors
package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.Random;

public class Result extends AppCompatActivity {

    Intent i;
    int score;
    Integer[] pics=new Image[];

    //defining the pics array
    pics[]=[R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5,
    R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10, R.drawable.pic11];

    ImageView imageV = (ImageView) findViewById(R.id.picIV);
    TextView scoretv = (TextView) findViewById(R.id.resultTV);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        i=getIntent();
        score=i.getIntExtra("score");
        String s= "SCORE: "+score;
        scoretv.setText(s);
    }

    public void goHome(View view)
    {
        Intent i= new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void displayPic(View view)
    {
        int max=10, min=0;
        if(score==0)
            //pic 11 shows pic unavailable image
            imageV.setImageResource(R.drawable.pic11);
        else
        {
            Random r= new Random();
            int index= r.nextInt((max - min) + 1) + min;
            Drawable d = getResources().getDrawable(pics[index]);
            imageV.setImageDrawable(d);
        }

    }
}
