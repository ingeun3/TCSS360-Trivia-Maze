package model;

import model.Maze;

import javax.swing.*;
import java.awt.*;

public class Player {
	// Class constant

	// Image of a player icon.
	private static final ImageIcon myImage = new ImageIcon("playerIcon.jpg");

	// Class field

	// Number of available moves on a player object.
	private static int myMove;

	private Maze myMaze;

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
				myMove--;
			}
		}
		return canPass;
	}

	/**
	 * Checks if player is alive.
	 */
	public boolean isAlive() {
		boolean livingStatus = true;
		if(myMove <= 0) {
			livingStatus = false;
		}
		return livingStatus;
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

	public int getMazeLength() {
		return myMaze.getArray()[0].length;
	}

	public Point getLocation() {
		return myPlayerLocation;
	}

	public void setMyMove(final int theMove) {
		myMove = theMove;
	}

	public void movePlayer(Point thePoint) {
		myMaze.setPlayerLocation(thePoint);
		myPlayerLocation = thePoint;
	}


}
