package javajesus.entities.transporters;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.save.SaveData;

/*
 * A transporter is a clippable entity that sends a player to another level
 * This class is a generic door
 */
public class Transporter extends Entity {

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
		this.setBounds(getX(), getY(), 12, 16);
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
		
		if(nextLevel.getSpawnPoint() == null)
			nextLevel.setSpawnPoint(point.x, point.y);
	}

	/**
	 * Updates the transporter TODO Handle this in the player class
	 */
	public void tick() {

	}

	/**
	 * Displays the transporter on the screen
	 */
	public void render(Screen screen) {
		screen.render16bit(getX(), getY(), 0, 5, SpriteSheet.tiles, color);
	}
	
	/**
	 * Sets the nextLevel
	 * @param next
	 */
	public final void setNextLevel(Level next) {
		nextLevel = next;
	}
	
	/**
	 * @return the next level
	 */
	public Level getNextLevel() {
		return nextLevel;
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public long getData() {
		// TODO extra is ID to another level
		return SaveData.type3(getX(), getY(), (byte) 0);
	}

}
