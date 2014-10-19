package ca.javajesus.game.entities;

import ca.javajesus.game.InputHandler;
import ca.javajesus.game.gfx.Colours;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Player extends Mob {

	private InputHandler input;
	private int colour = Colours.get(-1, 111, 300, 543);
	private int scale = 1;
	protected boolean isSwimming = false;
	protected boolean isSwinging = false;
	private int tickCount = 0;
	private boolean changeLevel;
	private double scaledSpeed;
	private int swingTick = 0;
	private int swingTickCount = 0;
	Projectile bullet;
	private boolean cooldown = true;
	private boolean tempCooldown;

	public Player(Level level, double x, double y, InputHandler input) {
		super(level, "player", x, y, 1, 16, 16, SpriteSheet.player, 100);
		this.input = input;
	}

	public double getPlayerVelocity() {
		return velocity;
	}

	public Level getLevel() {
		if (level == null) {
			return new Level("/levels/water_test_level.png");
		}
		return level;
	}

	public void tick() {
		int xa = 0;
		int ya = 0;
		if (input.space.isPressed()) {
			isSwinging = true;
		}
		if (input.t.isPressed()) {
			if (!tempCooldown) {
				level.addEntity(new Demon(level, "Demon", (int) this.x,
						(int) this.y, 1, this));
			}
			tempCooldown = true;
		}
		if (input.r.isPressed()) {
			changeLevel = true;
		}
		if (input.up.isPressed()) {
			ya--;
		}
		if (input.down.isPressed()) {
			ya++;
		}
		if (input.left.isPressed()) {
			xa--;
		}
		if (input.right.isPressed()) {
			xa++;
		}
		if (isSwimming) {
			scaledSpeed = 0.35;
		} else if (input.shift.isPressed()) {
			scaledSpeed = 3;
		} else {
			scaledSpeed = 1;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya, scaledSpeed);
			isMoving = true;
		} else {
			isMoving = false;
		}
		int xx = (int) x;
		int yy = (int) y;
		if (level.getTile(xx >> 3, yy >> 3).getId() == 3) {
			isSwimming = true;
		}
		if (isSwimming && level.getTile(xx >> 3, yy >> 3).getId() != 3) {
			isSwimming = false;
		}
		tickCount++;
		if (isSwinging) {
			swingTickCount++;
		}
		if (swingTickCount % 60 <= 15) {
			swingTick = 0;
		} else {
			swingTick = 1;
		}

		if (tickCount % 10 == 0) {
			cooldown = false;
		} else {
			cooldown = true;
		}
		if (tempCooldown) {
			if (tickCount % 100 == 0) {
				tempCooldown = false;
			}
		}

	}

	public void render(Screen screen) {

		this.hitBox.setLocation((int) this.x, (int) this.y);
		if (changeLevel) {
			level.remEntity(this);
			init(screen.getGame().randomLevel);
			screen.getGame().updateLevel();
			changeLevel = false;
			level.addEntity(this);
		}

		int xTile = 0;
		int yTile = 0;
		int walkingSpeed = 4;
		if (scaledSpeed == 3) {
			numSteps++;
		}

		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		int flipSword1 = (swingTick >> walkingSpeed) & 1;
		int flipSword2 = (swingTick >> walkingSpeed) & 1;
		int swingModifier = 0;

		if (movingDir == 0) {
			xTile += 10;
		}
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}

		if (movingDir == 2) {
			flipSword1 = (movingDir - 1) % 2;
			flipSword2 = (movingDir - 1) % 2;
		}

		if (swingTick == 1) {
			swingModifier = 3;
		}

		int modifier = 8 * scale;
		double xOffset = x - modifier / 2.0;
		double yOffset = y - modifier / 2.0 - 4;
		if (isSwimming) {
			int waterColour = 0;
			yOffset += 4;
			if (tickCount % 60 < 15) {
				waterColour = Colours.get(-1, 225, -1, -1);
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				waterColour = Colours.get(-1, 115, 225, -1);
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColour = Colours.get(-1, 115, -1, -1);
			} else {
				yOffset -= 1;
				waterColour = Colours.get(-1, 225, 225, -1);
			}
			screen.render(xOffset, yOffset + 3, 0 + 8 * 32, waterColour, 0x00,
					1, sheet);
			screen.render(xOffset + 8, yOffset + 3, 0 + 8 * 32, waterColour,
					0x01, 1, sheet);
		}
		if (!isSwinging) {
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, colour, flipTop, scale, sheet);// upper body
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, colour, flipTop, scale, sheet);// upper
																				// body
			if (!isSwimming) {
				screen.render(xOffset + (modifier * flipBottom), yOffset
						+ modifier, xTile + (yTile + 1) * 32, colour,
						flipBottom, scale, sheet);// lower body
				screen.render(xOffset + modifier - (modifier * flipBottom),
						yOffset + modifier, (xTile + 1) + (yTile + 1) * 32,
						colour, flipBottom, scale, sheet);// lower body

			}
		}
		if (isSwinging) {
			xTile = 0;
			yTile = 0;

			// Upper Body 1
			screen.render(xOffset + (modifier * flipSword1), yOffset, xTile
					+ (yTile + 4) * 32, colour, flipSword1, scale, sheet);

			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flipSword1),
					yOffset, (xTile + 1 + swingModifier) + (yTile + 4) * 32,
					colour, flipSword1, scale, sheet);

			// Upper Body 3
			screen.render(xOffset + 2 * modifier - 3 * (modifier * flipSword1),
					yOffset, (xTile + 2 + swingModifier) + (yTile + 4) * 32,
					colour, flipSword1, scale, sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipSword2),
					yOffset + modifier, xTile + (yTile + 5) * 32, colour,
					flipSword2, scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipSword2), yOffset
					+ modifier, (xTile + 1 + swingModifier) + (yTile + 5) * 32,
					colour, flipSword2, scale, sheet);

			if (movingDir == 2) {
				xOffset -= 2 * modifier;
			}
			// Lower Body 3
			screen.render(xOffset + 2 * modifier - (modifier * flipSword2),
					yOffset + modifier, (xTile + 2 + swingModifier)
							+ (yTile + 5) * 32, colour, flipSword2, scale,
					sheet);
			if (!cooldown) {
				bullet = new Projectile(level, 0, colour, this.x, this.y, 2,
						movingDir);
				level.addEntity(bullet);
			}

			if (swingTick == 1 && swingTickCount % 60 == 29) {
				isSwinging = false;
				swingTickCount = 0;
				if (bullet != null) {
					level.remEntity(bullet);
				}
			}

		}
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
}
