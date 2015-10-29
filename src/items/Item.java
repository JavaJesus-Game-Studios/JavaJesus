package items;

import game.SoundHandler;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import items.Armor.ArmorSet;
import items.Gun.Ammo;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 6019227186916064573L;
	
	public static Item apple = new Item("Apple", 0, 2, 5, new int[] {
			0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "This is a red fruit!");
	public static Item banana = new Item("Banana", 1, 3, 5, new int[] {
			0xFF111111, 0xFFFFF600, 0xFF000000 }, "Monkey like.");
	public static Item orange = new Item("Orange", 2, 2, 5, new int[] {
			0xFF111111, 0xFFFFAE00, 0xFF0CA101 },
			"Orange you glad I said banana.");
	public static Item feather = new Item("Feather", 3, 4, 5, new int[] {
			0xFF111111, 0xFF79B2FF, 0xFF000000 }, "So light.");

	public static Item revolver = new Gun("Revolver", 4, 0, 0, new int[] {
			0xFF4D2607, 0xFFCFCFCF, 0xFFF7F7F7 }, "Standard Firearm", 0, 0, 6,
			10, 20, 50, Ammo.BULLET, SoundHandler.sound.revolver);
	public static Item laserRevolver = new Gun("Laser Revolver", 5, 1, 0,
			new int[] { 0xFF111111, 0xFF4D2607, 0xFFFFAE00 },
			"Standard Firearm", 1, 2, 5, 10, 20, 75, Ammo.LASER,
			SoundHandler.sound.laser);
	public static Item shotgun = new Gun("Shotgun", 6, 3, 0, new int[] {
			0xFF111111, 0xFF4D2607, 0xFFCFCFCF }, "Standard Firearm", 2, 6, 2,
			10, 20, 85, Ammo.SHELL, SoundHandler.sound.shotgun);
	public static Item assaultRifle = new Gun("Assault Rifle", 7, 8, 0,
			new int[] { 0xFF111111, 0xFFCFCFCF, 0xFF000000 },
			"Standard Firearm", 3, 4, 30, 1, 10, 10, Ammo.BULLET,
			SoundHandler.sound.assaultRifle);
	public static Item crossBow = new Gun("Crossbow", 8, 4, 0, new int[] {
			0xFF111111, 0xFF4D2607, 0xFFCFCFCF }, "Standard Firearm", 4, 8, 1,
			10, 20, 75, Ammo.ARROW, SoundHandler.sound.revolver);
	public static Item bazooka = new Bazooka();

	public static Item shortSword = new Sword("Short Sword", 9, 0, 1, 0, 0,
			new int[] { 0xFFF2F3F9, 0xFF000000, 0xFFD6D7DC },
			"This is a sword", 0, 25, 30, new int[] { 7, 12, 16, 21 });
	public static Item longSword = new Sword("Long Sword", 10, 1, 1, 0, 4,
			new int[] { 0xFFF2F3F9, 0xFF000000, 0xFFD6D7DC },
			"This is a sword", 0, 40, 50, new int[] { 7, 13, 18, 23 }, 1);
	public static Item claymore = new Sword("Claymore", 11, 2, 1, 0, 10,
			new int[] { 0xFFF2F3F9, 0xFF000000, 0xFFD6D7DC },
			"This is a sword", 0, 60, 75, new int[] { 8, 16, 24, 32 }, 2);
	public static Item sabre = new Sword("Sabre", 12, 3, 1, 0, 19, new int[] {
			0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "This is a sword", 0, 20, 45,
			new int[] { 7, 13, 18, 23 }, 1);
	public static Item heavenlySword = new Sword("Heavenly Sword", 13, 4, 1, 0,
			15, new int[] { 0xFFEBCD00, 0xFF000000, 0xFF2568FF },
			"This is a sword", 0, 30, 5, new int[] { 7, 13, 18, 23 }, 1);
	public static Item heavenlyShortSword = new Sword("Heavenly Short Sword",
			14, 0, 1, 0, 22, new int[] { 0xFFEBCD00, 0xFF000000, 0xFF2568FF },
			"This is a sword", 0, 30, 5, new int[] { 7, 12, 16, 21 });
	public static Item kingSword = new Sword("King Short Sword", 22, 3, 1, 0,
			25, new int[] { 0xFFEBCD00, 0xFF000000, 0xFF2568FF },
			"This is a sword", 0, 30, 5, new int[] { 7, 13, 18, 23 }, 1);

	public static Item vest = new Armor("Simple Vest", 15, 0, 9, new int[] {
			0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "", ArmorSet.VEST);
	public static Item knight = new Armor("Knight Gear", 16, 1, 9, new int[] {
			0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "", ArmorSet.KNIGHT);
	public static Item horned = new Armor("Horned Armor", 17, 2, 9, new int[] {
			0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "", ArmorSet.HORNED);
	public static Item owl = new Armor("Fancy Suit", 19, 4, 9, new int[] { -1,
			0xFF000000, 0xFFEBCD00, 0xFFD6D7DC }, "", ArmorSet.OWL);

	public static Item blackHoleGun = new Gun("Secret", 20, 0, 0, new int[] {
			0xFF4D2607, 0xFFCFCFCF, 0xFFF7F7F7 }, "??????", 0, 0, 6, 10, 20,
			50, Ammo.BLACKHOLE, SoundHandler.sound.revolver);
	public static Item flameThrower = new Gun("Flamethrower", 21, 0, 0,
			new int[] { 0xFF4D2607, 0xFFCFCFCF, 0xFFF7F7F7 },
			"Please apply cold water to burn", 0, 0, 6, 10, 20, 50,
			Ammo.FLAMETHROWER, SoundHandler.sound.revolver);

	public static Item assaultRifleAmmo = new Item("Ammo", 22, 0, 6, new int[] {
			0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "Assault Rifle AMmo");
	public static Item revolverAmmo = new Item("Ammo", 23, 2, 6, new int[] {
			0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "Assault Rifle AMmo");
	public static Item shotgunAmmo = new Item("Ammo", 24, 3, 6, new int[] {
			0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "Assault Rifle AMmo");
	public static Item laserAmmo = new Item("Ammo", 25, 4, 6, new int[] {
			0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "Assault Rifle AMmo");
	public static Item arrowAmmo = new Item("Ammo", 26, 1, 7, new int[] {
			0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "Assault Rifle AMmo");
	
	public static Item strongHealthPack = new Item("Health", 27, 1, 5, new int[] {
			0xFF111111, 0xFFFF0000, 0xFF0CA101 }, "Health Pack");

	public String name;
	public int id;
	protected int[] color;
	protected int xTile;
	protected int yTile;
	public String description;
	public int amount = 1;

	public Item(String name, int id, int xTile, int yTile, int[] color,
			String description) {
		this.name = name;
		this.id = id;
		this.color = color;
		this.xTile = xTile;
		this.yTile = yTile;
		this.description = description;
	}

	public String toString() {
		return name;
	}

	public void render(Screen screen, int xOffset, int yOffset) {
		screen.render(xOffset, yOffset, xTile + yTile * SpriteSheet.items.boxes, color, 0, 6,
				SpriteSheet.items);
	}

	public static Item returnItem(String item) {
		switch (item) {
		case "Apple":
			return Item.apple;
		case "Banana":
			return Item.banana;
		case "Orange":
			return Item.orange;
		case "Feather":
			return Item.feather;
		case "Revolver":
			return Item.revolver;
		case "Laser Revolver":
			return Item.laserRevolver;
		case "Shotgun":
			return Item.shotgun;
		case "Assault Rifle":
			return Item.assaultRifle;
		case "Crossbow":
			return Item.crossBow;
		case "Short Sword":
			return Item.shortSword;
		case "Long Sword":
			return Item.longSword;
		case "Claymore":
			return Item.claymore;
		case "Sabre":
			return Item.sabre;
		case "Heavenly Sword":
			return Item.heavenlySword;
		case "Heavenly Short Sword":
			return Item.heavenlyShortSword;
		case "Bazooka":
			return Item.bazooka;
		case "Secret":
			return Item.blackHoleGun;
		default:
			return null;
		}
	}

	public int getID() {
		return this.id;
	}

}
