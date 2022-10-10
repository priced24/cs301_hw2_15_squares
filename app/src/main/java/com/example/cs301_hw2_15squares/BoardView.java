package com.example.cs301_hw2_15squares;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class BoardView extends SurfaceView implements View.OnTouchListener, View.OnClickListener{
    // declare instance variables
    private GameBoard gb;
    private float scrWidth;
    private float scrHeight;

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // getting screen dimensions (needed to create 4x4 game board across all devices)
        scrWidth = context.getResources().getDisplayMetrics().widthPixels;
        scrHeight = context.getResources().getDisplayMetrics().heightPixels;

        setWillNotDraw(false);

        // setting required members
        gb = new GameBoard(scrWidth);
        // Log.i("draw board", "" + scrWidth + " " + scrHeight + " ");
    }

    protected void onDraw(Canvas c) {
        // call this method to draw game board to SurfaceView
        gb.drawBoard(c);
        // Log.i("draw", "a");
        // update SurfaceView to display game board
    }

    @Override
    public void onClick(View view) {
        // no need for if statement since there is only one button in the app
        // call shuffle method when button is clicked
        gb.shuffle();

        // update SurfaceView to display shuffled game
        invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent e) {
        if (e.getActionMasked() == MotionEvent.ACTION_DOWN) {
            // get pen/finger location
            int x = (int) e.getX();
            int y = (int) e.getY();

            // to do: convert x and y to actual square coordinates (0,3), etd...
            int convX = x / ((int) scrWidth / 4);
            int convY = y / ((int) scrWidth / 4);

            // log the x and y converted values before calling swap method
            // Log.i("get coordinates", "" + convX + " " + convY);
            // call swap method with given x and y values
            gb.swap(convX, convY);

            // update SurfaceView to display updated game board
            invalidate();
        }
        return true;
    }
}
