package javajesus.entities.strategies;

import javajesus.JavaJesus;
import javajesus.entities.Mob;

public class RideableCollisionStrategy extends LooseCollisionStrategy {
	
	private boolean isUsed;

	public RideableCollisionStrategy(Mob m) {
		super(m);
	}
	
	@Override
	public Mob getMobCollision() {

		// loop through the list of mobs
		for (Mob mob : mob.getLevel().getMobs()) {
			if (mob == this.mob)
				continue;
			if (mob.getBounds().intersects(this.mob.getBounds()) && !mob.isDead() && (!isUsed || mob != JavaJesus.getPlayer()))
				return mob;
		}
		return null;
	}
	
	public void setUsed(boolean used) {
		isUsed = used;
	}

}
