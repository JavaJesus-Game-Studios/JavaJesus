package ca.javajesus.game.entities.structures;

import java.awt.Color;
import java.util.ArrayList;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class Chest extends SolidEntity {

	protected int color = Colors.get(-1, 111, Colors.fromHex("#8d8d8d"),
			Colors.fromHex("#eefeff"));
	protected boolean isOpen = false;
	ArrayList<Item> contents;

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

	public void open(Player player) {
		if (!isOpen) {
			isOpen = true;
			for (Item e : contents) {
				player.inventory.addItem(e);
				ChatHandler.sendMessage("You have obtained " + e, Color.GREEN);
			}
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
