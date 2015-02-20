package ca.javajesus.game.entities.npcs;

import java.awt.geom.Ellipse2D;

import quests.SampleQuest;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Jesus extends NPC {

	public Jesus(Level level, double x, double y, String walkPath,
			int walkDistance) {
		super(level, "Jesus", x, y, 1, 16, 16, 9000, Colors.get(-1, 111, 555,
				Colors.fromHex("#ffd89b")), 0, 6, walkPath, walkDistance, 0);
		this.walkRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		
		this.addQuest(new SampleQuest(this));
		this.currentQuest = quests.get(0);

	}

	public void render(Screen screen) {
		this.hitBox.setLocation((int) this.x - 8, (int) this.y - 8);
		this.standBox.setLocation((int) this.x - 10, (int) this.y - 10);
		int xTile = this.xTile;
		int yTile = this.yTile;

		int walkingAnimationSpeed = 4;

		int flipTop = (numSteps >> walkingAnimationSpeed) & 1;
		int flipBottom = (numSteps >> walkingAnimationSpeed) & 1;

		if (movingDir == 0) {
			xTile += 10;
			if (!isMoving) {
				xTile = 8;
			}
		}
		if (movingDir == 1) {
			xTile += 2;
			if (!isMoving) {
				xTile = 0;
			}
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingAnimationSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
			if (!isMoving) {
				xTile = 4;
			}
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
