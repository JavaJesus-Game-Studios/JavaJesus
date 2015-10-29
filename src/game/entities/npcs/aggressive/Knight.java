package game.entities.npcs.aggressive;

import game.ChatHandler;
import game.Game;
import game.entities.Player;

import java.awt.Color;

import level.Level;

public class Knight extends Shooter {

	private static final long serialVersionUID = 4729539304976811370L;
	
	private int coolTicks = 0;

	public Knight(Level level, int x, int y, String walkPath, int walkDistance) {
		super(level, "Knight", x, y, 1, 16, 16, 100, new int[] { 0xFF111111,
				0xFF7E7E7E, 0xFFFFFFFF }, 0, 2, walkPath, walkDistance);
		this.strength = 10;
	}

	public Knight(Level level, int x, int y) {
		super(level, "Knight", x, y, 1, 16, 16, 200, new int[] { 0xFF111111,
				0xFF7E7E7E, 0xFFFFFFFF }, 0, 2, "", 0);
		this.strength = 10;
	}

	public void tick() {
		super.tick();
		int xa = 0;
		int ya = 0;
		if (mob != null && this.aggroRadius.intersects(mob.getBounds())) {
			if (!cooldown && this.getOuterBounds().intersects(mob.getBounds())) {
				isShooting = true;
				cooldown = true;
				mob.damage(this.strength);
			}

			if (isShooting) {
				shootTickCount++;
				if (shootTickCount % 40 == 0) {
					shootTickCount = 0;
					isShooting = false;
				}
			}

			if (cooldown) {
				coolTicks++;
				if (coolTicks % 100 == 0) {
					coolTicks = 0;
					cooldown = false;
				}
			}

			if (!isShooting
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
		ChatHandler.displayText(name + ": Hello " + Game.player.getName(),
				Color.white);
		return;
	}

}
