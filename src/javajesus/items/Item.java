package javajesus.items;

import java.io.Serializable;

import javajesus.SoundHandler;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.items.Armor.ArmorSet;
import javajesus.items.Gun.Ammo;

/*
 * An Item can be collected by a player and used in various ways
 */
public class Item implements Serializable {

	// serialization
	private static final long serialVersionUID = 6019227186916064573L;

	// the name of the Item
	private final String name;

	// the id of the item
	private byte id;

	// the colorset of the item
	private final int[] color;

	// the horizontal/vertical position on the spritesheet
	private int xTile, yTile;

	// the description of the item
	private final String description;

	// the amount of this item
	private int amount = 1;
	
	// the amount of health the item heals on use
	private int health;

	// A set of all Items types in the game
	public static final Item[] items = new Item[256];
	
	// whether or not the item can be equipped
	private boolean equipable;

	// consumables
	public static final Item apple = new Item("Apple", 0, 2, 3, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"This red fruit will restore a little health.", 10);
	public static final Item banana = new Item("Banana", 1, 3, 3, new int[] { 0xFF111111, 0xFFFFF600, 0xFF000000 },
			"The currency of the wild apes. Restores a little health.", 10);
	public static final Item orange = new Item("Orange", 2, 2, 3, new int[] { 0xFF111111, 0xFFFFAE00, 0xFF0CA101 },
			"The orange fruit will restore a little health.", 10);
	public static final Item feather = new Item("Feather", 3, 4, 3, new int[] { 0xFF111111, 0xFF79B2FF, 0xFF000000 },
			"So light. Does Nothing.", 0);

	// guns
	public static final Item revolver = new Gun("Revolver", 4, 0, 0, new int[] { 0xFF4D2607, 0xFFCFCFCF, 0xFFF7F7F7 },
			"Standard Firearm", 0, 6, 10, 20, 50, Ammo.BULLET, SoundHandler.revolver);
	public static final Item laserRevolver = new Gun("Laser Revolver", 5, 1, 0,
			new int[] { 0xFF111111, 0xFF4D2607, 0xFFFFAE00 }, "Standard Firearm", 2, 5, 10, 20, 75, Ammo.LASER,
			SoundHandler.laser);
	public static final Item shotgun = new Gun("Shotgun", 6, 3, 0, new int[] { 0xFF111111, 0xFF4D2607, 0xFFCFCFCF },
			"Standard Firearm", 6, 2, 10, 20, 85, Ammo.SHELL, SoundHandler.shotgun);
	public static final Item assaultRifle = new Gun("Assault Rifle", 7, 2, 0,
			new int[] { 0xFF111111, 0xFFCFCFCF, 0xFF000000 }, "Standard Firearm", 4, 30, 1, 10, 10, Ammo.BULLET,
			SoundHandler.assaultRifle);
	public static final Item crossBow = new Gun("Crossbow", 8, 4, 0, new int[] { 0xFF111111, 0xFF4D2607, 0xFFCFCFCF },
			"Standard Firearm", 8, 1, 10, 20, 75, Ammo.ARROW, SoundHandler.revolver);
	public static final Item bazooka = new Bazooka();

