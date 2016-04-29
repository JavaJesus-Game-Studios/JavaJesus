package game.entities.structures.transporters;

import java.awt.Point;
import game.entities.Entity;
import game.entities.Player;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import level.Level;

/*
 * A transporter is a clippable entity that sends a player to another level
 * This class is a generic door
 */
public class Transporter extends Entity {

	private static final long serialVersionUID = -7494182586888198075L;
	
	// colorset of this door
	private static final int[] color = { 0xFF111111, 0xFF704200, 0xFFFFDE00 };

	// the level the transporter leads to
	private Level nextLevel;

	/**
	 * Creates a Transporter to change the level
	 * @param currentLevel the level it is on
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param nextLevel the new level
	 */
	public Transporter(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y);

		this.nextLevel = nextLevel;
		this.setBounds(getX(), getY(), 8, 16);
	}

	/**
	 * Creates a Transporter to change the level but also
	 * updates that new level's spawnpoint
	 * @param currentLevel the level it is on
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param nextLevel the new level
	 * @param point the spawnpoint for the new level
	 */
	public Transporter(Level currentLevel, int x, int y, Level nextLevel, Point point) {
		this(currentLevel, x, y, nextLevel);
		nextLevel.spawnPoint = point;
	}

	/**
	 * Updates the transporter TODO Handle this in the player class
	 */
	public void tick() {

		for (Player player : getLevel().getPlayers()) {
			if (getBounds().intersects(player.getBounds())) {
				if (player.getInput().e.isPressed()) {
					getLevel().setSpawnPoint(player.getX(), player.getY());
					player.updateLevel(nextLevel);
				}
			}
		}

	}

	/**
	 * Displays the transporter on the screen
	 */
	public void render(Screen screen) {

		screen.render(getX() + 0, getY() + 0, 0 + 5 * 32, color, SpriteSheet.tiles);
		screen.render(getX() + 8, getY() + 0, 1 + 5 * 32, color, SpriteSheet.tiles);
		screen.render(getX() + 0, getY() + 8, 0 + 6 * 32, color, SpriteSheet.tiles);
		screen.render(getX() + 8, getY() + 8, 1 + 6 * 32, color, SpriteSheet.tiles);
	}
	
	/**
	 * @return the next level
	 */
	public Level getNextLevel() {
		return nextLevel;
	}

}
