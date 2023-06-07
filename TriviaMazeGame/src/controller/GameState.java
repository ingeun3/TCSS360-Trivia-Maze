package controller;
/*
 *
 * This class is the Game State class that serialized with the GameLoop.
 *
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */

import java.io.Serializable;

public class GameState implements Serializable {

    /** Initialized a player completed Level. */
    private int myCompletedLevel;

    /** Initialized a player current Level. */
    private int myCurrentLevel;

    /**
     *
     * @param theCompletedLevel The Player completed Level.
     * @param theCurrentLevel   The Player Current Level.
     */
    public GameState(int theCompletedLevel, int theCurrentLevel) {
        myCompletedLevel = theCompletedLevel;
        myCurrentLevel = theCurrentLevel;
    }

    /**
     * This method return a player completed level.
     * @return myCompletedLevel.
     */
    public int getCompletedLevel() {
        return myCompletedLevel;
    }

    /**
     * This method return a player current level.
     * @return myCurrentLevel.
     */
    public int getCurrentLevel() {
        return myCurrentLevel;
    }
}
