package javajesus.entities.solid.furniture;

import java.awt.Rectangle;
import java.util.ArrayList;

import javajesus.dataIO.EntityData;
import javajesus.dataIO.LootData;
import javajesus.entities.Entity;
import javajesus.entities.SolidEntity;
import javajesus.entities.Type;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.items.Item;
import javajesus.level.Level;

/*
 * Creates a chest that stores items for the player to use
 */
public class Chest extends Entity implements SolidEntity, Type {

	// color set of the chest
	private static final int[] color = new int[] { 0xFF111111, 0xFF452909, 0xFFFFE011 };

	// whether or not the chest is open
	private boolean isOpen;

	// what items are in the chest
	private final ArrayList<Item> contents = new ArrayList<Item>();

	// the size of the chest
	private static final int SIZE = 8;

	// fake shadow, but used to conform to solid entity
	private static final Rectangle shadow = new Rectangle(0, 0, 0, 0);
	
	// loot data
	private byte lootId;

	/**
	 * Creates an empty chest
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 */
	public Chest(Level level, int x, int y) {
		this(level, x, y, new Item[] {});

	}

	/**
	 * Creates a chest filled with certain items
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param items - the items it contains
	 */
	public Chest(Level level, int x, int y, Item... items) {
		super(level, x, y);

		// add all the items to the contents
		for (Item e: items) {
			contents.add(e);
		}
		setBounds(getX(), getY(), SIZE + 2, SIZE);
	}

	/**
	 * Creates a chest filled with certain items
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param items - the items it contains
	 */
	public Chest(Level level, int x, int y, byte lootTableId) {
		super(level, x, y);
		
		// instance data
		lootId = lootTableId;
		
		// load from a loot table
		LootData.load(lootTableId, contents);

		setBounds(getX(), getY(), SIZE + 2, SIZE);
	}

	/**
	 * Opens the chest
	 */
	public boolean open() {
		if (isOpen)
			return false;
		else
			return isOpen = true;
	}

	/**
	 * @return the items in the chest
	 */
	public ArrayList<Item> getContents() {
		return contents;
	}

	/**
	 * Display the chest on the screen
	 */
	public void render(Screen screen) {

		if (!isOpen) {
			screen.render(getX(), getY(), 0, 20, SpriteSheet.tiles, false, color);
			screen.render(getX() + 8, getY(), 1, 20, SpriteSheet.tiles,false, color);
		} else {
			screen.render(getX(), getY(), 2, 20, SpriteSheet.tiles, false, color);
			screen.render(getX() + 8, getY(), 3, 20, SpriteSheet.tiles, false, color);
		}

	}

	/**
	 * Fake shadow
	 */
	@Override
	public Rectangle getShadow() {
		return shadow;
	}

	/**
	 * Chests don't tick
	 */
	@Override
	public void tick() {}

	@Override
    public byte getId(){
        return Entity.CHEST;
    }

	/**
	 * @return Chest data
	 */
	@Override
	public long getData() {
		return EntityData.type3(getX(), getY(), lootId);
	}

	/**
	 * @return  The Loot Table ID
	 */
	@Override
	public byte getType() {
		return lootId;
	}

	/**
	 * @param type - the loot table ID
	 */
	@Override
	public void setType(byte type) {
		this.lootId = type;
		
		// add the new content
		contents.clear();
		LootData.load(type, contents);
	}

}
