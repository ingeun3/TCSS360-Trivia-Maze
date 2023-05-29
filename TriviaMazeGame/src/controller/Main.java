package controller;

import model.Maze;
import model.Player;
import model.Question;
import org.sqlite.SQLiteDataSource;
import view.GameInterface;
import view.GamePanel;
import view.Sound;

import javax.swing.*;
import java.io.FileNotFoundException;


import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Main {


    public static void main(String[] theArgs) throws FileNotFoundException {
        Sound sound = new Sound();
        sound.playMusic();
       // setLookAndFeel();
        new GameLoop().start();
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