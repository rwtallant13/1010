package com.company;

import java.awt.*;

/**
 * Created by Wesley Romary.
 */

public class Block {
    boolean[][] block;
    Color color;

    public Block(boolean[][] b, Color c){
        block = b;
        color = c;
    }
    public boolean[][] getBlock(){
        return block;
    }
    public Color[][] getColoredBlock(){
        /*
            When finished, returns colored blocks instead of boolean blocks
         */
        Color[][] board = new Color[block.length][block[0].length];
        return board;

    }

}
