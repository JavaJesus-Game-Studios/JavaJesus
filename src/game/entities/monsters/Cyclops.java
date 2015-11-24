package game.entities.monsters;

import level.Level;
import utility.Direction;
import game.Game;
import game.entities.particles.HealthBar;
import game.graphics.Screen;

public class Cyclops extends Monster {
	
	private static final long serialVersionUID = -6014297804180801819L;
	
	protected boolean isAttacking = false;
	private int coolTicks = 0;

	public Cyclops(Level level, int x, int y) {
		super(level, "Cyclops", x, y, 1, 32, 48, 14, 5000, new int[] {
				0xFF111111, 0xFFFFD99C, 0xFFFFFFFF });
		this.bar = new HealthBar(level, x, y, this);
		level.addEntity(bar);
		this.strength = 20;
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
			//sound.play(SoundHandler.sound.chimpanzee);
		}

		if (mob != null && !cooldown
				&& this.getOuterBounds().intersects(mob.getBounds())) {
			isAttacking = true;
			cooldown = true;
			mob.damage(this.strength);
		}

		if (isAttacking) {
			shootTickCount++;
			if (shootTickCount % 40 == 0) {
				shootTickCount = 0;
				isAttacking = false;
			}
		}

		if (cooldown) {
			coolTicks++;
			if (coolTicks % 100 == 0) {
				coolTicks = 0;
				cooldown = false;
			}
		}

		int xa = 0;
		int ya = 0;

		if (mob != null && !isAttacking
				&& this.aggroRadius.intersects(mob.getBounds())
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

	}

	public void render(Screen screen) {
		super.render(screen);
		this.getBounds().setLocation((int) this.x - 16, (int) this.y - 24);
		this.getOuterBounds().setLocation((int) this.x - 18, (int) this.y - 26);

		int xTile = 0;
		int walkingSpeed = 4;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile = 24;
			if (flip == 1) {
				xTile += 4;
				flip = 0;
			}
		}
		if (getDirection() == Direction.SOUTH) {
			xTile = 4;
			if (flip == 1) {
				xTile += 4;
				flip = 0;
			}
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
		
		if (isAttacking) {
			yTile += 6;
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
