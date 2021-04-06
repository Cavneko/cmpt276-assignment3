package com.example.assignment3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.assignment3.R;

public class MainMenuActivity extends AppCompatActivity {


    public static Intent makeIntent(DialogInterface.OnClickListener context) {
        return new Intent((Context) context, MainMenuActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu2);
        //Locking Activity orientation
        Log.e("Assignment3", "Running onCreate()!");
        setupPlayButton();
        setupOptionButton();
        setupHelpButton();
    }

    private void setupHelpButton() {
        Button btn = (Button) findViewById(R.id.help_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = HelpActivity.makeIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    private void setupOptionButton() {
        Button btn = (Button) findViewById(R.id.option_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OptionActivity.makeIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    private void setupPlayButton() {
        Button btn = (Button) findViewById(R.id.play_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = PlayGameActivity.makeIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


}