package javajesus.entities;

import java.awt.Rectangle;

/*
 * Anything that can be destroyed via a health system
 */
public interface Damageable {
	
	public short getCurrentHealth();
	
	public short getMaxHealth();
	
	public void setMaxHealth(short max);
	
	public void damage(int damage);
	
	public Rectangle getBounds();
	
	public boolean isDead();

}
