package view;

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
    private final GUIMaze myMazemap;



    // The Player object that contains graphic of the player.
    GUIPlayer myPlayerGUI;

    Lighting myE;

    public GamePanel(char[][] theArray, Player thePlayer) throws FileNotFoundException {
        // this.setPreferredSize(new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myMazeArray = theArray;
        // The 2D Array of the map layout.
        myMazemap = new GUIMaze(myMazeArray, theArray[0].length);
        myPlayerGUI = new GUIPlayer(thePlayer.getLocation(), theArray[0].length);
        myE = Lighting.getInstance();
        myE.setup();
        start();
        System.out.println(myPlayerGUI.getGap());

    }

    /**
     * Initializing GamePanel object.
     */
    public void start() {

        this.setFocusable(true);
        run();
        myE.setup();
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

        myE.draw(g2);

        g2.dispose();
    }
}