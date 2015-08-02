package com.markediez.java.boardgames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mark Diez on 8/1/2015.
 */
public class ConnectFour extends JPanel implements ActionListener{
    private int turns;
    private JLabel turnLabel;
    private GamePiece[][] pieces;

    public ConnectFour() {
        this.setPreferredSize(new Dimension(800, 500));
        this.init();
    } // end of constructor

    private void init() {
        // Header
        JPanel header = new JPanel(new GridLayout(2,1));
        turns = 0;
        JLabel title = new JLabel("Connect 4");
        turnLabel = new JLabel("Turns: " + turns);
        header.add(title);
        header.add(turnLabel);

        // Board
        JPanel gameBoard = new JPanel(new GridLayout(7,7));
        pieces = new GamePiece[7][7];

        // Initialize JButtons
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = new GamePiece(i, j);
                pieces[i][j].addActionListener(this);
                gameBoard.add(pieces[i][j]);
            }
        }

        // form
        JPanel form = new JPanel(new GridLayout(2,1));
        form.add(header);
        form.add(gameBoard);
        // Add Panels
        this.add(form);
        checkAvailableSpot(pieces);
    } // end of init

    // Checks
    public void checkAvailableSpot(GamePiece[][] gp) {
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                try {
                    pieces[i][j].check(pieces[i + 1][j]);
                } catch (Exception e) {
                    System.out.println("in");
                    pieces[i][j].check(new GamePiece(GamePiece.PieceColor.RED));
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                if(e.getSource() == pieces[i][j]) {
                    pieces[i][j].place(turns);
                    turns++;
                    turnLabel.setText("Turns: " + turns);
                    checkAvailableSpot(pieces);
                } // end of if
            } // end of inner for loop
        } // end of outer for loop
    } // end of actionPerformed
} // end of class
