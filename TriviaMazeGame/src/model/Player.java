package model;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Player {
	// Class constant

	// Image of a player icon.
	private static final ImageIcon myImage = new ImageIcon("playerIcon.jpg");

	// Class field

	// Number of available moves on a player object.
	private static int myMove;
	// Boolean living status of player object (true if alive, false if otherwise),
	private static boolean myAlive;

	private Maze myMaze;

	private Point myPlayerLocation;

	/**
	 * Default constructor of a player object
	 * @param theMove the number of available moves on a player.
	 */
	public Player(final int theMove, final String theMapName) throws FileNotFoundException {
		myMaze = new Maze(theMapName);
		myPlayerLocation = myMaze.getMyPlayerLocation();
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
	 * @param theTargetPoint the terrain player wants to move
	 * @return false if the given terrain is wall and true otherwise.
	 */
	public boolean canMove(final Point theTargetPoint) {
		boolean canPass = false;
		if(theTargetPoint.x >= 0 && theTargetPoint.x < myMaze.getArray()[0].length
		&& theTargetPoint.y >= 0 && theTargetPoint.y < myMaze.getArray().length) {
			if (myMaze.charAt(theTargetPoint.x, theTargetPoint.y) != '@') {
				movePlayer(theTargetPoint);
				canPass = true;
			}
		}

		return canPass;
	}

	public void movePlayer(Point thePoint) {
		myMaze.setArray(thePoint);
		myPlayerLocation = thePoint;
	}

	public boolean isQuestionPoint() {
		boolean isQuestionPt = false;
		if (myMaze.getQuestionPoints().contains(myPlayerLocation)) {
			isQuestionPt = true;
		}
		return isQuestionPt;
	}

	public int getMyMove() {
		return myMove;
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
	 * Overrides the toString method.
	 */
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        return sb.toString();
    }
}
