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
     * The number of total tiles in the map.
     */
    private static int myTileNumber;
    /**
     * The size of each tile.
     */
    private static int myTileSize;
    /**
     * The image of player facing upward.
     */
    private static final ImageIcon up = new ImageIcon("up.png");
    /**
     * The image of player facing downward.
     */
    private static final ImageIcon down = new ImageIcon("down.png");
    /**
     * The image of player facing left.
     */
    private static final ImageIcon left = new ImageIcon("left.png");
    /**
     * The image of player facing right.
     */
    private static final ImageIcon right = new ImageIcon("right.png");
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
    public GUIPlayer(Point theSpawnPoint, int theTileNum) {
        myTileNumber = theTileNum;
        myTileSize = (int) screenSize.getWidth() / myTileNumber;
        myGap = (int) (screenSize.getWidth() - (myTileSize * theTileNum)) / 2;
        myX = (int) theSpawnPoint.getX() * myTileSize + myGap;
        myY = (int) theSpawnPoint.getY() * myTileSize;
        direction = "up";
        myImage = new ImageIcon("up.png");
    }


    public void setDirection(String theDirection) {
        direction = theDirection;
    }

    public void setY(int theY) {
        myY = theY;
    }

    public void setX(int theX) {
        myX = theX;
    }

    public int getY() {
        return myY;
    }

    public int getX() {
        return myX;
    }

    public int getTileSize() {
        return myTileSize;
    }

    public int getGap() {
        return myGap;
    }

    public Point getsize(){
        return new Point(screenSize.width, screenSize.height);
    }

    /**
     * Draws the Player GUI
     *
     * @param theGraphics the Graphics object to draw on the JPanel.
     */

    //maybe random number generator for multiple direction images so it looks like character is moving
    public void draw(Graphics2D theGraphics) {
        ImageIcon image = null;
        switch (direction) {
            case "up":
                myImage = up;
                break;
            case "down":
                myImage = down;
                break;
            case "left":
                myImage = left;
                break;
            case "right":
                myImage = right;
                break;
        }
        theGraphics.drawImage(myImage.getImage(), myX, myY, myTileSize , myTileSize, null);
    }
}
