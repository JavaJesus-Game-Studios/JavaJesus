package ca.javajesus.game.entities;

import java.awt.geom.Ellipse2D;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class NPC extends Mob {

	public static NPC npc1 = new NPC(Level.level1, "Knight", 50, 50, 1, 16, 16,
			100, Colors.get(-1, 300, 500, 100), 0, 2);
	public static NPC npc2 = new NPC(Level.level1, "Someone", 50, 100, 1, 16, 16,
			100, Colors.get(-1, 300, 500, 100), 0, 4);
	public static NPC npc3 = new NPC(Level.level1, "Someone1", 50, 150, 1, 16, 16,
			100, Colors.get(-1, 300, 500, 100), 0, 6);
	public static NPC npc4 = new NPC(Level.level1, "Lady?", 50, 200, 1, 16, 16,
			100, Colors.get(-1, 300, 500, 100), 0, 8);

	private double scaledSpeed = 0.35;
	/** Range that the NPC can walk */
	protected Ellipse2D.Double walkRadius;
	protected final int RADIUS = 32 * 8;
	protected int color;
	protected int xTile;
	protected int yTile;

	public NPC(Level level, String name, double x, double y, int speed,
			int width, int height, double defaultHealth, int color, int xTile,
			int yTile) {
		super(level, name, x, y, speed, width, height, SpriteSheet.npcs,
				defaultHealth);
		this.walkRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		this.color = color;
		this.xTile = xTile;
		this.yTile = yTile;
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

		if (isMobCollision()) {
			moveRandomly();
			return;
		}
		int xa = 0;
		int ya = 0;

		if ((int) 200 > (int) this.x) {
			//xa++;
		}
		if ((int) 200 < (int) this.x) {
			//xa--;
		}
		if ((int) 200 > (int) this.y) {
			//ya++;
		}
		if ((int) 200 < (int) this.y) {
			//ya--;
		}

		if (xa != 0 || ya != 0) {
			this.hitBox.setLocation(this.hitBox.x + xa, this.hitBox.y + ya);
			if (isMobCollision()) {
				this.hitBox.setLocation(this.hitBox.x - xa, this.hitBox.y - ya);
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
		this.hitBox.setLocation((int) this.x, (int) this.y);
		int xTile = this.xTile;
		int yTile = this.yTile;

		int walkingAnimationSpeed = 4;
		if (scaledSpeed == 3) {
			numSteps++;
		}

		int flipTop = (numSteps >> walkingAnimationSpeed) & 1;
		int flipBottom = (numSteps >> walkingAnimationSpeed) & 1;

		if (movingDir == 0) {
			xTile += 10;
		}
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingAnimationSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
		}

		int modifier = 8 * scale;
		double xOffset = x - modifier / 2.0;
		double yOffset = y - modifier / 2.0 - 4;

		// Upper body 1
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
				* 32, color, flipTop, scale, sheet);
		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
				(xTile + 1) + yTile * 32, color, flipTop, scale, sheet);

		// Lower Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier,
				xTile + (yTile + 1) * 32, color, flipBottom, scale, sheet);
		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom,
				scale, sheet);

	}

}
