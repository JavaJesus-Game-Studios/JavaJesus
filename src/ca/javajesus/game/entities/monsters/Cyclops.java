package ca.javajesus.game.entities.monsters;

import java.awt.Color;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Cyclops extends Monster {

	public Cyclops(Level level, double x, double y) {
		super(level, "Cyclops", x, y, 1, 32, 48, 14, 300, Colors.get(-1, 111,
				222, 333));
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

		if (hasDied)
			return;

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
			move(xa, ya, scaledSpeed);
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
			xTile = 20;
		}
		if (movingDir == 1) {
			xTile = 4;
		} else if (movingDir > 1) {
			xTile = 8 + ((numSteps >> walkingSpeed) & 1) * 4;
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

		// if (hasDied)
		// xTile = 12;

		for (int i = 0; i < 6; i++) {
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
		}

	}

	public void speak(Player player) {
		ChatHandler.sendMessage("I'm gonna kill you fool!", Color.black);
		return;
	}

}
