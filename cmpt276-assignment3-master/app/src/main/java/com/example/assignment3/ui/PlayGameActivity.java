package com.example.assignment3.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.assignment3.R;
import com.example.assignment3.model.MessageFraqment;
import com.example.assignment3.model.Mines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayGameActivity extends AppCompatActivity {
    private Mines mine = Mines.getInstance();

    private final int Num_Rows = mine.getRow();
    private final int Num_Cols = mine.getCol();
    private final int Num_Mines = mine.getNum();
    private static int restNumMine = 0;
    private static int Num_Scan = 0;
    private static int Num_MinesFounded = 0;
    Button buttons[][] = new Button[Num_Rows][Num_Cols];
    int map[][] = new int[Num_Rows][Num_Cols];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        //Locking Activity orientation
        Log.e("Assignment3", "Running onCreate()!");

        resetData();
        setText();
        generatePanel();
        populateBoard();
    }

    private void resetData() {
        restNumMine = 0;
        Num_Scan = 0;
        Num_MinesFounded = 0;
    }

    private void setText() {
        TextView tv = findViewById(R.id.text_numMine);
        tv.setText("Found " + Num_MinesFounded + " of " + Num_Mines + " Mines");
        TextView tv2 = findViewById(R.id.text_numScan);
        tv2.setText("# Scans used: " + Num_Scan);
    }

    private void generatePanel() {
        int check[][] = new int[Num_Rows][Num_Cols];
        for(int i = 0; i < Num_Mines; i++){
            int rRow = (int) (Math.random() * Num_Rows);
            int rCol = (int) (Math.random() * Num_Cols);
            if(check[rRow][rCol] == 0){
                map[rRow][rCol] = 1;
                check[rRow][rCol] = 1;
            }
            else{
                i--;
            }

            System.out.println(rRow + "," + rCol + " *");
        }
    }

    private void populateBoard() {
        TableLayout table = (TableLayout) findViewById(R.id.Board);

        for(int row = 0; row < Num_Rows; row++){
            TableRow tablerow = new TableRow(this);
            table.addView(tablerow);
            tablerow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));

            for(int col = 0; col < Num_Cols; col++){
                final int finalRow = row;
                final int finalCol = col;
                final Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                //make text not clip on small buttons
                button.setPadding(0,0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridClickButton(finalRow,finalCol);
                    }
                });

                tablerow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridClickButton(int row, int col) {
        Button button = buttons[row][col];
        if(map[row][col] == 1){
            //Lock Button Sizes;
            lockButtonSizes();

            //Does not scale image
            //button.setBackgroundResource(R.drawable.bad_apple);

            //Scale image to button
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bad_apple);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resources = getResources();
            button.setBackground(new BitmapDrawable(resources, scaledBitmap));

            map[row][col] = 0;
            Num_MinesFounded++;
        }
        else{
            restNumMine = checkNumMines(row,col);
            button.setText("" + restNumMine);
        }
        refreshText();
        if(Num_MinesFounded == Num_Mines){
            FragmentManager manager = getSupportFragmentManager();
            MessageFraqment dialog = new MessageFraqment();
            dialog.show(manager, "MessageDialog");
        }
    }

    private void lockButtonSizes() {
        for(int row = 0; row < Num_Rows; row++) {
            for (int col = 0; col < Num_Cols; col++) {
                Button button = buttons[row][col];
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);

            }
        }
        if(Num_MinesFounded == Num_Mines){
            FragmentManager manager = getSupportFragmentManager();
            MessageFraqment dialog = new MessageFraqment();
            dialog.show(manager, "MessageDialog");

        }
    }

    private void refreshText() {
        TextView tv = findViewById(R.id.text_numMine);
        tv.setText("Found " + Num_MinesFounded + " of " + Num_Mines + " Mines");
        TextView tv2 = findViewById(R.id.text_numScan);
        tv2.setText("# Scans used: " + Num_Scan);
    }

    private int checkNumMines(int row, int col) {
        int count = 0;
        //check rows
        for(int i = 0; i < Num_Rows; i++){
            if(map[i][col] == 1){
                count++;
            }
        }
        //check cols
        for(int i = 0; i < Num_Cols; i++){
            if(map[row][i] == 1)
                count++;
        }
        Num_Scan++;
        return count;
    }


    public static Intent makeIntent(Context context) {
        return new Intent(context, PlayGameActivity.class);
    }


}