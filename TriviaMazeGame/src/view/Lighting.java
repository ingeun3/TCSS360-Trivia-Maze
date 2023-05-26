package view;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;

    public Lighting(GUIPlayer gp, int circleSize){
        // buffered image
        darknessFilter = new BufferedImage(gp.screenW, gp.screenH, BufferedImage.TYPE_INT_ARGB );
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();
        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenW, gp.screenH));
        int centerX = gp.getX() + (gp.getsize()/2); // x and y of the light circle.
        int centerY = gp.getY() + (gp.getsize()/2);

        double x = centerX - (circleSize / 2); // get top of the light circle.
        double y = centerY - (circleSize / 2);

        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);
        // create anohter light circle
        Area lightArea = new Area (circleShape);
        // subtract from the screenARea
        screenArea.subtract(lightArea);
        g2.setColor(new Color(0,0,0,0.95f));
        g2.fill(screenArea);
        g2.dispose();
    }
    public void draw(Graphics2D g2){
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
