package edu.austral.starship.controllers;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.engines.StarshipEngine;
import edu.austral.starship.models.Starship;
import edu.austral.starship.models.bullets.BulletType;
import edu.austral.starship.models.guns.AutomaticGun;
import edu.austral.starship.models.guns.HeavyGun;
import edu.austral.starship.models.guns.MultiGun;
import edu.austral.starship.views.StarshipView;

import java.util.ArrayList;
import java.util.List;

public class StarshipController extends Controller{
    private BulletController bulletController;
    private StarshipEngine starshipEngine;
    private StarshipView starshipView;
    private List<Starship> starships;
    private int maxHeight;
    private int maxWidth;


    public StarshipController(ArrayList<Starship> starships, int maxHeight, int maxWidth) {
        this.starships = starships;
        this.starshipEngine = new StarshipEngine(starships);
        this.starshipView = new StarshipView(starships);
        this.bulletController = new BulletController(maxHeight, maxWidth);
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    public StarshipView getStarshipView() {
        return starshipView;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public List<Starship> getStarships() {
        return starships;
    }

    public void moveForward(int index){
        Starship starship = starshipEngine.moveForward(index);
        this.starships.set(index, starship);
        starshipView.setStarships(starships);
    }

    public void breakSpeed(int index){
        Starship starship = starshipEngine.breakSpeed(index);
        this.starships.set(index, starship);
        starshipView.setStarships(starships);
    }

    public void rotateRight(int index){
        Starship starship = starshipEngine.rotate(index, 0.05);
        this.starships.set(index, starship);
        starshipView.setStarships(starships);
    }

    public void rotateLeft(int index){
        Starship starship = starshipEngine.rotate(index, -0.05);
        this.starships.set(index, starship);
        starshipView.setStarships(starships);
    }

    public void shotBullets(int index){
        bulletController.addNewBullets(starshipEngine.shotBullets(index));
    }

    public void changeWeapon(int index) {
        Starship starship = starships.get(index);
        BulletType bulletType = starship.getGun().getBulletType();
        if (bulletType.equals(BulletType.FAST)){
            starship.setGun(new MultiGun());
        }else if (bulletType.equals(BulletType.MULTI)){
            starship.setGun(new HeavyGun());
        }else {
            starship.setGun(new AutomaticGun());
        }
        this.starships.set(index, starship);
        starshipView.setStarships(starships);
        starshipEngine.setStarships(starships);
    }

    public void bounceAgainstWall(){
        for (Starship starship : starships) {
            if (starship.isNotInScreen(maxHeight, maxWidth)) {
                if (starship.getPosition().getX() > maxWidth || starship.getPosition().getX() < 0) {
                    starship.setDirection(Vector2.vector(starship.getDirection().getX() * -1, starship.getDirection().getY()));
                } else {
                    starship.setDirection(Vector2.vector(starship.getDirection().getX(), starship.getDirection().getY() * -1));
                }
            }
        }
    }

    public boolean endGame(){
        for (Starship starship : starships) {
            if (starship.isAlive()) return false;
        }
        return true;
    }

    public void move(float time) {
        for (Starship starship : starships) {
            starship.move(time);
        }
    }

    public void sumBulletPoints(){
        for (Starship starship : starships) {
            int points = bulletController.getPoints(starship);
            starship.addPoints(points);
        }
    }
}
