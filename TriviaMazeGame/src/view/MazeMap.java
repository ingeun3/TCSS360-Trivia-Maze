package view;
// comment comment
import java.awt.*;

import javax.swing.*;

import model.Terrain;

public class MazeMap extends JPanel {


	private static final long serialVersionUID = 1L;
	
	private static final int SQUARE_SIZE = 40;
	
	private char[][] myArray;
	
	public MazeMap(char[][] theArray) {
		myArray = theArray;
		start();

	}
	
	public void start(){
        this.setPreferredSize(new Dimension(520,520));
	}
	
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		final Graphics2D g2 = (Graphics2D) theGraphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        drawMap(g2);
	}
	
	private void drawMap(final Graphics2D theGraphics) {
	
	for (int y = 0; y < myArray.length; y++) {
        final int topy = y * SQUARE_SIZE;

        for (int x = 0; x < myArray[y].length; x++) {
            final int leftx = x * SQUARE_SIZE;

            switch (myArray[y][x]) {
                case '?':
                	theGraphics.setPaint(Color.YELLOW);
                    theGraphics.fillRect(leftx, topy, SQUARE_SIZE, SQUARE_SIZE);

                case '@':
                    theGraphics.setPaint(Color.GRAY);
                    theGraphics.fillRect(leftx, topy, SQUARE_SIZE, SQUARE_SIZE);
                     System.out.println(leftx + "x");
                    System.out.println(topy + "y");
                    break;
                case '+':
                	   theGraphics.setPaint(Color.CYAN);
                       theGraphics.fillRect(leftx, topy, SQUARE_SIZE, SQUARE_SIZE);

                default:
            }

        }
    }
}

	

}
