package game.entities.npcs.aggressive;

import game.entities.Mob;
import game.entities.monsters.Monster;
import game.entities.npcs.NPC;
import game.graphics.Screen;

import java.awt.geom.Ellipse2D;

import level.Level;

public class Shooter extends NPC {

	private static final long serialVersionUID = -4738701705942228492L;
	
	protected Ellipse2D.Double standRange;
	protected Mob mob;
	protected Ellipse2D.Double aggroRadius;
	protected final int RADIUS = 32 * 8;
	protected boolean cooldown = true;
	protected int shootTickCount = 0;

	public Shooter(Level level, String name, int x, int y, int speed,
			int width, int height, int defaultHealth, int[] color, int xTile,
			int yTile, String walkPath, int walkDistance) {
		super(level, name, x, y, speed, width, height, defaultHealth, color,
				xTile, yTile, walkPath, walkDistance);
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		standRange = new Ellipse2D.Double(x - RADIUS / 4, y - RADIUS / 4,
				RADIUS / 2, RADIUS / 2);
		checkRadius();
	}

	protected void checkRadius() {

		if (mob != null
				&& (mob.isDead() || !(this.aggroRadius.intersects(mob
						.getBounds())))) {
			mob = null;
			movingToOrigin = true;
		}

		if (mob == null) {
			isShooting = false;
			for (Mob mob : level.getMobs()) {
				if (mob instanceof Monster && !mob.isDead()) {
					if (this.aggroRadius.intersects(mob.getBounds())) {
						this.mob = mob;
						mob.setTargeted(true);
						return;
					}
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
	}

	public void render(Screen screen) {
		super.render(screen);
		this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
				RADIUS);
		this.standRange.setFrame(x - RADIUS / 4, y - RADIUS / 4, RADIUS / 2,
				RADIUS / 2);

	}

}
