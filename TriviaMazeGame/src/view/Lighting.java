package view;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/*
 * The Lighting object that will create dark and only light around the player.
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 */
public class Lighting {

    private static Lighting instance;

    /**
     * The Dark Image.
     */
    private BufferedImage darknessFilter;
    /**
     * The width of the screen.
     */
    private final int myW;
    /**
     * The height of the screen.
     */
    private final int myH;
    /**
     * The diameter of the circle.
     */
    private static int myDiameter;
    /**
     * The GUIPlayer object to tracker the Player's location.
     */
    private static GUIPlayer mySprite;

    /**
     * The private Lighting object.
     * @param theGUIPlayer the GUIPlayer instance to track Player's movement.
     * @param theCircleSize the initial Diameter of the circle.
     */
    public Lighting(final GUIPlayer theGUIPlayer, final int theCircleSize) {
        mySprite = theGUIPlayer;
        myDiameter = theCircleSize;
        myW = (int) mySprite.getSize().getX();
        myH = (int) mySprite.getSize().getY();
        setup();
    }

    /**
     * Creates the singleton instance of the Lighting object.
     * @param theGUIPlayer the GUIPlayer instance to track Player's movement.
     * @param theCircleSize the initial Diameter of the circle.
     * @return the singleton instance of the Lighting object.
     */
    public static Lighting getInstance(final GUIPlayer theGUIPlayer, final int theCircleSize) {
        if (instance == null) {
            instance = new Lighting(theGUIPlayer, theCircleSize);
        }
        return instance;
    }

    /**
     * Returns teh singleton instance of the Lighting object.
     * @return the singleton instance of the Lighting object.
     */
    public static Lighting getInstance() {
        return instance;
    }

    /**
     * Increase the diameter by the given parameter.
     * @param theDiameter the size you want to add to the current diameter.
     */
    public void increaseSize(final int theDiameter) {
        myDiameter = myDiameter + theDiameter;
    }

    /**
     * Change the diameter to the given parameter.
     * @param theDiameter the size you want to change the current diameter to.
     */
    public void setSize(final int theDiameter) {
        myDiameter = theDiameter;
    }

    /**
     * Sets up the Lighting object.
     */
    public void setup() {

        darknessFilter = new BufferedImage(myW, myH, BufferedImage.TYPE_INT_ARGB );
        Graphics2D myGraphics = (Graphics2D) darknessFilter.getGraphics();
        Area myScreenArea = new Area(new Rectangle2D.Double(0, 0, myW, myH));


        int myCenterX = mySprite.getX() + (mySprite.getTileSize() / 2); // x and y of the light circle.
        int myCenterY = mySprite.getY() + (mySprite.getTileSize() / 2);

        double myTopLeftX = myCenterX - ((double) myDiameter / 2); // get top of the light circle.
        double myTopLeftY = myCenterY - ((double) myDiameter / 2);

        Shape circleShape = new Ellipse2D.Double(myTopLeftX, myTopLeftY, myDiameter, myDiameter);
        // create another light circle
        Area myLightArea = new Area(circleShape);
        // subtract from the screenARea
        myScreenArea.subtract(myLightArea);

        Color[] color = new Color[5];
        float[] fraction = new float[5];

        color[0] = new Color(0,0,0,0f);
        color[1] = new Color(0,0,0,0.25f);
        color[2] = new Color(0,0,0,0.5f);
        color[3] = new Color(0,0,0,0.75f);
        color[4] = new Color(0,0,0,0.98f);

        fraction[0] = 0f;
        fraction[1] = 0.25f;
        fraction[2] = 0.5f;
        fraction[3] = 0.75f;
        fraction[4] = 1f;

        RadialGradientPaint gPaint = new RadialGradientPaint(myCenterX, myCenterY, ((float) myDiameter /2), fraction, color);

        myGraphics.setPaint(gPaint);

        myGraphics.fill(myLightArea);
        myGraphics.fill(myScreenArea);
        myGraphics.dispose();
    }

    /**
     * Disables the darkness.
     */
    public void disableLight() {
        darknessFilter = null;
    }

    /**
     * Draws the lighting object.
     * @param theGraphics2D the Graphics object to draw the lighting object.
     */
    public void draw(Graphics2D theGraphics2D){
        theGraphics2D.drawImage(darknessFilter, 0, 0, null);
    }


}
