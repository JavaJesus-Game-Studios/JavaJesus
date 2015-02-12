package ca.javajesus.game.entities.monsters;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.npcs.NPC;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Monster extends Mob {

	public static Monster gang1 = new GangMember(Level.level1, "Gang Member 1",
			430, 120, 1, 100, 0);
	public static Monster horseThing1 = new Centaur(Level.level1, "HorseThing",
			500, 200, 1, 100);
	public static Monster monkey = new Monkey(Level.level1, "Monkey", 70, 70,
			1, 100);
	public static Monster gang2 = new GangMember(Level.level1, "Criminal", 100, 150, 1, 100, 1);
	public static Monster man2;

	protected int colour;
	/** The player the monster is chasing */
	protected Mob mob;

	/** Range that the monster can target a player */
	protected Ellipse2D.Double aggroRadius;

	protected final int RADIUS = 32 * 8;
	protected boolean cooldown = true;
	protected int tickCount = 0;
	protected boolean isShooting = false;
	protected int shootTickCount = 0;
	protected int yTile;

	public Monster(Level level, String name, double x, double y, int speed,
			int width, int height, int yTile, double health, int color) {
		super(level, name, x, y, speed, width, height, SpriteSheet.enemies,
				health);
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		this.yTile = yTile;
		checkRadius();
		this.colour = color;
		this.scaledSpeed = 0.35;
	}

	protected void checkRadius() {

		if (mob != null && mob.hasDied) {
			mob = null;
		}

		if (mob == null)
			for (Mob mob : level.getMobs()) {
				if (mob instanceof Player || mob instanceof NPC) {
					if (this.aggroRadius.intersects(mob.hitBox)) {
						// if (!mob.isTargeted) {
						this.mob = mob;
						mob.isTargeted = true;
						return;
						// }
					}
				}
			}

	}

	public boolean hasCollided(int xa, int ya) {
		return false;

	}

	public void tick() {
		
		if (hasDied)
			return;
		
	}
	
	public void speak(Player player) {
		switch (random.nextInt(6)) {
		case 0: {
			ChatHandler.sendMessage("Rawr!",
					Color.black);
			return;
		}
		case 1: {
			ChatHandler.sendMessage("Arghhh", Color.black);
			return;
		}
		case 2: {
			ChatHandler.sendMessage("OOOHH", Color.black);
			return;
		}
		case 3: {
			ChatHandler
					.sendMessage(
							"ROAAR",
							Color.black);
			return;
		}
		case 4: {
			ChatHandler.sendMessage("HHRRRRSSHSHH",
					Color.black);
			return;
		}
		default: {
			ChatHandler.sendMessage("RRRRRRRRRHHHH", Color.black);
			return;
		}
		}
	}

	public void render(Screen screen) {
		
	}

}
