package javajesus.entities.solid.furniture;

import java.awt.Rectangle;

import javajesus.dataIO.EntityData;
import javajesus.entities.Entity;
import javajesus.entities.SolidEntity;
import javajesus.entities.Type;
import javajesus.graphics.Screen;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Furniture is used as cluster inside of buildings
 */
public abstract class Furniture extends Entity implements SolidEntity, Type{

	// the sprite used for rendering
	private Sprite sprite;

	// the orientation
	private byte type;

	// fake shadow, but used to conform to solid entity
	private static final Rectangle shadow = new Rectangle(0, 0, 0, 0);
	
	// constants for orientation
	public final static byte NORTH = 0, WEST = 1, SOUTH = 2, EAST = 3, HORIZONTAL = 0, VERTICAL = 1;
	
	/**
	 * Creates clutter
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param sprite - the sprite to use
	 * @param color - the color
	 */
	public Furniture(Level level, int x, int y, byte orientation) {
		super(level, x, y);
		
		// instance data
		this.type = orientation;
		sprite = getSprite(orientation);
		setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
	}

	/**
	 * @return the sprite the furniture uses
	 */
	protected abstract Sprite getSprite(byte orientation);
	
	/**
	 * Changes the sprite and updates the bounds
	 * @param sprite - the new sprite to use
	 */
	protected void setSprite(Sprite sprite) {
		this.sprite = sprite;
		setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
	}

	/**
	 * Displays the furniture on the screen
	 */
	public void render(Screen screen) {
		screen.render(getX(), getY(), null, sprite);
	}
	
	@Override
	public Rectangle getShadow() {
		return shadow;
	}

	@Override
	public void tick() {}
	
	@Override
	public void setType(byte type) {
		this.type = type;
		setSprite(getSprite(type));
	}
	
	@Override
	public byte getType() {
		return type;
	}
	
	@Override
	public long getData() {
		return EntityData.type3(getX(), getY(), type);
	}
	
	@Override
	public void onCollisionWithEntity(Entity e) {
		return;
		
	}

	@Override
	public void onRemovedCollisionWithEntity(Entity e) {
		return;
	}

}
