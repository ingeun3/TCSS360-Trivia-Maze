package view;

import java.awt.*;

public class Enviroment {

    GUIPlayer gp;
    Lighting lighting;

    public Enviroment (GUIPlayer gp){
        this.gp = gp;

    }
    public void setup(){
        lighting = new Lighting (gp, 350);
    }
    public void draw(Graphics2D g2){
        lighting.draw(g2);
    }
}
