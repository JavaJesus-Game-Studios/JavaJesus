package ca.javajesus.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;

public class Item {

	private static final long serialVersionUID = 1L;
	
	public static Item apple = new Item("Apple", 0, 0, 0, Colors.get(-1, 200, 200, Colors.fromHex("#FF0000")), "This is a red fruit!");
	public static Item banana = new Item("Banana", 1, 1, 0, Colors.get(-1, 300, 300, Colors.fromHex("#FF0000")), "Monkey like.");
	public static Item orange = new Item("Orange", 2, 2, 0, Colors.get(-1, 400, 400, Colors.fromHex("#FF0000")), "Orange you glad I said banana.");
	public static Item feather = new Item("Feather", 3, 3, 0, Colors.get(-1, 500, 500, Colors.fromHex("#FF0000")), "So light.");

	public static List<Item> items = new ArrayList<Item>();
	
	public String name;
	public int id;
	private int color;
	private int xTile;
	private int yTile;
	protected int xOffset = 0;
	protected int yOffset = 0;
	public String description;

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
