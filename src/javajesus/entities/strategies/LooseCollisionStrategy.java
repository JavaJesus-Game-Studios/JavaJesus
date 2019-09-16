package javajesus.entities.strategies;

import java.awt.Rectangle;

import javajesus.entities.Entity;
import javajesus.entities.FireEntity;
import javajesus.entities.Mob;
import javajesus.entities.SolidEntity;

/**
 * Loose Collision Strategy collides with solid tiles and solid entities but not
 * mobs
 */
public class LooseCollisionStrategy implements CollisionStrategy {

	protected Mob mob;

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

		// the clipping offset when facing east/west
		int clip_xoffset = 5;
		int clip_yoffset = 2;

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
		Rectangle temp = new Rectangle(xMin, yMin, mob.getBounds().width - 2 * clip_xoffset,
				mob.getBounds().height / 2 - clip_yoffset);

		// loop through all entities
		for (Entity entity : mob.getLevel().getEntities()) {

			if (entity instanceof SolidEntity && temp.intersects(entity.getBounds())) {

				// fire collision
				if (entity instanceof FireEntity) {
					mob.ignite();
				}
				return true;
			}

		}
		return false;
	}

	public Mob getMobCollision() {

		// loop through the list of mobs
		for (Mob mob : mob.getLevel().getMobs()) {
			if (mob == this.mob)
				continue;
			if (!mob.isDead() && mob.getBounds().intersects(this.mob.getBounds()))
				return mob;
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

		// create a temporary range box shifted by dx and dy
		Rectangle range = new Rectangle(mob.getX() + dx, mob.getY() + dy, mob.getBounds().width,
				mob.getBounds().height);

		for (Mob mob : mob.getLevel().getMobs()) {
			if (range.intersects(mob.getBounds()) && mob != this.mob && !mob.isDead())
				return true;
		}

		return false;
	}

}
