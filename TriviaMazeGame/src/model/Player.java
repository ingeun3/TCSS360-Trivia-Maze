package model;

import javax.swing.ImageIcon;

public class Player {
	
	private static final ImageIcon myImage = new ImageIcon("playerIcon.jpg");
	
	private static int myX;
	private static int myY;
	private static int myMove;
	private static boolean myAlive = true;
	
	
	public Player(final int theX, final int theY, final int theMove) {
		myX = theX;
		myY = theY;
		myMove = theMove;
	}

    
    
    /**
     * Overrides the toString method.
     */
    
	public boolean canMove(final Terrain theTerrain) {
		boolean canPass = true;
		if (!theTerrain.equals(Terrain.WALL)) {
            canPass = false;

        } 
		return canPass;
	}
	public void isAlive() {
		if(myMove < 0) {
			myAlive = false;
		}
	}
	
	public void setX(final int theX) {
        myX = theX;

    }
    
    public void setY(final int theY) {
        myY = theY;

    }
    
	public ImageIcon getIcon() {
		return myImage;
	}
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        return sb.toString();
    }
}
