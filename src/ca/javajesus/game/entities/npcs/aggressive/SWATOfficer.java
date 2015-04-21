package ca.javajesus.game.entities.npcs.aggressive;

import java.awt.Color;
import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.projectiles.Bullet;
import ca.javajesus.level.Level;

public class SWATOfficer extends Shooter {

	private static final long serialVersionUID = -2320584920776635420L;

	public SWATOfficer(Level level, int x, int y, int defaultHealth,
			String walkPath, int walkDistance) {
		super(level, "Swat Officer", x, y, 1, 16, 16, defaultHealth, new int[] {
				0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 10, walkPath,
				walkDistance);
	}

	public SWATOfficer(Level level, int x, int y) {
		super(level, "Swat Officer", x, y, 1, 16, 16, 200, new int[] {
				0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 10, "", 0);
	}

	public void tick() {
		super.tick();
		int xa = 0;
		int ya = 0;
		if (mob != null && this.aggroRadius.intersects(mob.getBounds())) {
			if (!cooldown) {
				isShooting = true;
				level.addEntity(new Bullet(level, this.x + 5, (this.y - 7), mob
						.getX(), mob.getY() - 4, this, 3, sound.assaultRifle));
			}
			if (!this.standRange.intersects(mob.getBounds())
					&& !this.getOuterBounds().intersects(mob.getBounds())) {

				if (mob.getX() > this.x) {
					xa++;
				}
				if (mob.getX() < this.x) {
					xa--;
				}
				if (mob.getY() > this.y) {
					ya++;
				}
				if (mob.getY() < this.y) {
					ya--;
				}
			}

			if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
					&& !isMobCollision(xa, ya)) {
				setMoving(true);
				move(xa, ya);
			} else {
				setMoving(false);
			}

		} else {
			if (movingToOrigin)
				findOrigin();
			else {
				findPath();
			}
		}

	}

	public void speak(Player player) {
		isTalking = true;
		ChatHandler.sendMessage(name + ": Stop right there, Criminal Scum!",
				Color.red);
		return;
	}
}
