package ca.javajesus.game.entities.monsters;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Centaur extends Monster {

	public Centaur(Level level, String name, double x, double y, int speed, int health) {
		super(level, name, x, y, speed, 14, 24, 5, health, Colors.get(-1, 111,
				Colors.toHex("#8f4c1f"), 543));

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

		updateHealth();
		checkRadius();

		if (isShooting) {
			shootTickCount++;
			if (shootTickCount > 20) {
				shootTickCount = 0;
				isShooting = false;
			}
		}

		if (isMobCollision()) {
			moveRandomly();
			return;
		}
		int xa = 0;
		int ya = 0;
		if (mob != null) {
			if (this.aggroRadius.intersects(mob.hitBox)) {

				if ((int) mob.x > (int) this.x) {
					xa++;
				}
				if ((int) mob.x < (int) this.x) {
					xa--;
				}
				if ((int) mob.y > (int) this.y) {
					ya++;
				}
				if ((int) mob.y < (int) this.y) {
					ya--;
				}
				tickCount++;

				if (tickCount % 100 == 0) {
					cooldown = false;
				} else {
					cooldown = true;
				}
			}
		}

		if (xa != 0 || ya != 0) {
			if (isMobCollision()) {
				isMoving = false;
				return;
			}
			move(xa, ya, scaledSpeed);
			isMoving = true;
		} else {
			isMoving = false;
		}

	}

	public void render(Screen screen) {
		if (movingDir == 0 || movingDir == 1) {
	        this.width = 14;
	        this.height = 24;
	    } else {
	        this.width = 24;
	        this.height = 24;
	    }
	    this.hitBox.setSize(width, height);

		this.hitBox.setLocation((int) this.x - 8, (int) this.y - 16);
		this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
				RADIUS);
		int xTile = 0;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipMiddle = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		if (movingDir == 0) {
			xTile += 12;
		}
		
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 3;
			flipTop = (movingDir - 1) % 2;
			flipMiddle = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
		}

		int modifier = 8 * scale;
		double xOffset = x - modifier / 2;
		double yOffset = (y - modifier / 2 - 4) - modifier;
		
		if (movingDir == 0 || movingDir == 1) {
			// Upper body
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, colour, flipTop, scale, sheet);

			// Upper body
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, colour, flipTop, scale, sheet);

			// Middle Body
			screen.render(xOffset + (modifier * flipMiddle),
					yOffset + modifier, xTile + (yTile + 1) * 32, colour,
					flipBottom, scale, sheet);

			// Middle Body
			screen.render(xOffset + modifier - (modifier * flipMiddle), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, colour,
					flipBottom, scale, sheet);

			// Lower Body
			screen.render(xOffset + (modifier * flipBottom), yOffset + 2
					* modifier, xTile + (yTile + 2) * 32, colour, flipBottom,
					scale, sheet);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ 2 * modifier, (xTile + 1) + (yTile + 2) * 32, colour,
					flipBottom, scale, sheet);
		} else {
			
			int xOff2 = 0;
			if (movingDir == 2) {
				xOff2 = -16;
			}
			
			// Upper body
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, colour, flipTop, scale, sheet);

			// Upper body
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, colour, flipTop, scale, sheet);
			// Upper body
			screen.render(xOffset + xOff2 + 2 * modifier - (modifier * flipTop),
					yOffset, (xTile + 2) + yTile * 32, colour, flipTop, scale,
					sheet);

			// Middle Body
			screen.render(xOffset + (modifier * flipMiddle),
					yOffset + modifier, xTile + (yTile + 1) * 32, colour,
					flipBottom, scale, sheet);

			// Middle Body
			screen.render(xOffset + modifier - (modifier * flipMiddle), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, colour,
					flipBottom, scale, sheet);

			// Middle Body
			screen.render(xOffset + xOff2 + 2 * modifier - (modifier * flipMiddle),
					yOffset + modifier, (xTile + 2) + (yTile + 1) * 32, colour,
					flipBottom, scale, sheet);

			// Lower Body
			screen.render(xOffset + (modifier * flipBottom), yOffset + 2
					* modifier, xTile + (yTile + 2) * 32, colour, flipBottom,
					scale, sheet);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ 2 * modifier, (xTile + 1) + (yTile + 2) * 32, colour,
					flipBottom, scale, sheet);

			// Lower Body
			screen.render(xOffset + xOff2 + 2 * modifier - (modifier * flipBottom),
					yOffset + 2 * modifier, (xTile + 2) + (yTile + 2) * 32,
					colour, flipBottom, scale, sheet);
		}

		if (!cooldown) {
			if (mob == null) {
				return;
			}
			isShooting = true;

		}

	}

}
