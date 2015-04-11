package ca.javajesus.game.entities.projectiles;

import javax.sound.sampled.Clip;

import ca.javajesus.game.entities.Mob;
import ca.javajesus.level.Level;

public class Arrow extends Projectile {

	public Arrow(Level level, double x, double y, double xPos, double yPos,
			Mob mob, double damage, Clip clip) {
		super(level, 2, 1, 1, new int[] { 0xFF000000, 0xFF000000, 0xFFFFFF00 },
				x, y, 6, xPos, yPos, mob, damage, clip);
		switch (mob.getDirection()) {
		case NORTH:
			this.tileNumber = 2 + 2 * sheet.boxes;
			return;
		case SOUTH:
			this.tileNumber = 2 + 1 * sheet.boxes;
			return;
		case WEST:
			this.tileNumber = 2 + 3 * sheet.boxes;
			return;
		case EAST:
			this.tileNumber = 2 + 0 * sheet.boxes;
			return;
		}
	}

	public Arrow(Level level, double x, double y, int direction, Mob mob,
			double damage, Clip clip) {
		super(level, 2, 1, 1, new int[] { 0xFF000000, 0xFF000000, 0xFFFFFF00 },
				x, y, 6, direction, mob, damage, clip);
		switch (mob.getDirection()) {
		case NORTH:
			this.tileNumber = 2 + 2 * sheet.boxes;
			return;
		case SOUTH:
			this.tileNumber = 2 + 1 * sheet.boxes;
			return;
		case WEST:
			this.tileNumber = 2 + 3 * sheet.boxes;
			return;
		case EAST:
			this.tileNumber = 2 + 0 * sheet.boxes;
			return;
		}
	}

}
