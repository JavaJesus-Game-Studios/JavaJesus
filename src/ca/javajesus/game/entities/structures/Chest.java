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
			return (Gun) Item.revolver;
		case 1:
			return (Gun) Item.laserRevolver;
		case 2:
			return (Gun) Item.shotgun;
		case 3:
			return (Gun) Item.assaultRifle;
		case 4:
			return (Gun) Item.crossBow;
		default:
			return (Gun) Item.bazooka;
		}
	}

	private Sword getRandomSword() {
		return (Sword) Item.shortSword;
	}

	private Item getRandomItem() {
		switch (random.nextInt(4)) {
		case 0:
			return Item.apple;
		case 1:
			return Item.banana;
		case 2:
			return Item.orange;
		default:
			return Item.feather;
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
