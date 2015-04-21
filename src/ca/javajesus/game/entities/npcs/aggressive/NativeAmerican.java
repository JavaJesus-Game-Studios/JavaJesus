package ca.javajesus.game.entities.npcs.aggressive;

import java.awt.Color;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.projectiles.Bullet;
import ca.javajesus.level.Level;

public class NativeAmerican extends Shooter {

	private static final long serialVersionUID = 4219698068135987513L;
	
	private boolean shooter;

	public NativeAmerican(Level level, int x, int y, int defaultHealth,
			String walkPath, int walkDistance, Gender g) {
		super(level, "Native American", x, y, 1, 16, 16, defaultHealth,
				new int[] { 0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 28,
				walkPath, walkDistance);
		if (g == Gender.MALE) {
			shooter = true;
		} else {
			shooter = false;
			yTile += 2;
		}
	}

	public NativeAmerican(Level level, int x, int y, Gender g) {
		super(level, "Native American", x, y, 1, 16, 16, 200, new int[] {
				0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 28, "", 0);
		if (g == Gender.MALE) {
			shooter = true;
		} else {
			shooter = false;
			yTile += 2;
		}
	}

	public void tick() {
		super.tick();
		int xa = 0;
		int ya = 0;
		if (mob != null && this.aggroRadius.intersects(mob.getBounds())
				&& shooter) {
			if (!cooldown) {
				isShooting = true;
				level.addEntity(new Bullet(level, this.x + 5, (this.y - 7), mob
						.getX(), mob.getY() - 4, this, 3, sound.revolver));
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
		ChatHandler.sendMessage(name + ": I belong to the wind.", Color.white);
		return;
	}

}
