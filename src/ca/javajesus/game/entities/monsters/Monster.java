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

	/** The player the monster is chasing */
	protected Mob mob;

	/** Range that the monster can target a player */
	protected Ellipse2D.Double aggroRadius;

	protected final int RADIUS = 32 * 8;
	protected boolean cooldown = true;
	protected int tickCount = 0;
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
		this.speed = speed * 0.35;
		this.color = color;
	}

	protected void checkRadius() {

		if (mob != null && mob.isDead) {
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
		
		if (isDead)
			return;
		
		if (isHit) {
			isHitTicks++;
			if (isHitTicks > 20) {
				isHitTicks = 0;
				isHit = false;
			}
		}
		
		if (isTalking) {
			talkCount++;
			if (talkCount > 350) {
				talkCount = 0;
				isTalking = false;
			}
		}
		
	}
	
	public void speak(Player player) {
		isTalking = true;
		switch (random.nextInt(6)) {
		case 0: {
			ChatHandler.sendMessage("Rawr!",
					Color.white);
			return;
		}
		case 1: {
			ChatHandler.sendMessage("Arghhh", Color.white);
			return;
		}
		case 2: {
			ChatHandler.sendMessage("OOOHH", Color.white);
			return;
		}
		case 3: {
			ChatHandler
					.sendMessage(
							"ROAAR",
							Color.white);
			return;
		}
		case 4: {
			ChatHandler.sendMessage("HHRRRRSSHSHH",
					Color.white);
			return;
		}
		default: {
			ChatHandler.sendMessage("RRRRRRRRRHHHH", Color.white);
			return;
		}
		}
	}

	public void render(Screen screen) {
		
	}

}
