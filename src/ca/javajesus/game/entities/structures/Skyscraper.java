package ca.javajesus.game.entities.structures;

import java.awt.Rectangle;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
import ca.javajesus.level.Level;

public class Skyscraper extends SolidEntity {
	
	protected int color = Colors.get(-1, 111, Colors.fromHex("#673101"), 555);
	
	public Skyscraper(Level level, double x, double y) {
		super(level, x, y, 80, 250);
		this.shadow = new Rectangle(width, (2 * height / 3));
		this.shadow.setLocation((int) x, (int) y);
		this.bounds = new Rectangle(width, (height / 3) - 8);
		this.bounds.setLocation((int) x, (int) y + shadow.height);
		//level.addEntity(new Transporter(level, x + 38, y + 234, new PoorHouseInterior(new Point((int) x + 40, (int) y + 242))));
		level.addEntity(new Transporter(level, x + 38, y + 234, Level.random2));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.skyscraper);

	}

}
