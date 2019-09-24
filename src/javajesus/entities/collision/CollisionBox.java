package javajesus.entities.collision;

import java.util.ArrayList;

import javajesus.entities.Entity;

public class CollisionBox implements CoordinateObserver {
	
	public int x, y, width, height;
	
	private CollisionListener listener;
	
	private ArrayList<CollisionBox> collisions;
	
	public CollisionBox(int x, int y, int width, int height, CollisionListener l) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.listener = l;
	}

	@Override
	public void onCollisionBoxAdded(CoordinateObserver o) {
		CollisionBox other = (CollisionBox) o;
		collisions.add(other);
		
		// do some logic
		listener.onCollisionWithEntity(other.getEntity());
		
	}

	@Override
	public void onCollisionBoxRemoved(CoordinateObserver o) {
		CollisionBox other = (CollisionBox) o;
		collisions.remove(other);
		
		// do some logic
		listener.onRemovedCollisionWithEntity(other.getEntity());
		
	}
	
	public Entity getEntity() {
		return listener.getEntity();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getCenterX() {
		return x + width / 2;
	}
	
	public int getCenterY() {
		return y + height / 2;
	}
	
	public ArrayList<CollisionBox> getCollisions() {
		return collisions;
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public boolean intersects(CollisionBox other) {
		return x <= other.x + other.width && x + width >= other.x &&
				y <= other.y + other.height && y + height >= other.y;
	}


}
