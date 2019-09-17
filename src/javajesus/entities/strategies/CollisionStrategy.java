package javajesus.entities.strategies;

import javajesus.entities.Mob;

public interface CollisionStrategy {
	
	public boolean willCollide(int dx, int dy);
	
	/**
	 * Determines if a mob's bounds are intersecting another mob's bounds as it is
	 * 
	 * @return the other mob that is colliding with this mob, null if there isn't
	 *         one
	 */
	public Mob getMobCollision();
	
	public boolean isMobCollision(int dx, int dy);
	

}
