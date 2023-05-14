package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel{
    private final JPanel myButtonPanel;

    private final JButton myHelpButton;

    private final JButton mySaveButton;

    private final JButton myLevelButton;

    private JLabel myLevel;

    private JLabel myMoves;

    public NorthPanel(String theLevel, String theMoves) {
        myButtonPanel = new JPanel(new FlowLayout());
        myHelpButton = new JButton("Help");
        mySaveButton     = new JButton("Save");
        myLevelButton = new JButton("Level");
        myLevel = new JLabel(theLevel,  SwingConstants.CENTER);
        myMoves = new JLabel(theMoves,  SwingConstants.CENTER);
        start();
    }
    private void start() {
        myButtonPanel.add(myHelpButton);
        myButtonPanel.add(mySaveButton);
        myButtonPanel.add(myLevelButton);
        myButtonPanel.setLayout(new GridLayout(1,3));

        myHelpButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                TutorialFrame tutorial = new TutorialFrame();
            }
        });

        this.add(myButtonPanel);
        this.add(myLevel);
        this.add(myMoves);
        this.setLayout(new GridLayout(1, 3));
    }

    public void setLevel(String theLevel) {
        myLevel = new JLabel(theLevel);
        start();
    }

    public void setMoves(String theMoves) {
        myMoves = new JLabel(theMoves);
        start();
    }
}
