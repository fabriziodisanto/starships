package edu.austral.starship.views;

import edu.austral.starship.models.bullets.Bullet;
import edu.austral.starship.models.bullets.BulletType;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class BulletView extends View{
    private List<Bullet> bulletList = new ArrayList<>();
    private PImage[] pImageList;


    public BulletView() {
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public void setpImageList(PImage[] pImageList) {
        this.pImageList = pImageList;
    }

    public void draw(PGraphics graphics) {
        for (Bullet bullet : bulletList) {
            graphics.rect(bullet.getShape().getBounds().x, bullet.getShape().getBounds().y, bullet.getShape().getBounds().width, bullet.getShape().getBounds().height);
            if (bullet.getBulletType().equals(BulletType.FAST)){
                graphics.imageMode(3);
                graphics.pushMatrix();
                graphics.translate(bullet.getShape().getBounds().x + 2.5f, bullet.getShape().getBounds().y + 2.5f);
                graphics.rotate((float) (bullet.getDirection().angle() + Math.PI/2));
                graphics.image(pImageList[0],0,0);
                graphics.popMatrix();
            }else if (bullet.getBulletType().equals(BulletType.MULTI)){
                graphics.imageMode(3);
                graphics.pushMatrix();
                graphics.translate(bullet.getShape().getBounds().x + 4f, bullet.getShape().getBounds().y + 4f);
                graphics.rotate((float) (bullet.getDirection().angle() + Math.PI/2));
                graphics.image(pImageList[1],0,0);
                graphics.popMatrix();
            }else{
                graphics.imageMode(3);
                graphics.pushMatrix();
                graphics.translate(bullet.getShape().getBounds().x + 6f, bullet.getShape().getBounds().y + 6f);
                graphics.rotate((float) (bullet.getDirection().angle() + Math.PI/2));
                graphics.image(pImageList[2],0,0);
                graphics.popMatrix();
            }
        }
    }
}