	// swords
	public static final Item shortSword = new Sword("Short Sword", 9, 0, 1, 0, 0,
			new int[] { 0xFFF2F3F9, 0xFF000000, 0xFFD6D7DC }, "This is a sword", 25, 30, new int[] { 7, 12, 16, 21 });
	public static final Item longSword = new Sword("Long Sword", 10, 1, 1, 0, 4,
			new int[] { 0xFFF2F3F9, 0xFF000000, 0xFFD6D7DC }, "This is a sword", 40, 40, new int[] { 7, 13, 18, 23 }, 1,
			Sword.MEDIUM);
	public static final Item claymore = new Sword("Claymore", 11, 2, 1, 0, 10,
			new int[] { 0xFFF2F3F9, 0xFF000000, 0xFFD6D7DC }, "This is a sword", 60, 75, new int[] { 8, 16, 24, 32 }, 2,
			Sword.LONG);
	public static final Item sabre = new Sword("Sabre", 12, 3, 1, 0, 19,
			new int[] { 0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "This is a sword", 20, 45, new int[] { 7, 13, 18, 23 }, 1,
			Sword.MEDIUM);
	public static final Item heavenlySword = new Sword("Heavenly Sword", 13, 4, 1, 0, 15,
			new int[] { 0xFFEBCD00, 0xFF000000, 0xFF2568FF }, "This is a sword", 30, 5, new int[] { 7, 13, 18, 23 }, 1,
			Sword.MEDIUM);
	public static final Item heavenlyShortSword = new Sword("Heavenly Short Sword", 14, 0, 1, 0, 22,
			new int[] { 0xFFEBCD00, 0xFF000000, 0xFF2568FF }, "This is a sword", 30, 5, new int[] { 7, 12, 16, 21 });
	public static final Item kingSword = new Sword("King Short Sword", 18, 3, 1, 0, 25,
			new int[] { 0xFFEBCD00, 0xFF000000, 0xFF2568FF }, "This is a sword", 30, 5, new int[] { 7, 13, 18, 23 }, 1,
			Sword.MEDIUM);

	// armor
	public static final Item vest = new Armor("Simple Vest", 15, 0, 7, new int[] { 0xFF000000, 0xFFEBCD00, 0xFFD6D7DC },
			"", ArmorSet.VEST);
	public static final Item knight = new Armor("Knight Gear", 16, 1, 7,
			new int[] { 0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "", ArmorSet.KNIGHT);
	public static final Item horned = new Armor("Horned Armor", 17, 2, 7,
			new int[] { 0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "", ArmorSet.HORNED);
	public static final Item owl = new Armor("Fancy Suit", 19, 3, 7,
			new int[] { -1, 0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "", ArmorSet.OWL);

	public static final Item blackHoleGun = new Gun("Secret", 20, 0, 0,
			new int[] { 0xFF4D2607, 0xFFCFCFCF, 0xFFF7F7F7 }, "??????", 0, 6, 10, 20, 50, Ammo.BLACKHOLE,
			SoundHandler.revolver);
	public static final Item flameThrower = new Gun("Flamethrower", 21, 0, 0,
			new int[] { 0xFF4D2607, 0xFFCFCFCF, 0xFFF7F7F7 }, "Please apply cold water to burn", 0, 200, 1, 0, 3,
			Ammo.FLAMETHROWER, SoundHandler.revolver);

	// ammo
	public static final Item assaultRifleAmmo = new Item("Ammo", 22, 0, 4,
			new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "Assault Rifle Ammo", 0);
	public static final Item revolverAmmo = new Item("Ammo", 23, 2, 4, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"Revolver Ammo", 0);
	public static final Item shotgunAmmo = new Item("Ammo", 24, 3, 4, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"Shotgun Ammo", 0);
	public static final Item laserAmmo = new Item("Ammo", 25, 4, 4, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"Laser Ammo", 0);
	public static final Item arrowAmmo = new Item("Ammo", 26, 1, 5, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"Arrow Ammo", 0);

	// restores health
	public static final Item strongHealthPack = new Item("Health", 27, 1, 3,
			new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "This Health Pack will restore a large amount of health.", 50);
	
	public static final Item quickHealthPack = new Item("Quick Health", 30, 0, 3,
			new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "This Health Pack will restore a small amount of health.", 20);
	
	// inventory filler
	public static final Item blank = new Item("Empty", 29, 0, 2, null, "None", 0);

	/**
	 * Item ctor()
	 * Creates an Item
	 * 
	 * @param name - the name of the item
	 * @param id - the unique id of the item
	 * @param xTile - the horizontal position on the spritesheet
	 * @param yTile - the vertical position on the spritesheet
	 * @param color - the colorset
	 * @param description - the description of this item
	 */
	public Item(final String name, int id, int xTile, int yTile, final int[] color,
			final String description, boolean equipable) {

		// initialize instance data
		this.name = name;
		this.id = (byte) id;
		this.color = color;
		this.xTile = xTile;
		this.yTile = yTile;
		this.description = description;
		this.equipable = equipable;

		// make sure each ID is unique
		if (items[id] != null)
			throw new RuntimeException("Duplicate item id on " + id);

		items[id] = this;
	}
	
	/**
	 * Item ctor()
	 * Creates an Item
	 * 
	 * @param name - the name of the item
	 * @param id - the unique id of the item
	 * @param xTile - the horizontal position on the spritesheet
	 * @param yTile - the vertical position on the spritesheet
	 * @param color - the colorset
	 * @param description - the description of this item
	 * @param health - the amount of health this item heals on use
	 */
	public Item(final String name, int id, int xTile, int yTile, final int[] color,
			final String description, int health) {
		this(name, id, xTile, yTile, color, description, false);
		
		// instance data
		this.health = health;
		
	}
	
	/**
	 * @return the item description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return general information about this item
	 */
	public String toString() {
		return "Name: " + name + "\nDescription: " + description + "\nQuantity: " + amount;
	}

	/**
	 * Displays the item GUI in 16 bit
	 * 
	 * @param screen - the screen to display it on
	 */
	public void render(final Screen screen) {
		
		// size of each box
		int modifier = 8;
		
		// 16 bit so multiply by 2
		int xTile = this.xTile * 2;
		int yTile = this.yTile * 2;
		
		// upper left
		screen.render(0, 0, xTile + yTile * SpriteSheet.gui_items.getNumBoxes(), color, SpriteSheet.gui_items);
		
		// upper right
		screen.render(modifier, 0, (xTile + 1) + yTile * SpriteSheet.gui_items.getNumBoxes(), color,
		        SpriteSheet.gui_items);

		// lower left
		screen.render(0, modifier, xTile + (yTile + 1) * SpriteSheet.gui_items.getNumBoxes(), color,
		        SpriteSheet.gui_items);

		// lower right
		screen.render(modifier, modifier, (xTile + 1) + (yTile + 1) * SpriteSheet.gui_items.getNumBoxes(), color,
		        SpriteSheet.gui_items);
	}
	
	/**
	 * Displays the item HUD in 24 bit
	 * 
	 * @param screen - the screen to display it on
	 */
	public void renderHUD(final Screen screen) {
		
		// size of each box
		int modifier = 8;
		
		// 24 bit so multiply by 3
		int xTile = this.xTile * 3;
		int yTile = this.yTile * 3;
		
		// top to bottom
		for (int i = 0; i < 3; i++) {
			
			// left to right
			for (int j = 0; j < 3; j++) {
				
				// render the box
				screen.render(modifier * j, modifier * i,
				        (xTile + j) + (yTile + i) * SpriteSheet.hud_weapons.getNumBoxes(), null,
				        SpriteSheet.hud_weapons);
				
			}
		}
		
	}

	/**
	 * @return the ID of this item
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @return the name of this item
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Uses this item
	 * Plays a click sound
	 * 
	 * @param player - the player using the item
	 */
	public void use(Player player) {
		
		// play a sound when used
		SoundHandler.play(SoundHandler.click);
		
		// heal if the item has health
		if (health > 0) {
			player.changeHealth(health);
		}
		
		// equip if equipable
		if (equipable) {
			player.equip(this);
			
			// keeps the item in the inventory
			amount++;
		}
	}
	
	/**
	 * Removes one item in this stack
	 */
	public void remove() {
		amount--;
	}

	/**
	 * Increments the item stack count
	 */
	public void add() {
		amount++;
	}

	/**
	 * Gets how much is left of this item
	 * 
	 * @return the quantity
	 */
	public int getQuantity() {
		return amount;
	}

	/**
	 * @return the colorset for this item
	 */
	public int[] getColor() {
		return color;
	}
	
	/**
	 * Checks if two items are the same
	 */
	@Override
	public boolean equals(Object object) {
		
		// not equal if not the same type
		if (!(object instanceof Item)) {
			return false;
		}
		
		// equal if ids are the same
		return this.getId() == ((Item) object).getId();
		
	}
	
	/**
	 * @return whether or not the item can be used
	 */
	public boolean isUsable() {
		
		return equipable || health > 0;
		
	}
	
	/**
	 * @return x tile on spritesheet
	 */
	public int getXTile() {
		return xTile;
	}
	
	/**
	 * @return y tile on spritesheet
	 */
	public int getYTile() {
		return yTile;
	}
	
}
