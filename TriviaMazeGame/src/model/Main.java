package model;

import model.Maze;

import java.awt.Point;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Maze maze = new Maze("maze_map1.txt");
        maze.setArray(new Point(2,2));
        System.out.println(maze);

    }

}