package com.markediez.java.boardgames;

import javax.swing.*;
import java.awt.*;

/**
 * Created by byrus on 8/1/2015.
 */
public class GamePiece extends JButton {
    // Public variables
    public static enum PieceColor{RED, YELLOW, BLANK};
    // Location
    private int row, col;
    private PieceColor color;

    // Constructor
    public GamePiece(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.color = PieceColor.BLANK;
        this.setText(this.color.name());
        this.setEnabled(false);
    }

    public GamePiece(PieceColor color) {
        this.color = color;
    }

    // Public Methods
    public void check(GamePiece piece) {
        if(piece.getPieceColor() != PieceColor.BLANK && this.color == PieceColor.BLANK) {
            this.setEnabled(true);
        } else {
            this.setEnabled(false);
        }
    }
    public void place(int turn) {
        int remainder = turn % 2;
        if(remainder == 0) {
            this.color = PieceColor.RED;
        } else {
            this.color = PieceColor.YELLOW;
        }
        this.setText("");
        this.setColor(color);
        this.setEnabled(false);
    }

    /**
     * Checks if the PieceType are similar
     * @param gamePiece
     * @return true if similar PieceType
     */
    public boolean checkConnection(GamePiece gamePiece) {
        if(this.color == gamePiece.color) {
            return true;
        } else {
            return false;
        }
    }

    // Private Methods
    private void setColor(PieceColor c) {
        if (c == PieceColor.YELLOW) {
            this.setBackground(Color.YELLOW);
        } else if( c== PieceColor.RED) {
            this.setBackground(Color.RED);
        } else {
            this.setBackground(null);
            this.setText("BLANK");
            this.setEnabled(false);
        }
    }
    // Getters and Setters
    public int getRow() { return this.row; }
    public int getCol() { return this.col; }
    public PieceColor getPieceColor() { return this.color; }
    public String getColor() { return this.color.name(); }

    public void setPieceColor(PieceColor color) { this.color = color; setColor(color); }

}
