package view;

import java.awt.*;
import javax.swing.*;


public class MazeMap {
    // Class Constants
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static final int TILE_NUMBER = 15;
    // Tile size is set for 48x48.
    private static final int TILE_SIZE = (int) screenSize.getWidth() / TILE_NUMBER; // change 18 or 14

    private static final int gap = ( (int) screenSize.getWidth() - TILE_SIZE *  TILE_NUMBER) /2;
    // The serial Version ID.
    private static final long serialVersionUID =
            1L;
    // Image of the Road.
    private static final ImageIcon road = new ImageIcon("road.jpeg");
    // Image of the Wall.
    private static final ImageIcon wall = new ImageIcon("wall.png");

    private static final ImageIcon up = new ImageIcon("up.png");

    // Class Fields

    // The 2D layout of the maze map.
    private final char[][] myArray;
    GamePanel gp;



    /**
     * Default constructor for MazeMap object.
     * @param theArray the 2D layout of the map we want to make graphic out of.
     */
    public MazeMap(GamePanel gp, char[][] theArray) {
        this.gp = gp;
        myArray = theArray;
    }

    public char[][] getMap() {
        return myArray;
    }



    /**
     * Draws the Map GUI
     * @param theGraphics the Graphics object to draw on the JPanel.
     */
    public void draw(final Graphics2D theGraphics) {
        int topy = -TILE_SIZE;

        for (int y = 0; y < myArray.length; y++) {
            topy = topy + TILE_SIZE;
            int leftx = -TILE_SIZE + gap;
            for (int x = 0; x < myArray[y].length; x++) {
                leftx = leftx + TILE_SIZE;

                switch (myArray[y][x]) {
                    case '@':
                        theGraphics.drawImage(wall.getImage(), leftx, topy, TILE_SIZE, TILE_SIZE , null);
                        break;
                    case '+':
                        theGraphics.drawImage(road.getImage(), leftx, topy, TILE_SIZE, TILE_SIZE, null);
                        break;
                }

            }
        }
    }
}
