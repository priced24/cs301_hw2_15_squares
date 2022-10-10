package com.example.cs301_hw2_15squares;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {
    // declare instance variables
    private float scrWidth;
    private ArrayList<Square> squares, solution;

    // constructor for GameBoard class
    public GameBoard(float width) {
        // initialize array lists
        squares = new ArrayList();
        solution = new ArrayList();

        // set instance variables to passed in parameters
        scrWidth = width;

        // add square objects array list of squares
        for (int i = 0; i < 15; i++) {
            squares.add(new Square(i + 1, scrWidth));

            // also initialize the solution array (in ascending order)
            solution.add(new Square(i + 1, scrWidth));
        }
        // make 16th square in the list blank
        squares.add(new Square(0, scrWidth));
        solution.add(new Square(0, scrWidth));

        // make last square in the list blank
        squares.get(squares.size() - 1).setBlank();
        solution.get(solution.size() - 1).setBlank();

        // automatically shuffle squares list upon program start
        // Collections.shuffle(squares);
        isSolved();
    }

    public void shuffle() {
        // check if puzzle is solved prior to shuffling
        if (isSolved()) {
            resetSolved(); // reset each square's correct values to false
        }

        // call built-in shuffle method to shuffle array list (game board)
        Collections.shuffle(squares);

        // call additional method to see if puzzle is partially or completely solved
        inRightSpot();
        isSolved();
    }

    public void drawBoard(Canvas c) {
        // nested for loop to turn array list of square objects into 2d 4x4 grid
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int index = (4 * i) + j;
                squares.get(index).drawSquare(c, (float) (scrWidth / 4) * j, (float) (scrWidth / 4) * i);
            }
        }
    }

    public Square findSelection(int x, int y) {
        // iterate through each square in array list
        Log.i("within?", "" + x + " " + y);
        /* for (Square s : squares) {
            if (s.withinSquare(x, y)) {
                return s; // return square if finger/pen location is within user's desired square
            }
        } */
        int index = (4 * y) + x;
        return squares.get(index); // otherwise, return null
    }

    public boolean isValidSelection(Square user, Square blank) {
        // create two int variables, both of which are the index of user and blank, respectively
        int userPos = squares.indexOf(user);
        int blankPos = squares.indexOf(blank);

        // check edge cases of 4x4 grid, starting with top edge
        if (blankPos >= 0 && blankPos < 3) {
            /* reminder: checking if blankPos < 3 since the right edge will cover blankPos == 3 */
            if ((userPos == blankPos + 1) || (userPos == blankPos - 1) || (userPos == blankPos + 4)) {
                return true;
            }
        }
        // left edge of grid
        else if (blankPos % 4 == 0) {
            if ((userPos == blankPos + 1) || (userPos == blankPos - 4) || (userPos == blankPos + 4)) {
                return true;
            }
        }
        // right edge of grid
        else if (blankPos % 4 == 3) {
            if ((userPos == blankPos - 1) || (userPos == blankPos - 4) || (userPos == blankPos + 4)) {
                return true;
            }
        }
        // bottom edge
        else if (blankPos >= 12 && blankPos <= 15) {
            if ((userPos == blankPos + 1) || (userPos == blankPos - 1) || (userPos == blankPos - 4)) {
                return true;
            }
        }
        // middle of the board
        else {
            if ((userPos == blankPos + 1) || (userPos == blankPos - 1) || (userPos == blankPos + 4) || (userPos == blankPos - 4)) {
                return true;
            }
        }
        return false; // if neither statement is true, return false
    }

    public Square findBlank() {
        // iterate through each square in array list
        for (Square s : squares) {
            if (s.getBlank()) {
                return s; // return this square if blank is found
            }
        }
        return null; // otherwise, return null
    }

    public void swap(int x, int y) {
        // create two square variables for this method, one for user, one for blank
        Square sqUser = findSelection(x, y);
        Square blank = findBlank();

        // check if above square objects are valid selections
        if (isValidSelection(sqUser, blank)) {
            // call built-in swap method to swap the two squares around
            Collections.swap(squares, squares.indexOf(sqUser), squares.indexOf(blank));
            inRightSpot(); // call this method to check if the swap resulted in a square in correct spot

            // check if puzzle has been solved
            if (isSolved()) {
                // iterate through each square in array list
                for (Square s : squares) {
                    s.setSolved(true); // if solved, give every square the sqSolved color

                }
            }
        }
    }

    public void inRightSpot() {
        // iterate through the solution array list (regular for loop)
        for (int i = 0; i < solution.size(); i++) {
            if (squares.get(i).getSqText() == solution.get(i).getSqText()) {
                squares.get(i).setCorrect(true); // square gets sqCorrect color if statement is true
            } else {
                squares.get(i).setCorrect(false); // otherwise, return false
            }
        }
    }

    public boolean isSolved() {
        // iterate through squares array list (regular for loop)
        for (int i = 0; i < squares.size(); i++) {
            // calling getter method in Square class to check if there is square in wrong spot
            if (!squares.get(i).getCorrect()) {
                return false; // return false if statement is true;
            }
        }
        return true; // otherwise, return true;
    }


    public void resetSolved() {
        // iterate through squares array list (regular for loop)
        for (int i = 0; i < squares.size(); i++) {
            // set each square solved value to false
            squares.get(i).setSolved(false);
        }
    }
}
