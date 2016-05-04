package game.entities.vehicles;

import java.util.Random;

import game.Game;
import game.entities.Mob;
import game.entities.Player;
import game.entities.npcs.NPC;
import game.entities.particles.HealthBar;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import level.Level;
import utility.Direction;

/*
 * A horse is a npc that can be ridden
 */
public class Horse extends NPC implements Ridable {

	private static final long serialVersionUID = -3649878712351708546L;

	// the player that is on the horse
	private Player player;

	// size of each box
	private static final int UNIT_SIZE = 8;

	// used for offsetting the bounds based on direction
	private static final int SHORT_SIDE = 16, LONG_SIDE = 24;

	// the xTile offset when not used
	private static final int NOT_USED_XTILE = 14;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	public Horse(Level level, int x, int y, int defaultHealth, String walkPath, int walkDistance, int yTile) {
		super(level, "Horse", x, y, 1, SHORT_SIDE, LONG_SIDE, defaultHealth, Game.player.getColor(), NOT_USED_XTILE,
				yTile, walkPath, walkDistance);
		setSpriteSheet(SpriteSheet.horses);

	}

	/**
	 * Moves a horse on the level
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	public void move(int dx, int dy) {

		// standing upright
		if (isLongitudinal()) {

			setBounds(getX(), getY(), SHORT_SIDE, LONG_SIDE);
			setOuterBounds(SHORT_SIDE, LONG_SIDE);

			// standing sideways
		} else {
			setBounds(getX(), getY(), LONG_SIDE, LONG_SIDE);
			setOuterBounds(LONG_SIDE, LONG_SIDE);
		}

		super.move(dx, dy);
	}

	/**
	 * Displays the horse on the screen
	 */
	public void render(Screen screen) {
		super.render(screen);
		int modifier = 8 * scale;
		int xOffset = (x - 2 * modifier);
		int yOffset = (y - 2 * modifier);

		int xTile = this.xTile;
		int walkingSpeed = 3;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 14;
		}

		if (getDirection() == Direction.SOUTH) {
			xTile += 2;
		} else if (isLatitudinal(getDirection())) {
			xTile += 4 + flip * 4;
			if (getDirection() == Direction.WEST) {
				flip = 1;
			} else {
				flip = 0;
			}
		}

		if (isDead) {
			if (isLongitudinal(getDirection())) {
				setDirection(Direction.WEST);
			}
			xTile = 14;
		}

		if (isLongitudinal(getDirection())) {
			// Upper body
			screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile * sheet.boxes, color, flip, scale, sheet);

			// Upper body
			screen.render(xOffset + modifier - (modifier * flip), yOffset, (xTile + 1) + yTile * sheet.boxes, color,
					flip, scale, sheet);

			// Middle Body
			screen.render(xOffset + (modifier * flip), yOffset + modifier, xTile + (yTile + 1) * sheet.boxes, color,
					flip, scale, sheet);

			// Middle Body
			screen.render(xOffset + modifier - (modifier * flip), yOffset + modifier,
					(xTile + 1) + (yTile + 1) * sheet.boxes, color, flip, scale, sheet);

			// Lower Body
			screen.render(xOffset + (modifier * flip), yOffset + 2 * modifier, xTile + (yTile + 2) * sheet.boxes, color,
					flip, scale, sheet);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * flip), yOffset + 2 * modifier,
					(xTile + 1) + (yTile + 2) * sheet.boxes, color, flip, scale, sheet);
		} else {

			for (int i = 0; i < 3; i++) {
				screen.render(xOffset + (3 * modifier * flip), yOffset + (modifier * i),
						xTile + (yTile + i) * sheet.boxes, color, flip, scale, sheet);

				screen.render(xOffset + modifier + (modifier * flip), yOffset + (modifier * i),
						(xTile + 1) + (yTile + i) * sheet.boxes, color, flip, scale, sheet);

				screen.render(xOffset + 2 * modifier - (modifier * flip), yOffset + (modifier * i),
						(xTile + 2) + (yTile + i) * sheet.boxes, color, flip, scale, sheet);

				screen.render(xOffset + 3 * modifier - (3 * modifier * flip), yOffset + (modifier * i),
						(xTile + 3) + (yTile + i) * sheet.boxes, color, flip, scale, sheet);
			}
		}

	}

	@Override
	public void drive(Player player) {
		this.player = player;
		xTile = 0;
	}

	@Override
	public void exit() {
		player = null;
		xTile = NOT_USED_XTILE;
	}

	@Override
	public boolean isUsed() {
		return player != null;
	}

}
