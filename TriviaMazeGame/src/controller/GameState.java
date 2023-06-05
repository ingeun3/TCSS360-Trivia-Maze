package controller;

import java.io.Serializable;

public class GameState implements Serializable {
    private int myCompletedLevel;
    private int myCurrentLevel;

    public GameState(int theCompletedLevel, int theCurrentLevel) {
        myCompletedLevel = theCompletedLevel;
        myCurrentLevel = theCurrentLevel;
    }

    public int getCompletedLevel() {
        return myCompletedLevel;
    }

    public int getCurrentLevel() {
        return myCurrentLevel;
    }
}
