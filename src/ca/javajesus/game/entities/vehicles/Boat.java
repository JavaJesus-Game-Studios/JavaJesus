package ca.javajesus.game.entities.vehicles;

import java.awt.Rectangle;

import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;
import ca.javajesus.level.tile.Tile;

public class Boat extends Vehicle {

	public Boat(Level level, String name, int x, int y, int speed,
			int defaultHealth) {
		super(level, name, x, y, speed, 32, 40, SpriteSheet.vehicles,
				defaultHealth);
		getColor();
		this.setHitBox(new Rectangle(width, height));
	}

	private void getColor() {
		int[] color = { 0xFF111111, 0xFF000000, 0xFFC2FEFF };
		switch (random.nextInt(8)) {
		case 0: {
			// red color
			color[1] = 0xFFFF0000;
			break;
		}
		case 1: {
			// gold color
			color[1] = 0xFFCFB53B;
			break;
		}
		case 2: {
			// blue color
			color[1] = 0xFF005AFF;
			break;
		}
		case 3: {
			// silver color
			color[1] = 0xFFCCCCCC;
			break;
		}
		case 4: {
			// black color
			color[1] = 0xFF000000;
			break;
		}
		case 5: {
			// green color
			color[1] = 0xFF066518;
			break;
		}
		case 6: {
			// purple color
			color[1] = 0xFF580271;
			break;
		}
		default: {
			// White
			color[1] = 0xFFFFFFFF;
			break;
		}
		}
		super.color = color;
	}

	public void render(Screen screen) {

		int modifier = 8 * scale;
		int xOffset = 0;
		int yOffset = 0;

		if (isLongitudinal(getDirection())) {
			xOffset = x - modifier * 2;
			yOffset = y - modifier * 2 - modifier / 2;
			this.width = 32;
			this.height = 40;
		} else {
			xOffset = x - modifier * 2 - modifier / 2;
			yOffset = y - modifier * 2;
			this.width = 40;
			this.height = 32;
		}
		this.getBounds().setSize(width, height);
		this.getBounds().setLocation(this.x - width / 2, this.y - height / 2);

		int xTile = 0;
		int yTile = 16;

		int flip = 0;

		if (getDirection() == Direction.NORTH) {
			xTile += 14;
		} else if (getDirection() == Direction.WEST) {
			xTile += 9;
		} else if (getDirection() == Direction.EAST) {
			xTile += 4;
		}

		// Upper body 1
		screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile
				* sheet.boxes, color, flip, scale, sheet);
		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * flip), yOffset,
				(xTile + 1) + yTile * sheet.boxes, color, flip, scale, sheet);

		// Upper Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flip), yOffset,
				(xTile + 2) + yTile * sheet.boxes, color, flip, scale, sheet);

		// Upper Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flip), yOffset,
				(xTile + 3) + yTile * sheet.boxes, color, flip, scale, sheet);

		// Second Body 1
		screen.render(xOffset + (modifier * flip), yOffset + modifier, xTile
				+ (yTile + 1) * sheet.boxes, color, flip, scale, sheet);

		// Second Body 2
		screen.render(xOffset + modifier - (modifier * flip), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color,
				flip, scale, sheet);

		// Second Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flip), yOffset
				+ modifier, (xTile + 2) + (yTile + 1) * sheet.boxes, color,
				flip, scale, sheet);

		// Second Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flip), yOffset
				+ modifier, (xTile + 3) + (yTile + 1) * sheet.boxes, color,
				flip, scale, sheet);

		// Third Body 1
		screen.render(xOffset + (modifier * flip), yOffset + 2 * modifier,
				xTile + (yTile + 2) * sheet.boxes, color, flip, scale, sheet);

		// Third Body 2
		screen.render(xOffset + modifier - (modifier * flip), yOffset + 2
				* modifier, (xTile + 1) + (yTile + 2) * sheet.boxes, color,
				flip, scale, sheet);

		// Third Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flip), yOffset + 2
				* modifier, (xTile + 2) + (yTile + 2) * sheet.boxes, color,
				flip, scale, sheet);

		// Third Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flip), yOffset + 2
				* modifier, (xTile + 3) + (yTile + 2) * sheet.boxes, color,
				flip, scale, sheet);

		// Fourth Body 1
		screen.render(xOffset + (modifier * flip), yOffset + 3 * modifier,
				xTile + (yTile + 3) * sheet.boxes, color, flip, scale, sheet);

		// Fourth Body 2
		screen.render(xOffset + modifier - (modifier * flip), yOffset + 3
				* modifier, (xTile + 1) + (yTile + 3) * sheet.boxes, color,
				flip, scale, sheet);

		// Fourth Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flip), yOffset + 3
				* modifier, (xTile + 2) + (yTile + 3) * sheet.boxes, color,
				flip, scale, sheet);

		// Fourth Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flip), yOffset + 3
				* modifier, (xTile + 3) + (yTile + 3) * sheet.boxes, color,
				flip, scale, sheet);

		if (isLongitudinal(getDirection())) {
			// Lower Body 1
			screen.render(xOffset + (modifier * flip), yOffset + 4 * modifier,
					xTile + (yTile + 4) * sheet.boxes, color, flip, scale,
					sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flip), yOffset + 4
					* modifier, (xTile + 1) + (yTile + 4) * sheet.boxes, color,
					flip, scale, sheet);

			// Lower Body 3
			screen.render(xOffset + 2 * modifier - (modifier * flip), yOffset
					+ 4 * modifier, (xTile + 2) + (yTile + 4) * sheet.boxes,
					color, flip, scale, sheet);

			// Lower Body 4
			screen.render(xOffset + 3 * modifier - (modifier * flip), yOffset
					+ 4 * modifier, (xTile + 3) + (yTile + 4) * sheet.boxes,
					color, flip, scale, sheet);

		} else {
			// Upper Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flip), yOffset,
					(xTile + 4) + yTile * sheet.boxes, color, flip, scale,
					sheet);
			// Second Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flip), yOffset
					+ modifier, (xTile + 4) + (yTile + 1) * sheet.boxes, color,
					flip, scale, sheet);
			// Third Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flip), yOffset
					+ 2 * modifier, (xTile + 4) + (yTile + 2) * sheet.boxes,
					color, flip, scale, sheet);
			// Fourth Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flip), yOffset
					+ 3 * modifier, (xTile + 4) + (yTile + 3) * sheet.boxes,
					color, flip, scale, sheet);

		}

	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 0;
		int yMin = 0;
		int yMax = 0;
		if (isLongitudinal(getDirection())) {
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
