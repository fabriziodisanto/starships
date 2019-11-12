package edu.austral.starship.models.guns;

import edu.austral.starship.base.vector.Vector2;
import edu.austral.starship.models.Starship;
import edu.austral.starship.models.bullets.Bullet;
import edu.austral.starship.models.bullets.BulletType;
import edu.austral.starship.models.bullets.NormalBullet;

//shot 2 normal bullet at low speed
public class MultiGun extends Gun {

    public MultiGun() {
        super(BulletType.MULTI);
    }

    @Override
    public Bullet[] shot(Starship starship, Vector2 direction, Vector2 position) {
        Bullet[] bullets = new Bullet[2];
        bullets[0] = new NormalBullet(starship, direction, position);
        bullets[1] = new NormalBullet(starship, direction.multiply(-1f), position.substract(direction.unitary().multiply(120f)));
        return bullets;
    }
}
