package model;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Player {
	// Class constant

	// Image of a player icon.
	private static final ImageIcon myImage = new ImageIcon("playerIcon.jpg");

	// Class field

	// The direction the player is facing.
	private String direction;
	// The current image of a player.

	// Number of available moves on a player object.
	private static int myMove;
	// Boolean living status of player object (true if alive, false if otherwise),
	private static boolean myAlive;

	private Answer myAnswer;

	private Maze myMaze;

	private Point myPlayerLocation;

	/**
	 * Default constructor of a player object
	 * @param theMove the number of available moves on a player.
	 */
	public Player(final int theMove) throws FileNotFoundException {
		myMaze = new Maze("maze_map2.txt");
		myPlayerLocation = new Point(1,1);
		myAlive = true;
		myMove = theMove;

	}

	public Point PlayerN() {
		return new Point(myPlayerLocation.x, myPlayerLocation.y - 1);
	}

	public Point PlayerS() {
		return new Point(myPlayerLocation.x, myPlayerLocation.y + 1);
	}

	public Point PlayerW() {
		return new Point(myPlayerLocation.x - 1, myPlayerLocation.y);
	}

	public Point PlayerE() {
		return new Point(myPlayerLocation.x + 1, myPlayerLocation.y);
	}

	/**
	 * Checks if player can make a move on a given terrain
	 * @param theTerrain the terrain player wants to move
	 * @return false if the given terrain is wall and true otherwise.
	 */
	public boolean canMove(final Point theTerrain) {
		boolean canPass = false;
//		if (myMaze.getQuestionPoints().contains(theTerrain)) {
//			myPlayerLocation = theTerrain;
////			JOptionPane.showMessageDialog(null, "point found");
//
//		} else
		if (myMaze.charAt(theTerrain.x, theTerrain.y) != '@') {
			//if (myAnswer.getCorrectness() == true) {
			myMaze.setArray(theTerrain);
			myPlayerLocation = theTerrain;

			System.out.println(theTerrain);
		//	System.out.println(myMaze.toString());
			canPass = true;
//			myMove--;
//			isAlive();
			//}
		}
		return canPass;
	}

	public void setMyMove() {
		myMove--;
		if(myMove <= 0) {
			myAlive = false;
		}
	}



	/**
	 * Checks if player is alive.
	 */
	protected void isAlive() {
		if(myMove <= 0) {
			myAlive = false;
		}
	}

	public Point getLocation() {
		return myPlayerLocation;
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
