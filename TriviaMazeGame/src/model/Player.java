package model;

import javax.swing.ImageIcon;

public class Player {

	// Image of a player icon.
	private static final ImageIcon myImage = new ImageIcon("playerIcon.jpg");
	
	// Number of available moves on a player object.
	private static int myMove;
	// Boolean living status of player object (true if alive, false if otherwise),
	private static boolean myAlive = true;

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
            canPass = true;
			myMove--;
			isAlive();
        } 
		return canPass;
	}

	/**
	 * Checks if player is alive.
	 */
	private void isAlive() {
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