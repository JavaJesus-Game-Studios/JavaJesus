package ca.javajesus.game.entities.npcs;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.monsters.Monster;
import ca.javajesus.game.entities.projectiles.Bullet;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Policeman extends NPC {
	private static int[] color = { 0xFF000000, 0xFF000046, 0xFFEDC5AB };
	protected Ellipse2D.Double standRange;
	protected Mob mob;
	protected Ellipse2D.Double aggroRadius;
	protected final int RADIUS = 32 * 8;
	protected boolean cooldown = true;
	protected boolean isShooting = false;
	protected int shootTickCount = 0;

	public Policeman(Level level, int x, int y, int defaultHealth,
			String walkPath, int walkDistance) {
		super(level, "SWAT Officer", x, y, 1, 16, 16, defaultHealth, color, 0,
				10, walkPath, walkDistance, 8);
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		standRange = new Ellipse2D.Double(x - RADIUS / 4, y - RADIUS / 4,
				RADIUS / 2, RADIUS / 2);
		checkRadius();
	}

	public Policeman(Level level, int x, int y) {
		super(level, "SWAT Officer", x, y, 1, 16, 16, 200, color, 0, 10, "", 0,
				8);
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		standRange = new Ellipse2D.Double(x - RADIUS / 4, y - RADIUS / 4,
				RADIUS / 2, RADIUS / 2);
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
		if (tickCount % 100 == 0) {
			cooldown = false;
		} else {
			cooldown = true;
		}
		int xa = 0;
		int ya = 0;
		if (mob != null && this.aggroRadius.intersects(mob.getBounds())) {
			if (!cooldown) {
				isShooting = true;
				level.addEntity(new Bullet(level, this.x + 5, (this.y - 7), mob
						.getX(), mob.getY() - 4, this, 3));
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

	public void render(Screen screen) {

		super.render(screen);
		this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
				RADIUS);
		this.standRange.setFrame(x - RADIUS / 4, y - RADIUS / 4, RADIUS / 2,
				RADIUS / 2);

	}

	public void speak(Player player) {
		isTalking = true;
		ChatHandler.sendMessage(name + ": Stop right there, Criminal Scum!",
				Color.red);
		return;
	}
}
