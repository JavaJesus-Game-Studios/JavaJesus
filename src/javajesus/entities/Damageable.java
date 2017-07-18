package javajesus.entities;

import java.awt.Rectangle;

/*
 * Anything that can be destroyed via a health system
 */
public interface Damageable {
	
	public int getCurrentHealth();
	
	public int getMaxHealth();
	
	public void damage(int damage);
	
	public Rectangle getBounds();

}
