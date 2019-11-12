package edu.austral.starship.views;

import edu.austral.starship.models.Starship;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class StarshipView extends View{

    private List<Starship> starships;
    private PImage pImage;

    public StarshipView(ArrayList<Starship> starships) {
        this.starships = starships;
    }

    public void draw(PGraphics graphics) {
        for (Starship starship : starships) {
            if(starship.isAlive()) {
                graphics.imageMode(3);
                graphics.pushMatrix();
                graphics.translate(starship.getShape().getBounds().x + 35, starship.getShape().getBounds().y + 35);
                graphics.rotate((float) (starship.getDirection().angle() + Math.PI / 2));
                graphics.image(pImage, 0, 0);
                graphics.popMatrix();
            }
        }
    }

    public void setpImage(PImage pImage) {
        this.pImage = pImage;
    }

    public void setStarships(List<Starship> starships) {
        this.starships = starships;
    }
}
