package javajesus.entities.npcs;

import javajesus.ChatHandler;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

import java.awt.Color;

/*
 * Main character's Daughter
 */
public class Daughter extends NPC {

	private static final long serialVersionUID = -6160414459786768128L;

	// dimensions of the daughter
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates the player's daughter
	 * 
	 * @param level
	 *            the level she is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public Daughter(Level level, int x, int y) {
		super(level, "Daughter", x, y, 1, WIDTH, HEIGHT, 500, null, 0, 5, "", 0);
		setSpriteSheet(SpriteSheet.characters);
	}

	/**
	 * Dialogue options
	 */
	public void doDialogue() {

		switch (random.nextInt(13)) {
		case 0: {
			ChatHandler.displayText(getName() + ": Hi daddy!", Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(getName() + ": I love you daddy!", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(getName() + ": Daddy can we watch Chilled?", Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(getName() + ": Daddy why is the sky blue?", Color.white);
			return;
		}
		case 4: {
			ChatHandler.displayText(getName() + ": Daddy how many stars are there!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(getName() + ": Daddy, Mommy said you're immature, what does that mean?",
					Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(getName() + ": Daddy! He took my dolly!", Color.white);
			return;
		}
		case 7: {
			ChatHandler.displayText(getName() + ": I want to see the tall buildings!", Color.white);
			return;
		}
		case 8: {
			ChatHandler.displayText(getName() + ": Daddy can we watch the Tiger King?", Color.white);
			return;
		}
		case 9: {
			ChatHandler.displayText(getName() + ": Da da da dum da dum dah duh dum dee doo dum", Color.white);
			return;
		}
		case 10: {
			ChatHandler.displayText(getName() + ": Daddy let's go to the park!", Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(getName() + ": Mommy said we're going on a picnic tomorrow!", Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(getName() + ": Daddy why is your face so scratchy?", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(getName() + ": Bye!", Color.white);
			return;
		}
		}

	}
}
