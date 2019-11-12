package edu.austral.starship.views;

import edu.austral.starship.models.Asteroid;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class AsteroidView extends View{

    private List<Asteroid> asteroidList = new ArrayList<>();
    private PImage pImage;


    public AsteroidView() {

    }

    public void setpImage(PImage pImage) {
        this.pImage = pImage;
    }

    public void setAsteroidList(List<Asteroid> asteroidList) {
        this.asteroidList = asteroidList;
    }

    public void draw(PGraphics graphics) {
        for (Asteroid asteroid : asteroidList) {
            graphics.image(pImage, asteroid.getShape().getBounds().x, asteroid.getShape().getBounds().y);
        }
    }
}
