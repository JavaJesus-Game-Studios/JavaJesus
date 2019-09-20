package javajesus.entities.collision;

import javajesus.entities.Entity;

public interface CollisionListener {
	
	public Entity getEntity();
	
	public void onCollisionWithEntity(Entity e);
	
	public void onRemovedCollisionWithEntity(Entity e);

}
