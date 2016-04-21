package game.entities.npcs;

import game.ChatHandler;
import game.graphics.SpriteSheet;
import java.awt.Color;
import level.Level;

/*
 * The evil gorilla of the north
 */
public class Kobe extends NPC {

	private static final long serialVersionUID = 4773669926144737010L;

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
			ChatHandler.displayText(getName() + ": I'm richer than you.", Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(getName() + ": Is that any way to dress in my court?", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(getName() + ": I'm Lord Hillsborough.", Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(getName() + ": Get out of my sight.", Color.white);
			return;
		}
		case 4: {
			ChatHandler.displayText(getName() + ": I lost my chickens!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(getName() + ": Hello Officer!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(getName() + ": Who goes there!", Color.white);
			return;
		}
		case 7: {
			ChatHandler.displayText(getName() + ": Have you been to San Cisco? I hear they're having lovely weather.",
					Color.white);
			return;
		}
		case 8: {
			ChatHandler.displayText(getName() + ": It's you! It really is! All Hail the Hero of the Bay!", Color.white);
			return;
		}
		case 9: {
			ChatHandler.displayText(getName() + ": I'm not racist but when you're driving in the East Bay,"
					+ " roll up your windows and lock your doors.", Color.white);
			return;
		}
		case 10: {
			ChatHandler.displayText(getName() + ": Have you seen my friend Bob? He's a peasant and he seems to have"
					+ "literally dissapeared!", Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(
					getName() + ": Nasty business it is with those Apes in the North!" + " Nasty business indeed.",
					Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(getName() + ": Hola, mi nombre es Esteban Norteruta!", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(getName() + ": Hello!", Color.white);
			return;
		}
		}

	}

}
