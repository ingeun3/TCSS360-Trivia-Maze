package view;

import javax.swing.*;
import java.awt.*;

/**
 * The GUIMaze object that will visualize the array representation of the map.
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 */

public class GUIPlayer {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * The size of each tile.
     */
    private static int myTileSize;
    /**
     * The image of player facing upward.
     */
    private static final ImageIcon up = new ImageIcon("./resources/visuals/up.png");
    /**
     * The image of player facing downward.
     */
    private static final ImageIcon down = new ImageIcon("./resources/visuals/down.png");
    /**
     * The image of player facing left.
     */
    private static final ImageIcon left = new ImageIcon("./resources/visuals/left.png");
    /**
     * The image of player facing right.
     */
    private static final ImageIcon right = new ImageIcon("./resources/visuals/right.png");
    /**
     * The Size of the gap on the width if the map doesn't fit perfectly to the computer screen.
     */
    private final int myGap;
    /**
     * The X location of the Player.
     */
    private static int myX;
    /**
     * The Y location of the Player.
     */
    private static int myY;
    /**
     * The direction player is facing.
     */
    private static String direction;
    /**
     * The current image of a Player.
     */
    private static ImageIcon myImage;

    /**
     * Default constructor for GUIPlayer object.
     * @param theSpawnPoint the starting point of the player.
     * @param theTileNum the Number of Total tiles in the map.
     */
    public GUIPlayer(final Point theSpawnPoint, final int theTileNum) {
        myTileSize = (int) screenSize.getWidth() / theTileNum;
        myGap = (int) (screenSize.getWidth() - (myTileSize * theTileNum)) / 2;
        myX = (int) theSpawnPoint.getX() * myTileSize + myGap;
        myY = (int) theSpawnPoint.getY() * myTileSize;
        direction = "up";
        myImage = new ImageIcon("./resources/visuals/up.png");
    }

    /**
     * Sets the direction to where the player moved.
     */
    public void setDirection(final String theDirection) {
        direction = theDirection;
    }

    /**
     * Sets the X Location of teh Player.
     * @param theX Location the Player wants to move.
     */
    public void setX(final int theX) {
        myX = theX;
    }

    /**
     * Sets the Y location of the Player.
     * @param theY Location the Player wants to move.
     */
    public void setY(final int theY) {
        myY = theY;
    }

    /**
     * Returns the X Location of the Player.
     * @return the X Location.
     */
    public int getX() {
        return myX;
    }

    /**
     * Returns the Y Location of the Player.
     * @return the Y Location.
     */
    public int getY() {
        return myY;
    }

    /**
     * Returns the total number of tiles.
     */
    public int getTileSize() {
        return myTileSize;
    }

    /**
     * Returns the size of the gap on the width if the map doesn't fit perfectly to the computer screen.
     * @return the size of teh gap.
     */
    public int getGap() {
        return myGap;
    }

    /**
     * Returns the screen size.
     */
    public Point getSize(){
        return new Point(screenSize.width, screenSize.height);
    }

    /**
     * Draws the Player GUI
     *
     * @param theGraphics the Graphics object to draw on the JPanel.
     */
    public void draw(final Graphics2D theGraphics) {
        switch (direction) {
            case "up" -> myImage = up;
            case "down" -> myImage = down;
            case "left" -> myImage = left;
            case "right" -> myImage = right;
        }
        theGraphics.drawImage(myImage.getImage(), myX, myY, myTileSize , myTileSize, null);
    }
}
