package edu.austral.starship.controllers;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.engines.AsteroidEngine;
import edu.austral.starship.models.Asteroid;
import edu.austral.starship.views.AsteroidView;

import java.util.ArrayList;

public class AsteroidController extends Controller {

    private AsteroidEngine asteroidEngine;
    private AsteroidView asteroidView;
    private ArrayList<Asteroid> asteroidList = new ArrayList<>();
    private int maxHeight;
    private int maxWidth;


    public AsteroidController(int maxHeight, int maxWidth) {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        this.asteroidEngine = new AsteroidEngine(maxHeight, maxWidth);
        this.asteroidView = new AsteroidView();
    }

    public void update(float time){
        asteroidList.removeAll(asteroidEngine.update(time));
        asteroidView.setAsteroidList(asteroidList);
        asteroidEngine.setAsteroidList(asteroidList);
    }

    public ArrayList<Asteroid> getAsteroidList() {
        return asteroidList;
    }

    public AsteroidView getAsteroidView() {
        return asteroidView;
    }

    public void generateRandomAsteroid(){
        float directionX = (float) Math.random();
        float directionY = (float) Math.random();
        boolean negativeOrNot = Math.random() > 0.5;
        if (negativeOrNot) {
            directionX = -directionX;
        }
        float speedAmplifier = (float) Math.random()*3 + 1;

        Asteroid asteroid = new Asteroid(Vector2.vector(directionX * speedAmplifier, directionY * speedAmplifier),Vector2.vector((float)Math.random()*maxWidth, 0));
        asteroidList.add(asteroid);
        asteroidEngine.setAsteroidList(asteroidList);
        asteroidView.setAsteroidList(asteroidList);
    }
}
