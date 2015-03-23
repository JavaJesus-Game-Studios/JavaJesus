package ca.javajesus.game.entities.particles;

import java.awt.Rectangle;

import ca.javajesus.game.Game;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.items.Item;
import ca.javajesus.level.Level;

public class Pickup extends Particle {

	private final Rectangle BOX = new Rectangle(8, 8);
	private Item item;

	public Pickup(Level level, int x, int y, Item item) {
		super(level, 9, Colors.get(-1, 555, 300, 500), x, y);
		this.x = x;
		this.y = y;
		this.item = item;
	}
	
	public Pickup(Level level, int x, int y, Item item, int color) {
		super(level, 9, color, x, y);
		this.x = x;
		this.y = y;
		this.item = item;
		this.color = color;
	}

	public void render(Screen screen) {

		screen.render(this.x, this.y, tileNumber, color, 1, 1, sheet);
		BOX.setLocation((int) this.x, (int) this.y);
		if (BOX.intersects(Game.player.getBounds())) {
			Game.player.inventory.addItem(item);
			sound.play(SoundHandler.sound.click);
			level.remEntity(this);
		}

	}

}
