package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/*
 * The main JPanel that will display the game.
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 */

public class GamePanel extends JPanel {
    /**
     * The char array that will contain the array version of the map.
     */
    char[][] myMazeArray;
    /**
     * The GUIMaze object that will make visual representation of the array map.
     */
    private final GUIMaze myMazeMap;
    /**
     * The GUIPlayer object that will make visual representation of the player.
     */
    private final GUIPlayer mySprite;
    /**
     * The Lighting object that will light around the player.
     */
    private final Lighting myLighting;

    /**
     * Constructor for to GamePanel object.
     * @param theArray the char array representation of the mpa.
     * @param thePlayerLocation the Starting location of the Player.
     */
    public GamePanel(final char[][] theArray, final Point thePlayerLocation) {
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myMazeArray = theArray;
        myMazeMap = new GUIMaze(myMazeArray, theArray[0].length);
        mySprite = new GUIPlayer(thePlayerLocation, theArray[0].length);
        myLighting = Lighting.getInstance();
        myLighting.setup();
        start();
    }

    /**
     * Start the GamePanel.
     */
    public void start() {
        this.setFocusable(true);
        run();
        myLighting.setup();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                requestFocusInWindow();
            }
        });
    }

    /**
     * The updates the GamePanel whenever player makes move.
     */
    public void run() {
        repaint();
    }

    /**
     * paintComponent method that draws the player and map.
     * @param theGraphics the Graphics object to draw on the JPanel.
     */
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        Graphics2D g2 = (Graphics2D) theGraphics;
        myMazeMap.draw(g2);
        mySprite.draw(g2);
        myLighting.draw(g2);
        g2.dispose();
    }
}