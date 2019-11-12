package edu.austral.starship.models;

import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;

import java.awt.*;

public class Asteroid implements Element {

    private Vector2 direction;
    private Vector2 position;
    private float speed;
    private Shape shape;
    private boolean destroyed;

    public Asteroid(Vector2 direction, Vector2 position) {
        this.direction = direction;
        this.destroyed = false;
        this.position = position;
        this.shape = new Rectangle((int) position.getX(), (int) position.getY(), 20, 20);
        this.speed = (float) Math.random() + 1;

    }

    public boolean isNotInScreen(int maxHeight, int maxWidth) {
        return position.getX() > maxWidth || position.getX() < 0 || position.getY() > maxHeight || position.getY() < 0;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void collisionedWith(Collisionable collisionable) {
        crash();
    }



    @Override
    public void move(float time) {
        position = position.add(direction.multiply(time).multiply(speed));
        shape = new Rectangle((int) position.getX(), (int) position.getY(), 20, 20);

    }

    public void crash() {
        destroyed = true;
    }


}
