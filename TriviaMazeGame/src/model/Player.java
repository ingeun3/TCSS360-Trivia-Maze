package model;

import java.awt.*;
/**
 *
 * This class is the Player.
 *
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */
public class Player {

	/** Number of available moves on a player object. */
	private static int myMove;

	/**
	 * The maze object.
	 */
	private final Maze myMaze;

	/**
	 * Current location of player.
	 */
	private Point myPlayerLocation;

	/**
	 * Default constructor of a player object
	 * @param theMove the number of available moves on a player.
	 */
	public Player(final int theMove, final Maze theMaze) {
		myMaze = theMaze;
		myPlayerLocation = myMaze.getMyPlayerLocation();
		myMove = theMove;

	}

	/**
	 * Method to move player north
	 * @return Point the new location.
	 */
	public Point PlayerN() {
		return new Point(myPlayerLocation.x, myPlayerLocation.y - 1);
	}

	/**
	 * Method to move player south.
	 * @return Point the new location.
	 */
	public Point PlayerS() {
		return new Point(myPlayerLocation.x, myPlayerLocation.y + 1);
	}

	/**
	 * Method to move player west.
	 * @return Point the new location.
	 */
	public Point PlayerW() {
		return new Point(myPlayerLocation.x - 1, myPlayerLocation.y);
	}

	/**
	 * Method to move player east.
	 * @return Point the new location.
	 */
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
				myMove--;
			}
		}
		return canPass;
	}

	/**
	 * Checks if player is alive.
	 */
	public boolean isAlive() {
		return myMove > 0;
	}

	/**
	 * Checks if current player location is a question point.
	 * @return boolean if it is a question point.
	 */
	public boolean isQuestionPoint() {
		return myMaze.getQuestionPoints().contains(myPlayerLocation);
	}

	/**
	 * Getter for remaining moves.
	 * @return int myMove.
	 */
	public int getMyMove() {
		return myMove;
	}

	/**
	 * Getter for length of maze.
	 * @return int length of myMaze.
	 */
	public int getMazeLength() {
		return myMaze.getArray()[0].length;
	}


	/**
	 * Getter for player current location.
	 * @return Point myPLayerLocation.
	 */
	public Point getLocation() {
		return myPlayerLocation;
	}

	/**
	 * Setter for new remaining moves.
	 * @param theMove the new move int.
	 */
	public void setMyMove(final int theMove) {
		myMove = theMove;
	}

	/**
	 * Moves player to new location.
	 * @param thePoint the new point to move to.
	 */
	public void movePlayer(final Point thePoint) {
		myMaze.setPlayerLocation(thePoint);
		myPlayerLocation = thePoint;
	}


}
