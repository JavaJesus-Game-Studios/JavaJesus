package ca.javajesus.game.entities.vehicles;

import java.awt.Rectangle;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.tile.Tile;

public class Boat extends Vehicle {

	public Boat(Level level, String name, int x, int y,
			int speed, int defaultHealth) {
		super(level, name, x, y, speed, 32, 40, SpriteSheet.vehicles, defaultHealth);
		getColor();
		this.setHitBox(new Rectangle(width, height));
	}

	private void getColor() {
		switch (random.nextInt(8)) {
		case 0: {
			// red color
			color = Colors.get(-1, 111, Colors.fromHex("#ff0000"),
					Colors.fromHex("#c2feff"));
			break;
		}
		case 1: {
			// gold color
			color = Colors.get(-1, 111, Colors.fromHex("#CFB53B"),
					Colors.fromHex("#c2feff"));
			break;
		}

		case 2: {
			// blue color
			color = Colors.get(-1, 111, Colors.fromHex("#005aff"),
					Colors.fromHex("#c2feff"));
			break;
		}
		case 3: {
			// silver color
			color = Colors.get(-1, 111, Colors.fromHex("#CCCCCC"),
					Colors.fromHex("#c2feff"));
			break;
		}
		case 4: {
			// black color
			color = Colors.get(-1, 111, 000, Colors.fromHex("#c2efeff"));
			break;
		}
		case 5: {
			// green color
			color = Colors.get(-1, 111, Colors.fromHex("#066518"),
					Colors.fromHex("#c2efeff"));
			break;
		}
		case 6: {
			// purple color
			color = Colors.get(-1, 111, Colors.fromHex("#580271"),
					Colors.fromHex("#c2efeff"));
			break;
		}

		default: {
			// White
			color = Colors.get(-1, 111, 555, Colors.fromHex("#c2feff"));
			break;
		}
		}
	}

	public void render(Screen screen) {

		if (getDirection() == 0 || getDirection() == 1) {
			this.width = 32;
			this.height = 40;
		} else {
			this.width = 40;
			this.height = 32;
		}
		this.getBounds().setSize(width, height);
		this.getBounds().setLocation((int) this.x - 8, (int) this.y - 8);

		int xTile = 0;
		int yTile = 16;

		int flipTop = 0;
		int flipBottom = 0;
		int modifier = 8 * scale;
		double xOffset = x - modifier / 2.0;
		double yOffset = y - modifier / 2.0 - 4;

		if (getDirection() == 0) {
			xTile += 14;
		} else if (getDirection() == 2) {
			xTile += 9;
		} else if (getDirection() == 3) {
			xTile += 4;
		}

		// Upper body 1
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
				* sheet.boxes, color, flipTop, scale, sheet);
		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
				(xTile + 1) + yTile * sheet.boxes, color, flipTop, scale, sheet);

		// Upper Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipTop), yOffset,
				(xTile + 2) + yTile * sheet.boxes, color, flipTop, scale, sheet);

		// Upper Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flipTop), yOffset,
				(xTile + 3) + yTile * sheet.boxes, color, flipTop, scale, sheet);

		// Second Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier,
				xTile + (yTile + 1) * sheet.boxes, color, flipBottom, scale, sheet);

		// Second Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color, flipBottom,
				scale, sheet);

		// Second Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 2) + (yTile + 1) * sheet.boxes, color, flipBottom,
				scale, sheet);

		// Second Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 3) + (yTile + 1) * sheet.boxes, color, flipBottom,
				scale, sheet);

		// Third Body 1
		screen.render(xOffset + (modifier * flipBottom),
				yOffset + 2 * modifier, xTile + (yTile + 2) * sheet.boxes, color,
				flipBottom, scale, sheet);

		// Third Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + 2
				* modifier, (xTile + 1) + (yTile + 2) * sheet.boxes, color, flipBottom,
				scale, sheet);

		// Third Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipBottom), yOffset
				+ 2 * modifier, (xTile + 2) + (yTile + 2) * sheet.boxes, color,
				flipBottom, scale, sheet);

		// Third Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flipBottom), yOffset
				+ 2 * modifier, (xTile + 3) + (yTile + 2) * sheet.boxes, color,
				flipBottom, scale, sheet);

		// Fourth Body 1
		screen.render(xOffset + (modifier * flipBottom),
				yOffset + 3 * modifier, xTile + (yTile + 3) * sheet.boxes, color,
				flipBottom, scale, sheet);

		// Fourth Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + 3
				* modifier, (xTile + 1) + (yTile + 3) * sheet.boxes, color, flipBottom,
				scale, sheet);

		// Fourth Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipBottom), yOffset
				+ 3 * modifier, (xTile + 2) + (yTile + 3) * sheet.boxes, color,
				flipBottom, scale, sheet);

		// Fourth Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flipBottom), yOffset
				+ 3 * modifier, (xTile + 3) + (yTile + 3) * sheet.boxes, color,
				flipBottom, scale, sheet);

		if (getDirection() == 0 || getDirection() == 1) {
			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom), yOffset + 4
					* modifier, xTile + (yTile + 4) * sheet.boxes, color, flipBottom,
					scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ 4 * modifier, (xTile + 1) + (yTile + 4) * sheet.boxes, color,
					flipBottom, scale, sheet);

			// Lower Body 3
			screen.render(xOffset + 2 * modifier - (modifier * flipBottom),
					yOffset + 4 * modifier, (xTile + 2) + (yTile + 4) * sheet.boxes,
					color, flipBottom, scale, sheet);

			// Lower Body 4
			screen.render(xOffset + 3 * modifier - (modifier * flipBottom),
					yOffset + 4 * modifier, (xTile + 3) + (yTile + 4) * sheet.boxes,
					color, flipBottom, scale, sheet);

		} else {
			// Upper Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flipTop),
					yOffset, (xTile + 4) + yTile * sheet.boxes, color, flipTop, scale,
					sheet);
			// Second Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flipBottom),
					yOffset + modifier, (xTile + 4) + (yTile + 1) * sheet.boxes, color,
					flipBottom, scale, sheet);
			// Third Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flipBottom),
					yOffset + 2 * modifier, (xTile + 4) + (yTile + 2) * sheet.boxes,
					color, flipBottom, scale, sheet);
			// Fourth Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flipBottom),
					yOffset + 3 * modifier, (xTile + 4) + (yTile + 3) * sheet.boxes,
					color, flipBottom, scale, sheet);

		}

	}
	
	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 0;
		int yMin = 0;
		int yMax = 0;
		if (getDirection() == 0 || getDirection() == 1) {
			xMin = 0;
			xMax = 31;
			yMin = 0;
			yMax = 39;
		} else {
			xMin = 0;
			xMax = 39;
			yMin = 0;
			yMax = 31;
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin) || isWaterTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax) || isWaterTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y) || isWaterTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y) || isWaterTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isWaterTile(int xa, int ya, int x, int y) {
		if (level == null) {
			return false;
		}
		int xx = (int) this.x;
		int yy = (int) this.y;
		Tile lastTile = level.getTile((xx + x) >> 3, (yy + y) >> 3);
		Tile newTile = level.getTile((xx + x + xa) >> 3, (yy + y + ya) >> 3);
		if (!lastTile.equals(newTile) && newTile.equals(Tile.WATERSAND)) {
			return true;
		}
		return false;
	}

}
