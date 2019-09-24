package javajesus.entities;

import javajesus.entities.collision.CollisionBox;

/*
 * Anything that can be destroyed via a health system
 */
public interface Damageable {
	
	public short getCurrentHealth();
	
	public short getMaxHealth();
	
	public void setMaxHealth(short max);
	
	public void damage(int damage);
	
	public CollisionBox getBounds();
	
	public boolean isDead();

}
