package game.entities.npcs;

import game.ChatHandler;
import game.graphics.SpriteSheet;
import java.awt.Color;
import level.Level;

/*
 * Zorra
 */
public class Zorra extends NPC {

	private static final long serialVersionUID = -4981994300664864597L;

	// dimensions Zorra
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates Zorra
	 * 
	 * @param level
	 *            the level she is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public Zorra(Level level, int x, int y) {
		super(level, "Zorra", x, y, 1, WIDTH, HEIGHT, 500, null, 10, 5, "", 0);
		setSpriteSheet(SpriteSheet.characters);
	}

	/**
	 * Zorra's dialogue options
	 */
	public void doDialogue() {

		switch (random.nextInt(13)) {
		case 0: {
			ChatHandler.displayText(getName() + ": We freed you, now help us!", Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(getName() + ": Bautista is terrorizing my people!", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(getName() + ": Help me win back my city!", Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(getName() + ": Mierda!", Color.white);
			return;
		}
		case 4: {
			ChatHandler.displayText(getName() + ": Bautista es un punto!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(getName() + ": Hello Officer!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(getName() + ": Bendejo!", Color.white);
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
			ChatHandler.displayText(getName() + ": Hola, mi nombre es Zorra!", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(getName() + ": Hola!", Color.white);
			return;
		}
		}

	}

}
