package ca.javajesus.items;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;

public class Item {

	public static Item apple = new Item("Apple", 0, 0, 0, Colors.get(-1, 200, 200, Colors.fromHex("#FF0000")), "This is a red fruit!");
	public static Item banana = new Item("Banana", 1, 1, 0, Colors.get(-1, 300, 300, Colors.fromHex("#FF0000")), "Monkey like.");
	public static Item orange = new Item("Orange", 2, 2, 0, Colors.get(-1, 400, 400, Colors.fromHex("#FF0000")), "Orange you glad I said banana.");
	public static Item feather = new Item("Feather", 3, 3, 0, Colors.get(-1, 500, 500, Colors.fromHex("#FF0000")), "So light.");
	public static Item revolver = new Gun("Revolver", 4, 0, 0, Colors.get(-1, 500, 500, Colors.fromHex("#FF0000")), "Standard Firearm", 0 , 6, 10, 20);
	public static Item laserRevolver = new Gun("Laser Revolver", 4, 2, 0, Colors.get(-1, 500, 500, Colors.fromHex("#FF0000")), "Standard Firearm", 1, 10, 10, 20);
	public static Item shotgun = new Gun("Shotgun", 5, 4, 0, Colors.get(-1, 500, 500, Colors.fromHex("#FF0000")), "Standard Firearm", 2, 5, 10, 20);
	public static Item assaultRifle = new Gun("Assault Rifle", 6, 6, 0, Colors.get(-1, 500, 500, Colors.fromHex("#FF0000")), "Standard Firearm", 3, 20, 10, 20);
	public static Item crossBow = new Gun("Crossbow", 7, 8, 0, Colors.get(-1, 500, 500, Colors.fromHex("#FF0000")), "Standard Firearm", 4, 8, 10, 20);
	
	public String name;
	public int id;
	protected int color;
	protected int xTile;
	protected int yTile;
	protected int xOffset = 0;
	protected int yOffset = 0;
	public String description;
	protected int amount = 1;

	public Item(String name, int id, int xTile, int yTile, int color, String description) {
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

	public void render(Screen screen) {
		screen.render(xOffset, yOffset, xTile + yTile * 32, color, 0, 1, SpriteSheet.letters);
	}
	
}
