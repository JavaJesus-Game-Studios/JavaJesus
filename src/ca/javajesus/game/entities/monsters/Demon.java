package ca.javajesus.game.entities.monsters;

import ca.javajesus.game.Game;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.entities.projectiles.FireBall;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Demon extends Monster {
	private static int[] color = { 0xFF111111, 0xFF700000, 0xFFDBA800 };

	public Demon(Level level, String name, int x, int y, int speed) {
		super(level, name, x, y, speed, 14, 24, 0, 150, color);
		this.bar = new HealthBar(level, 32, this.x, this.y, this, 8);
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

		if (this.aggroRadius.intersects(Game.player.getBounds())
				&& random.nextInt(400) == 0) {
			sound.play(SoundHandler.sound.demon);
		}
		if (tickCount % 100 == 0) {
			cooldown = false;
		} else {
			cooldown = true;
		}

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
				&& !this.getOuterBounds().intersects(mob.getBounds())) {

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

		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			setMoving(true);
			move(xa, ya);
		} else {
			setMoving(false);
		}

		if (!cooldown && mob != null) {
			isShooting = true;
			level.addEntity(new FireBall(level, this.x + 5, (this.y - 7), mob
					.getX(), mob.getY(), this));
		}

	}

	public void render(Screen screen) {
		super.render(screen);
		if (!isDead) {
			this.getBounds().setLocation((int) this.x - 7, (int) this.y - 12);
			this.getOuterBounds().setLocation((int) this.x - 9,
					(int) this.y - 14);
		}
		int xTile = 0;
		int walkingSpeed = 4;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 10;
		}
		if (getDirection() == Direction.SOUTH) {
			xTile += 2;
		} else if (isLatitudinal(getDirection())) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			if (getDirection() == Direction.WEST) {
				flip = 1;
				flip = 1;
				flip = 1;
			} else {
				flip = 0;
				flip = 0;
				flip = 0;
			}
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier;
		int yOffset = y - modifier - modifier / 2;

		if (isShooting)
			xTile += 12;

		if (isDead) {
			if (isLongitudinal(getDirection())) {
				setDirection(Direction.WEST);
			}
			xTile = 24;
		}

		if (!isDead) {

			// Upper body 1
			screen.render(xOffset + (modifier * flip), yOffset, xTile
					+ yTile * sheet.boxes, color, flip, scale, sheet);

			// Upper body 2
			screen.render(xOffset + modifier - (modifier * flip), yOffset,
					(xTile + 1) + yTile * sheet.boxes, color, flip, scale,
					sheet);
		}

		// Middle Body 1
		screen.render(xOffset + (modifier * flip), yOffset + modifier,
				xTile + (yTile + 1) * sheet.boxes, color, flip, scale,
				sheet);

		// Middle Body 2
		screen.render(xOffset + modifier - (modifier * flip), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color,
				flip, scale, sheet);

		// Lower Body 1
		screen.render(xOffset + (modifier * flip),
				yOffset + 2 * modifier, xTile + (yTile + 2) * sheet.boxes,
				color, flip, scale, sheet);

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * flip), yOffset + 2
				* modifier, (xTile + 1) + (yTile + 2) * sheet.boxes, color,
				flip, scale, sheet);

		if (isDead) {

			int offset = 0;

			if (getDirection() == Direction.WEST)
				offset = -16;

			// Middle Body 3
			screen.render(xOffset + offset + 2 * modifier
					- (modifier * flip), yOffset + modifier, (xTile + 2)
					+ (yTile + 1) * sheet.boxes, color, flip, scale,
					sheet);

			// Lower Body 3
			screen.render(xOffset + offset + 2 * modifier
					- (modifier * flip), yOffset + 2 * modifier,
					(xTile + 2) + (yTile + 2) * sheet.boxes, color, flip,
					scale, sheet);

		}

	}

}
