package view;

import controller.Controller;
import model.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    // The serial Version ID.
    private static final long serialVersionUID = 1L;

    // Class Fields

    char[][] myMazeArray;
    // The Map Object that contains the graphic of the map
    private final MazeMap myMazemap;



    // The Player object that contains graphic of the player.
    GUIPlayer myPlayerGUI;

    // Singleton instance
    private static GamePanel instance;

    private GamePanel(char[][] theArray, Player thePlayer) throws FileNotFoundException {
        // this.setPreferredSize(new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myMazeArray = theArray;
        // The 2D Array of the map layout.
        myMazemap = new MazeMap(myMazeArray, theArray[0].length);
        myPlayerGUI = GUIPlayer.getInstance(thePlayer.getLocation(), theArray[0].length);
        start();
    }

    public static GamePanel getInstance(char[][] theArray, Player thePlayer) throws FileNotFoundException {
        if (instance == null) {
            instance = new GamePanel(theArray, thePlayer);
        }
        return instance;
    }

    /**
     * Initializing GamePanel object.
     */
    public void start() {

        this.setFocusable(true);
        run();
        //playMusic(0);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Request focus when the panel is clicked
                requestFocusInWindow();
            }
        });

    }

    /**
     * The main logic that updates the GamePanel.
     */
    public void run() {
        repaint();
    }

    /**
     * paintComponent method that draws the player.
     * @param theGraphics the Graphics object to draw on the JPanel.
     */
    public void paintComponent(Graphics theGraphics) {
        super.paintComponent(theGraphics);// you need to type i this whenever you do it
        Graphics2D g2 = (Graphics2D) theGraphics; // graphics to graphics 2D
        myMazemap.draw(g2);
        myPlayerGUI.draw(g2);
        g2.dispose();
    }
}
