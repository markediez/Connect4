package com.markediez.java.boardgames;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mark Diez on 8/1/2015.
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Connect 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(new ConnectFour(), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
