package controller;

import model.Maze;
import model.Player;
import view.GameInterface;
import view.GamePanel;
import view.QuestionPane;

import javax.swing.*;
import java.io.FileNotFoundException;

public class GameLoop {
    private Player myPlayer;
    private GamePanel myGamePanel;
    private GameInterface myGameInterface;
    private Maze myMaze;

    private QuestionPane myQuestion;
    public GameLoop(final int move, final String theMap) throws FileNotFoundException {
        myMaze = new Maze(theMap);
        myPlayer = new Player(move);
        myGameInterface = new GameInterface(1,10, myMaze.getArray(), myPlayer);
        myGamePanel = GamePanel.getInstance(myMaze.getArray(), myPlayer);
        start();
    }
    public void start() {
        myGameInterface.start();
        myGamePanel.start();

        while(myPlayer.getLivingStatus()) {
            myGamePanel.run();
            if (myMaze.getQuestionPoints().contains(myPlayer.getLocation())) {
                JOptionPane.showMessageDialog(null, "point found");
            }
        }
    }
}
