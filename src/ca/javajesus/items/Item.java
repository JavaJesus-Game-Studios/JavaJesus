package ca.javajesus.items;

import ca.javajesus.game.graphics.Colors;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.items.Armor.ArmorSet;
import ca.javajesus.items.Gun.Ammo;

public class Item {

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
			10, 20, 50, Ammo.BULLET);
	public static Item laserRevolver = new Gun("Laser Revolver", 5, 1, 0,
			new int[] { 0xFF111111, 0xFF4D2607, 0xFFFFAE00 },
			"Standard Firearm", 1, 0, 5, 10, 20, 75, Ammo.LASER);
	public static Item shotgun = new Gun("Shotgun", 6, 3, 0, new int[] {
			0xFF111111, 0xFF4D2607, 0xFFCFCFCF }, "Standard Firearm", 2, 4, 2,
			10, 20, 85, Ammo.BULLET);
	public static Item assaultRifle = new Gun("Assault Rifle", 7, 2, 0,
			new int[] { -1, 111, Colors.fromHex("#cfcfcf"), -1 },
			"Standard Firearm", 3, 8, 30, 1, 6, 10, Ammo.BULLET);
	public static Item crossBow = new Gun("Crossbow", 8, 4, 0, new int[] { -1,
			111, Colors.fromHex("#4d2607"), Colors.fromHex("#cfcfcf") },
			"Standard Firearm", 4, 16, 1, 10, 20, 75, Ammo.ARROW);
	public static Item bazooka = new Bazooka();

	public static Item shortSword = new Sword("Short Sword", 9, 0, 1, 0, 3,
			new int[] { -1, Colors.fromHex("#f2f3f9"), -1,
					Colors.fromHex("#d6d7dc") }, "This is a sword", 0, 25, 30);
	public static Item longSword = new Sword("Long Sword", 10, 0, 1, 0, 3,
			new int[] { -1, Colors.fromHex("#f2f3f9"), -1,
					Colors.fromHex("#d6d7dc") }, "This is a sword", 0, 40, 50);
	public static Item claymore = new Sword("Claymore", 11, 2, 1, 0, 3,
			new int[] { -1, Colors.fromHex("#f2f3f9"),
					Colors.fromHex("#4d2607"), Colors.fromHex("#d6d7dc") },
			"This is a sword", 0, 60, 75);
	public static Item sabre = new Sword("Sabre", 12, 3, 1, 0, 3, new int[] {
			-1, -1, Colors.fromHex("#ebcd00"), Colors.fromHex("#d6d7dc") },
			"This is a sword", 0, 20, 45);
	public static Item heavenlySword = new Sword("Heavenly Sword", 13, 4, 1, 0,
			3, new int[] { -1, Colors.fromHex("#ebcd00"), -1,
					Colors.fromHex("#2568ff") }, "This is a sword", 0, 30, 5);
	public static Item heavenlyShortSword = new Sword("Heavenly Short Sword",
			14, 4, 1, 0, 3, new int[] { -1, Colors.fromHex("#ebcd00"), -1,
					Colors.fromHex("#2568ff") }, "This is a sword", 0, 30, 5);
	public static Item vest = new Armor("Simple Vest", 15, 0, 0, new int[] {
			-1, -1, Colors.fromHex("#ebcd00"), Colors.fromHex("#d6d7dc") }, "",
			ArmorSet.VEST);
	public static Item knight = new Armor("Knight Gear", 16, 0, 0, new int[] {
			-1, -1, Colors.fromHex("#ebcd00"), Colors.fromHex("#d6d7dc") }, "",
			ArmorSet.KNIGHT);
	public static Item horned = new Armor("Horned Armor", 17, 0, 0, new int[] {
			-1, -1, Colors.fromHex("#ebcd00"), Colors.fromHex("#d6d7dc") }, "",
			ArmorSet.HORNED);
	public static Item owl = new Armor("Fancy Suit", 19, 0, 0, new int[] { -1,
			-1, Colors.fromHex("#ebcd00"), Colors.fromHex("#d6d7dc") }, "",
			ArmorSet.OWL);
	public static Item blackHoleGun = new Gun("Secret", 20, 0, 0, new int[] {
			-1, Colors.fromHex("#4d2607"), Colors.fromHex("#cfcfcf"),
			Colors.fromHex("#f7f7f7") }, "??????", 0, 0, 6, 10, 20, 50,
			Ammo.BLACKHOLE);
	public static Item flameThrower = new Gun("Flamethrower", 21, 0, 0,
			new int[] { -1, Colors.fromHex("#4d2607"),
					Colors.fromHex("#cfcfcf"), Colors.fromHex("#f7f7f7") },
			"Please apply cold water to burn", 0, 0, 6, 10, 20, 50,
			Ammo.FLAMETHROWER);

	public String name;
	public int id;
	protected int[] color;
	protected int xTile;
	protected int yTile;
	public String description;
	protected int amount = 1;

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
		screen.render(xOffset, yOffset, xTile + yTile * 32, color, 0, 6,
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
		default:
			return null;
		}
	}

}
