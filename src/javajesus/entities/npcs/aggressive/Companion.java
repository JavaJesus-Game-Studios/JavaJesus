package javajesus.entities.npcs.aggressive;

import java.awt.Color;

import javajesus.ChatHandler;
import javajesus.Game;
import javajesus.entities.Player;
import level.Level;

/*
 * Friendly shooter that follows the player
 */
public class Companion extends Shooter {

	private static final long serialVersionUID = 1114048658566656656L;

	// the player to accompany
	private Player player;

	// dimensions the companion
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates a NPC that follows the player
	 * @param level the level to place it on
	 * @param x the x coord
	 * @param y the y coord
	 * @param defaultHealth the default health
	 * @param color the colorset
	 * @param xTile the xtile on the spritesheet
	 * @param yTile the y tile on the spritesheet
	 * @param player the player to follow
	 */
	public Companion(Level level, int x, int y, int defaultHealth, int[] color, int xTile, int yTile) {
		super(level, "Companion", x, y, 1, WIDTH, HEIGHT, defaultHealth, color, xTile, yTile, "", 0);
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
		
		// find the closest player
		if (player == null) {
			player = getLevel().getPlayer(Game.PLAYER_NAME);
			return;
		}
		
		// Dont follow the player if shooting
		if (target != null) {
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
			if ((dx != 0 || dy != 0) && !isMobCollision(dx, dy)) {
				isMoving = true;
				move(dx, dy);
			}
		}

	}

	/**
	 * Companion Dialogue options
	 */
	public void doDialogue() {
		switch (random.nextInt(2)) {
		case 0:
			ChatHandler.displayText(getName() + ": What's up, bud?", Color.white);
			break;
		case 1:
			ChatHandler.displayText(getName() + ": I got your back!", Color.white);
			break;
		}
		return;
	}
}
