package controller;
/*
 *
 * This class is the main class that start the gameloop.
 *
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */
import java.io.IOException;

public class Main {
    public static void main(String[] theArgs) throws IOException {
        // start the Game Loop.
        new GameLoop().start();
    }

}