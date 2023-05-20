package view;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class LevelInterface {

    // The JPanel object that level buttons contain.
    public final JPanel myLevelButtonPanels;

    // The JButton object for level1.
    private final JButton myLevel1;

    // The JButton object for level2.
    private final JButton myLevel2;

    // The JButton object for level3.
    private final JButton myLevel3;

    private char[][] myLevelArrayMap;


    public LevelInterface(char[][] theLevelMap) {
        myLevelButtonPanels = new JPanel(new BorderLayout());
        myLevel1 = new JButton("Level1");
        myLevel2     = new JButton("Level2");
        myLevel3 = new JButton("Level3");
        myLevelArrayMap = theLevelMap;
        start();
    }

    private void start() {
        myLevelButtonPanels.add(myLevel1);
        myLevelButtonPanels.add(myLevel2);
        myLevelButtonPanels.add(myLevel3);
        myLevelButtonPanels.setLayout(new GridLayout(3,1));

        myLevel1.addActionListener(new ActionListener() {
        public void actionPerformed(final ActionEvent theEvent) {
            System.out.println("it clicked");
        }
        });
        myLevel2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                System.out.println("it clicked");
            }
        });

        myLevel3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                System.out.println("it clicked");
            }
        });
}
    }
