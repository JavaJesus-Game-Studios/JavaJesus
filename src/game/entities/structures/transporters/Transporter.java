package game.entities.structures.transporters;

import game.entities.Entity;
import game.entities.Player;
import game.entities.SolidEntity;
import game.graphics.Screen;
import game.graphics.SpriteSheet;

import java.awt.Point;
import java.awt.Rectangle;

import level.Level;

public class Transporter extends Entity {

	private static final long serialVersionUID = -7494182586888198075L;

	protected Level nextLevel;

	public Transporter(Level currentLevel, int x, int y, Level nextLevel) {
		super(currentLevel, x, y, 8, 16);
		this.nextLevel = nextLevel;
		this.bounds = new Rectangle(8, 16);
		this.bounds.setLocation(x - 4, y - 8);
	}

	public Transporter(Level currentLevel, int x, int y, Level nextLevel, Point point) {
		super(currentLevel, x, y, 8, 16);
		this.nextLevel = nextLevel;
		this.bounds = new Rectangle(8, 16);
		this.bounds.setLocation(x - 4, y - 8);
		nextLevel.spawnPoint = point;
	}

	public void tick() {
		for (Player player : level.getPlayers()) {
			if (this.bounds.intersects(player.getBounds())) {
				if (player.input.e.isPressed()) {
					level.spawnPoint = new Point(player.getX(), player.getY());
					player.changeLevel(nextLevel);
				}
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
