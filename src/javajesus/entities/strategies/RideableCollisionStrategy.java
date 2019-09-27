package javajesus.entities.strategies;

import java.util.ArrayList;

import javajesus.JavaJesus;
import javajesus.entities.Entity;
import javajesus.entities.Mob;

public class RideableCollisionStrategy extends LooseCollisionStrategy {
	
	private boolean isUsed;
	
	private ArrayList<Entity> tempEntities = new ArrayList<Entity>(JavaJesus.ENTITY_LIMIT);


	public RideableCollisionStrategy(Mob m) {
		super(m);
	}
	
	@Override
	public Mob getMobCollision() {
		// Get list of nearby Entities
		tempEntities.clear();
		mob.getLevel().getCollisionTree().retrieve(tempEntities, this.mob.getBounds());
		Mob mob;
		// loop through the list of nearby mobs
		for (Entity e: tempEntities) {
			if(e instanceof Mob) {
				mob = (Mob)e;
				if (mob == this.mob)
					continue;
				if (!mob.isDead() && (!isUsed || mob != JavaJesus.getPlayer()))
					return mob;
			}
		}
		return null;
	}
	
	public void setUsed(boolean used) {
		isUsed = used;
	}

}
