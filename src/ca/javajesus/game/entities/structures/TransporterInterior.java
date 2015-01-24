package ca.javajesus.game.entities.structures;

import java.awt.Point;
import java.awt.Rectangle;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class TransporterInterior extends SolidEntity {
	
	private SpriteSheet sheet = SpriteSheet.tiles;

	public final Rectangle hitBox = new Rectangle(16, 8);
	private Level nextLevel;

	public TransporterInterior(Level currentLevel, double x, double y, Level nextLevel) {
		super(currentLevel, x, y, 8, 16);
		this.nextLevel = nextLevel;
		this.hitBox.setLocation((int) x, (int) y); 
	}
	
	public TransporterInterior(Level currentLevel, double x, double y, Level nextLevel, Point point) {
		super(currentLevel, x, y, 8, 16);
		this.nextLevel = nextLevel;
		this.hitBox.setLocation((int) x, (int) y); 
		nextLevel.setSpawnPoint(point);
	}

	public void tick() {
		for (Player player : level.getPlayers()) {
			if (this.hitBox.intersects(player.hitBox)) {
				player.changeLevel(nextLevel);
			}
		}
	}

	public void render(Screen screen) {
		
		screen.render(x + 0, y + 2, 0 + 5 * 32, Colors.get(-1, 000, 000, 000), 0, 1, sheet);
	}

}
