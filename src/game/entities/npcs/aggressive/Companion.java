package game.entities.npcs.aggressive;

import game.ChatHandler;
import game.entities.Player;
import game.entities.projectiles.Bullet;

import java.awt.Color;
import java.util.Random;

import level.Level;

public class Companion extends Shooter {

	private static final long serialVersionUID = 1114048658566656656L;
	
	private Player player;

	public Companion(Level level, String name, int x, int y, int width,
			int height, int defaultHealth, int[] color, int xTile, int yTile, Player player) {
		super(level, name, x, y, 1, width, height, defaultHealth, color, xTile,
				yTile, "", 0);
		this.player = player;
	}

	public void tick() {
		super.tick();
		int xa = 0;
		int ya = 0;
		if (mob != null && this.aggroRadius.intersects(mob.getBounds())) {
			if (!cooldown) {
				cooldown = true;
				level.addEntity(new Bullet(level, this.x + 5, (this.y - 7), mob
						.getX(), mob.getY(), this, 3, sound.revolver));
			}
			if (!this.standRange.intersects(mob.getBounds())) {

				if ((int) mob.getX() > (int) this.x) {
					xa++;
				}
				if ((int) mob.getX() < (int) this.x) {
					xa--;
				}
				if ((int) mob.getY() > (int) this.y) {
					ya++;
				}
				if ((int) mob.getY() < (int) this.y) {
					ya--;
				}
			}

			if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
					&& !isMobCollision(xa, ya)) {
				move(xa, ya);
				setMoving(true);
			} else {
				setMoving(false);
			}

		} else {
			if (!this.getBounds().intersects(player.getBounds())) {

				if ((int) player.getX() > (int) this.x) {
					xa++;
				}
				if ((int) player.getX() < (int) this.x) {
					xa--;
				}
				if ((int) player.getY() > (int) this.y) {
					ya++;
				}
				if ((int) player.getY() < (int) this.y) {
					ya--;
				}
				if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
						&& !isMobCollision(xa, ya)) {
					move(xa, ya);
					setMoving(true);
				} else {
					setMoving(false);
				}
			}

		}

		if (cooldown) {
			if (tickCount % 100 == 0) {
				cooldown = false;
			}
		}

	}

	public void speak(Player player) {
		isTalking = true;
		Random random = new Random();
		switch (random.nextInt(2)) {
		case 0:
			ChatHandler.displayText(name + ": What's up, bud?", Color.white);
			break;
		case 1:
			ChatHandler.displayText(name + ": I got your back!", Color.white);
			break;
		}
		return;
	}
}
