package javajesus.entities;

import java.awt.Rectangle;
import java.util.Random;

import javajesus.dataIO.EntityData;
import javajesus.graphics.Animation;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.level.tile.Tile;

/*
 * A pseudo animated tile that damages the player over time
 */
public class FireEntity extends Entity implements SolidEntity {
	
	// horizontal x position on sprite sheet
	public static int xTile;
	
	// vertical y position on sprite sheet
	public static final int yTile = 0;
	
	// colors of the flames
	private static final int[] color = { 0xFFF7790A, 0xFFF72808, 0xFF000000 };
	
	
	// dummy bounds of the fire
	private static final Rectangle shadow = new Rectangle();
	
	private Animation animation = new Animation();
	private int[] frames = {0,1,2,3,4};
	private int rate;
	/**
	 * Creates a fire entity that damages the player
	 * 
	 * @param - level the current level
	 * @param x - the x coord on the map
	 * @param y - the y coord on the map
	 * 
	 * The x and y coordiantes will be rounded to the upper
	 * left corner up a tile to snap into place
	 */
	public FireEntity(Level level, int x, int y) {
		super(level, Tile.snapToCorner(x), Tile.snapToCorner(y));
		Random randInt = new Random();
		this.rate = randInt.nextInt(6);
		this.rate += 4;

		// set the bounds
		setBounds(getX(), getY(), Tile.SIZE, Tile.SIZE);
		
	}

	/**
	 * Displays the pixels on the screen
	 */
	public void render(Screen screen) {
		animation.renderAnimation(screen, getX(), getY(), frames, yTile, 1, 1, rate, SpriteSheet.fireSmall, color);
	}

	@Override
	public byte getId() {
		return Entity.FIRE_ENTITY;
	}

	@Override
	public long getData() {
		return EntityData.type1(getX(), getY());
	}

	@Override
	public Rectangle getShadow() {
		return shadow;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
