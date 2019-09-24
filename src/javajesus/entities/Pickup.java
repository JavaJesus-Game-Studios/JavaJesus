package javajesus.entities;

import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.items.Item;
import javajesus.level.Level;

/*
 * A Pickup is an entity that contains an Item to give to the player
 */
public class Pickup extends Entity {

	// the item it stores
	private Item item;
	
	// location on spritesheet
	private int xTile, yTile;
	
	// the color set of the pickup
	private final int[] color;
	
	// the amount of item in this pickup
	// used for ammo
	private int amount = 1;
	
	// whether or not to use it on pickup
	private boolean useOnPickup;
	
	// spritesheet of the pickup
	private static final SpriteSheet sheet = SpriteSheet.pickups;
	
	/**
	 * Pickup ctor()
	 * Creates a pickup based on the item it contains
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param item - the Item it holds
	 */
	public Pickup(Level level, int x, int y, Item item) {
		super(level, x, y);
		
		// instance data
		this.item = item;
		xTile = item.getXTile();
		yTile = item.getYTile();
		color = item.getColor();
		
		// set collision bounds
		setBounds(getX(), getY(), 8, 8);
	}
	
	/**
	 * Pickup ctor()
	 * Creates a pickup based on the item it contains
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param item - the Item it holds
	 * @param quantity - the amount of item in this pickup
	 */
	public Pickup(Level level, int x, int y, Item item, int quantity) {
		this(level, x, y, item);
		
		// instance data
		amount = quantity;
	}
	
	/**
	 * Pickup ctor()
	 * Creates a pickup that is used instantaneously
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord
	 * @param y - the y coord
	 * @param item - the Item it holds
	 * @param use - whether or not to use it instantaneously
	 */
	public Pickup(Level level, int x, int y, Item item, boolean use) {
		this(level, x, y, item);
		
		// instance data
		useOnPickup = use;
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

	/**
	 * A Pickup has no logic
	 */
	public void tick() {
		
	}

	/**
	 * Renders the pickup on the screen
	 */
	public void render(Screen screen) {
		screen.render(getX(), getY(), xTile, yTile, sheet, false, color);
	}

	/**
	 * @return the amount of item in this pickup
	 */
	public int getQuantity() {
		return amount;
	}
	
	/**
	 * @return whether or not to use on pickup
	 */
	public boolean isInstantaneous() {
		return useOnPickup;
	}

	/**
	 * Pickups won't be saved 
	 */
	@Override
	public byte getId() {
		return -1;
	}

	/**
	 * Won't be saved
	 */
	@Override
	public long getData() {
		return 0;
	}

	@Override
	public void onCollisionWithEntity(Entity e) {
		if (e instanceof Player) {
			
			Player player = (Player)e;
			
			// should we use it on collision?
			if (this.isInstantaneous()) {
				
				// use the item
				this.getItem().use(player);
				
				// now remove it
				this.remove();
				
			} else {
				
				// add the pickup item to the inventory
				player.addItem(this.getItem());
			}
		}
		
	}

	@Override
	public void onRemovedCollisionWithEntity(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
