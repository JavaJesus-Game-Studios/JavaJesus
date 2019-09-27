package javajesus.entities.strategies;

import java.awt.Rectangle;
import java.util.ArrayList;

import javajesus.JavaJesus;
import javajesus.SoundHandler;
import javajesus.entities.Entity;
import javajesus.entities.Mob;
import javajesus.entities.monsters.Monster;

/**
 * Strict Collision Strategy collides with solid tiles and solid entities AND
 * mobs
 */
public class StrictCollisionStrategy extends LooseCollisionStrategy {
	
	private ArrayList<Entity> tempEntities = new ArrayList<Entity>(JavaJesus.ENTITY_LIMIT);

	public StrictCollisionStrategy(Mob m) {
		super(m);
	}

	/**
	 * Determines if the change in x or y results in a solid collision
	 * 
	 * @param dx - the change in x
	 * @param dy - the change in y
	 * @return true if the change in coordinates results in a solid tile collision
	 */
	public boolean willCollide(int dx, int dy) {

		boolean collision = super.willCollide(dx, dy) || isMobCollision(dx, dy);

		if (collision) {
			SoundHandler.playAmbience(SoundHandler.bump);
		}

		return collision;
	}

	public boolean isMobCollision(int dx, int dy) {

		// create a temporary range box shifted by dx and dy
		Rectangle range = new Rectangle(mob.getX() + dx, mob.getY() + dy, mob.getBounds().width,
				mob.getBounds().height);
		
		// Get list of nearby Entities
		tempEntities.clear();
		mob.getLevel().getCollisionTree().retrieve(tempEntities, range);
		Mob mob;
		for (Entity e : tempEntities) {
			if( e instanceof Mob ) {
				mob = (Mob)e;
				if (mob instanceof Monster) {
					Rectangle other = new Rectangle(mob.getX() + clip_xoffset, mob.getY() + mob.getBounds().height / 2,
							mob.getBounds().width - 2 * clip_xoffset, mob.getBounds().height / 2 - clip_yoffset);
					if (other.intersects(range.getBounds()) && mob != this.mob && !mob.isDead())
						return true;
				}
			}
		}

		return false;
	}

}
