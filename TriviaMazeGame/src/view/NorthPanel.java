package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import model.Maze;

public class NorthPanel extends JPanel {

    private static NorthPanel myInstance;
    ;
    private JButton myStageButton;


    private JLabel myLevel;
    private JLabel myMoves;

    private boolean myStageButtonClicked;

    private NorthPanel(String theLevel, String theMoves) {
        myStageButtonClicked = false;
        myStageButton = new JButton("Stage");
        myLevel = new JLabel(theLevel, SwingConstants.CENTER);
        myMoves = new JLabel(theMoves, SwingConstants.CENTER);

        myStageButton.setBackground(Color.GRAY);

        this.add(myStageButton);
        this.add(myLevel);
        this.add(myMoves);
        this.setLayout(new GridLayout(1, 3));
        start();
    }

    public static NorthPanel getInstance(String theLevel, String theMoves) {
        if (myInstance == null) {
            synchronized (NorthPanel.class) {
                if (myInstance == null) {
                    myInstance = new NorthPanel(theLevel, theMoves);
                }
            }
        }
        return myInstance;
    }

    public void start() {
        myStageButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myStageButtonClicked = true;
            }

        });
    }
    public static NorthPanel getInstance() {
        return myInstance;
    }

    public boolean stageButton() {
        boolean flag = myStageButtonClicked;
        myStageButtonClicked = false;
        return flag;
    }

    public void setLevel(String theLevel) {
        myLevel.setText(theLevel);
    }

    public void setMoves(String theMoves) {
        myMoves.setText(theMoves);
    }
}
