package ca.javajesus.game.entities.structures.transporters;

import java.awt.Point;

import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class Transporter extends SolidEntity {

	protected Level nextLevel;

	public Transporter(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, 8, 16);
		this.nextLevel = nextLevel;
		this.bounds = new JavaRectangle(8, 16, this);
		this.bounds.setLocation(x, y);
	}

	public Transporter(Level currentLevel, int x, int y, Level nextLevel,
			Point point) {
		super(currentLevel, x, y, 8, 16);
		this.nextLevel = nextLevel;
		this.bounds = new JavaRectangle(8, 16, this);
		this.bounds.setLocation(x - 4, y - 8);
		nextLevel.spawnPoint = point;
	}

	public void tick() {
		for (Player player : level.getPlayers()) {
			if (this.bounds.intersects(player.getBounds())) {
				player.changeLevel(nextLevel);
			}
		}
	}

	public void render(Screen screen) {
		int[] color = { 0xFF111111, 0xFF704200, 0xFFFFDE00 };

		screen.render(x + 0, y + 0, 0 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 8, y + 0, 1 + 5 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 0, y + 8, 0 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
		screen.render(x + 8, y + 8, 1 + 6 * 32, color, 0, 1, SpriteSheet.tiles);
	}

}
