package edu.austral.starship.models.guns;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.models.Starship;
import edu.austral.starship.models.bullets.Bullet;
import edu.austral.starship.models.bullets.BulletType;
import edu.austral.starship.models.bullets.FastBullet;

//shot 1 normal bullet at high speed
public class AutomaticGun extends Gun {

    public AutomaticGun() {
        super(BulletType.FAST);
    }

    @Override
    public Bullet[] shot(Starship starship, Vector2 direction, Vector2 initialPosition) {
        Bullet[] bullets = new Bullet[1];
        bullets[0] = new FastBullet(starship, direction, initialPosition);
        return bullets;
    }

}
