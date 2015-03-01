package ca.javajesus.game.entities.monsters;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.entities.projectiles.Bullet;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.JJFont;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class GangMember extends Monster {

	protected Ellipse2D.Double standRange;

	public GangMember(Level level, String name, double x, double y, int speed,
			double health, int type) {
		super(level, name, x, y, speed, 14, 16, 1, health, Colors.get(-1, 111,
				555, 543));
		getType(type);
		standRange = new Ellipse2D.Double(x - RADIUS / 4, y - RADIUS / 4,
				RADIUS / 2, RADIUS / 2);
		this.bar = new HealthBar(level, 0 + 2 * 32, this.x, this.y, this, 0);
		if (level != null)
			level.addEntity(bar);
	}

	private void getType(int type) {
		switch (type) {
		case 0:
			yTile = 3;
			break;
		case 1:
			yTile = 10;
			break;
		default:
			yTile = 3;
			break;
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

	public void tick() {

		if (isDead) {
			isShooting = false;
			return;
		}
		
		if (isTalking) {
			talkCount++;
			if (talkCount > 350) {
				talkCount = 0;
				isTalking = false;
			}
		}

		checkRadius();

		if (isShooting) {
			shootTickCount++;
			if (shootTickCount > 20) {
				shootTickCount = 0;
				isShooting = false;
			}
		}

		if (isMobCollision()) {
			moveAroundMobCollision();
			return;
		}
		int xa = 0;
		int ya = 0;
		if (mob != null && this.aggroRadius.intersects(mob.hitBox)) {
			if (!cooldown) {
				if (mob == null) {
					return;
				}
				isShooting = true;
				level.addEntity(new Bullet(level, this.x + 5, (this.y - 7),
						mob.x, mob.y - 4, this));
			}
			if (!this.standRange.intersects(mob.hitBox)) {

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
			} else {
				if (mob.isMoving) {
					if ((int) mob.x > (int) this.x) {
						xa--;
					}
					if ((int) mob.x < (int) this.x) {
						xa++;
					}
					if ((int) mob.y > (int) this.y) {
						ya--;
					}
					if ((int) mob.y < (int) this.y) {
						ya++;
					}
				}
			}
		}

		tickCount++;

		if (tickCount % 100 == 0) {
			cooldown = false;
		} else {
			cooldown = true;
		}

		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			if (isMobCollision()) {
				isMoving = false;
				return;
			}
			move(xa, ya);
			isMoving = true;
		} else {
			isMoving = false;
		}

	}

	public void render(Screen screen) {

		this.hitBox.setLocation((int) this.x - 7, (int) this.y - 16);
		this.standBox.setLocation((int) this.x - 9, (int) this.y - 18);
		this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
				RADIUS);
		this.standRange.setFrame(x - RADIUS / 4, y - RADIUS / 4, RADIUS / 2,
				RADIUS / 2);
		int xTile = 0;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		if (movingDir == 0) {
			xTile += 10;
		}
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
		}

		int modifier = 8 * scale;
		double xOffset = x - modifier / 2;
		double yOffset = (y - modifier / 2 - 4) - modifier;

		if (isDead)
			xTile = 12;

		if (isShooting) {

			xTile = 14;

			// Upper body 1
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, color, flipTop, scale, sheet);

			// Upper body 2
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, color, flipTop, scale, sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom),
					yOffset + modifier, xTile + (yTile + 1) * 32, color,
					flipBottom, scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, color,
					flipBottom, scale, sheet);
		} else {

			// Upper body
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, color, flipTop, scale, sheet);

			// Upper body
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, color, flipTop, scale, sheet);

			// Lower Body
			screen.render(xOffset + (modifier * flipBottom),
					yOffset + modifier, xTile + (yTile + 1) * 32, color,
					flipBottom, scale, sheet);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, color,
					flipBottom, scale, sheet);
		}
		
		if (isTalking) {
			JJFont.render(name, screen, (int) xOffset
					- ((name.length() - 1) / 2 * 8), (int) yOffset - 10, Colors.get(-1, -1, -1, Colors.fromHex("#FFCC00")),
					1);
		}

	}

	public void speak(Player player) {
		isTalking = true;
		ChatHandler.sendMessage("I'm gonna kill you fool!", Color.white);
		return;
	}

}
