package edu.austral.starship.models.guns;


//automatic Gun --> shot 1 normal bullet at high speed
//heavyGun --> shot 1 power bullet at lower speed
//multiGun --> shot 2 normal bullet at lower speed

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.models.Starship;
import edu.austral.starship.models.bullets.Bullet;
import edu.austral.starship.models.bullets.BulletType;

public abstract class Gun {
    private BulletType bulletType;

    public Gun(BulletType bulletType) {
        this.bulletType = bulletType;
    }

    public abstract Bullet[] shot(Starship starship, Vector2 direction, Vector2 initialPosition);

    public BulletType getBulletType() {
        return bulletType;
    }

}
