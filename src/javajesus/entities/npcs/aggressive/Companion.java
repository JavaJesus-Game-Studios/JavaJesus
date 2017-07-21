package javajesus.entities.npcs.aggressive;

import java.awt.Color;

import javajesus.MessageHandler;
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

	/**
	 * Creates a NPC that follows the player
	 * @param level the level to place it on
	 * @param x the x coord
	 * @param y the y coord
	 * @param defaultHealth the default health
	 * @param npc - npc type to set as a companion
	 */
	public Companion(Level level, int x, int y, NPC type) {
		super(level, "Companion", x, y, 1, WIDTH, HEIGHT, type.getMaxHealth(), type.getColor(), type.getXTile(), type.getYTile(), "", 0);
	}
	
	/**
	 * Creates a default companion that follows the player
	 * @param level the level to place it on
	 * @param x the x coord
	 * @param y the y coord
	 */
	public Companion(Level level, int x, int y) {
		super(level, "Companion", x, y, 1, WIDTH, HEIGHT, 250, new int[] { 0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 4, "", 0);
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
		if (target != null || getLevel().getPlayer() == null) {
			return;
		}

		// makes the companion follow the player
		if (!this.getOuterBounds().intersects(getLevel().getPlayer().getOuterBounds())) {

			if (getLevel().getPlayer().getX() > getX()) {
				dx++;
			} else if (getLevel().getPlayer().getX() < getX()) {
				dx--;
			}
			if (getLevel().getPlayer().getY() > getY()) {
				dy++;
			} else if (getLevel().getPlayer().getY() < getY()) {
				dy--;
			}
			if ((dx != 0 || dy != 0) && !isMobCollision(dx, dy)) {
				isMoving = true;
				move(dx, dy);
			}
		}

	}

	/**
	 * Companion Dialogue options
	 */
	@Override
	public void doDialogue(Player player) {
		switch (random.nextInt(2)) {
		case 0:
			MessageHandler.displayText(getName() + ": What's up, bud?", Color.white);
			break;
		case 1:
			MessageHandler.displayText(getName() + ": I got your back!", Color.white);
			break;
		}
		return;
	}


	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Entity.COMPANION;
	}
}
