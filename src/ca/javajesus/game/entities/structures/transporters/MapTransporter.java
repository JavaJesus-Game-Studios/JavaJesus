package ca.javajesus.game.entities.structures.transporters;

import java.awt.Point;

import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.level.Level;

public class MapTransporter extends Transporter {

	public MapTransporter(Level currentLevel, int x, int y, Level nextLevel,
			Point point, int width, int height) {
		super(currentLevel, x, y, nextLevel, point);
		this.bounds = new JavaRectangle(width, height, this);
		this.bounds.setLocation(x, y);
	}

	public void tick() {
		if (nextLevel != null)
			for (Player player : level.getPlayers()) {
				if (this.bounds.intersects(player.getBounds())) {
					player.changeLevel(nextLevel);
				}
			}
	}

	public void render(Screen screen) {

	}

}
