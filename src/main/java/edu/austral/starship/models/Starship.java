package edu.austral.starship.models;

import edu.austral.starship.base.collision.Collisionable;
import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.models.bullets.Bullet;
import edu.austral.starship.models.guns.Gun;

import java.awt.*;

public class Starship implements Element {

    private String name;
    private int points = 0;
    private int lives = 3;
    private Gun gun;
    private Vector2 position;
    private Vector2 direction = Vector2.vector((float) 0, (float) -0.1);
    private Shape shape;

    public Starship(Gun gun, int posX, int posY, String name) {
        this.name = name;
        this.gun = gun;
        this.shape = new Rectangle(0,0, 70, 70);
        this.position = Vector2.vector(posX, posY);
    }

    public Bullet[] shotGun(){
        return gun.shot(this, direction.unitary(), position.add(Vector2.vector(30, 35).add(direction.unitary().multiply(58f))));
    }

    public Vector2 getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public Gun getGun() {
        return gun;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void collisionedWith(Collisionable collisionable) {
        crash();
    }

    public int getLives() {
        return lives;
    }

    @Override
    public void move(float time) {
        if(isAlive()) {
            position = position.add(direction.multiply(time).multiply((float) 0.1));
            shape = new Rectangle((int) position.getX(), (int) position.getY(), 70, 70);
        }
    }

    public Starship moveForward() {
        if(this.direction.module() < 20){
            this.direction = this.direction.multiply(1.2f);
        }
        return this;
    }

    public Starship rotate(double angle) {
        this.direction = this.direction.rotate((float) angle).multiply(0.95f);
        return this;
    }

    public Starship breakSpeed() {
        if(this.direction.getX() == 0 && this.direction.getY() == 0){
            return this;
        }
        this.direction = this.direction.multiply(0.95f);
        return this;
    }

    public boolean isAlive(){
        return lives > 0;
    }

    @Override
    public boolean isNotInScreen(int maxHeight, int maxWidth) {
        return position.getX() > maxWidth || position.getX() < 0 || position.getY() > maxHeight || position.getY() < 0;
    }

    private void crash() {
        lives--;
        if(!isAlive()){
            this.shape = new Rectangle(0,0,0,0);
        }
    }

    public void addPoints(int points) {
        this.points += points;
    }
}
