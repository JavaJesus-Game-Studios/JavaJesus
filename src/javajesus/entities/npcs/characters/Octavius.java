package javajesus.entities.npcs.characters;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.entities.npcs.NPC;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.utility.Direction;

import java.awt.Color;

/*
 * The good ape of the north
 */
public class Octavius extends NPC {

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
		int modifier = UNIT_SIZE;

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
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset, xTile, yTile, getSpriteSheet(), flip);

		// Upper crown 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset, xTile + 1, yTile, getSpriteSheet(),
		        flip);
	}

	/**
	 * Dialogue options for Octavius
	 */
	public void doDialogue() {

		switch (random.nextInt(13)) {
		case 0: {
			MessageHandler.displayText(getName() + ": Human must go.", Color.black);
			return;
		}
		case 1: {
			MessageHandler.displayText(getName() + ": Ape together strong, Ape fighting weak.", Color.white);
			return;
		}
		case 2: {
			MessageHandler.displayText(getName() + ": Kobe must be punished.", Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText(getName() + ": Gorilla loyal, Gorilla not abandon Chimp.", Color.white);
			return;
		}
		case 4: {
			MessageHandler.displayText(
					getName() + ": Bonobo bad, Kobe Bonobo. Human worse, human violent" + "human kill ape.",
					Color.white);
			return;
		}
		case 5: {
			MessageHandler.displayText(getName() + ": You should not, be here!", Color.white);
			return;
		}
		case 6: {
			MessageHandler.displayText(getName() + ": My kingdom, is not, for Humans!", Color.white);
			return;
		}
		case 7: {
			MessageHandler.displayText(getName() + ": I love the lights in Human City.", Color.white);
			return;
		}
		case 8: {
			MessageHandler.displayText(getName() + ": Ape is family, You ape to, part of Ape family.", Color.white);
			return;
		}
		case 9: {
			MessageHandler.displayText(getName() + ": Demon help Kobe, Demon kill Ape! Ape hate Demon.", Color.white);
			return;
		}
		case 10: {
			MessageHandler.displayText(getName() + ": My son, no listen to me.", Color.white);
			return;
		}
		case 11: {
			MessageHandler.displayText(
					getName() + ": Human raise me, human hurt me, ape attack human city, ape cross human bridge"
							+ "ape live in forest.",
					Color.white);
			return;
		}
		case 12: {
			MessageHandler.displayText(getName() + ": This is ape home, go back to human home!", Color.white);
			return;
		}
		default: {
			MessageHandler.displayText(getName() + ": Hello!", Color.white);
			return;
		}
		}

	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Entity.OCTAVIUS;
	}
}
