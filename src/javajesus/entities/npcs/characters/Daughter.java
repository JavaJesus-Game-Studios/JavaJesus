package javajesus.entities.npcs.characters;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.entities.npcs.NPC;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

import java.awt.Color;

/*
 * Main character's Daughter
 */
public class Daughter extends NPC {

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
			MessageHandler.displayText(getName() + ": Hi daddy!", Color.black);
			return;
		}
		case 1: {
			MessageHandler.displayText(getName() + ": I love you daddy!", Color.white);
			return;
		}
		case 2: {
			MessageHandler.displayText(getName() + ": Daddy can we watch Chilled?", Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText(getName() + ": Daddy why is the sky blue?", Color.white);
			return;
		}
		case 4: {
			MessageHandler.displayText(getName() + ": Daddy how many stars are there!", Color.white);
			return;
		}
		case 5: {
			MessageHandler.displayText(getName() + ": Daddy, Mommy said you're immature, what does that mean?",
					Color.white);
			return;
		}
		case 6: {
			MessageHandler.displayText(getName() + ": Daddy! He took my dolly!", Color.white);
			return;
		}
		case 7: {
			MessageHandler.displayText(getName() + ": I want to see the tall buildings!", Color.white);
			return;
		}
		case 8: {
			MessageHandler.displayText(getName() + ": Daddy can we watch the Tiger King?", Color.white);
			return;
		}
		case 9: {
			MessageHandler.displayText(getName() + ": Da da da dum da dum dah duh dum dee doo dum", Color.white);
			return;
		}
		case 10: {
			MessageHandler.displayText(getName() + ": Daddy let's go to the park!", Color.white);
			return;
		}
		case 11: {
			MessageHandler.displayText(getName() + ": Mommy said we're going on a picnic tomorrow!", Color.white);
			return;
		}
		case 12: {
			MessageHandler.displayText(getName() + ": Daddy why is your face so scratchy?", Color.white);
			return;
		}
		default: {
			MessageHandler.displayText(getName() + ": Bye!", Color.white);
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
		return Entity.DAUGHTER;
	}
}
