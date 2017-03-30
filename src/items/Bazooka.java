package items;

import javajesus.SoundHandler;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import utility.Direction;

/*
 * A gun that fires explosive missiles!
 */
public class Bazooka extends Gun {

	private static final long serialVersionUID = -2457352828009998981L;

	// unit size on spritesheet
	private static final int UNIT_SIZE = 8;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	/**
	 * Creates a bazooka, BOOM!
	 */
	public Bazooka() {
		super("Bazooka", 28, 5, 0, new int[] { 0xFF000000, 0xFF434343, 0xFF371B09 }, "Standard Explosive Artillery", 0,
				2, 10, 20, 300, Ammo.MISSILE, SoundHandler.explosion);
	}

	/**
	 * Some items require the player to have additional animations that aren't
	 * available normally for the player
	 * 
	 * Delegate the rendering calls of the player here
	 * 
	 * @param screen
	 *            the screen to display it on
	 * @param player
	 *            the player to display
	 * @param sheet
	 * 			  the spritesheet to use
	 */
	public void renderPlayer(Screen screen, Player player, SpriteSheet sheet, Direction shootingDir) {

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * player.getScale();

		// no x or y offset, use the upper left corner as absolute
		int xOffset = player.getX(), yOffset = player.getY();

		// the horizontal/vertical position on the spritesheet
		int xTile = 0, yTile = 10;

		// whether or not to render backwards
		boolean flip = ((player.getNumSteps() >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (shootingDir == Direction.NORTH) {
			xTile = 15 + (flip ? 3 : 0);
			flip = false;
			if (!player.isMoving())
				xTile = 21;
		} else if (shootingDir == Direction.SOUTH) {
			xTile = 6 + (flip ? 3 : 0);
			flip = false;
			if (!player.isMoving())
				xTile = 12;
		} else {
			xTile = (flip ? 3 : 0);
			if (!player.isMoving())
				xTile = 3;
			flip = shootingDir == Direction.WEST;
		}

		// Upper Body 1
		screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset, xTile + yTile * sheet.getNumBoxes(), player.getColor(), flip,
				player.getScale(), sheet);
		// Upper Body 2
		screen.render(xOffset + modifier, yOffset, (xTile + 1) + yTile * sheet.getNumBoxes(), player.getColor(), flip,
				player.getScale(), sheet);

		// Upper Body 3
		screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset, (xTile + 2) + yTile * sheet.getNumBoxes(),
				player.getColor(), flip, player.getScale(), sheet);

		// Lower Body 1
		screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + modifier, xTile + (yTile + 1) * sheet.getNumBoxes(), player.getColor(),
				flip, player.getScale(), sheet);

		// Lower Body 2
		screen.render(xOffset + modifier, yOffset + modifier, (xTile + 1) + (yTile + 1) * sheet.getNumBoxes(),
				player.getColor(), flip, player.getScale(), sheet);

		// Lower Body 3
		screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + modifier,
				(xTile + 2) + (yTile + 1) * sheet.getNumBoxes(), player.getColor(), flip, player.getScale(), sheet);

	}

}
