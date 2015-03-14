package ca.javajesus.items;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;

public class Item {

	public static Item apple = new Item("Apple", 0, 2, 5, Colors.get(-1, 111,
			Colors.fromHex("#FF0000"), Colors.fromHex("#0ca101")),
			"This is a red fruit!");
	public static Item banana = new Item("Banana", 1, 3, 5, Colors.get(-1, 111,
			Colors.fromHex("#fff600"), -1), "Monkey like.");
	public static Item orange = new Item("Orange", 2, 2, 5, Colors.get(-1, 111,
			Colors.fromHex("#ffae00"), Colors.fromHex("#0ca101")),
			"Orange you glad I said banana.");
	public static Item feather = new Item("Feather", 3, 4, 5, Colors.get(-1,
			111, Colors.fromHex("#79b2ff"), -1), "So light.");
	public static Item revolver = new Gun("Revolver", 4, 0, 0, Colors.get(-1,
			Colors.fromHex("#4d2607"), Colors.fromHex("#cfcfcf"),
			Colors.fromHex("#f7f7f7")), "Standard Firearm", 0, 0, 6, 10, 20, 50);
	public static Item laserRevolver = new Gun("Laser Revolver", 5, 1, 0,
			Colors.get(-1, 111, Colors.fromHex("#4d2607"),
					Colors.fromHex("#ffae00")), "Standard Firearm", 1, 4, 10,
			10, 20, 2);
	public static Item shotgun = new Gun("Shotgun", 6, 3, 0, Colors.get(-1,
			111, Colors.fromHex("#4d2607"), Colors.fromHex("#cfcfcf")),
			"Standard Firearm", 2, 12, 5, 10, 20, 2);
	public static Item assaultRifle = new Gun("Assault Rifle", 7, 2, 0,
			Colors.get(-1, 111, Colors.fromHex("#cfcfcf"), -1),
			"Standard Firearm", 3, 8, 60, 1, 6, 100);
	public static Item crossBow = new Gun("Crossbow", 8, 4, 0, Colors.get(-1,
			111, Colors.fromHex("#4d2607"), Colors.fromHex("#cfcfcf")),
			"Standard Firearm", 4, 16, 8, 10, 20, 2);
	public static Item shortSword = new Sword("Short Sword", 9, 0, 1, 0, 3,
			Colors.get(-1, Colors.fromHex("#f2f3f9"), -1,
					Colors.fromHex("#d6d7dc")), "This is a sword", 0, 30, 5);
	public static Item longSword = new Sword("Long Sword", 10, 0, 1, 0, 3,
			Colors.get(-1, Colors.fromHex("#f2f3f9"), -1,
					Colors.fromHex("#d6d7dc")), "This is a sword", 0, 30, 5);
	public static Item claymore = new Sword("Claymore", 11, 2, 1, 0, 3,
			Colors.get(-1, Colors.fromHex("#f2f3f9"),
					Colors.fromHex("#4d2607"), Colors.fromHex("#d6d7dc")),
			"This is a sword", 0, 30, 5);
	public static Item sabre = new Sword("Sabre", 12, 3, 1, 0, 3, Colors.get(
			-1, -1, Colors.fromHex("#ebcd00"), Colors.fromHex("#d6d7dc")),
			"This is a sword", 0, 30, 5);
	public static Item heavenlySword = new Sword("Heavenly Sword", 13, 4, 1, 0,
			3, Colors.get(-1, Colors.fromHex("#ebcd00"), -1,
					Colors.fromHex("#2568ff")), "This is a sword", 0, 30, 5);
	public static Item heavenlyShortSword = new Sword("Hevanly Short Sword",
			14, 4, 1, 0, 3, Colors.get(-1, Colors.fromHex("#ebcd00"), -1,
					Colors.fromHex("#2568ff")), "This is a sword", 0, 30, 5);
	public static Item bazooka = new Bazooka();

	public String name;
	public int id;
	protected int color;
	protected int xTile;
	protected int yTile;
	public String description;
	protected int amount = 1;

	public Item(String name, int id, int xTile, int yTile, int color,
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

}
