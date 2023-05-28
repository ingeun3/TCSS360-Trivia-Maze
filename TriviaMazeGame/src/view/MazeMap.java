package view;

import java.awt.*;
import javax.swing.*;


public class MazeMap {
    // Class Constants
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();



    // The serial Version ID.
    private static final long serialVersionUID = 1L;
    // Image of the Road.
    private static final ImageIcon road = new ImageIcon("road.jpeg");
    // Image of the Wall.
    private static final ImageIcon wall = new ImageIcon("wall.png");

    private static final ImageIcon up = new ImageIcon("up.png");

    // Class Fields

    // The 2D layout of the maze map.
    private final char[][] myArray;
    private final int myTileNumber;
    // Tile size is set for 48x48.
    private final int myTileSize;

    private final int myGap;

    /**
     * Default constructor for MazeMap object.
     * @param theArray the 2D layout of the map we want to make graphic out of.
     */
    public MazeMap(char[][] theArray, int theTileNum) {
        myTileNumber = theTileNum;
        myTileSize = (int) screenSize.getWidth() / myTileNumber;
        myGap = (int) (screenSize.getWidth() - myTileSize *  myTileNumber) /2;
        myArray = theArray;
    }

    /**
     * Draws the Map GUI
     * @param theGraphics the Graphics object to draw on the JPanel.
     */
    public void draw(final Graphics2D theGraphics) {
        int topy = -myTileSize;

        for (int y = 0; y < myArray.length; y++) {
            topy = topy + myTileSize;
            int leftx = -myTileSize + myGap;
            for (int x = 0; x < myArray[y].length; x++) {
                leftx = leftx + myTileSize;

                if (myArray[y][x] == '@') {
                        theGraphics.drawImage(wall.getImage(), leftx, topy, myTileSize, myTileSize , null);
                } else {
                        theGraphics.drawImage(road.getImage(), leftx, topy, myTileSize, myTileSize, null);
                }

            }
        }
    }
}
