package controller;

import java.io.Serializable;

public class GameState implements Serializable {
    private int myCompletedLevel;
    private int myCurrentLevel;

    public GameState(int completedLevel, int currentLevel) {
        myCompletedLevel = completedLevel;
        myCurrentLevel = currentLevel;
    }

    public int getCompletedLevel() {
        System.out.println(myCompletedLevel);
        return myCompletedLevel;
    }

    public int getCurrentLevel() {
        return myCurrentLevel;
    }
}
