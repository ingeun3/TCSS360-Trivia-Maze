package view;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/*
 * This EscFrame object is a JFrame that will appear when Player pressed 'Esc' key.
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 */
public class EscFrame extends JFrame {

    /**
     * Constructor for EscFrame object.
     */
    public EscFrame() {
        Font messageFont;
        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            assert is != null;
            messageFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        setSize(500, 600);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 200)); // Transparent black background

        // Create label for "Would you like to quit?"
        JLabel label = new JLabel("Would you like to quit?");
        label.setFont(messageFont.deriveFont(Font.PLAIN, 45));
        label.setForeground(new Color(255, 255, 255, 200));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Create panel for buttons and set layout to center them
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0, 0, 0, 0)); // Set the panel's background same as frame

        // Create Yes and No buttons
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        yesButton.addActionListener(e -> System.exit(0));

        noButton.addActionListener(e -> {
            dispose(); // Close the window
        });

        yesButton.setFont(messageFont.deriveFont(Font.PLAIN, 45));
        yesButton.setForeground(Color.WHITE);
        yesButton.setBorderPainted(false);
        yesButton.setOpaque(true);
        yesButton.setBackground(new Color(0, 0, 0));

        noButton.setFont(messageFont.deriveFont(Font.PLAIN, 45));
        noButton.setForeground(Color.WHITE);
        noButton.setOpaque(true);
        noButton.setBorderPainted(false);
        noButton.setBackground(new Color(0, 0, 0));


        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        add(label, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        yesButton.setFocusable(false);
        noButton.setFocusable(false);
    }

    /**
     * Runs the EscFrame.
     */
    public static void start() {
        SwingUtilities.invokeLater(() -> {
            EscFrame frame = new EscFrame();
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
        });
    }

}