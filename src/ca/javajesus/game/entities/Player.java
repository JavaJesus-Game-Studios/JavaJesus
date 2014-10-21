package ca.javajesus.game.entities;

import ca.javajesus.game.InputHandler;
import ca.javajesus.game.gfx.Colours;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Player extends Mob {

	private InputHandler input;
	private int colour = Colours.get(-1, 111, 300, 543);
	private int bulletColour = Colours.get(-1, -1, -1, 550);
	private int scale = 1;
	protected boolean isSwimming = false;
	protected boolean isSwinging = false;
	protected boolean isShooting = false;
	private int tickCount = 0;
	private boolean changeLevel;
	private double scaledSpeed;
	private int swingTick = 0;
	private int swingTickCount = 0;
	Projectile bullet;
	HealthBar bar;
	private boolean cooldown = true;
	private boolean demonCooldown;
	private final int GUN_COOLDOWN = 30;
	protected double health = 100;

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
		if (input.f.isPressed()) {
			if (!isSwinging) isShooting = true;
		}
		if (input.space.isPressed()) {
			if (!isShooting) isSwinging = true;
		}
		if (input.t.isPressed()) {
			if (!demonCooldown) {
				level.addEntity(new Demon(level, "Demon", (int) this.x,
						(int) this.y, 1, this));
			}
			demonCooldown = true;
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
		if (isSwinging || isShooting) {
			swingTickCount++;
		}
		if (swingTickCount % 60 <= 15 && isSwinging) {
			swingTick = 0;
		} else if (swingTickCount % GUN_COOLDOWN <= 5 && isShooting) {
			swingTick = 0;
		} else {
			swingTick = 1;
		}

		if (tickCount % 10 == 0 && isSwinging) {
			cooldown = false;
		} else if (tickCount % GUN_COOLDOWN == 0 && isShooting) {
			cooldown = false;
		} else {
			cooldown = true;
		}
		if (demonCooldown) {
			if (tickCount % 100 == 0) {
				demonCooldown = false;
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

		int flipAttack1 = (swingTick >> walkingSpeed) & 1;
		int flipAttack2 = (swingTick >> walkingSpeed) & 1;

		int swingModifier = 0;
		int shootModifier = 0;

		if (movingDir == 0) {
			xTile += 10;
		}
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir -1) % 2;
		}

		if (movingDir == 2) {
			flipAttack1 = (movingDir - 1) % 2;
			flipAttack2 = (movingDir - 1) % 2;
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
		// Not Attacking Anything
		if (!(isSwinging || isShooting)) {
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
		if (isShooting && !isSwimming) {
			xTile = 0;
			yTile = 0;

			// Above Body Health Bar
			if((this.health>80)&&(this.health<=100))
			{
			bar = new HealthBar(level, 64, colour, this.x, this.y);
			}
			else if((this.health>60)&&(this.health<=80))
		    {
	        bar = new HealthBar(level, 65, colour, this.x, this.y);
	        }
			else if((this.health>40)&&(this.health<=60))
            {
            bar = new HealthBar(level, 66, colour, this.x, this.y);
            }
			else if((this.health>20)&&(this.health<=40))
            {
            bar = new HealthBar(level, 67, colour, this.x, this.y);
            }
			else
            {
            bar = new HealthBar(level, 68, colour, this.x, this.y);
            }
			
			level.addEntity(bar);
			
			// Upper Body 1
			screen.render(xOffset + (modifier * flipAttack1), yOffset, xTile
					+ (yTile + 2) * 32, colour, flipAttack1, scale, sheet);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flipAttack1), yOffset,
					(xTile + 1 + shootModifier) + (yTile + 2) * 32, colour,
					flipAttack1, scale, sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipAttack2), yOffset + modifier,
					xTile + (yTile + 3) * 32, colour, flipAttack2, scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipAttack2), yOffset
					+ modifier, (xTile + 1 + shootModifier) + (yTile + 3) * 32,
					colour, flipAttack2, scale, sheet);

			if (!cooldown) {
				int bulletOffset = 0;
				if (movingDir == 2) {
					bulletOffset = -12;
				}
				bullet = new Projectile(level, 1, bulletColour,
						(this.x + 7 + bulletOffset), (this.y - 5), 2, movingDir);
				level.addEntity(bullet);
			}

			if (swingTickCount % 60 == 5) {
				isShooting = false;
				swingTickCount = 0;
			}

		}
		if (isSwinging && !isSwimming) {
			xTile = 0;
			yTile = 0;

			// Upper Body 1
			screen.render(xOffset + (modifier * flipAttack1), yOffset, xTile
					+ (yTile + 4) * 32, colour, flipAttack1, scale, sheet);

			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flipAttack1),
					yOffset, (xTile + 1 + swingModifier) + (yTile + 4) * 32,
					colour, flipAttack1, scale, sheet);

			// Upper Body 3
			screen.render(xOffset + 2 * modifier - 3 * (modifier * flipAttack1),
					yOffset, (xTile + 2 + swingModifier) + (yTile + 4) * 32,
					colour, flipAttack1, scale, sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipAttack2),
					yOffset + modifier, xTile + (yTile + 5) * 32, colour,
					flipAttack2, scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipAttack2), yOffset
					+ modifier, (xTile + 1 + swingModifier) + (yTile + 5) * 32,
					colour, flipAttack2, scale, sheet);

			if (movingDir == 2) {
				xOffset -= 2 * modifier;
			}
			// Lower Body 3
			screen.render(xOffset + 2 * modifier - (modifier * flipAttack2),
					yOffset + modifier, (xTile + 2 + swingModifier)
							+ (yTile + 5) * 32, colour, flipAttack2, scale,
					sheet);
		}
		
		if (swingTickCount % 60 == 30) {
			isSwinging = false;
			swingTickCount = 0;
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
