package javajesus.items;

import java.io.Serializable;

import javajesus.SoundHandler;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
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
	private static final Item[] items = new Item[256];
	
	// whether or not the item can be equipped
	private boolean equipable;

	// consumables
	public static final Item apple = new Item("Apple", 0, 2, 3, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"This red fruit will restores a moderate amount of stamina.", 10);
	public static final Item banana = new Item("Banana", 1, 3, 3, new int[] { 0xFF111111, 0xFFFFF600, 0xFF000000 },
			"Got a cramp? Eat up champ, the currency of the wild apes. Restores a lot of Stamina.", 10);
	public static final Item orange = new Item("Orange", 2, 5, 3, new int[] { 0xFF111111, 0xFFFFAE00, 0xFF0CA101 },
			"Be the Soccer Mom you always wanted to be, This orange fruit will restore a small amount of stamina.", 10);
	public static final Item feather = new Item("Feather", 3, 4, 3, new int[] { 0xFF111111, 0xFF79B2FF, 0xFF000000 },
			"Why did we waste time adding an interactible feather to the game?", 0);

	// guns
	public static final Item revolver = new Gun("Revolver", 4, 0, 0, new int[] { 0xFF4D2607, 0xFFCFCFCF, 0xFFF7F7F7 },
			"Standard Six Shooter, more powerful than it should be, known to cause tinnitus and Dirty Harritus."
			, 0, 6, 5, 20, 60, Ammo.REVOLVER, SoundHandler.revolver);
	public static final Item laserRevolver = new Gun("Laser Revolver", 5, 1, 0,
			new int[] { 0xFF111111, 0xFF4D2607, 0xFFFFAE00 }, 
			"The new revolutionary Laser Revolver. Simply kills. Battery life lasts for one to six discharges."
			, 2, 6, 10, 21, 90, Ammo.LASER,
			SoundHandler.laser);
	public static final Item assaultRifle = new Gun("M239 Assault Rifle", 7, 2, 0,
			new int[] { 0xFF111111, 0xFFCFCFCF, 0xFF000000 },
			"This M239 US Army Assault Rifle, can clear a room of people in 2.5 seconds, reload in 1 second."
			+ " Main uses include hunting deer and target practice. Available at your local Gunstore, God Bless America.",
			4, 36, 1, 5, 55, Ammo.RIFLE,
			SoundHandler.assaultRifle);
	public static final Item shotgun = new Gun("Shotgun", 6, 3, 0, new int[] { 0xFF111111, 0xFF4D2607, 0xFFCFCFCF },
			"The gun that allowed these United States to \"liberate\" the west. Put two bullets in, point in your "
			+ "target's general direction and watch them dissapear.",
			6, 2, 5, 15, 200, Ammo.SHELL, SoundHandler.shotgun);
	public static final Item crossBow = new Gun("Crossbow", 8, 4, 0, new int[] { 0xFF111111, 0xFF4D2607, 0xFFCFCFCF },
			"Useful in defending Castles from the English in 1400, now useful for driving a stake "
			+ "through the hearts of the armies of Hell. One arrow at a time, slow to reload but packs"
			+ "a hell of a punch.",
			8, 1, 3, 30, 200, Ammo.ARROW, SoundHandler.revolver);
	public static final Item bazooka = new Bazooka();

	// swords
	public static final Item shortSword = new Sword("Cutlass", 9, 0, 1, 0, 0,
			new int[] { 0xFFF2F3F9, 0xFF000000, 0xFFD6D7DC }, 
			"More of a large knife than a sword, at the very least it'll give your opponent"
			+ "a good sting.",
			15, 30, new int[] { 7, 12, 16, 21 }, 0, Sword.SHORT, 0);
	public static final Item longSword = new Sword("Long Sword", 10, 1, 1, 0, 4,
			new int[] { 0xFFF2F3F9, 0xFF000000, 0xFFD6D7DC }, 
			"Now thats a sword, a fine steel blade mass produced for the Knights of The Bay, nothing"
			+ "to write home about but will certainly give your oppoent second thoughts.",
			40, 60, new int[] { 7, 13, 18, 23 }, 1,
			Sword.MEDIUM, 10);
	public static final Item claymore = new Sword("Claymore", 11, 2, 1, 0, 10,
			new int[] { 0xFFF2F3F9, 0xFF000000, 0xFFD6D7DC },
			"Sword of the Knights Templar, the sheer weight of the blade will make your swings"
			+ " slow and easy to dodge but if you manage to hit anything it's going down for real.",
			60, 85, new int[] { 8, 16, 24, 32 }, 2,
			Sword.LONG, 20);
	public static final Item sabre = new Sword("Sabre", 12, 3, 1, 0, 14,
			new int[] { 0xFF000000, 0xFFEBCD00, 0xFFD6D7DC },
			"The Dueling Blade, a more elegant weapon from a more civilized time,"
			+ " this is a sword made for defending a fair maiden's honor. A quick stabbing blade"
			+ "your opponent will have to be quick on his feet to defeat you.",
			20, 50, new int[] { 7, 13, 18, 23 }, 1,
			Sword.MEDIUM, 10);
	public static final Item heavenlySword = new Sword("Heavenly Sword", 13, 4, 1, 0, 18,
			new int[] { 0xFFEBCD00, 0xFF000000, 0xFF2568FF },
			"Sword handed crafted by God himself, given to only the holiest of Saints, nothing"
			+ "because nothing says \"brotherly love\" like righteous slaughter.",
			35, 70, new int[] { 7, 13, 18, 23 }, 1,
			Sword.MEDIUM, 10);
	public static final Item kingSword = new Sword("The Sword of Kings", 18, 5, 1, 0, 22,
			new int[] { 0xFFEBCD00, 0xFF000000, 0xFF2568FF },
			"This is the best blade in the game and \"The Sword of Kings\" is the best"
			+ "we could come up with?",
			25, 80, new int[] { 7, 13, 18, 23 }, 1,
			Sword.MEDIUM, 20);

	 // armor
	/**
	 * CURRENTLY NOT IMPLEMENTED
	 * 
	public static final Item vest = new Armor("Simple Vest", 15, 1, 2, new int[] { 0xFF000000, 0xFFEBCD00, 0xFFD6D7DC },
			"", ArmorSet.VEST);
	public static final Item knight = new Armor("Knight Gear", 16, 2, 2,
			new int[] { 0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "", ArmorSet.KNIGHT);
	public static final Item horned = new Armor("Horned Armor", 17, 3, 2,
			new int[] { 0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "", ArmorSet.HORNED);
	public static final Item owl = new Armor("Fancy Suit", 19, 4, 2,
			new int[] { -1, 0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "", ArmorSet.ISTRAHIIM);
			*/

	public static final Item blackHoleGun = new Gun("Secret", 20, 0, 0,
			new int[] { 0xFF4D2607, 0xFFCFCFCF, 0xFFF7F7F7 }, "??????", 0, 6, 10, 20, 50, Ammo.BLACKHOLE,
			SoundHandler.revolver);
	public static final Item flameThrower = new Gun("Flamethrower", 21, 0, 0,
			new int[] { 0xFF4D2607, 0xFFCFCFCF, 0xFFF7F7F7 }, "Please apply cold water to burn", 0, 10000, 0.2f, 0, 3,
			Ammo.FLAMETHROWER, SoundHandler.revolver);
			
	

	// ammo
	public static final Item revolverAmmo = new Item("Revolver Ammo", 23, 0, 4, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"Revolver Ammo, .375 Magnum Round, the kind movies love to talk about."
			+ " Can tear a hole through anything in a 10 yard radius.", 0);
	public static final Item laserAmmo = new Item("BatteryPack", 25, 1, 4, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"Laser Ammo, Contains enough charge to perform one to six discharges.", 0);
	public static final Item assaultRifleAmmo = new Item("Assault Rifle Magazine", 22, 2, 4,
			new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"Assault Rifle Ammo, .556 Rounds capable of being fired in rapid succession, can "
			+ "very easily kill a room full of people.", 0);
	public static final Item shotgunAmmo = new Item("Shotgun Ammo", 24, 3, 4, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"Shotgun Ammo, buckshot, can take out any living thing within a 5 yard radius. No longer"
			+ "okay to use against the Native Population.", 0);
	public static final Item arrowAmmo = new Item("Arrow Quiver", 26, 4, 4, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"A quiver full of Arrows, useful for having arrows to shoot at people.", 0);
	public static final Item rocketAmmo = new Item("Ammo", 29, 5, 4, new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 },
			"This is a rocket, you put it in a Rocket Launcher, then you shoot it. Once the rocket"
			+ "hits what you were aiming at it explodes. That's how it works.", 0);

	// restores health
	public static final Item strongHealthPack = new Item("Health", 27, 1, 3,
			new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "This Health Pack will restore a large amount of health.", 50);
	
	public static final Item quickHealthPack = new Item("Quick Health", 30, 0, 3,
			new int[] { 0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "This Health Pack will restore a small amount of health.", 20);
	
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
	 * Displays the item GUI in 24 bit
	 * 
	 * @param screen - the screen to display it on
	 */
	public void render(final Screen screen) {
		screen.render24bit(xTile, yTile, SpriteSheet.inventoryItems);
	}

	/**
	 * Displays the item HUD in 24 bit
	 * 
	 * @param screen - the screen to display it on
	 */
	public void renderHUD(final Screen screen) {
		screen.render24bit(xTile, yTile, SpriteSheet.hudWeapons);
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
		
		// heal if the item has health
		if (health > 0) {
			player.heal(health);
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
	 * Sets the amount of this item
	 * @param num - the amount of this item
	 */
	public void setQuanity(int num) {
		amount = num;
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
	 * @param text - pattern to search for
	 * @return whether or not the text is in the name
	 */
	public boolean contains(String text) {
		return name.contains(text);
	}
	
	/**
	 * @return whether or not the item can be used
	 */
	public boolean isUsable() {
		
		return equipable || health > 0;
		
	}
	
	/**
	 * @return whether or not the item contains health
	 */
	public boolean containsHealth() {
		
		return health > 0;
		
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
	
	/**
	 * Gets the Item from the ID
	 * 
	 * @param id - the ID of the item
	 * @return - the item associated with that ID
	 */
	public static final Item getItem(int id) {
		return items[id];
	}
	
	/**
	 * @return the number of items
	 */
	public static final int getNumItems() {
		return items.length;
	}
	
}
