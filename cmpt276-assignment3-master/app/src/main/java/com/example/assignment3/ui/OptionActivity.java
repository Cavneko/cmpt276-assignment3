package com.example.assignment3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.assignment3.R;
import com.example.assignment3.model.Mines;

public class OptionActivity extends AppCompatActivity {

    private Mines mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        //Locking Activity orientation
        Log.e("Assignment3", "Running onCreate()!");

        mine = Mines.getInstance();
        createRadioSizeButton();
        createRadioMineButton();

        int savedSize = GetBoardSizeInstalled(this);
        int savedMine = GetMineNumInstalled(this);
    }

    private void createRadioMineButton() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_numMine);

        final int[] numSize = getResources().getIntArray(R.array.mineSize);
        //create buttons
        for (int i = 0; i < numSize.length; i++) {
            final int num = numSize[i];

            RadioButton button = new RadioButton(this);
            button.setText(num + " mines");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //save the value
                    saveMineNum(num);
                    refreshData();
                }
            });

            //add to radio group
            group.addView(button);
            // Select default button
            if (num == GetMineNumInstalled(this)) {
                button.setChecked(true);
            }
        }
    }

    private void createRadioSizeButton() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radio_gameSize);

        final int[] numSize = getResources().getIntArray(R.array.gameSize);

        //create buttons
        for (int i = 0; i < numSize.length; i++) {
            final int num = numSize[i];

            RadioButton button = new RadioButton(this);
            button.setText(num + " rows");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //save the value
                    saveSize(num);
                    refreshData();
                }
            });

            //add to radio group
            group.addView(button);
            // Select default button
            if (num == GetBoardSizeInstalled(this)) {
                button.setChecked(true);
            }
        }

    }

    private void refreshData() {
        int newRow = GetBoardSizeInstalled(this);
        int newNumMine = GetMineNumInstalled(this);
        mine.setRow(newRow);
        if(newRow == 4){
            mine.setCol(6);
        }
        else if (newRow == 5){
            mine.setCol(10);
        }
        else{
            mine.setCol(15);
        }
        mine.setNum(newNumMine);
    }

    private void saveSize(int num) {
        SharedPreferences prefs = this.getSharedPreferences("SizePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Board Size", num);
        editor.apply();
    }

    static public int GetBoardSizeInstalled(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("SizePrefs", MODE_PRIVATE);
        int defaultNum = context.getResources().getInteger(R.integer.default_Size);
        return prefs.getInt("Board Size", defaultNum);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, OptionActivity.class);
    }


    private void saveMineNum(int num) {
        SharedPreferences prefs = this.getSharedPreferences("MinePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Mine Num", num);
        editor.apply();
    }

    static public int GetMineNumInstalled(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("MinePrefs", MODE_PRIVATE);
        int defaultNum = context.getResources().getInteger(R.integer.default_mines);
        return prefs.getInt("Mine Num", defaultNum);
    }

}