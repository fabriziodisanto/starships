package edu.austral.starship.models.guns;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.models.Starship;
import edu.austral.starship.models.bullets.Bullet;
import edu.austral.starship.models.bullets.BulletType;
import edu.austral.starship.models.bullets.PowerBullet;

//shot 1 power bullet at low speed
public class HeavyGun extends Gun {

    public HeavyGun() {
        super(BulletType.POWER);
    }

    @Override
    public Bullet[] shot(Starship starship, Vector2 direction, Vector2 initialPosition) {
        Bullet[] bullets = new Bullet[1];
        bullets[0] = new PowerBullet(starship, direction, initialPosition);
        return bullets;
    }
}
