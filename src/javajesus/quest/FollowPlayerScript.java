package javajesus.quest;

import java.awt.Point;

import javajesus.JavaJesus;
import javajesus.entities.Mob;
import javajesus.entities.Player;
import javajesus.utility.movement.Script;

/*
 * Simple logic script that makes the mob follow the player
 */
public class FollowPlayerScript extends Script {

	private static Player player;

	/**
	 * @param mob - the mob that has this script
	 */
	public FollowPlayerScript(Mob mob) {
		super(mob, new Point(mob.getX(), mob.getY()));
		player = JavaJesus.getPlayer();
	}

	/**
	 * Updates the destination to be that of the player
	 */
	private void update() {

		// offset the destination horizontally
		if (mob.getX() < player.getX()) {
			destination.x = player.getX() - 16;
		} else {
			destination.x = player.getX() + 16;
		}

		// offset the destination vertically
		if (mob.getY() < player.getY()) {
			destination.y = player.getY() - 16;
		} else {
			destination.y = player.getY() + 16;
		}

	}

	@Override
	public boolean isCompleted() {

		return false;
	}

	/**
	 * Updates the destination before returning it
	 */
	@Override
	public Point getDestination() {
		update();
		return super.getDestination();
	}

}
