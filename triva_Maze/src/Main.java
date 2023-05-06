import java.awt.Point;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Maze maze = new Maze();
        maze.setArray(new Point(2,2));
        System.out.println(maze);

    }

}