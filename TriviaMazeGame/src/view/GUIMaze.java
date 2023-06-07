package view;

import java.awt.*;
import javax.swing.*;

/**
 * The GUIMaze object that will visualize the array representation of the map.
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 */
public class GUIMaze {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Image of the Road.
     */
    private static final ImageIcon road = new ImageIcon("road.png");
    /**
     * Image of the Wall.
     */
    private static final ImageIcon wall = new ImageIcon("wall.png");

    /**
     * Array representation of the map.
     */
    private final char[][] myArray;

    /**
     * The Size of each tile.
     */
    private final int myTileSize;

    /**
     * The Size of the gap on the width if the map doesn't fit perfectly to the computer screen.
     */
    private final int myGap;

    /**
     * Default constructor for MazeMap object.
     * @param theArray the 2D layout of the map we want to make graphic out of.
     */
    public GUIMaze(final char[][] theArray, final int theTileNum) {
        myTileSize = (int) screenSize.getWidth() / theTileNum;
        myGap = (int) (screenSize.getWidth() - myTileSize * theTileNum) /2;
        myArray = theArray;
    }

    /**
     * Draws the Map GUI
     * @param theGraphics the Graphics object to draw on the JPanel.
     */
    public void draw(final Graphics2D theGraphics) {
        int topy = -myTileSize;

        for (char[] chars : myArray) {
            topy = topy + myTileSize;
            int leftx = -myTileSize + myGap;
            for (char aChar : chars) {
                leftx = leftx + myTileSize;
                if (aChar == '@') {
                    theGraphics.drawImage(wall.getImage(), leftx, topy, myTileSize, myTileSize, null);
                } else {
                    theGraphics.drawImage(road.getImage(), leftx, topy, myTileSize, myTileSize, null);
                }

            }
        }
    }
}
