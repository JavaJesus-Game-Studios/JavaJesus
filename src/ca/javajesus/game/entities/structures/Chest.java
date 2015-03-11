package ca.javajesus.game.entities.structures;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.items.Bazooka;
import ca.javajesus.items.Gun;
import ca.javajesus.items.Item;
import ca.javajesus.items.Sword;
import ca.javajesus.level.Level;

public class Chest extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#452909"),
			Colors.fromHex("#ffe011"));
	protected boolean isOpen = false;
	ArrayList<Item> contents;

	Random random = new Random();

	public Chest(Level level, double x, double y) {
		super(level, x, y, 8, 8);
		contents = new ArrayList<Item>();
		this.bounds.setSize(8, 8);
		this.bounds.setLocation((int) x, (int) y);

	}

	public Chest(Level level, double x, double y, ArrayList<Item> items) {
		super(level, x, y, 8, 8);
		this.contents = items;
		this.bounds.setSize(8, 8);
		this.bounds.setLocation((int) x, (int) y);
	}

	public Chest(Level level, double x, double y, String type, int amt) {
		super(level, x, y, 8, 8);
		contents = new ArrayList<Item>();
		fillRandomItems(type, amt);
		this.bounds.setSize(8, 8);
		this.bounds.setLocation((int) x, (int) y);
	}

	private Gun getRandomGun() {
		switch (random.nextInt(5)) {
		case 0:
			return new Gun("Revolver", 4, 0, 0, Colors.get(-1, 500, 500,
					Colors.fromHex("#FF0000")), "Standard Firearm", 0, 0, 6,
					10, 20, 50);
		case 1:
			return new Gun("Laser Revolver", 4, 2, 0, Colors.get(-1, 500, 500,
					Colors.fromHex("#FF0000")), "Standard Firearm", 1, 0, 10,
					10, 20, 2);
		case 2:
			return new Gun("Shotgun", 5, 4, 0, Colors.get(-1, 500, 500,
					Colors.fromHex("#FF0000")), "Standard Firearm", 2, 4, 5,
					10, 20, 2);
		case 3:
			return new Gun("Assault Rifle", 6, 6, 0, Colors.get(-1, 500, 500,
					Colors.fromHex("#FF0000")), "Standard Firearm", 3, 8, 60,
					1, 6, 100);
		case 4:
			return new Gun("Crossbow", 7, 8, 0, Colors.get(-1, 500, 500,
					Colors.fromHex("#FF0000")), "Standard Firearm", 4, 12, 8,
					10, 20, 2);
		default:
			return new Bazooka();
		}
	}

	private Sword getRandomSword() {
		return new Sword("Small Sword", 8, 0, 3, Colors.get(-1,
				Colors.fromHex("#f2f3f9"), -1, Colors.fromHex("#d6d7dc")),
				"This is a sword", 0, 30, 5);
	}

	private Item getRandomItem() {
		switch (random.nextInt(4)) {
		case 0:
			return new Item("Apple", 0, 0, 0, Colors.get(-1, 200, 200,
					Colors.fromHex("#FF0000")), "This is a red fruit!");
		case 1:
			return new Item("Banana", 1, 1, 0, Colors.get(-1, 300, 300,
					Colors.fromHex("#FF0000")), "Monkey like.");
		case 2:
			return new Item("Orange", 2, 2, 0, Colors.get(-1, 400, 400,
					Colors.fromHex("#FF0000")),
					"Orange you glad I said banana.");
		default:
			return new Item("Feather", 3, 3, 0, Colors.get(-1, 500, 500,
					Colors.fromHex("#FF0000")), "So light.");
		}
	}

	private void fillRandomItems(String string, int amt) {
		switch (string) {
		case "gun":
			contents.add(getRandomGun());
			break;
		case "sword":
			contents.add(getRandomSword());
			break;
		case "item":
			contents.add(getRandomItem());
			break;
		default:
			switch (random.nextInt(3)) {
			case 0:
				contents.add(getRandomGun());
				break;
			case 1:
				contents.add(getRandomSword());
				break;
			default:
				contents.add(getRandomItem());
				break;
			}
		}
		if (amt > 0)
			fillRandomItems(string, amt - 1);
	}

	public void open(Player player) {
		if (!isOpen) {
			isOpen = true;
			for (Item e : contents) {
				player.inventory.addItem(e);
				ChatHandler.sendMessage("You have obtained " + e, Color.GREEN);
			}
			player.equip();
		}
	}

	public void render(Screen screen) {

		if (!isOpen) {
			screen.render(x, y, 0 + 20 * 32, color, 0, 1, SpriteSheet.tiles);
			screen.render(x + 8, y, 1 + 20 * 32, color, 0, 1, SpriteSheet.tiles);
		} else {
			screen.render(x, y, 2 + 20 * 32, color, 0, 1, SpriteSheet.tiles);
			screen.render(x + 8, y, 3 + 20 * 32, color, 0, 1, SpriteSheet.tiles);
		}

	}

}
