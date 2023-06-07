package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class displays the levels of different playable maps.
 * @author Kevin Truong, Ingeun Hwang, Khin Win.
 */

public class LevelPanel extends JPanel {

    private static final Color WHITE = new Color(255, 255, 255);

    private static final Color BLACK = new Color(0, 0, 0);

    private static final int BUTTON_SIZE = 45;


    /**
     * Tutorial level button.
     */
    private final JButton myLevel0;

    /**
     * Level 1 button.
     */
    private final JButton myLevel1;

    /**
     * Level 2 button.
     */
    private final JButton myLevel2;

    /**
     * Level 3 button.
     */
    private final JButton myLevel3;

    /**
     * Back button to TitlePanel.
     */
    private final JButton myBackButton;

    /**
     * Current panel number to display.
     */
    private int myPanelNumber ;

    /**
     * How many complete levels.
     */
    private int myCompletedLevel ;

    /**
     * Label for level panel.
     */
    private JLabel myStageTitle;

    /**
     * Retro font.
     */
    private Font myFont;

    /**
     * Constructor for LevelPanel.
     * @param theCompletedLevel the level that was completed.
     */
    public LevelPanel(final int theCompletedLevel) {
        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        myPanelNumber = 0;
        myCompletedLevel = theCompletedLevel;
        setLayout(new BorderLayout());
        myLevel0 = setupButtons(1);
        myLevel1 = setupButtons(2);
        myLevel2 = setupButtons(3);
        myLevel3 = setupButtons(4);
        myBackButton = new JButton("<-");
        myStageTitle = new JLabel("SELECT LEVEL", SwingConstants.CENTER);

        start();
    }

    /**
     * Initializing method for button visuals and listeners.
     */
    private void start() {
        setBackground(new Color(0, 0, 0));

        myBackButton.setEnabled(true);


        unLockLevel();

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        JPanel backPanel = new JPanel(new GridLayout(3,3));

        buttonPanel.setBackground(new Color(0, 0, 0));
        backPanel.setBackground(new Color(0, 0, 0));

        buttonPanel.setOpaque(false);

        myBackButton.setForeground(Color.WHITE);
        myBackButton.setBackground(new Color(0, 0, 0));
        myBackButton.setBorderPainted(false);

        backPanel.setOpaque(false);

        myBackButton.setFont(myFont.deriveFont(Font.PLAIN, 40));
        myStageTitle.setFont(myFont.deriveFont(Font.PLAIN, 60));
        myStageTitle.setForeground(Color.WHITE);

        backPanel.add(myBackButton);
        backPanel.add(new JLabel());
        backPanel.add(new JLabel());
        backPanel.add(new JLabel());
        backPanel.add(new JLabel());
        backPanel.add(new JLabel());
        backPanel.add(new JLabel());
        backPanel.add(myStageTitle);

        buttonPanel.add(myLevel0);
        buttonPanel.add(myLevel1);
        buttonPanel.add(myLevel2);
        buttonPanel.add(myLevel3);

        this.add(backPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);

        myBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = -1;
            }
        });
    }


    /**
     * Setup level buttons.
     * @param thePanelNum the panel number to display.
     * @return an instantiated button.
     */
    private JButton setupButtons(final int thePanelNum) {
        JButton button = new JButton("LVL      " + (thePanelNum - 1));
        button.setEnabled(false);
        button.setForeground(WHITE);
        button.setBackground(BLACK);
        button.setBorderPainted(false);
        button.setFont(myFont.deriveFont(Font.PLAIN, BUTTON_SIZE));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                myPanelNumber = thePanelNum;
            }
        });

        return button;
    }

    /**
     * Getter for the current panel number.
     * @return int myPanelNumber.
     */
    public int getMyNum(){
        int temp = myPanelNumber;
        myPanelNumber = 0;
        return temp;
    }

    /**
     * Method to unlock level depending on myCompletedLevel.
     */
    public void unLockLevel(){
        if (myCompletedLevel == 0){
            myLevel0.setEnabled(true);
            myLevel1.setEnabled(false);
            myLevel2.setEnabled(false);
            myLevel3.setEnabled(false);
        } else if (myCompletedLevel == 1){
            myLevel0.setEnabled(true);
            myLevel1.setEnabled(true);
            myLevel2.setEnabled(false);
            myLevel3.setEnabled(false);
        } else if (myCompletedLevel == 2){
            myLevel0.setEnabled(true);
            myLevel1.setEnabled(true);
            myLevel2.setEnabled(true);
            myLevel3.setEnabled(false);
        } else if (myCompletedLevel == 3){
            myLevel0.setEnabled(true);
            myLevel1.setEnabled(true);
            myLevel2.setEnabled(true);
            myLevel3.setEnabled(true);
        }
        myCompletedLevel++;
    }
}