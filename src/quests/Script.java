package quests;

import game.entities.Mob;

import java.awt.Point;
import java.io.Serializable;

/*
 * A Script is a specific action on a specific mob
 * Scripts have the highest priority in determining a mob's direction
 */
public class Script implements Serializable {

	private static final long serialVersionUID = -7841027938100196923L;

	private Mob mob;
	private Point destination;
	private boolean isCompleted;

	/**
	 * @param mob
	 *            The targeted mob
	 * @param first
	 *            The first location to which the mob will travel
	 */
	public Script(Mob mob, Point first) {
		this.mob = mob;
		destination = first;
	}

	/**
	 * Handles movement for the Mob
	 */
	public void moveToPoint() {

		if (destination.x == mob.getX() && destination.y == mob.getY()) {
			finish();
			return;
		}
		int xa = 0;
		int ya = 0;
		if (destination.x > mob.getX()) {
			xa++;
		}
		if (destination.x < mob.getX()) {
			xa--;
		}
		if (destination.y > mob.getY()) {
			ya++;
		}
		if (destination.y < mob.getY()) {
			ya--;
		}
		if ((xa != 0 || ya != 0) && !mob.isSolidEntityCollision(xa, ya)
				&& !mob.isMobCollision(xa, ya)) {
			mob.setMoving(true);
			mob.move(xa, ya);
		} else {
			mob.setMoving(false);
		}
	}
	
	/**
	 * @return True if the script has been completed
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	private void finish() {
		isCompleted = true;
	}

}
