package javajesus.items;

import java.net.URL;

import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.utility.Direction;

/*
 * Handles the player rendering when using the flamethrower
 */
public class Flamethrower extends Gun implements DifferentOffsetItem{

	// unit size on spritesheet
	private static final int UNIT_SIZE = 8;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	/**
	 * Creates a flamethrower, hot!
	 */
	public Flamethrower(String name, int id, int xTile, int yTile, int[] color, String description, int yPlayerSheet,
	        int clipSize, float rate, int reload, int damage, Ammo type, URL clip) {
		super(name, id, xTile, yTile, color, description, yPlayerSheet, clipSize, rate, reload, damage, type, clip);
	}

	/**
	 * Some items require the player to have additional animations that aren't
	 * available normally for the player
	 * 
	 * @param screen - the screen to display it on
	 * @param player - the player to display
	 * @param sheet - the spritesheet to use
	 */
	public void renderPlayer(Screen screen, Player player, SpriteSheet sheet, Direction shootingDir) {

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = player.getX(), yOffset = player.getY();

		// the horizontal/vertical position on the spritesheet
		int xTile = 0, yTile = 12;

		// whether or not to render backwards
		boolean flip = ((player.getNumSteps() >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (shootingDir == Direction.NORTH) {
			xTile = 14 + (flip ? 2 : 0);
			if (!player.isMoving())
				xTile = 12;
			flip = false; // not real mirror image
		} else if (shootingDir == Direction.SOUTH) {
			xTile = 2 + (flip ? 2 : 0);
			if (!player.isMoving())
				xTile = 0;
			flip = false; // not real mirror image
		} else {
			xTile = 6 + (flip && player.isMoving() ? 3 : 0);
			flip = shootingDir == Direction.WEST;
		}

		// 3x3 for east/west
		if (shootingDir == Direction.WEST || shootingDir == Direction.EAST) {

			// iterate top to bottom
			for (int i = 0; i < 2; i++) {

				// left
				screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + i * modifier, xTile, yTile + i, sheet,
				        flip, player.getColor());

				// middle
				screen.render(xOffset + modifier, yOffset + i * modifier, xTile + 1, yTile + i, sheet, flip,
				        player.getColor());

				// right
				screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + i * modifier, xTile + 2,
				        yTile + i, sheet, flip, player.getColor());
			}

			// normal rendering
		} else {
			// iterate top to bottom
			for (int i = 0; i < 2; i++) {
				// iterate left to right
				for (int j = 0; j < 2; j++) {
					screen.render(xOffset + j * modifier, yOffset + i * modifier, xTile + j, yTile + i,
					        sheet, flip, player.getColor());
				}
			}
		}

	}

}
