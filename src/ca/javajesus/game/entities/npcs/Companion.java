package ca.javajesus.game.entities.npcs;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.monsters.Monster;
import ca.javajesus.game.entities.projectiles.Bullet;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Companion extends NPC {

	protected Ellipse2D.Double standRange;
	protected Mob mob;
	protected Ellipse2D.Double aggroRadius;
	protected final int RADIUS = 32 * 8;
	protected boolean cooldown = false;
	protected boolean isShooting = false;
	private Player player;

	public Companion(Level level, String name, int x, int y, int width,
			int height, int defaultHealth, int color, int xTile, int yTile,
			int yChange, Player player) {
		super(level, name, x, y, 1, width, height, defaultHealth, color, xTile,
				yTile, "", 0, yChange);
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		standRange = new Ellipse2D.Double(x - RADIUS / 4, y - RADIUS / 4,
				RADIUS / 2, RADIUS / 2);
		this.player = player;
		checkRadius();
	}

	private void checkRadius() {

		if (mob != null && mob.isDead()) {
			mob = null;
			movingToOrigin = true;
		}

		if (mob == null)
			for (Mob mob : level.getMobs()) {
				if (mob instanceof Monster) {
					if (this.aggroRadius.intersects(mob.getBounds())) {
						this.mob = mob;
						mob.setTargeted(true);
						return;
					}
				}
			}
	}

	public void tick() {
		super.tick();
		checkRadius();

		int xa = 0;
		int ya = 0;
		if (mob != null && this.aggroRadius.intersects(mob.getBounds())) {
			if (!cooldown) {
				cooldown = true;
				level.addEntity(new Bullet(level, this.x + 5, (this.y - 7), mob
						.getX(), mob.getY(), this, 3));
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

	public void render(Screen screen) {
		super.render(screen);
		this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
				RADIUS);
		this.standRange.setFrame(x - RADIUS / 4, y - RADIUS / 4, RADIUS / 2,
				RADIUS / 2);
	}

	public void speak(Player player) {
		isTalking = true;
		Random random = new Random();
		switch (random.nextInt(2)) {
		case 0:
			ChatHandler.sendMessage(name + ": What's up, bud?", Color.white);
			break;
		case 1:
			ChatHandler.sendMessage(name + ": I got your back!", Color.white);
			break;
		}
		return;
	}
}
