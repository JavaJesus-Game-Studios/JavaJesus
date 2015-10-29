package game.entities.monsters;

import game.ChatHandler;
import game.entities.Mob;
import game.entities.Player;
import game.entities.npcs.NPC;
import game.graphics.Screen;
import game.graphics.SpriteSheet;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import level.Level;

public class Monster extends Mob {

	private static final long serialVersionUID = 4156279188503056448L;
	
	/** The player the monster is chasing */
	protected Mob mob;

	/** Range that the monster can target a player */
	protected Ellipse2D.Double aggroRadius;
	protected final int RADIUS = 32 * 8;
	protected boolean cooldown = true;
	protected int shootTickCount = 0;
	protected int yTile;

	public Monster(Level level, String name, int x, int y, int speed,
			int width, int height, int yTile, int health, int[] color) {
		super(level, name, x, y, speed, width, height, SpriteSheet.enemies,
				health);
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		this.yTile = yTile;
		checkRadius();
		this.speed = 1;
		this.color = color;
	}

	protected void checkRadius() {

		if (mob != null
				&& (mob.isDead() || !(this.aggroRadius.intersects(mob
						.getBounds())))) {
			mob = null;
		}

		if (mob == null)
			for (Mob mob : level.getMobs()) {
				if (mob instanceof Player || mob instanceof NPC) {
					if (this.aggroRadius.intersects(mob.getBounds()) && !mob.isDead()) {
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
	}

	public void speak(Player player) {
		isTalking = true;
		switch (random.nextInt(6)) {
		case 0: {
			ChatHandler.displayText("Rawr!", Color.white);
			return;
		}
		case 1: {
			ChatHandler.displayText("Arghhh", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText("OOOHH", Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText("ROAAR", Color.white);
			return;
		}
		case 4: {
			ChatHandler.displayText("HHRRRRSSHSHH", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText("RRRRRRRRRHHHH", Color.white);
			return;
		}
		}
	}

	public void render(Screen screen) {
		super.render(screen);
		this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
				RADIUS);

	}

}
