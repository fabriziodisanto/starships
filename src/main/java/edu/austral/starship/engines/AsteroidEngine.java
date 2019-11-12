package edu.austral.starship.engines;

import edu.austral.starship.models.Asteroid;

import java.util.ArrayList;
import java.util.List;

public class AsteroidEngine extends Engine{

    private ArrayList<Asteroid> asteroidList = new ArrayList<>();
    private int maxHeight;
    private int maxWidth;

    public AsteroidEngine(int maxHeight, int maxWidth) {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }


    public void setAsteroidList(ArrayList<Asteroid> asteroidList) {
        this.asteroidList = asteroidList;
    }

    public List<Asteroid> update(float time) {
        ArrayList<Asteroid> toRemove = new ArrayList<>();
        for (Asteroid asteroid : asteroidList) {
            if (asteroid.isDestroyed()) {
                toRemove.add(asteroid);
                continue;
            }
            asteroid.move(time);
            if(asteroid.isNotInScreen(maxHeight, maxWidth)){
                toRemove.add(asteroid);
            }
        }
        return toRemove;
    }

}
