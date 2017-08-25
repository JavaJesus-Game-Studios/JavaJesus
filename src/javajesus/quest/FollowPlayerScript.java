package javajesus.quest;

import java.awt.Point;

import javajesus.entities.Mob;
import javajesus.entities.Player;
import javajesus.utility.movement.Script;

/*
 * Simple logic script that makes the mob follow the player
 */
public class FollowPlayerScript extends Script {

	/**
	 * @param mob - the mob that has this script
	 */
	public FollowPlayerScript(Mob mob) {
		super(mob, new Point(mob.getX(), mob.getY()));
	}
	
	/**
	 * Updates the destination to be that of the player
	 */
	private void update() {
		
		// get the player on the mobs level
		for (Mob m: mob.getLevel().getMobs()) {
			
			// if player, get its location
			if (m instanceof Player) {
				
				// cast the mob as a player
				Player player = (Player) m;
				
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
				
				break;
			}
			
		}
		
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
