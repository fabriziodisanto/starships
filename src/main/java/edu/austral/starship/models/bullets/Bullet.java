package edu.austral.starship.models.bullets;

import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.models.Element;
import edu.austral.starship.models.Starship;

import java.awt.*;

public abstract class Bullet implements Element {
    private float speed;
    private boolean canPierce;
    private Vector2 direction;
    private Vector2 position;
    private Shape shape;
    private boolean destroyed;
    private Starship starship;
    private BulletType bulletType;
    private int points = 0;
    private boolean alreadySum = false;

    public Bullet(float speed, boolean canPierce, Starship starship ,Vector2 direction, Vector2 position, Shape shape) {
        this.speed = speed;
        this.canPierce = canPierce;
        this.starship = starship;
        this.direction = direction;
        this.position = position;
        this.shape = shape;
        this.destroyed = false;
    }

    public Vector2 getDirection() {
        return direction;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void collisionedWith(Collisionable collisionable) {
        if(!canPierce){
            this.destroyed = true;
        }
        points += 50;
        alreadySum = false;
    }

    @Override
    public void move(float time) {
        position = position.add(direction.multiply(time).multiply(speed));
        shape = updateShape((int) position.getX(), (int) position.getY());
    }

    public abstract Shape updateShape(int x, int y);

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public boolean isNotInScreen(int maxHeight, int maxWidth) {
        return position.getX() > maxWidth || position.getX() < 0 || position.getY() > maxHeight || position.getY() < 0;
    }

    public BulletType getBulletType() {
        return bulletType;
    }

    public Starship getStarship() {
        return starship;
    }

    public int getPoints() {
        alreadySum = true;
        return points;
    }

    public boolean isAlreadySum() {
        return alreadySum;
    }
}
