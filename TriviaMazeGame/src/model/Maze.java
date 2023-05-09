package model;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Maze {

    // the final Random.
    private static Random RANDOM_POINTS = new Random();

    // the default map file for maze.
    private static String myMazeMap;

    // initializing the 2D maze.
    private char[][] myMaze;

    //initializing the intersection points
    private ArrayList<Point> myIntersections;

    //initializing the intersection points
    private ArrayList<Point> myQuestionPoints;

    //initialize the scanner
    private Scanner myScanner;

    /**
     * This is a default constructor for Maze class.
     *
     * @throws FileNotFoundException
     */
    public Maze(String mapName) throws FileNotFoundException {
        // For Program running purpose.
        myMazeMap = mapName;
        start();
    }


    /**
     * This method calls all of the sub-methods that has in this class.
     *
     * @throws FileNotFoundException
     */
    private void start() throws FileNotFoundException {

        // Assign the instance variable myMaze to the create Maze.
        myMaze = createMaze(myMazeMap);
        // Assign the instance variable myIntersections to the create Maze.
        myIntersections = intersections(myMaze);
        myQuestionPoints = questionPointgenerator();
    }

    /**
     * This method return the 2D maze List.
     *
     * @return the 2D list.
     */
    public char[][] getArray() {
        return myMaze;
    }

    // everytime players move just update the map.
    public void setArray(Point thePoint) {
        if (myMaze[thePoint.x][thePoint.y] != '@') {
            Point player = playerLocation();
            myMaze[player.x][player.y] = '+';
            myMaze[thePoint.x][thePoint.y] = 'M';
        }

    }

    private Point playerLocation() {
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze.length; j++) {
                if (myMaze[i][j] == 'M') {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    /**
     * This method reads the character from the map.
     *
     * @param theMapName the row length.
     * @return the 2D maze List.
     * @throws FileNotFoundException
     */
    public char[][] createMaze(String theMapName) throws FileNotFoundException {

        File mazeMapFile = new File(theMapName);
        // read the map file from scanner.
        myScanner = new Scanner(mazeMapFile);
        // Read the Row length from the map.
        int row = myScanner.nextInt();
        //// Read the Column length from the map.
        int column = myScanner.nextInt();
        //initialize the array size.
        char[][] mazeMap = new char[row][column];
        // read the character from the map.
        for (int i = 0; i < row; i++) {
            String line = myScanner.next();
            for (int j = 0; j < column; j++) {
                mazeMap[i][j] = line.charAt(j);
            }
        }
        return mazeMap;

    }

    /**
     * This method return the intersection (x, y)points.
     *
     * @param theMaze the 2D List.
     * @return the intersections point.
     */
    public ArrayList<Point> intersections(char[][] theMaze) {
        ArrayList<Point> intersectionsPoint = new ArrayList<Point>();
        for (int i = 0; i < theMaze.length; i++) {
            for (int j = 0; j < theMaze[0].length; j++) {
                int up = i - 1;
                int down = i + 1;
                int left = j - 1;
                int right = j + 1;
                // check the road up, down, left, and right, and also the edge cases.
                if (theMaze[i][j] == '+') {
                    int counter = 0;
                    if (up >= 0 && theMaze[up][j] != '@') {
                        // add the point into the arrayList.
                        counter++;
                    }
                    if (down < theMaze.length && theMaze[down][j] != '@') {
                        // add the point into the arrayList.
                        counter++;
                    }
                    if (left >= 0 && theMaze[i][left] != '@') {
                        // add the point into the arrayList.
                        counter++;
                    }
                    if (right < theMaze[0].length && theMaze[i][right] != '@') {
                        // add the point into the arrayList.
                        counter++;
                    }
                    if (counter > 2) {
                        intersectionsPoint.add(new Point(j, i));
                    }
                }
            }
        }
        System.out.println(intersectionsPoint);
        return intersectionsPoint;

    }


    public ArrayList<Point> questionPointgenerator() {
        // initialize how many question will have in the map.
        int number = myIntersections.size() / 2;
        ArrayList<Point> questionPoint = new ArrayList<Point>();
        for (int i = 0; i < number; i++) {
            //initailize the ranPoints.
            int ranPoints = RANDOM_POINTS.nextInt(myIntersections.size());
            // Get the intersections Point from the ranPoints.
            Point randomIntersection = myIntersections.get(ranPoints);
            // assign the random question point in the maze
            questionPoint.add(new Point(randomIntersection.y, randomIntersection.x));
        }
        return questionPoint;
    }

    /**
     * For testing purpose.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[i].length; j++) {
                sb.append(myMaze[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
