package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel {

    enum State {
        START, WON, RUNNING, OVER
    }

    private Board board;
    private State gamestate;
    /*
     * Example array with "all" possable blocks
     * Right now only contains the single box and the 2x2 box as a proof of concept
     */
    private Block[] blocks = new Block[]{new Block(new boolean[][]{{true},{true}},new Color(102,102,255)),
            new Block(new boolean[][]{{true, true},{true, true}}, new Color(153, 255, 51))};

    private Color gridBG = new Color(0xBBADA0);
    private Color emptyColor = new Color(0xCDC1B4);
    private Color startColor = new Color(0xFFEBCD);

    public Main(){
        gamestate =  State.START;
        setPreferredSize(new Dimension(900, 700));	//needs to be changed
        setBackground(new Color(43, 43, 43));
        setFont(new Font("SansSerif", Font.BOLD, 48));
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startGame();
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g);
    }

    void startGame(){
        if(gamestate != State.RUNNING){
            gamestate = State.RUNNING;
            board = new Board();
        }
    }
    void drawGrid(Graphics2D g){
        g.setColor(gridBG);
        g.fillRoundRect(140, 40, 610, 610, 15, 15);

        if(gamestate == State.RUNNING){
            for(int y = 0; y<board.getBoard().length; y++){
                for(int x = 0;x<board.getBoard().length; x++){
                    if(board.getBoard()[x][y] != null){
                        g.setColor(board.getBoard()[x][y]);
                        g.fillRoundRect(150 + x * 60, 50 + y * 60, 50, 50, 7, 7); //x,y,width,height,arcwidth,archeight
                    }else{
                        g.setColor(emptyColor);
                        g.fillRoundRect(150 + x * 60, 50 + y * 60, 50, 50, 7, 7);
                    }
                }
            }
        }else{
            g.setColor(startColor);
            g.fillRoundRect(140, 40, 610, 610, 15, 15);

            g.setColor(gridBG.darker());
            g.setFont(new Font("SansSerif", Font.BOLD, 128));
            g.drawString("1010", 290, 270);

            g.setFont(new Font("SansSerif", Font.BOLD, 20));

            if (gamestate == State.WON) {
                g.drawString("You won!", 390, 350);

            }
            if (gamestate == State.OVER)
                g.drawString("game over", 400, 350);
            if(board != null)
                g.drawString("Your Score: "+board.getScore(), 350, 400);

            g.setColor(gridBG);
            g.drawString("click to start a new game", 330, 470);
            g.drawString("Use mouse to drag tiles", 335, 530);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("1010");
        frame.setResizable(true);
        frame.add(new Main(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
