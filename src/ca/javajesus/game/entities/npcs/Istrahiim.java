package ca.javajesus.game.entities.npcs;

import java.awt.Color;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.Game;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.Mob.Direction;
import ca.javajesus.game.graphics.JJFont;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class Istrahiim extends NPC {

	public Istrahiim(Level level, int x, int y, String walkPath,
			int walkDistance) {
		super(level, "Istrahiim", x, y, 1, 16, 16, 200, null, 0, 25, walkPath,
				walkDistance);
	}

	public Istrahiim(Level level, int x, int y) {
		super(level, "Istrahiim", x, y, 1, 16, 16, 200, null, 0, 25, "", 0);
	}

	public void speak(Player player) {
		isTalking = true;
		ChatHandler.sendMessage(name
				+ ": Hello, I am not an owl. My Name is Istrahiim.",
				Color.white);
		return;
	}

	public void render(Screen screen) {

		this.getBounds().setLocation(this.x - (this.width / 2),
				this.y - (this.height / 2));
		this.getOuterBounds().setLocation(
				this.x - (int) getBounds().getWidth() / 2 - 2,
				this.y - (int) getBounds().getHeight() / 2 - 2);

		int modifier = 8 * scale;
		int xOffset = x - modifier;
		int yOffset = (y - modifier - modifier / 2);

		if (isSwimming) {
			if (isOnFire()) {
				setOnFire(false);
			}
			int[] waterColor = { 0xFF5A52FF, 0xFF000000, 0xFF000000 };
			yOffset += 4;
			if (tickCount % 60 < 15) {
				waterColor[0] = 0xFF5266FF;
				waterColor[1] = 0xFF000000;
				waterColor[2] = 0xFF000000;
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				waterColor[0] = 0xFF3D54FF;
				waterColor[1] = 0xFF5266FF;
				waterColor[2] = 0xFF000000;
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColor[0] = 0xFF3D54FF;
				waterColor[1] = 0xFF000000;
				waterColor[2] = 0xFF000000;
			} else {
				yOffset -= 1;
				waterColor[0] = 0xFF5266FF;
				waterColor[1] = 0xFF5266FF;
				waterColor[2] = 0xFF000000;
			}
			screen.render(xOffset, yOffset + 3, 0 + 10 * sheet.boxes,
					waterColor, 0x00, 1, sheet);
			screen.render(xOffset + 8, yOffset + 3, 0 + 10 * sheet.boxes,
					waterColor, 0x01, 1, sheet);
		}

		// Handles fire animation
		if (isOnFire()) {
			int[] firecolor = { 0xFFF7790A, 0xFFF72808, 0xFF000000 };

			screen.render(xOffset + 3, yOffset, this.level.fireList.get(0)
					.getXTile() + 15 * sheet.boxes, firecolor, 0, 2,
					SpriteSheet.tiles);

		}

		if (!isDead) {

			if (isTalking) {
				JJFont.render(name, screen, (int) xOffset
						- ((name.length() - 1) / 2 * 8), (int) yOffset - 10,
						new int[] { 0xFF000000, 0xFF000000, 0xFFFFCC00 }, 1);
			}

			if (isHit) {
				JJFont.render(damageTaken, screen, (int) xOffset + isHitX,
						(int) yOffset - 10 + isHitY, isHitColor, 1);
			}
		}

		int xTile = 0;
		int walkingSpeed = 4;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 12 + flip * 2;
			if (!isMoving()) {
				xTile = 10;
			}
			flip = 0;
		} else if (getDirection() == Direction.SOUTH) {
			xTile += 2 + flip * 2;
			if (!isMoving()) {
				xTile = 0;
			}
			flip = 0;
		} else {
			xTile += 6 + flip * 2;
			if (!isMoving()) {
				xTile = 6;
			}
			if (getDirection() == Direction.WEST) {
				flip = 1;
			} else {
				flip = 0;
			}
		}

		if (isDead) {
			xTile = 16;
			int yTile = this.yTile + 1;
			for (int i = 0; i < 2; i++) {

				screen.render(xOffset + (2 * modifier * flip), yOffset
						+ (modifier * i), xTile + (yTile + i) * sheet.boxes,
						color, flip, scale, sheet);

				screen.render(xOffset + modifier, yOffset + (modifier * i),
						(xTile + 1) + (yTile + i) * sheet.boxes, color, flip,
						scale, sheet);

				screen.render(xOffset + 2 * modifier - (2 * modifier * flip),
						yOffset + (modifier * i), (xTile + 2) + (yTile + i)
								* sheet.boxes, color, flip, scale, sheet);
			}
		} else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 2; j++) {
					screen.render(xOffset + (modifier * j), yOffset
							+ (modifier * i), (xTile + j) + (yTile + i)
							* sheet.boxes, color, flip, scale, sheet);
				}
			}
		}
	}

}
