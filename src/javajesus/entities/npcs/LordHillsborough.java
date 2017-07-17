package javajesus.entities.npcs;

import javajesus.MessageHandler;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.utility.Direction;

import java.awt.Color;

/*
 * Lord Hillsborough
 */
public class LordHillsborough extends NPC {

	private static final long serialVersionUID = -4740270757337394209L;

	// dimensions Lord Hillsborough
	private static final int WIDTH = 16, HEIGHT = 18;

	// how fast the npcs toggles steps
	private static final int WALKING_ANIMATION_SPEED = 4;

	/**
	 * Creates Lord Hillsborough
	 * 
	 * @param level
	 *            the level to place him on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public LordHillsborough(Level level, int x, int y) {
		super(level, "Lord Hillsborough", x, y, 1, WIDTH, HEIGHT, 500, null, 0, 1, "", 0);
		setSpriteSheet(SpriteSheet.characters);
	}

	/**
	 * Displays Lord Hillsborough on the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY() - modifier;

		// the crown position
		int xTile = this.xTile, yTile = this.yTile - 1;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// Gets the proper horizontal position
		if (getDirection() == Direction.NORTH) {
			xTile += 10;
		} else if (getDirection() == Direction.SOUTH) {
			xTile += 2;
		} else {
			xTile += 4 + (flip ? 2 : 0);
			flip = getDirection() == Direction.WEST;
		}

		// Upper crown 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile, yTile, getSpriteSheet(), flip);

		// Upper crown 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset, xTile + 1, yTile, getSpriteSheet(),
		        flip);
	}

	/**
	 * Lord Hillsborough's dialogue options
	 */
	public void doDialogue() {

		switch (random.nextInt(13)) {
		case 0: {
			MessageHandler.displayText(getName() + ": I'm richer than you.", Color.black);
			return;
		}
		case 1: {
			MessageHandler.displayText(getName() + ": Is that any way to dress in my court?", Color.white);
			return;
		}
		case 2: {
			MessageHandler
					.displayText(
							getName() + ": I'm Lord Hillsborough, the domain of Hillsborough has been in my family"
									+ "for generations, when my Great-Grandfather won it in the Mexican American War."
									+ "Of course, 'ol Bautista will give a different version of the story.",
							Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText(getName() + ": Get out of my sight.", Color.white);
			return;
		}
		case 4: {
			MessageHandler.displayText(getName() + ": Uggh you bore me with your trifling!", Color.white);
			return;
		}
		case 5: {
			MessageHandler.displayText(getName() + ": Hello, salutations, and welcome to my domain Officer!", Color.white);
			return;
		}
		case 6: {
			MessageHandler.displayText(getName() + ": They may be peasants, but they are my peasants, and I'll be damned"
					+ "if some bow-legged, red-skinned devil men try to take them from me!", Color.white);
			return;
		}
		case 7: {
			MessageHandler.displayText(getName() + ": Oh how I loathe the cities and their lack royalty, how is one to k"
					+ "now if he is better than everyone else without a title" + "to prove it?", Color.white);
			return;
		}
		case 8: {
			MessageHandler.displayText(getName() + ": Manners maketh man.", Color.white);
			return;
		}
		case 9: {
			MessageHandler.displayText(getName() + ": I'm am racist so when you're driving in the East Bay,"
					+ " roll up your windows and lock your doors. To think those"
					+ "gangsters consider themselves to be on an equal status to me, is " + "utterly disgusting.",
					Color.white);
			return;
		}
		case 10: {
			MessageHandler
					.displayText(getName() + ": The only thing more boring than this conversation is the conversation"
							+ "I had with the peasant who was missing a sheep last week."
							+ "You wouldn't think people so poor could be so entitled.", Color.white);
			return;
		}
		case 11: {
			MessageHandler.displayText(
					getName() + ": Pah! Those 'Officers of the law' with their so called 'superior weaponry'"
							+ " in San Cisco couldn't even stop Zoo animals from "
							+ "causing havoc and escaping into the forest, my Knights could have "
							+ "easily put down that rebellion",
					Color.white);
			return;
		}
		case 12: {
			MessageHandler.displayText(getName() + ": Oh how I miss the elder days that father spoke of, let me tell you"
					+ "their are a few lords I wouldn't miss the chance to duel with. It has been a long"
					+ "while since I've tested my mettle with a blade, I was once the envy of the land you know,"
					+ " quite a catch.", Color.white);
			return;
		}
		default: {
			MessageHandler.displayText(getName() + ": A gentlemen's weapon is the sabre, these 'firearms' that "
					+ "common rabble use are nothing but a fad, simply a louder and more primitive version of a good"
					+ "crossbow. To think the Saxons have sunk below the savages whose land we aquirred is "
					+ "most troubling.", Color.white);
			return;
		}
		}

	}
}
