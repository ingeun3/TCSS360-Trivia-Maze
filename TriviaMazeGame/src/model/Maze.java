package model;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the logic for the maze class that reads a 2D array and creates.
 * @author Kevin Truong, Khin Win, Ingeun Hwang
 */
public class Maze {

    /**
     * The final Random.
     */
    private static final Random RANDOM_POINTS = new Random();

    /**
     * The 2D maze.
     */
    private final char[][] myMaze;

    /**
     * The default map file for maze.
     */
    private static String myMazeMap;

    /**
     * The location of the exit.
     */
    private final Point myExit;

    /**
     * ArrayList of all intersection
     */
    private final ArrayList<Point> myIntersections;
    //initializing the intersection points

    /**
     * ArrayList of all QuestionPoints
     */
    private final ArrayList<Point> myQuestionPoints;

    /**
     * The location of the player.
     */
    private Point myPlayerLocation;

    /**
     * The number of paths in the maze.
     */
    private int myNumOfPaths;

    /**
     * This is a default constructor for Maze class.
     * @param theMapName the name of the map file.
     * @throws FileNotFoundException
     */
    public Maze(final String theMapName) throws FileNotFoundException {
        myNumOfPaths = 0;
        // For Program running purpose.
        myMazeMap = theMapName;
        // For Program running purpose.
        myMaze = createMaze(theMapName);
        // Assign the instance variable myIntersections to the creates Maze.
        myIntersections = intersections(myMaze);
        myQuestionPoints = questionPointGenerator();
        myPlayerLocation = playerLocation();
        myExit = exitLocation();
    }


    /**
     * Gets a location of the maze.
     * @param theX x value.
     * @param theY y value.
     * @return a character from the maze.
     */
    public char charAt(final int theX, final int theY) {
        return myMaze[theY][theX];
    }
    /**
     * This method returns the location of the player in Point object.
     * @return the location of the player.
     */
    private Point playerLocation() {
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[0].length; j++) {
                if (myMaze[i][j] == 'M') {
                    return new Point(j,i);
                }
            }
        }
        return null;
    }

    /**
     * Finds and returns the exit point.
     * @return Point the exit location.
     */
    private Point exitLocation() {
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[0].length; j++) {
                if (myMaze[i][j] == 'E') {
                    return new Point(j,i);
                }
            }
        }
        return null;
    }


    /**
     * This method reads the character from the map.
     *
     * @param theMapName the name of the map.
     * @return the 2D array representation of the maze.
     * @throws FileNotFoundException when the file is not found.
     */
    public char[][] createMaze(final String theMapName) throws FileNotFoundException {

        File mazeMapFile = new File(theMapName);
        // read the map file from scanner.
        //initialize the scanner
        Scanner myScanner = new Scanner(mazeMapFile);
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
     * This method looks for every intersection and return the intersection (x, y)points.
     *
     * @param theMaze the 2D array representation of the map.
     * @return the lists of intersections.
     */
    public ArrayList<Point> intersections(final char[][] theMaze) {
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
                    myNumOfPaths++;
                }
            }
        }
        return intersectionsPoint;

    }

    /**
     * Randomly assigns 50 percent of the intersection points to become a question points.
     * @return the list of question points.
     */
    public ArrayList<Point> questionPointGenerator() {
        // initialize how many question will have in the map.
        int number = myIntersections.size() / 2;
        int all = myIntersections.size();
        ArrayList<Point> questionPoint = new ArrayList<Point>();
        for (int i = 0; i < all; i++) {
            //initialize the ranPoints.
            int ranPoints = RANDOM_POINTS.nextInt(myIntersections.size());
            // Get the intersections Point from the ranPoints.
            Point randomIntersection = myIntersections.get(ranPoints);
            // assign the random question point in the maze
            questionPoint.add(new Point(randomIntersection.x, randomIntersection.y));
        }
        return questionPoint;
    }

    /**
     *  This method returns the lists of the question points.
     * @return the list of the question points.
     */
    public ArrayList<Point> getQuestionPoints() {
       return myQuestionPoints;
    }

    public int getNumOfPaths() {
        return myNumOfPaths;
    }

    /**
     * Getter for the location of the player.
     * @return Point myPlayerLocation.
     */
    public Point getMyPlayerLocation() {
        return myPlayerLocation;
    }

    /**
     * Getter for exit location.
     * @return Point myExit.
     */
    public Point getMyExit() {
        return myExit;
    }

    /**
     * This method return the 2D array representation of the maze.
     *
     * @return the 2D list.
     */
    public char[][] getArray() {
        return myMaze;
    }

    /**
     * Set method for player character in myMaze and myPlayerLocation point.
     * @param thePoint the new location.
     */
    public void setPlayerLocation(Point thePoint) {
        if(thePoint.x >= 0 && thePoint.x <= myMaze.length - 1 && thePoint.y >= 0 && thePoint.y <= myMaze[0].length - 1) {
            if (myMaze[thePoint.y][thePoint.x] != '@') {
                myMaze[myPlayerLocation.y][myPlayerLocation.x] = '+';
                myMaze[thePoint.y][thePoint.x] = 'M';
                myPlayerLocation = new Point(thePoint.x,thePoint.y);
            }
        }

    }

}
