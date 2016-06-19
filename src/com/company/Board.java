package com.company;

import java.awt.*;

public class Board {
    private Color[][] board;
    private int score;

    public Board(){
        board = new Color[10][10];
        score = 0;
    }
    public Color[][] getBoard(){
        return board;
    }
    public int getScore(){
        return score;
    }
}
