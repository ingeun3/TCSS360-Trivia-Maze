package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class EscPanel extends JFrame {

    private Font myFont;
    public EscPanel() {
        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        setSize(500, 600);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 200)); // Transparent black background

        // Create label for "Would you like to quit?"
        JLabel label = new JLabel("Would you like to quit?");
        label.setFont(myFont.deriveFont(Font.PLAIN, 45));
        label.setForeground(new Color(255, 255, 255, 200));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Create panel for buttons and set layout to center them
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0, 0, 0, 0)); // Set the panel's background same as frame

        // Create Yes and No buttons
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 System.exit(0);
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });

        // Set button size and font
        yesButton.setPreferredSize(new Dimension(120, 60));
        yesButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        yesButton.setForeground(Color.WHITE);
        yesButton.setBackground(new Color(0, 0, 0));
        yesButton.setOpaque(false);
        noButton.setPreferredSize(new Dimension(120, 60));
        noButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        noButton.setForeground(Color.WHITE);
        noButton.setBackground(new Color(0, 0, 0));
        noButton.setOpaque(false);

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        // Add label and button panel to the frame
        add(label, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        yesButton.setFocusable(false);
        noButton.setFocusable(false);
    }

    public static void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EscPanel frame = new EscPanel();
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
        });
    }

}
