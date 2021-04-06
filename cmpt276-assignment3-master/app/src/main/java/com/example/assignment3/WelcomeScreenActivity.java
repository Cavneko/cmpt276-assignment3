package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment3.ui.MainMenuActivity;

public class WelcomeScreenActivity extends AppCompatActivity {

    //variables
    Animation topAnimation;
    Animation rotateAnimation;
    ImageView image;
    TextView logo;



    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome_screen);

        //Animation
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);

        //Hooks
        logo = findViewById(R.id.textView3);
        logo.setAnimation(topAnimation);
        image = findViewById(R.id.imageView4);
        image.setAnimation(rotateAnimation);

        //Button for skip the Welcome Screen
        Button button = (Button) findViewById(R.id.Skip);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                skipSplashScreen();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openMainMenuActivity();
            }
        }, 5000);
    }

    public void openMainMenuActivity()
    {
        Intent intent = new Intent(WelcomeScreenActivity.this,MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void skipSplashScreen(){
        if (handler != null)
            handler.removeCallbacksAndMessages(null);
        openMainMenuActivity();
    }

}