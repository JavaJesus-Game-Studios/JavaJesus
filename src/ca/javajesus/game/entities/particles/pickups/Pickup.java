package ca.javajesus.game.entities.particles.pickups;

import ca.javajesus.game.Game;
import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.particles.Particle;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class Pickup extends Particle {

	private static final long serialVersionUID = 8454550791664174098L;
	
	private Item item;
	private int quantity;

	public Pickup(Level level, int x, int y, Item item) {
		super(level, 9, new int[] { 0xFFFFFFFF, 0xFF990000, 0xFFFF0000 }, x, y);
		this.x = x;
		this.y = y;
		this.item = item;
		this.bounds = new JavaRectangle(8, 8, this);
		bounds.setLocation((int) this.x, (int) this.y);
		this.quantity = 1;
	}

	public Pickup(Level level, int x, int y, Item item, int[] color, int xTile,
			int yTile, int amount) {
		super(level, 9, color, x, y);
		this.x = x;
		this.y = y;
		this.item = item;
		this.color = color;
		this.bounds = new JavaRectangle(8, 8, this);
		bounds.setLocation((int) this.x, (int) this.y);
		this.sheet = SpriteSheet.items;
		this.tileNumber = xTile + yTile * this.sheet.boxes;
		this.quantity = amount;
	}

	public void render(Screen screen) {

		screen.render((int) this.x, (int) this.y, tileNumber, color, 1, 1,
				sheet);
		if (bounds.intersects(Game.player.getBounds())) {
			for (int i = 0; i < quantity; i++)
				Game.player.inventory.addItem(item);
			sound.play(SoundHandler.sound.click);
			level.remEntity(this);
		}

	}

}
