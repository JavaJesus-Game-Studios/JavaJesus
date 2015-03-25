package ca.javajesus.game.entities.monsters;

import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.level.Level;

public class Centaur extends Monster {
	private static int[] color = { 0xFF111111, 0xFF8F4C1F, 0xFFEDC5AB };

	public Centaur(Level level, String name, int x, int y, int speed, int health) {
		super(level, name, x, y, speed, 14, 24, 5, health, color);
		this.bar = new HealthBar(level, this.x, this.y, this);
		if (level != null)
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

		if (isShooting) {
			shootTickCount++;
			if (shootTickCount > 20) {
				shootTickCount = 0;
				isShooting = false;
			}
		}

		int xa = 0;
		int ya = 0;
		if (mob != null && this.aggroRadius.intersects(mob.getBounds())
				&& !this.getOuterBounds().intersects(mob.getOuterBounds())) {

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

			if (tickCount % 100 == 0) {
				cooldown = false;
			} else {
				cooldown = true;
			}
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
		int modifier = 8 * scale;
		int xOffset = 0;
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

		int xTile = 0;
		int walkingSpeed = 4;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 12;
		}

		if (getDirection() == Direction.SOUTH) {
			xTile += 2;
		} else if (isLatitudinal(getDirection())) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 3;
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

		if (!cooldown) {
			if (mob == null) {
				return;
			}
			isShooting = true;

		}

	}

}
