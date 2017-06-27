package javajesus.entities.npcs;

import javajesus.ChatHandler;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.utility.Direction;

import java.awt.Color;

/*
 * The good ape of the north
 */
public class Octavius extends NPC {

	private static final long serialVersionUID = -6699489501735991630L;

	// dimensions Bautista
	private static final int WIDTH = 16, HEIGHT = 24;

	// how fast the npcs toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	/**
	 * Creates Octavius
	 * 
	 * @param level
	 *            the level he is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public Octavius(Level level, int x, int y) {
		super(level, "Octavius", x, y, 1, WIDTH, HEIGHT, 500, null, 0, 12, "", 0);
		setSpriteSheet(SpriteSheet.characters);
	}

	/**
	 * Displays Octavius on the Screen
	 */
	public void render(Screen screen) {
		super.render(screen);
		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * getScale();

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the crown position
		int xTile = this.xTile, yTile = this.yTile - 1;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// gets spritesheet positions
		if (getDirection() == Direction.NORTH) {
			xTile += 10;
		}else
		if (getDirection() == Direction.SOUTH) {
			xTile += 2;
		} else{
			xTile += 4 + (flip ? 2 : 0);
			flip = getDirection() == Direction.WEST;
		}

		// Upper crown 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile + yTile * getSpriteSheet().getNumBoxes(),
				getColor(), flip, getScale(), getSpriteSheet());

		// Upper crown 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset,
				(xTile + 1) + yTile * getSpriteSheet().getNumBoxes(), getColor(), flip, getScale(), getSpriteSheet());
	}

	/**
	 * Dialogue options for Octavius
	 */
	public void doDialogue() {

		switch (random.nextInt(13)) {
		case 0: {
			ChatHandler.displayText(getName() + ": Human must go.", Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(getName() + ": Ape together strong, Ape fighting weak.", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(getName() + ": Kobe must be punished.", Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(getName() + ": Gorilla loyal, Gorilla not abandon Chimp.", Color.white);
			return;
		}
		case 4: {
			ChatHandler.displayText(
					getName() + ": Bonobo bad, Kobe Bonobo. Human worse, human violent" + "human kill ape.",
					Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(getName() + ": You should not, be here!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(getName() + ": My kingdom, is not, for Humans!", Color.white);
			return;
		}
		case 7: {
			ChatHandler.displayText(getName() + ": I love the lights in Human City.", Color.white);
			return;
		}
		case 8: {
			ChatHandler.displayText(getName() + ": Ape is family, You ape to, part of Ape family.", Color.white);
			return;
		}
		case 9: {
			ChatHandler.displayText(getName() + ": Demon help Kobe, Demon kill Ape! Ape hate Demon.", Color.white);
			return;
		}
		case 10: {
			ChatHandler.displayText(getName() + ": My son, no listen to me.", Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(
					getName() + ": Human raise me, human hurt me, ape attack human city, ape cross human bridge"
							+ "ape live in forest.",
					Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(getName() + ": This is ape home, go back to human home!", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(getName() + ": Hello!", Color.white);
			return;
		}
		}

	}
}
