package edu.austral.starship.models.bullets;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.models.Starship;

import java.awt.*;

public class PowerBullet extends Bullet {

    private BulletType bulletType;

    public PowerBullet(Starship starship, Vector2 direction, Vector2 position) {
        super((float) 4, true, starship, direction, position, new Rectangle((int) position.getX(), (int) position.getY(), 12, 12));
        this.bulletType = BulletType.POWER;
    }

    public BulletType getBulletType() {
        return bulletType;
    }

    public Shape updateShape(int x, int y) {
        return new Rectangle(x, y, 12, 12);
    }

}
