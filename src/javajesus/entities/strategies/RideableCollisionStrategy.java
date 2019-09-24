package javajesus.entities.strategies;

import java.util.ArrayList;

import javajesus.JavaJesus;
import javajesus.entities.Mob;
import javajesus.entities.collision.CollisionBox;

public class RideableCollisionStrategy extends LooseCollisionStrategy {
	
	private boolean isUsed;

	public RideableCollisionStrategy(Mob m) {
		super(m);
	}
	
	@Override
	public Mob getMobCollision() {
		
		ArrayList<CollisionBox> others = mob.getBounds().getCollisions();
		for (CollisionBox b: others) {
			if (b.getEntity() instanceof Mob && (!isUsed || b.getEntity() != JavaJesus.getPlayer())) {
				return (Mob) b.getEntity();
			}
		}
		return null;
	}
	
	public void setUsed(boolean used) {
		isUsed = used;
	}

}
