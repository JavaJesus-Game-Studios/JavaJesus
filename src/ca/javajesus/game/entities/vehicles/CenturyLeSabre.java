package ca.javajesus.game.entities.vehicles;

import java.awt.Rectangle;

import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class CenturyLeSabre extends Vehicle {

	public CenturyLeSabre(Level level, String name, double x, double y) {
		super(level, name, x, y, 2, 24, 32, SpriteSheet.vehicles, 200);
		getColor();
		this.hitBox = new Rectangle(width, height);
		this.bar = new HealthBar(level, 0 + 2 * 32, this.x, this.y, this, 16);
		if (level != null)
			level.addEntity(bar);
	}

	private void getColor() {
		switch (random.nextInt(8)) {
		case 0: {
			// red color
			color = Colors.get(-1, 111, Colors.fromHex("#ff0000"),
					Colors.fromHex("#c2feff"));
			break;
		}
		case 1: {
			// gold color
			color = Colors.get(-1, 111, Colors.fromHex("#CFB53B"),
					Colors.fromHex("#c2feff"));
			break;
		}

		case 2: {
			// blue color
			color = Colors.get(-1, 111, Colors.fromHex("#005aff"),
					Colors.fromHex("#c2feff"));
			break;
		}
		case 3: {
			// silver color
			color = Colors.get(-1, 111, Colors.fromHex("#CCCCCC"),
					Colors.fromHex("#c2feff"));
			break;
		}
		case 4: {
			// black color
			color = Colors.get(-1, 111, 000, Colors.fromHex("#c2efeff"));
			break;
		}
		case 5: {
			// green color
			color = Colors.get(-1, 111, Colors.fromHex("#066518"),
					Colors.fromHex("#c2efeff"));
			break;
		}
		case 6: {
			// purple color
			color = Colors.get(-1, 111, Colors.fromHex("#580271"),
					Colors.fromHex("#c2efeff"));
			break;
		}

		default: {
			// White
			color = Colors.get(-1, 111, 555, Colors.fromHex("#c2feff"));
			break;
		}
		}
	}

	public void render(Screen screen) {
		

		if (movingDir == 0 || movingDir == 1) {
			this.width = 32;
			this.height = 40;
			this.hitBox.setSize(width, height);
			this.hitBox.setLocation((int) this.x - width / 2 + 6, (int) this.y
					- height / 2 + 12);
		} else {
			this.width = 40;
			this.height = 32;
			this.hitBox.setSize(width, height);
			this.hitBox.setLocation((int) this.x - width / 2 + 10, (int) this.y
					- height / 2 + 7);
		}

		int xTile = 0;
		int yTile = 0;

		int flipTop = 0;
		int flipBottom = 0;
		int modifier = 8 * scale;
		double xOffset = x - modifier / 2.0;
		double yOffset = y - modifier / 2.0 - 4;

		if (movingDir == 0) {
			xTile += 14;
		} else if (movingDir == 2) {
			xTile += 9;
		} else if (movingDir == 3) {
			xTile += 4;
		}
		
		if (isDead) {
			xTile += 18;
		}

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
		screen.render(xOffset + 3 * modifier - (modifier * flipTop), yOffset,
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
		screen.render(xOffset + (modifier * flipBottom),
				yOffset + 2 * modifier, xTile + (yTile + 2) * 32, color,
				flipBottom, scale, sheet);

		// Third Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + 2
				* modifier, (xTile + 1) + (yTile + 2) * 32, color, flipBottom,
				scale, sheet);

		// Third Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipBottom), yOffset
				+ 2 * modifier, (xTile + 2) + (yTile + 2) * 32, color,
				flipBottom, scale, sheet);

		// Third Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flipBottom), yOffset
				+ 2 * modifier, (xTile + 3) + (yTile + 2) * 32, color,
				flipBottom, scale, sheet);

		// Fourth Body 1
		screen.render(xOffset + (modifier * flipBottom),
				yOffset + 3 * modifier, xTile + (yTile + 3) * 32, color,
				flipBottom, scale, sheet);

		// Fourth Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + 3
				* modifier, (xTile + 1) + (yTile + 3) * 32, color, flipBottom,
				scale, sheet);

		// Fourth Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipBottom), yOffset
				+ 3 * modifier, (xTile + 2) + (yTile + 3) * 32, color,
				flipBottom, scale, sheet);

		// Fourth Body 4
		screen.render(xOffset + 3 * modifier - (modifier * flipBottom), yOffset
				+ 3 * modifier, (xTile + 3) + (yTile + 3) * 32, color,
				flipBottom, scale, sheet);

		if (movingDir == 0 || movingDir == 1) {
			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom), yOffset + 4
					* modifier, xTile + (yTile + 4) * 32, color, flipBottom,
					scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ 4 * modifier, (xTile + 1) + (yTile + 4) * 32, color,
					flipBottom, scale, sheet);

			// Lower Body 3
			screen.render(xOffset + 2 * modifier - (modifier * flipBottom),
					yOffset + 4 * modifier, (xTile + 2) + (yTile + 4) * 32,
					color, flipBottom, scale, sheet);

			// Lower Body 4
			screen.render(xOffset + 3 * modifier - (modifier * flipBottom),
					yOffset + 4 * modifier, (xTile + 3) + (yTile + 4) * 32,
					color, flipBottom, scale, sheet);

		} else {
			// Upper Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flipTop),
					yOffset, (xTile + 4) + yTile * 32, color, flipTop, scale,
					sheet);
			// Second Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flipBottom),
					yOffset + modifier, (xTile + 4) + (yTile + 1) * 32, color,
					flipBottom, scale, sheet);
			// Third Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flipBottom),
					yOffset + 2 * modifier, (xTile + 4) + (yTile + 2) * 32,
					color, flipBottom, scale, sheet);
			// Fourth Body 5
			screen.render(xOffset + 4 * modifier - (modifier * flipBottom),
					yOffset + 3 * modifier, (xTile + 4) + (yTile + 3) * 32,
					color, flipBottom, scale, sheet);

		}

	}

}
