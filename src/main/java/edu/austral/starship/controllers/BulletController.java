package edu.austral.starship.controllers;

import edu.austral.starship.engines.BulletEngine;
import edu.austral.starship.models.Starship;
import edu.austral.starship.models.bullets.Bullet;
import edu.austral.starship.views.BulletView;

import java.util.ArrayList;
import java.util.Collections;

public class BulletController extends Controller{
    private BulletEngine bulletEngine;
    private BulletView bulletView;
    private ArrayList<Bullet> bulletList = new ArrayList<>();

    public BulletController(int maxHeight, int maxWidth) {
        this.bulletEngine = new BulletEngine(maxHeight, maxWidth);
        this.bulletView = new BulletView();
    }

    public ArrayList<Bullet> getBulletList() {
        return bulletList;
    }

    public BulletView getBulletView() {
        return bulletView;
    }

    public void update(float time){
        bulletList.removeAll(bulletEngine.update(time));
        bulletView.setBulletList(bulletList);
        bulletEngine.setBulletList(bulletList);
    }

    public void addNewBullets(Bullet[] shotBullets) {
        if(shotBullets != null) {
            Collections.addAll(bulletList, shotBullets);
            bulletEngine.setBulletList(bulletList);
            bulletView.setBulletList(bulletList);
        }
    }

    public int getPoints(Starship starship) {
        int points = 0;
        for (Bullet bullet : bulletList) {
            if (bullet.getStarship().equals(starship)) {
                if (!bullet.isAlreadySum()) {
                    points+= bullet.getPoints();
                }
            }
        }
        return points;
    }
}
