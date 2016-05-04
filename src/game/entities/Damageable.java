package game.entities;

public interface Damageable {
	
	public int getCurrentHealth();
	
	public int getMaxHealth();
	
	public void damage(int damage);

}
