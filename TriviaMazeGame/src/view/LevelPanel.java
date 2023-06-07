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

    /**
     * The screen size.
     */
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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
        myPanelNumber = 0;
        myCompletedLevel = theCompletedLevel;
        setLayout(new BorderLayout());
        myLevel0 = new JButton("LVL      0");
        myLevel1 = new JButton("LVL      1");
        myLevel2 = new JButton("LVL      2");
        myLevel3 = new JButton("LVL      3");
        myBackButton = new JButton("<-");
        myStageTitle = new JLabel("SELECT LEVEL", SwingConstants.CENTER);

        start();
    }

    /**
     * Initializing method for button visuals and listeners.
     */
    private void start() {
        setBackground(new Color(0, 0, 0));
        myLevel0.setEnabled(false);
        myLevel1.setEnabled(false);
        myLevel2.setEnabled(false);
        myLevel3.setEnabled(false);
        myBackButton.setEnabled(true);
        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        unLockLevel();

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        JPanel backPanel = new JPanel(new GridLayout(3,3));

        buttonPanel.setBackground(new Color(0, 0, 0));
        backPanel.setBackground(new Color(0, 0, 0));


        myLevel0.setForeground(Color.WHITE);
        myLevel0.setBackground(new Color(0, 0, 0));
        myLevel0.setBorderPainted(false);

        myLevel1.setForeground(Color.WHITE);
        myLevel1.setBackground(new Color(0, 0, 0));
        myLevel1.setBorderPainted(false);

        myLevel2.setForeground(Color.WHITE);
        myLevel2.setBackground(new Color(0, 0, 0));
        myLevel2.setBorderPainted(false);

        myLevel3.setForeground(Color.WHITE);
        myLevel3.setBackground(new Color(0, 0, 0));
        myLevel3.setBorderPainted(false);

        buttonPanel.setOpaque(false);

        myBackButton.setForeground(Color.WHITE);
        myBackButton.setBackground(new Color(0, 0, 0));
        myBackButton.setBorderPainted(false);

        backPanel.setOpaque(false);

        myLevel0.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myLevel1.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myLevel2.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myLevel3.setFont(myFont.deriveFont(Font.PLAIN, 45));

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
        myLevel0.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 1;
            }
        });
        myLevel1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 2;
            }
        });
        myLevel2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 3;
            }

        });
        myLevel3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 4;
            }
        });
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