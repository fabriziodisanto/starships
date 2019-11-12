package edu.austral.starship.models.bullets;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.models.Starship;

import java.awt.*;

public class NormalBullet extends Bullet {

    private BulletType bulletType;

    public NormalBullet(Starship starship, Vector2 direction, Vector2 position) {
        super((float) 6, false, starship ,direction, position, new Rectangle((int) position.getX(), (int) position.getY(), 8, 8));
        this.bulletType = BulletType.MULTI;
    }

    public BulletType getBulletType() {
        return bulletType;
    }

    public Shape updateShape(int x, int y) {
        return new Rectangle(x, y, 8, 8);
    }
}
