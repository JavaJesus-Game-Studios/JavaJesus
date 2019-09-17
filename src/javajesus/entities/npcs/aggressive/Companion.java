package javajesus.entities.npcs.aggressive;

import javajesus.entities.Entity;
import javajesus.entities.Player;
import javajesus.entities.npcs.NPC;
import javajesus.level.Level;

/*
 * Friendly shooter that follows the player
 */
public class Companion extends Shooter {

	// dimensions the companion
	private static final int WIDTH = 16, HEIGHT = 16;
	
	// the player it helps
	private Player player;

	/**
	 * Creates a NPC that follows the player
	 * @param level the level to place it on
	 * @param x the x coord
	 * @param y the y coord
	 * @param defaultHealth the default health
	 * @param npc - npc type to set as a companion
	 */
	public Companion(Level level, int x, int y, NPC type, Player player) {
		super(level, "Companion", x, y, 1, WIDTH, HEIGHT, type.getMaxHealth(), type.getColor(), type.getXTile(), type.getYTile(), "", 0);
		
		// initialize the player
		this.player = player;
	}
	
	/**
	 * Creates a default companion that follows the player
	 * @param level  - the level to place it on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param player - the player to assist
	 */
	public Companion(Level level, int x, int y, Player player) {
		super(level, "Companion", x, y, 1, WIDTH, HEIGHT, 250, new int[] { 0xFF111111, 0xFF000046, 0xFFEDC5AB, 0, 0 }, 0, 9, "", 0);
		
		// initialize the player
		this.player = player;
	}

	/**
	 * Updates the companion
	 */
	public void tick() {
		
		// prevents companion from walking back to spawn
		this.tickCount = 0;
		
		super.tick();

		// change in x and y
		int dx = 0, dy = 0;
		
		// Dont follow the player if shooting or player is null
		if (target != null || player == null) {
			return;
		}

		// makes the companion follow the player
		if (!this.getOuterBounds().intersects(player.getOuterBounds())) {

			if (player.getX() > getX()) {
				dx++;
			} else if (player.getX() < getX()) {
				dx--;
			}
			if (player.getY() > getY()) {
				dy++;
			} else if (player.getY() < getY()) {
				dy--;
			}
			if ((dx != 0 || dy != 0) && !collisionStrategy.isMobCollision(dx, dy)) {
				isMoving = true;
				move(dx, dy);
			}
		}

	}

	/**
	 * Companion Dialogue options
	 */
	public String getDialogue(Player player) {
		switch (random.nextInt(2)) {
		case 0: return " What's up, bud?";
		default: return "I got your back!";
		}
	}


	@Override
	public byte getId() {
		return Entity.COMPANION;
	}
}
