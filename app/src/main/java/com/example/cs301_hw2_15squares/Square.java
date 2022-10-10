package com.example.cs301_hw2_15squares;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Square {
    // declare instance variables
    private float sqSide, xCoord, yCoord;
    private int sqText;
    private boolean blank, selected, correct, solved;
    private Paint sqDefault, sqSelected, sqCorrect, sqSolved, text;
    private Rect rect;


    // constructor for Square class
    public Square(int number, float width) {
        // give values to square contents (side and its number)
        sqSide = width / 4; // divide screen width by 4 in order to fit 4 squares in single row
        sqText = number;

        // instantiate paint variables
        sqDefault = new Paint();
        sqSelected = new Paint();
        sqCorrect = new Paint();
        sqSolved = new Paint();
        text = new Paint();
        sqDefault.setColor(0xFFCA2C92); // default square color = fuchsia
        sqSelected.setColor(0xFFFFD68A); // selected color = light orange variant
        sqCorrect.setColor(0xFF00A36C); // correct color = jade green
        sqSolved.setColor(0xFFD8F2FF); // solved color = sky blue variant
        text.setColor(Color.WHITE);
        text.setTextSize(sqSide / 2); // square text should be 1/8 the screen width

        // instantiate boolean variables
        blank = selected = correct = solved = false;
    }

    // duplicate constructor for Square class
    public Square(Square copy) {
        /* set values for new square object equal to copy's values */
        // basic square contents
        sqSide = copy.sqSide;
        sqText = copy.sqText;

        // paint variables
        sqDefault = copy.sqDefault;
        sqDefault.setColor(copy.sqDefault.getColor());

        sqSelected = copy.sqSelected;
        sqSelected.setColor(copy.sqSelected.getColor());

        sqCorrect = copy.sqCorrect;
        sqCorrect.setColor(copy.sqCorrect.getColor());

        sqSolved = copy.sqSolved;
        sqSolved.setColor(copy.sqSolved.getColor());

        text = copy.text;
        text.setColor(copy.text.getColor());
        text.setTextSize(copy.text.getTextSize());

        // boolean variables
        blank = copy.blank;
        selected = copy.selected;
        correct = copy.correct;
        solved = copy.solved;
    }

    public void drawSquare(Canvas c, float x, float y) {
        text.setTextAlign(Paint.Align.CENTER);
        rect = new Rect((int) x, (int) y, (int) (x + sqSide) - 15, (int) (y + sqSide) - 15);

        // only draw it if it is not blank square
        if (!blank) {
            if (selected) {
                c.drawRect(rect, sqSelected); // give square sqSelected color if statement true
            }
            else if (solved) {
                c.drawRect(rect, sqSolved); // give square sqCorrect color if true
                text.setColor(Color.BLACK); // make text black if true (aesthetic purpose)
            }
            else if (correct) {
                c.drawRect(rect, sqCorrect); // give square sqSolved color if true
            }
            else {
                c.drawRect(rect, sqDefault); // otherwise, give default color
            }
            c.drawText(Integer.toString(sqText), rect.centerX(), rect.centerY() + (text.getTextSize() / 3), text);
            text.setTextAlign(Paint.Align.CENTER); // align text to center of the square after drawing it
            text.setColor(Color.WHITE); // make text white after shuffling solved puzzle
            // Log.i("drawing square", "" + rect.centerX() + " " + rect.centerY() + " " + sqText + " " + text);
        }
    }

    public void setBlank() {
        blank = true;
    }

    public boolean getBlank() {
        return blank;
    }

    public int getSqText() {
        return sqText;
    }

    public boolean withinSquare(float x, float y) {
        return rect.contains((int) x, (int) y);
    }

    public void setCorrect(boolean correctSq) {
        correct = correctSq;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void setSolved(boolean complete) {
        solved = complete;
    }
}
