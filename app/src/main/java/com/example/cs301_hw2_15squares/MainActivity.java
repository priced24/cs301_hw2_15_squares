package com.example.cs301_hw2_15squares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // finding views
        Button shuffleButton = findViewById(R.id.shuffleButton);
        BoardView gameBoard = findViewById(R.id.gameBoard);

        // linking views to an action
        shuffleButton.setOnClickListener(gameBoard);
        gameBoard.setOnTouchListener(gameBoard);
    }
}