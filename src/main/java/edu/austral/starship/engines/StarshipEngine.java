package edu.austral.starship.engines;

import edu.austral.starship.models.Starship;
import edu.austral.starship.models.bullets.Bullet;

import java.util.ArrayList;
import java.util.List;

public class StarshipEngine extends Engine{
    private List<Starship> starships;


    public StarshipEngine(ArrayList<Starship> starships) {
        this.starships = starships;
    }

    public void setStarships(List<Starship> starships) {
        this.starships = starships;
    }

    public Starship moveForward(int index) {
        return starships.get(index).moveForward();
    }

    public Starship breakSpeed(int index){
        return starships.get(index).breakSpeed();
    }

    public Bullet[] shotBullets(int index) {
        if(starships.get(index).isAlive()) {
            return starships.get(index).shotGun();
        }
        return null;
    }

    public Starship rotate(int index, double angle) {
        return starships.get(index).rotate(angle);
    }
}
