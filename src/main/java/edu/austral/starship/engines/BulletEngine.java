package edu.austral.starship.engines;

import edu.austral.starship.models.bullets.Bullet;

import java.util.ArrayList;
import java.util.List;

public class BulletEngine extends Engine{
    private ArrayList<Bullet> bulletList = new ArrayList<>();
    private int maxHeight;
    private int maxWidth;

    public BulletEngine(int maxHeight, int maxWidth) {
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    public List<Bullet> update(float time) {
        ArrayList<Bullet> toRemove = new ArrayList<>();
        for (Bullet bullet : bulletList) {
            if(bullet.isDestroyed()){
                toRemove.add(bullet);
                continue;
            }
            bullet.move(time);
            if(bullet.isNotInScreen(maxHeight, maxWidth)){
                toRemove.add(bullet);
            }
        }
        return toRemove;
    }


    public void setBulletList(ArrayList<Bullet> bulletList) {
        this.bulletList = bulletList;
    }
}

