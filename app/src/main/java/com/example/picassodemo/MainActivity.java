package com.example.picassodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    String url = "https://i.pinimg.com/originals/f5/7e/00/f57e00306f3183cc39fa919fec41418b.jpg";
    ImageView imageView;
    Button button, button2, button3;
    RotateAnimation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.ivDog);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        ImageCreate();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageTransform();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SideDog();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ErrorDog();
            }
        });
    }

    /**
     * Uses Picasso to grab an image.
     */
    public void ImageCreate(){
        Picasso.get().load(url).into(imageView);
    }

    /**
     * Using Picasso methods to alter and resize images it takes in.
     */
    public void ImageTransform(){
        imageView.clearAnimation();
        Picasso.get()
                .load(url)
                .resize(150, 150)
                .centerCrop()
                .into(imageView);
    }

    /**
     * Using Picasso to rotate the image it takes in, not just resize.
     */
    public void SideDog(){
        imageView.clearAnimation();
        Picasso.get()
                .load(url)
                .rotate(90)
                .into(imageView);
    }

    /**
     * Using Picasso callback methods when the image successfully loads.
     */
    public void ErrorDog(){
        Picasso.get()
                .load(url)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        anim.setInterpolator(new LinearInterpolator());
                        anim.setRepeatCount(Animation.INFINITE);
                        anim.setDuration(7000);
                        imageView.startAnimation(anim);
                        Toast.makeText(MainActivity.this, "Success Dog", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(MainActivity.this, "Error Dog", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
