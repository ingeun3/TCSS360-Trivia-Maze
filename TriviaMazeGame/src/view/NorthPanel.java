package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import model.Maze;

public class NorthPanel extends JPanel {

    private static NorthPanel myInstance;

    private JPanel myButtonPanel;
    private JButton myHomeButton;
    private JButton myHelpButton;
    private JButton mySaveButton;
    private JButton myLevelButton;
    private JLabel myLevel;
    private JLabel myMoves;

    private NorthPanel(String theLevel, String theMoves) {
        myButtonPanel = new JPanel(new FlowLayout());
        myHomeButton = new JButton("Home");
        myHelpButton = new JButton("Help");
        mySaveButton = new JButton("Save");
        myLevelButton = new JButton("Level");
        myLevel = new JLabel(theLevel, SwingConstants.CENTER);
        myMoves = new JLabel(theMoves, SwingConstants.CENTER);
        myButtonPanel.add(myHelpButton);
        myButtonPanel.add(mySaveButton);
        myButtonPanel.add(myLevelButton);
        myButtonPanel.setLayout(new FlowLayout());

        myHelpButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                try {
                    Maze mazeMap = new Maze("tutorial_map.txt");
                    TutorialFrame tutorial = new TutorialFrame();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        this.add(myButtonPanel);
        this.add(myLevel);
        this.add(myMoves);
        this.setLayout(new GridLayout(1, 3));
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

    public static NorthPanel getInstance() {
        return myInstance;
    }

    public void setLevel(String theLevel) {
        myLevel.setText(theLevel);
    }

    public void setMoves(String theMoves) {
        myMoves.setText(theMoves);
    }
}
