package ca.javajesus.game.entities;

import java.awt.Rectangle;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Transporter extends Entity {
	
	private SpriteSheet sheet = SpriteSheet.tiles;

	public final Rectangle hitBox = new Rectangle(16, 16);
	private Level nextLevel;

	public Transporter(Level currentLevel, double x, double y, Level nextLevel) {
		super(currentLevel);
		this.x = x;
		this.y = y;
		this.nextLevel = nextLevel;
		this.hitBox.setLocation((int) x, (int) y); 
	}

	public void tick() {
		for (Player player : level.getPlayers()) {
			if (this.hitBox.intersects(player.hitBox)) {
				player.changeLevel(nextLevel);
			}
		}
	}

	public void render(Screen screen) {
		
		screen.render(x + 0, y + 0, 0 + 5 * 32, Colors.get(-1, 555, 333, 222), 0, 1, sheet);
		screen.render(x + 8, y + 0, 1 + 5 * 32, Colors.get(-1, 555, 333, 222), 0, 1, sheet);
		screen.render(x + 0, y + 8, 0 + 6 * 32, Colors.get(-1, 555, 333, 222), 0, 1, sheet);
		screen.render(x + 8, y + 8, 1 + 6 * 32, Colors.get(-1, 555, 333, 222), 0, 1, sheet);
	}

}
