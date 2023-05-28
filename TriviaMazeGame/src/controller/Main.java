package controller;

import model.Maze;
import model.Player;
import model.Question;
import org.sqlite.SQLiteDataSource;
import view.GameInterface;
import view.GamePanel;

import javax.swing.*;
import java.io.FileNotFoundException;


import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Main {
    // initializing the Controller.
    private static Controller myClass;

    public static void main(String[] theArgs) throws FileNotFoundException {

        //myClass= new Controller();
        setLookAndFeel();
        new GameLoop(21, 1).start();
    }




    /**
     * Sets Look and Feel of the GUI.
     */
    private static void setLookAndFeel() {

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }

    }

}