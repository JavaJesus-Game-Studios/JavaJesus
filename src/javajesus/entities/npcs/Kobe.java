package javajesus.entities.npcs;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

import java.awt.Color;

/*
 * The evil gorilla of the north
 */
public class Kobe extends NPC {

	// dimensions Kobe
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates Kobe
	 * 
	 * @param level
	 *            the level to place him
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public Kobe(Level level, int x, int y) {
		super(level, "Kobe", x, y, 1, WIDTH, HEIGHT, 500, null, 0, 14, "", 0);
		setSpriteSheet(SpriteSheet.characters);
	}

	/**
	 * Dialogue options for Kobe
	 * 
	 * @param player
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
			MessageHandler.displayText(getName() + ": I'm Lord Hillsborough.", Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText(getName() + ": Get out of my sight.", Color.white);
			return;
		}
		case 4: {
			MessageHandler.displayText(getName() + ": I lost my chickens!", Color.white);
			return;
		}
		case 5: {
			MessageHandler.displayText(getName() + ": Hello Officer!", Color.white);
			return;
		}
		case 6: {
			MessageHandler.displayText(getName() + ": Who goes there!", Color.white);
			return;
		}
		case 7: {
			MessageHandler.displayText(getName() + ": Have you been to San Cisco? I hear they're having lovely weather.",
					Color.white);
			return;
		}
		case 8: {
			MessageHandler.displayText(getName() + ": It's you! It really is! All Hail the Hero of the Bay!", Color.white);
			return;
		}
		case 9: {
			MessageHandler.displayText(getName() + ": I'm not racist but when you're driving in the East Bay,"
					+ " roll up your windows and lock your doors.", Color.white);
			return;
		}
		case 10: {
			MessageHandler.displayText(getName() + ": Have you seen my friend Bob? He's a peasant and he seems to have"
					+ "literally dissapeared!", Color.white);
			return;
		}
		case 11: {
			MessageHandler.displayText(
					getName() + ": Nasty business it is with those Apes in the North!" + " Nasty business indeed.",
					Color.white);
			return;
		}
		case 12: {
			MessageHandler.displayText(getName() + ": Hola, mi nombre es Esteban Norteruta!", Color.white);
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
		return Entity.KOBE;
	}

}
