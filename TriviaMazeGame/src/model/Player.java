package model;

import javax.swing.ImageIcon;
import java.awt.*;

public class Player {
	// Class constant

	// Image of a player icon.
	private static final ImageIcon myImage = new ImageIcon("playerIcon.jpg");

	// Class field

	// The x, and y location of the player.
	private int x, y;
	// The direction the player is facing.
	private String direction;
	// The current image of a player.

	// Number of available moves on a player object.
	private static int myMove;
	// Boolean living status of player object (true if alive, false if otherwise),
	private static boolean myAlive = true;

	private Answer myAnswer;

	private Maze myMaze;

	private Point myPlayerLocation;

	/**
	 * Default constructor of a player object
	 * @param theMove the number of available moves on a player.
	 */
	public Player(final int theMove) {

		myMove = theMove;

	}

	/**
	 * Checks if player can make a move on a given terrain
	 * @param theTerrain the terrain player wants to move
	 * @return false if the given terrain is wall and true otherwise.
	 */
	public boolean canMove(final Terrain theTerrain) {

		boolean canPass = false;
		if (!theTerrain.equals(Terrain.WALL)) {
			System.out.println("this is pass");
			if (myAnswer.getCorrectness() == true) {
				myMaze.setArray(myPlayerLocation);
				canPass = true;
				myMove--;
				isAlive();
			}
		}
		return canPass;
	}

	/**
	 * Checks if player is alive.
	 */
	protected void isAlive() {
		if(myMove <= 0) {
			myAlive = false;
		}
	}

	/**
	 * Getter method for the living status of a player.
	 * @return true if player is alive and false otherwise.
	 */
	public boolean getLivingStatus() {
		return myAlive;
	}

	/**
	 * Getter method for the image of a player icon.
	 * @return
	 */
	public ImageIcon getIcon() {
		return myImage;
	}

	/**
	 * Overrides the toString method.
	 */
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        return sb.toString();
    }
}
