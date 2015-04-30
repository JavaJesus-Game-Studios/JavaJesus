package ca.javajesus.game.entities.structures.transporters;

import java.awt.Point;

import ca.javajesus.game.Game;
import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.entities.Mob.Direction;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.level.Level;

public class MapTransporter extends Transporter {

	private static final long serialVersionUID = -788215519977991994L;

	private Direction dir;
	double offset = 0;

	public MapTransporter(Level currentLevel, int x, int y, Level nextLevel,
			Direction dir, int width, int height) {
		super(currentLevel, x, y, nextLevel, null);
		this.dir = dir;
		calcPoint();
		this.bounds = new JavaRectangle(width, height, this);
		this.bounds.setLocation(x, y);
	}

	public MapTransporter(Level currentLevel, int x, int y, Level nextLevel,
			Direction dir, int width, int height, double offset) {
		super(currentLevel, x, y, nextLevel, null);
		this.dir = dir;
		calcPoint();
		this.bounds = new JavaRectangle(width, height, this);
		this.bounds.setLocation(x, y);
		this.offset = offset;
	}

	public void tick() {
		if (nextLevel != null)
			for (Player player : level.getPlayers()) {
				if (this.bounds.intersects(player.getBounds())
						&& !player.isDriving) {
					calcPoint();
					player.changeLevel(nextLevel);
				}
			}
	}

	private void calcPoint() {
		Point point;
		switch (dir) {
		case NORTH: {
			point = new Point(Game.player.getX(), (nextLevel.height * 8) - 16);
			break;
		}
		case SOUTH: {
			point = new Point(Game.player.getX(), 16);
			break;
		}
		case EAST: {
			point = new Point(16, Game.player.getY() + (int) offset);
			break;
		}
		default: {
			point = new Point((nextLevel.width * 8) - 16, Game.player.getY()
					+ (int) offset);
			break;
		}
		}
		nextLevel.spawnPoint = point;
	}

	public void render(Screen screen) {

	}

}
