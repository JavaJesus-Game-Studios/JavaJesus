package ca.javajesus.game.entities.vehicles;

import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Vehicle extends Mob {

	protected double scaledSpeed;
	protected int color;

	public static Vehicle vehicle1 = new CenturyLeSabre(Level.level1,
			"Century LeSabre", 300, 300, 5, SpriteSheet.vehicles, 200);

	public Vehicle(Level level, String name, double x, double y, int speed,
			int width, int height, SpriteSheet sheet, double defaultHealth) {
		super(level, name, x, y, speed, width, height, sheet, defaultHealth);
		this.scaledSpeed = speed;
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		return false;
	}

	public void tick() {

		updateHealth();

	}

	public void render(Screen screen) {

	}

}
