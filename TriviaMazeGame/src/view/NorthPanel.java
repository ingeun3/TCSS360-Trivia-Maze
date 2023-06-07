package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class to display level button, current level and moves.
 * @author Kevin Truong, Ingeun Hwang, Khin Win.
 */
public class NorthPanel extends JPanel {

    /**
     * Constant to display move prompt.
     */
    private static final String MOVE_PROMPT = "Remaining Moves: ";

    /**
     * Constant to display level prompt.
     */
    private static final String LEVEL_PROMPT = "Level ";

    /**
     * Singleton instance of NorthPanel.
     */
    private static NorthPanel myInstance;
    ;

    /**
     * Button to go to stage.
     */
    private JButton myStageButton;

    /**
     * Label for current level.
     */
    private JLabel myLevel;

    /**
     * Label for remaining moves.
     */
    private JLabel myMoves;

    /**
     * Retro font.
     */
    private Font myFont;

    /**
     * Flag to check if stage button is clicked.
     */
    private boolean myStageButtonClicked;


    /**
     * Private constructor to prevent multiple instantiation.
     * @param theLevel the current level.
     * @param theMoves the moves.
     */
    private NorthPanel(final String theLevel, final String theMoves) {
        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        myStageButtonClicked = false;

        setBackground(new Color(0, 0, 0));


        myStageButton = new JButton("Stage");
        myStageButton.setFont(myFont.deriveFont(Font.PLAIN, 20));
        myStageButton.setBackground(Color.black);
        myStageButton.setOpaque(true);
        myStageButton.setBorderPainted(false);
        myStageButton.setForeground(Color.WHITE);



        myLevel = new JLabel(theLevel, SwingConstants.CENTER);
        myLevel.setForeground(new Color(255, 255, 255, 200));
        myLevel.setFont(myFont.deriveFont(Font.PLAIN, 20));

        myMoves = new JLabel(theMoves, SwingConstants.CENTER);
        myMoves.setForeground(new Color(255, 255, 255, 200));
        myMoves.setFont(myFont.deriveFont(Font.PLAIN, 20));


        this.add(myStageButton);
        this.add(myLevel);
        this.add(myMoves);
        this.setLayout(new GridLayout(1, 3));

        start();
    }

    /**
     * Singleton instance maker.
     * @param theLevel the current level.
     * @param theMoves the moves.
     * @return Singleton instance of NorthPanel.
     */
    public static NorthPanel getInstance(final String theLevel, final String theMoves) {
        if (myInstance == null) {
            synchronized (NorthPanel.class) {
                if (myInstance == null) {
                    myInstance = new NorthPanel(theLevel, theMoves);
                }
            }
        }
        return myInstance;
    }

    /**
     * Adds listener to stage button.
     */
    public void start() {
        myStageButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myStageButtonClicked = true;
            }

        });
    }

    /**
     * Getter to return instance.
     * @return NorthPanel instance.
     */
    public static NorthPanel getInstance() {
        return myInstance;
    }


    /**
     * Method to reassign flag of if stage button was clicked.
     * @return boolean the new value.
     */
    public boolean stageButton() {
        boolean flag = myStageButtonClicked;
        myStageButtonClicked = false;
        return flag;
    }


    /**
     * Setter to display correct level label.
     * @param theLevel the level to display.
     */
    public void setLevel(final int theLevel) {
        if(theLevel <= 1) {
            myLevel.setText("TUTORIAL");
        } else  {
            myLevel.setText(LEVEL_PROMPT + (theLevel - 1));
        }
    }

    /**
     * Setter to display the correct remaining number of moves.
     * @param theMoves
     */
    public void setMoves(final int theMoves) {
        myMoves.setText(MOVE_PROMPT + theMoves);
    }
}
