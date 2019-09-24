package javajesus.entities.strategies;

import java.util.ArrayList;

import javajesus.entities.Mob;
import javajesus.entities.SolidEntity;
import javajesus.entities.collision.CollisionBox;
import javajesus.entities.collision.Coordinate;
import javajesus.entities.collision.CoordinateObserver;

/**
 * Loose Collision Strategy collides with solid tiles and solid entities but not
 * mobs
 */
public class LooseCollisionStrategy implements CollisionStrategy {

	protected Mob mob;
	// the clipping offset when facing east/west
	protected int clip_xoffset = 5;
	protected int clip_yoffset = 2;

	public LooseCollisionStrategy(Mob m) {
		this.mob = m;
	}

	/**
	 * Determines if the change in x or y results in a solid collision
	 * 
	 * @param dx - the change in x
	 * @param dy - the change in y
	 * @return true if the change in coordinates results in a solid tile collision
	 */
	public boolean willCollide(int dx, int dy) {

		// the left bound of the mob
		int xMin = mob.getX() + dx + clip_xoffset;

		// the right bound of the mob
		int xMax = mob.getX() + mob.getBounds().width + dx - clip_xoffset;

		// the top bound of the mob
		int yMin = mob.getY() + mob.getBounds().height / 2 + dy;

		// the bottom bound of the mob
		int yMax = mob.getY() + mob.getBounds().height + dy - clip_yoffset;

		// check for solid tile collisions at the 4 corners
		if (mob.getLevel().getTileFromEntityCoords(xMin, yMin).isSolid()
				|| mob.getLevel().getTileFromEntityCoords(xMax, yMin).isSolid()
				|| mob.getLevel().getTileFromEntityCoords(xMin, yMax).isSolid()
				|| mob.getLevel().getTileFromEntityCoords(xMax, yMax).isSolid()) {
			return true;
		}

		// check for solid entity collisions
		CollisionBox temp = new CollisionBox(xMin - dx, yMin - dy, mob.getBounds().width - 2 * clip_xoffset,
				mob.getBounds().height / 2 - clip_yoffset, null);

		ArrayList<Coordinate> coords = mob.getLevel().getCanvas().getNewCoordinates(temp, dx, dy);

		for (Coordinate c : coords) {
			for (CoordinateObserver o : c.getCollisions()) {
				CollisionBox b = (CollisionBox) o;
				if (b.getEntity() instanceof SolidEntity) {
					return true;
				}
			}
		}

		return false;
	}

	public Mob getMobCollision() {

		ArrayList<CollisionBox> others = mob.getBounds().getCollisions();
		for (CollisionBox b: others) {
			if (b.getEntity() instanceof Mob) {
				return (Mob) b.getEntity();
			}
		}
		return null;
	}

	/**
	 * Checks if a mob will collide with another mob at the new location
	 * 
	 * @param dx - the change in x
	 * @param dy - the change in y
	 * @return true if a mob is already occupying that space
	 */
	public boolean isMobCollision(int dx, int dy) {

		// check for solid entity collisions
		CollisionBox temp = new CollisionBox(mob.getX() + dx, mob.getY() + dy, mob.getBounds().width,
				mob.getBounds().height, null);

		ArrayList<Coordinate> coords = mob.getLevel().getCanvas().getNewCoordinates(temp, dx, dy);

		for (Coordinate c : coords) {
			for (CoordinateObserver o : c.getCollisions()) {
				CollisionBox b = (CollisionBox) o;
				if (b.getEntity() instanceof Mob) {
					return true;
				}
			}
		}

		return false;
	}

}
