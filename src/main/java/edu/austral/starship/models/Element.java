package edu.austral.starship.models;

import edu.austral.starship.base.collision.Collisionable;

public interface Element extends Movable, Collisionable {
    boolean isNotInScreen(int maxHeight, int maxWidth);

}
