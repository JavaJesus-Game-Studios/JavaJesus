package javajesus.entities.solid.furniture;

import java.awt.Rectangle;

import javajesus.entities.Entity;
import javajesus.entities.SolidEntity;
import javajesus.graphics.Screen;
import javajesus.graphics.Sprite;
import javajesus.level.Level;

/*
 * Furniture is used as cluster inside of buildings
 */
public abstract class Furniture extends Entity implements SolidEntity {

	// the sprite used for rendering
	private Sprite sprite;

	// the colorset
	private int[] color;

	// fake shadow, but used to conform to solid entity
	private static final Rectangle shadow = new Rectangle(0, 0, 0, 0);

	protected final static Sprite diningTableSprite = new Sprite(
			"/VISUAL_DATA/STATICS/FURNITURE/Long_Castle_Dining_Table.png");
	protected final static Sprite bed = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Bed.png");
	protected final static Sprite squareTable = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Square_Table.png");
	protected final static Sprite bench = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Bench.png");
	protected final static Sprite chairFront = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Chair_Front_Facing.png");
	protected final static Sprite chairSide = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Chair.png");
	protected final static Sprite computerMonitor = new Sprite(
			"/VISUAL_DATA/STATICS/FURNITURE/Simple_Computer_monitor.png");
	protected final static Sprite computerTower = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Computer_Tower.png");
	protected final static Sprite filingCabinet = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Filing_Cabinet.png");
	protected final static Sprite longTable = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Long_Table.png");
	protected final static Sprite nightstand = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Nightstand.png");
	protected final static Sprite sofa = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Sofa.png");
	protected final static Sprite stool = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Stool.png");
	protected final static Sprite throne = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_Throne.png");
	protected final static Sprite television = new Sprite("/VISUAL_DATA/STATICS/FURNITURE/Simple_TV.png");

	/**
	 * Creates clutter
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 * @param sprite
	 *            the sprite to use
	 * @param color
	 *            the color
	 */
	public Furniture(Level level, int x, int y, Sprite sprite, int[] color) {
		super(level, x, y);
		this.sprite = sprite;
		this.color = color;

		setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
	}

	/**
	 * @return the sprite the furniture uses
	 */
	protected Sprite getSprite() {
		return sprite;
	}

	/**
	 * Displays the furniture on the screen
	 */
	public void render(Screen screen) {

		screen.render(getX(), getY(), color, sprite);

	}

	@Override
	public Rectangle getShadow() {
		return shadow;
	}

	@Override
	public void tick() {

	}

}
