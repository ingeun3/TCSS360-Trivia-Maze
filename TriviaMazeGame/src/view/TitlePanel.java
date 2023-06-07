package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * This class is the making the Title Panel.
 *
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */
public class TitlePanel extends JPanel {

    private static final Color WHITE = new Color(255, 255, 255);

    private static final Color BLACK = new Color(0, 0, 0);

    private static final int BUTTON_SIZE = 45;

    private static final Dimension PREFERRED_SIZE = new Dimension(120, 60);

    /** Static for Image Icon. */
    private static final ImageIcon icon = new ImageIcon("./resources/visuals/titlename.png");

    /** Initialized the JLabel Title Name. */
    private final JLabel myTitle;

    /** Initialized the JButton for start, reset , and quit. */
    private final JButton myStartButton;
    private final JButton myResetButton;
    private final JButton myQuitButton;

    /** Initialized the Button */
    private final JPanel myButtonPanel;

    /** Initialized the panel Number that player clicks */
    private int myPanelNumber;

    /** Initialized the Font. */
    private Font myFont;

    /** Initialized the Reset Flag. */
    private boolean myReset;

    // This class creates the Title Panel.
    public TitlePanel() {
        // set the Reset Flag to false.
        myReset = false;
        myPanelNumber = -3;

        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        myStartButton = setupButtons("Start");
        myResetButton = setupButtons("Reset");
        myQuitButton = setupButtons("Quit");
        myTitle = new JLabel(icon);
        myButtonPanel = new JPanel();
        setup();
        start();
    }

    /**
     * This method creates the J Buttons and J Labels.
     */
    public void setup() {

        setBackground(new Color(0, 0, 0)); // Transparent black background
        myButtonPanel.setBackground(new Color(0, 0, 0)); // Set the panel's background same as frame
        myButtonPanel.add(myStartButton);
        myButtonPanel.add(myResetButton);
        myButtonPanel.add(myQuitButton);
        myButtonPanel.setLayout(new GridLayout(3, 1));

        setLayout(new GridLayout(2,1));
        add(myTitle);
        add(myButtonPanel);
    }

    /**
     * This method creates the action listener for JButtons.
     */

    public void start() {
        // if start button clicks then reset flag will be false and myPanel Number will be zero.
        myStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 0;
                myReset = false;
            }

        });
        // if reset button clicks then reset flag will be true and myPanel Number will be zero.
        myResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 0;
                myReset = true;
            }

        });
        // if quick button clicks then it will quit the game.
        myQuitButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                System.exit(0);
            }

        });
    }

    /**
     * Return the boolean flag for Reset Button.
     * @return the boolean value for myReset.
     */
    public boolean restartGame() {
        return myReset;
    }

    /**
     * Return the myPanelNumber that send the current Action Listener Value.
     * @return the current Action Listenenr Value.
     */
    public int getMyNum(){
        int temp = myPanelNumber;
        // reset the myPanelNumber.
        myPanelNumber = -1;
        return temp;
    }

    /**
     * Setup title buttons.
     * @param theName the name of the button.
     * @return an instantiated button.
     */
    private JButton setupButtons(final String theName) {
        JButton button = new JButton(theName);
        button.setForeground(WHITE);
        button.setBackground(BLACK);
        button.setBorderPainted(false);
        button.setFont(myFont.deriveFont(Font.PLAIN, BUTTON_SIZE));
        button.setPreferredSize(PREFERRED_SIZE);
        return button;
    }
}