package controller;

import model.Maze;
import model.Player;
import view.GameInterface;
import view.GamePanel;
import view.QuestionPane;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Controller {
    private Player myPlayer;
    private GamePanel myGamePanel;
    private GameInterface myGameInterface;
    private Maze myMaze;
    private keyBoardHandler keyH;

    private QuestionPane myQuestion;
    public Controller (final int move, final String theMap, QuestionPane theQuestion) throws FileNotFoundException {
        myMaze = new Maze(theMap);
        myPlayer = new Player(move);
        myQuestion = theQuestion;
        myGameInterface = new GameInterface(1,10, myMaze.getArray(), myPlayer);
        myGamePanel = GamePanel.getInstance(myMaze.getArray(), myPlayer);
        keyH = keyBoardHandler.getInstance();
        start();
    }
    public void start() {
        myGameInterface.start();
        myGamePanel.start();



        while(myPlayer.getLivingStatus()) {
            if (myMaze.getQuestionPoints().contains(myPlayer.getLocation())){
                JOptionPane.showMessageDialog(null, "point found");
            }
            myGamePanel.run();

        }
    }
}
