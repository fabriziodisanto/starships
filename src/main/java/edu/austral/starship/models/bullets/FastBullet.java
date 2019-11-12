package edu.austral.starship.models.bullets;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.models.Starship;

import java.awt.*;

public class FastBullet extends Bullet {

    private BulletType bulletType;

    public FastBullet(Starship starship, Vector2 direction, Vector2 position) {
        super((float) 10, false, starship ,direction, position, new Rectangle((int) position.getX(), (int) position.getY(), 5, 5));
        this.bulletType = BulletType.FAST;
    }

    public BulletType getBulletType() {
        return bulletType;
    }

    public Shape updateShape(int x, int y) {
        return new Rectangle(x, y, 5, 5);
    }

}
