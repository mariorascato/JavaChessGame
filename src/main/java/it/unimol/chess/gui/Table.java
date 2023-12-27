package it.unimol.chess.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {
    private final JFrame gameFrame;
    private static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    public Table() {
        this.gameFrame = new JFrame("Jchess");
        final JMenuBar tableMenuBar = new JMenuBar();
        populateMenuBar(tableMenuBar);
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setVisible(true);
    }

    private void populateMenuBar(final JMenuBar tableMenuBar) {
        tableMenuBar.add(createFileMenu());
    }
    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem openPGN = new JMenuItem("Carica file PGN ");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("apri il file PNG");
            }
        });
        fileMenu.add(openPGN);

        return fileMenu;
    }
}
