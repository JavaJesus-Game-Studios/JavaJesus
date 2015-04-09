package ca.javajesus.game.entities.vehicles;

import ca.javajesus.game.Game;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class Horse extends Vehicle {

	private int yTile;
	private int xTile;

	public Horse(Level level, int x, int y, int yTile) {
		super(level, "Horse", x, y, 1, 16, 24, SpriteSheet.horses, 300);
		this.yTile = yTile;
		this.xTile = 16;
		this.color = Game.player.getColor();
		this.DELAY = 1;
	}

	public void tick() {
		super.tick();
		
		if (this.isUsed) {
			xTile = 0;
		} else {
			xTile = 16;
		}
	}

	public void render(Screen screen) {
		super.render(screen);
		int modifier = 8 * scale;
		int xOffset = (x - modifier - modifier / 2);
		int yOffset = (y - modifier - modifier / 2);
		if (!isDead)
			if (isLongitudinal(getDirection())) {
				xOffset = x - modifier;
				this.width = 14;
				this.height = 24;
				this.getBounds().setSize(width, height);
				this.getBounds().setLocation(this.x - 7, this.y - 12);
				this.getOuterBounds().setSize(18, height);
				this.getOuterBounds().setLocation(this.x - 9, this.y - 14);
			} else {
				xOffset = x - modifier - modifier / 2;
				this.width = 24;
				this.height = 24;
				this.getBounds().setSize(width, height);
				this.getBounds().setLocation(this.x - 12, this.y - 12);
				this.getOuterBounds().setSize(width + 5, height);
				this.getOuterBounds().setLocation(this.x - 14, this.y - 14);
			}

		int xTile = this.xTile;
		int walkingSpeed = 4;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 12;
		}

		if (getDirection() == Direction.SOUTH) {
			xTile += 2;
		} else if (isLatitudinal(getDirection())) {
			xTile += 4 + flip * 4;
			if (getDirection() == Direction.WEST) {
				flip = 1;
			} else {
				flip = 0;
			}
		}

		if (isDead) {
			if (isLongitudinal(getDirection())) {
				setDirection(Direction.WEST);
			}
			xTile = 14;
		}

		if (isLongitudinal(getDirection())) {
			// Upper body
			screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile
					* sheet.boxes, color, flip, scale, sheet);

			// Upper body
			screen.render(xOffset + modifier - (modifier * flip), yOffset,
					(xTile + 1) + yTile * sheet.boxes, color, flip, scale,
					sheet);

			// Middle Body
			screen.render(xOffset + (modifier * flip), yOffset + modifier,
					xTile + (yTile + 1) * sheet.boxes, color, flip, scale,
					sheet);

			// Middle Body
			screen.render(xOffset + modifier - (modifier * flip), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color,
					flip, scale, sheet);

			// Lower Body
			screen.render(xOffset + (modifier * flip), yOffset + 2 * modifier,
					xTile + (yTile + 2) * sheet.boxes, color, flip, scale,
					sheet);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * flip), yOffset + 2
					* modifier, (xTile + 1) + (yTile + 2) * sheet.boxes, color,
					flip, scale, sheet);
		} else {

			int xOff2 = 0;
			if (getDirection() == Direction.WEST) {
				xOff2 = -16;
			}

			// Upper body
			screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile
					* sheet.boxes, color, flip, scale, sheet);

			// Upper body
			screen.render(xOffset + modifier - (modifier * flip), yOffset,
					(xTile + 1) + yTile * sheet.boxes, color, flip, scale,
					sheet);
			// Upper body
			screen.render(xOffset + xOff2 + 2 * modifier - (modifier * flip),
					yOffset, (xTile + 2) + yTile * sheet.boxes, color, flip,
					scale, sheet);

			// Middle Body
			screen.render(xOffset + (modifier * flip), yOffset + modifier,
					xTile + (yTile + 1) * sheet.boxes, color, flip, scale,
					sheet);

			// Middle Body
			screen.render(xOffset + modifier - (modifier * flip), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color,
					flip, scale, sheet);

			// Middle Body
			screen.render(xOffset + xOff2 + 2 * modifier - (modifier * flip),
					yOffset + modifier,
					(xTile + 2) + (yTile + 1) * sheet.boxes, color, flip,
					scale, sheet);

			// Lower Body
			screen.render(xOffset + (modifier * flip), yOffset + 2 * modifier,
					xTile + (yTile + 2) * sheet.boxes, color, flip, scale,
					sheet);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * flip), yOffset + 2
					* modifier, (xTile + 1) + (yTile + 2) * sheet.boxes, color,
					flip, scale, sheet);

			// Lower Body
			screen.render(xOffset + xOff2 + 2 * modifier - (modifier * flip),
					yOffset + 2 * modifier, (xTile + 2) + (yTile + 2)
							* sheet.boxes, color, flip, scale, sheet);
		}

	}

}
