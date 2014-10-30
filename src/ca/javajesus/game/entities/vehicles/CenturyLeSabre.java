package ca.javajesus.game.entities.vehicles;

import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class CenturyLeSabre extends Vehicle {

	protected final int color = Colors.get(-1, 444, 222, 111);
	
	public CenturyLeSabre(Level level, String name, double x, double y,
			int speed, int width, int height, SpriteSheet sheet,
			double defaultHealth) {
		super(level, name, x, y, speed, width, height, sheet, defaultHealth);
	}

	public void render(Screen screen) {
		this.hitBox.setLocation((int) this.x, (int) this.y);
		int xTile = 0;
		int yTile = 0;

		int walkingAnimationSpeed = 4;
		if (scaledSpeed == 3) {
			numSteps++;
		}

		int flipTop = (numSteps >> walkingAnimationSpeed) & 1;
		int flipBottom = (numSteps >> walkingAnimationSpeed) & 1;

		if (movingDir == 0) {
			//xTile += 10;
		}
		if (movingDir == 1) {
			//xTile += 2;
		} else if (movingDir > 1) {
			//xTile += 4 + ((numSteps >> walkingAnimationSpeed) & 1) * 2;
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

		// Upper Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipTop), yOffset,
				(xTile + 2) + yTile * 32, color, flipTop, scale, sheet);

		// Upper Body 4
		screen.render(xOffset + 3 *modifier - (modifier * flipTop), yOffset,
				(xTile + 3) + yTile * 32, color, flipTop, scale, sheet);

		// Second Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier,
				xTile + (yTile + 1) * 32, color, flipBottom, scale, sheet);

		// Second Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom,
				scale, sheet);

		// Second Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 2) + (yTile + 1) * 32, color, flipBottom,
				scale, sheet);

		// Second Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 3) + (yTile + 1) * 32, color, flipBottom,
				scale, sheet);

		// Third Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + 2* modifier,
				xTile + (yTile + 2) * 32, color, flipBottom, scale, sheet);

		// Third Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ 2 * modifier, (xTile + 1) + (yTile + 2) * 32, color, flipBottom,
				scale, sheet);

		// Third Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipBottom), yOffset
				+ 2 * modifier, (xTile + 2) + (yTile + 2) * 32, color, flipBottom,
				scale, sheet);

		// Third Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flipBottom), yOffset
				+ 2 * modifier, (xTile + 3) + (yTile + 2) * 32, color, flipBottom,
				scale, sheet);

		// Fourth Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + 3 * modifier,
				xTile + (yTile + 3) * 32, color, flipBottom, scale, sheet);

		// Fourth Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ 3 * modifier, (xTile + 1) + (yTile + 3) * 32, color, flipBottom,
				scale, sheet);

		// Fourth Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipBottom), yOffset
				+ 3* modifier, (xTile + 2) + (yTile + 3) * 32, color, flipBottom,
				scale, sheet);

		// Fourth Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flipBottom), yOffset
				+ 3 * modifier, (xTile + 3) + (yTile + 3) * 32, color, flipBottom,
				scale, sheet);

		// Lower Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + 4 * modifier,
				xTile + (yTile + 4) * 32, color, flipBottom, scale, sheet);

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ 4 * modifier, (xTile + 1) + (yTile + 4) * 32, color, flipBottom,
				scale, sheet);

		// Lower Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipBottom), yOffset
				+ 4 * modifier, (xTile + 2) + (yTile + 4) * 32, color, flipBottom,
				scale, sheet);

		// Lower Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flipBottom), yOffset
				+ 4 * modifier, (xTile + 3) + (yTile + 4) * 32, color, flipBottom,
				scale, sheet);

	}

}
