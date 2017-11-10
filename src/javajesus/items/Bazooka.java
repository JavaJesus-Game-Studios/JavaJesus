package javajesus.items;

import javajesus.SoundHandler;
import javajesus.entities.Player;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.utility.Direction;

/*
 * A gun that fires explosive missiles!
 */
public class Bazooka extends Gun implements DifferentOffsetItem{

	// unit size on spritesheet
	private static final int UNIT_SIZE = 8;

	// how fast the player toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	/**
	 * Creates a bazooka, BOOM!
	 */
	public Bazooka() {
		super("Bazooka", 28, 5, 0, new int[] { 0xFF000000, 0xFF434343, 0xFF371B09 },
				"The HR2010 Missile Launching Platform is a state of the art killing machine."
				+ "On one end of the machine are big holes, which is where you load the missiles,"
				+ "on the other are slightly smaller holes, this is where the missiles are fired. This "
				+ "\"Gun\" will kill anything that happens to cross it's path. Loads two Missiles at a time.", 0,
				2, 10, 20, 300, Ammo.MISSILE, SoundHandler.explosion);
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
		int xTile = 0, yTile = 10;

		// whether or not to render backwards
		boolean flip = ((player.getNumSteps() >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (shootingDir == Direction.NORTH) {
			xTile = 18 + (flip ? 3 : 0);
			flip = false;
			if (!player.isMoving())
				xTile = 15;
		} else if (shootingDir == Direction.SOUTH) {
			xTile = 3 + (flip ? 3 : 0);
			flip = false;
			if (!player.isMoving())
				xTile = 0;
		} else {
			xTile = (flip ? 9 : 12);
			if (!player.isMoving())
				xTile = 9;
			flip = shootingDir == Direction.WEST;
		}

		// Upper Body 1
		screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset, xTile, yTile, sheet, flip, player.getColor());
		// Upper Body 2
		screen.render(xOffset + modifier, yOffset, xTile + 1, yTile, sheet, flip, player.getColor());

		// Upper Body 3
		screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset, xTile + 2, yTile, sheet, flip,
		        player.getColor());

		// Lower Body 1
		screen.render(xOffset + (modifier * (flip ? 2 : 0)), yOffset + modifier, xTile, yTile + 1, sheet, flip,
		        player.getColor());

		// Lower Body 2
		screen.render(xOffset + modifier, yOffset + modifier, xTile + 1, yTile + 1, sheet, flip, player.getColor());

		// Lower Body 3
		screen.render(xOffset + 2 * modifier - (modifier * (flip ? 2 : 0)), yOffset + modifier, xTile + 2, yTile + 1,
		        sheet, flip, player.getColor());

	}

}
