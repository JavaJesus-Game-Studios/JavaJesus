package javajesus.entities.particles.pickups;

import javajesus.entities.particles.Particle;
import javajesus.graphics.SpriteSheet;
import javajesus.items.Item;
import javajesus.level.Level;

/*
 * A Pickup is a particle that contains an Item to give to the player
 */
public class Pickup extends Particle {

	private static final long serialVersionUID = 8454550791664174098L;

	// the item it stores
	private Item item;
	
	// number of items it stores
	private int quantity;

	/**
	 * Pickup ctor()
	 * Creates a generic pickup
	 * 
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 * @param item the Item it holds
	 */
	public Pickup(Level level, int x, int y, Item item) {
		super(level, x, y, 9, new int[] { 0xFFFFFFFF, 0xFF990000, 0xFFFF0000 });
		this.item = item;
		setBounds(getX(), getY(), 8, 8);
		this.quantity = 1;
	}

	/**
	 * Pickup ctor()
	 * Creates a customized pickup
	 * 
	 * @param level the level it is on
	 * @param x the x coord
	 * @param y the y coord
	 * @param item the item it holds
	 * @param color the colorset
	 * @param xTile the horizontal space on the tile sheet
	 * @param yTile
	 * @param amount
	 */
	public Pickup(Level level, int x, int y, Item item, int[] color, int xTile, int yTile, int amount) {
		super(level, x, y, 9, color);
		this.item = item;
		setBounds(getX(), getY(), 8, 8);
		setSpriteSheet(SpriteSheet.pickups);
		setTileNumber(xTile + yTile * getSpriteSheet().getNumBoxes());
		this.quantity = amount;
	}
	
	/**
	 * @return the quantity of whatever is in this pickup
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @return the item in the pickup
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * Removes this item from the game
	 */
	public void remove() {
		getLevel().remove(this);
	}

}
