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
    private int connections;
    private JPanel gameBoard;
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
        gameBoard = new JPanel(new GridLayout(7,7));
        pieces = new GamePiece[7][7];

        // Initialize JButtons
        setUpGame();

        // form
        JPanel form = new JPanel(new GridLayout(2,1));
        form.add(header);
        form.add(gameBoard);
        // Add Panels
        this.add(form);
        checkAvailableSpot(pieces);
    } // end of init

    // Checks if a spot is available
    public void setUpGame() {
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = new GamePiece(i, j);
                pieces[i][j].addActionListener(this);
                gameBoard.add(pieces[i][j]);
            }
        }
    }
    public void refresh() {
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                connections = 0;
                pieces[i][j].setPieceColor(GamePiece.PieceColor.BLANK);
                checkAvailableSpot(pieces);
            }
        }
    }
    public void checkAvailableSpot(GamePiece[][] gp) {
        for(int i = 0; i < gp.length; i++) {
            for(int j = 0; j < gp[i].length; j++) {
                try {
                    gp[i][j].check(gp[i + 1][j]);
                } catch (Exception e) {
                    gp[i][j].check(new GamePiece(GamePiece.PieceColor.RED));
                }
            }
        }
    } // end of checkAvailableSpot

    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                if(e.getSource() == pieces[i][j]) {
                    connections = 0;
                    pieces[i][j].place(turns);
                    checkWinStatus(pieces[i][j]);
                    turns++;
                    turnLabel.setText("Turns: " + turns);
                    checkAvailableSpot(pieces);
                } // end of if
            } // end of inner for loop
        } // end of outer for loop
    } // end of actionPerformed

    public void checkWinStatus(GamePiece piece) {
        System.out.println("Checking Win Status");
        if(checkBottom(piece) || checkLeft(piece) || checkBottomLeft(piece) || checkBottomRight(piece)) {
            int answer = JOptionPane.showConfirmDialog(null,"Would you like to play again?",piece.getColor() + " WINS!",JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                System.out.println("YES");
                refresh();
                checkAvailableSpot(pieces);
                repaint();
            } else {
                System.exit(0);
            }
        }
        System.out.println("Connections: " + connections);
    }

    public boolean checkBottomRight(GamePiece piece) {
        try {
            GamePiece pieceToCheck = pieces[piece.getRow()+1][piece.getCol()+1];
            if(piece.checkConnection(pieceToCheck)) {
                connections++;
                checkBottomRight(pieceToCheck);
            }
        } catch(Exception e) {
            System.out.println("Hit the corner wall!");
        }

        if (connections >= 3) {
            return true;
        } else {
            connections = 0;
            return checkTopLeft(piece);
        }
    }

    public boolean checkTopLeft(GamePiece piece) {
        try {
            GamePiece pieceToCheck = pieces[piece.getRow()-1][piece.getCol()-1];
            if(piece.checkConnection(pieceToCheck)) {
                connections++;
                checkTopLeft(pieceToCheck);
            }
        } catch(Exception e) {
            System.out.println("Hit the corner wall!");
        }

        if (connections >= 3) {
            return true;
        } else {
            connections = 0;
            return false;
        }
    }

    public boolean checkBottomLeft(GamePiece piece) {
        try {
            GamePiece pieceToCheck = pieces[piece.getRow()+1][piece.getCol()-1];
            if(piece.checkConnection(pieceToCheck)) {
                connections++;
                checkBottomLeft(pieceToCheck);
            }
        } catch(Exception e) {
            System.out.println("Hit the corner wall!");
        }

        if (connections >= 3) {
            return true;
        } else {
            connections = 0;
            return checkTopRight(piece);
        }
    }

    public boolean checkTopRight(GamePiece piece) {
        try {
            GamePiece pieceToCheck = pieces[piece.getRow()-1][piece.getCol()+1];
            if(piece.checkConnection(pieceToCheck)) {
                connections++;
                checkTopRight(pieceToCheck);
            }
        } catch(Exception e) {
            System.out.println("Hit the corner wall!");
        }

        if (connections >= 3) {
            return true;
        } else {
            connections = 0;
            return false;
        }
    }

    public boolean checkBottom(GamePiece piece) {
        try {
            GamePiece pieceToCheck = pieces[piece.getRow()+1][piece.getCol()];
            if(piece.checkConnection(pieceToCheck)) {
                connections++;
                checkBottom(pieceToCheck);
            }
        } catch(Exception e) {
            System.out.println("Hit the ground!");
        }

        if (connections >= 3) {
            return true;
        } else {
            connections = 0;
            return false;
        }
    }

    public boolean checkLeft(GamePiece piece) {
        try {
            GamePiece pieceToCheck = pieces[piece.getRow()][piece.getCol() - 1];
            if(piece.checkConnection(pieceToCheck)) {
                connections++;
                checkLeft(pieceToCheck);
            }
        } catch(Exception e) {
            System.out.println("Hit the left wall!");
        }

        if (connections >= 3) {
            return true;
        } else {
            connections = 0;
            return checkRight(piece);
        }
    }

    public boolean checkRight(GamePiece piece) {
        try {
            GamePiece pieceToCheck = pieces[piece.getRow()][piece.getCol() + 1];
            if(piece.checkConnection(pieceToCheck)) {
                connections++;
                checkRight(pieceToCheck);
            }
        } catch(Exception e) {
            System.out.println("Hit the right wall!");
        }

        if (connections >= 3) {
            return true;
        } else {
            connections = 0;
            return false;
        }
    }
} // end of class
