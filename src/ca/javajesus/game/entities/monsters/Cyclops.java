package ca.javajesus.game.entities.monsters;

import java.awt.Color;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.JJFont;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Cyclops extends Monster {

	public Cyclops(Level level, double x, double y) {
		super(level, "Cyclops", x, y, 1, 32, 48, 14, 50, Colors.get(-1, 111,
				Colors.fromHex("#ffd99c"), Colors.fromHex("#ffffff")));
		this.bar = new HealthBar(level, 0, x, y, this, 0);
		level.addEntity(bar);
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 32;
		int yMin = 15;
		int yMax = 47;
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

		if (isDead)
			return;
		
		if (isHit) {
			isHitTicks++;
			if (isHitTicks > 20) {
				isHitTicks = 0;
				isHit = false;
			}
		}
		
		if (isTalking) {
			talkCount++;
			if (talkCount > 350) {
				talkCount = 0;
				isTalking = false;
			}
		}

		checkRadius();

		if (isMobCollision()) {
			moveAroundMobCollision();
			return;
		}
		int xa = 0;
		int ya = 0;
		if (mob != null && !this.standBox.intersects(mob.hitBox)) {

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

		this.hitBox.setLocation((int) this.x - 16, (int) this.y - 24);
		this.standBox.setLocation((int) this.x - 18, (int) this.y - 26);

		int xTile = 0;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		if (movingDir == 0) {
			xTile = 28;
		}
		if (movingDir == 1) {
			xTile = 8;
		} else if (movingDir > 1) {
			xTile = 12 + ((numSteps >> walkingSpeed) & 1) * 4;
			if (movingDir == 2) {
				flipTop = (movingDir - 1) % 1 + 1;
				flipBottom = (movingDir - 1) % 1 + 1;
			} else {
				flipTop = (movingDir - 1) % 1;
				flipBottom = (movingDir - 1) % 1;
			}
		}

		int modifier = 8 * scale;
		double xOffset = x - modifier / 2;
		double yOffset = (y - modifier / 2 - 4) - modifier;

		int yTile = this.yTile;
		if (isDead) {
			xTile = 0;
			yTile = 26;
		}

		for (int i = 0; i < 6; i++) {
			
			if (isDead && i > 1) {
				break;
			}
			
			screen.render(xOffset + (modifier * flipTop * 3), yOffset + i
					* modifier, xTile + (yTile + i) * 32, color, flipTop,
					scale, sheet);

			screen.render(xOffset + modifier + (modifier * flipTop), yOffset
					+ i * modifier, (xTile + 1) + (yTile + i) * 32, color,
					flipTop, scale, sheet);

			screen.render(xOffset + 2 * modifier - (modifier * flipTop),
					yOffset + i * modifier, (xTile + 2) + (yTile + i) * 32,
					color, flipBottom, scale, sheet);

			screen.render(xOffset + 3 * modifier - (modifier * flipTop * 3),
					yOffset + i * modifier, (xTile + 3) + (yTile + i) * 32,
					color, flipBottom, scale, sheet);
			
			if (isDead) {
				screen.render(xOffset + 4 * modifier - (modifier * flipTop * 3),
						yOffset + i * modifier, (xTile + 4) + (yTile + i) * 32,
						color, flipBottom, scale, sheet);

				screen.render(xOffset + 5 * modifier - (modifier * flipTop),
						yOffset + i * modifier, (xTile + 5) + (yTile + i) * 32,
						color, flipBottom, scale, sheet);
			}
		}
		
		if (isTalking) {
			JJFont.render(name, screen, (int) xOffset
					- ((name.length() - 1) / 2 * 8), (int) yOffset - 10, Colors.get(-1, -1, -1, Colors.fromHex("#FFCC00")),
					1);
		}
		
		if (isHit) {
			JJFont.render(damageTaken, screen, (int) xOffset + random.nextInt(10) - 5, (int) yOffset - 10 + random.nextInt(6) - 3,
					Colors.get(-1, -1, -1, random.nextInt(200)), 1);
		}

	}

	public void speak(Player player) {
		isTalking = true;
		ChatHandler.sendMessage("I'm gonna kill you fool!", Color.white);
		return;
	}

}
