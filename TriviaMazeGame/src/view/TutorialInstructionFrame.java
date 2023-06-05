package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class TutorialInstructionFrame extends JFrame {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final String INSTRUCTION_PROMPT = "\n WELCOME TO TRIVIA MAZE!\n" +
                                                    "\n INSTRUCTIONS: " +
                                                    "\n You are lost in a cave maze with only your torch and you " +
                                                    "must escape! You need to find the exit but you only have a " +
                                                    "LIMITED number of MOVES to do so. \n\n Throughout the maze, " +
                                                    "you will need to answer trivia QUESTIONS to continue. Answer " +
                                                    "right and the light from your torch will grow and you may move " +
                                                    "along! Answer wrong and you will be penalized by getting " +
                                                    "teleported back to the last spot you answered correctly OR " +
                                                    "the start if you haven't gotten one right yet. \n\n " +
                                                    "Choose your MOVES wisely! Good luck!" +
                                                    "\n\n CONTROLS:" +
                                                    "\n PRESS  W -> UP" +
                                                    "\n PRESS  A -> LEFT" +
                                                    "\n PRESS  S -> RIGHT" +
                                                    "\n PRESS  D -> DOWN" +
                                                    "\n PRESS  ESC -> QUIT";
    private Font myFont;

    public TutorialInstructionFrame() {
        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        setSize(new Dimension((int) screenSize.getWidth(),(int) screenSize.getHeight() * 9 / 10));

        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 250)); // Transparent black background

        JTextArea instructionsArea = new JTextArea(
                INSTRUCTION_PROMPT
        );
        instructionsArea.setEditable(false);
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);

        instructionsArea.setFont(myFont.deriveFont(Font.PLAIN, 30));
        instructionsArea.setForeground(new Color(255, 255, 255, 200));
        instructionsArea.setBackground(Color.black);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0, 0, 0, 0)); // Set the panel's background same as frame

        JButton okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        // Set button size and font
        okButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        okButton.setForeground(Color.WHITE);
        okButton.setBorderPainted(false);
        okButton.setOpaque(true);
        okButton.setBackground(new Color(0, 0, 0));

        buttonPanel.add(okButton);

        // Add label and button panel to the frame
        add(instructionsArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        okButton.setFocusable(false);
    }

    public static void start() {
        TutorialInstructionFrame frame = new TutorialInstructionFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setLocation(x, y);
        frame.setVisible(true);

    }
}
