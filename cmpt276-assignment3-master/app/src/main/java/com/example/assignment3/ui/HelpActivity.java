package com.example.assignment3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.assignment3.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        //Locking Activity orientation
        Log.e("Assignment3", "Running onCreate()!");
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, HelpActivity.class);
    }

}