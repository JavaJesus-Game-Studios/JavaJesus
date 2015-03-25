package ca.javajesus.game.entities.monsters;

import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.level.Level;

public class Cyclops extends Monster {
	private static int[] color = { 0xFF111111, 0xFFFFD99C, 0xFFFFFFFF };

	public Cyclops(Level level, int x, int y) {
		super(level, "Cyclops", x, y, 1, 32, 48, 14, 5000, color);
		this.bar = new HealthBar(level, x, y, this);
		level.addEntity(bar);
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
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

	public void tick() {
		super.tick();
		int xa = 0;
		int ya = 0;
		if (mob != null && !this.getOuterBounds().intersects(mob.getBounds())) {

			if (mob.getX() > this.x) {
				xa++;
			}
			if (mob.getX() < this.x) {
				xa--;
			}
			if (mob.getY() > this.y) {
				ya++;
			}
			if (mob.getY() < this.y) {
				ya--;
			}
		}

		if (tickCount % 100 == 0) {
			cooldown = false;
		} else {
			cooldown = true;
		}

		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			setMoving(true);
			move(xa, ya);
		} else {
			setMoving(false);
		}

	}

	public void render(Screen screen) {
		super.render(screen);
		this.getBounds().setLocation((int) this.x - 16, (int) this.y - 24);
		this.getOuterBounds().setLocation((int) this.x - 18, (int) this.y - 26);

		int xTile = 0;
		int walkingSpeed = 4;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile = 28;
		}
		if (getDirection() == Direction.SOUTH) {
			xTile = 8;
		} else if (isLatitudinal(getDirection())) {
			xTile = 12 + ((numSteps >> walkingSpeed) & 1) * 4;
			if (getDirection() == Direction.WEST) {
				flip = 1;
			} else {
				flip = 0;
			}
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier * 3;
		int yOffset = y - modifier * 2;

		int yTile = this.yTile;
		if (isDead) {
			flip = 0;
			flip = 0;
			xTile = 32;
			yTile = 18;
		}

		for (int i = 0; i < 6; i++) {

			if (isDead && i > 1) {
				break;
			}

			screen.render(xOffset + (modifier * flip * 3), yOffset + i
					* modifier, xTile + (yTile + i) * sheet.boxes, color, flip,
					scale, sheet);

			screen.render(xOffset + modifier + (modifier * flip), yOffset + i
					* modifier, (xTile + 1) + (yTile + i) * sheet.boxes, color,
					flip, scale, sheet);

			screen.render(xOffset + 2 * modifier - (modifier * flip), yOffset
					+ i * modifier, (xTile + 2) + (yTile + i) * sheet.boxes,
					color, flip, scale, sheet);

			screen.render(xOffset + 3 * modifier - (modifier * flip * 3),
					yOffset + i * modifier, (xTile + 3) + (yTile + i)
							* sheet.boxes, color, flip, scale, sheet);

			if (isDead) {
				screen.render(xOffset + 4 * modifier - (modifier * flip * 3),
						yOffset + i * modifier, (xTile + 4) + (yTile + i)
								* sheet.boxes, color, flip, scale, sheet);

				screen.render(xOffset + 5 * modifier - (modifier * flip),
						yOffset + i * modifier, (xTile + 5) + (yTile + i)
								* sheet.boxes, color, flip, scale, sheet);

			}
		}

	}

}
