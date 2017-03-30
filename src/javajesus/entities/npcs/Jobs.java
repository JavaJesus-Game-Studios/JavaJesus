package javajesus.entities.npcs;

import java.awt.Color;

import javajesus.ChatHandler;
import javajesus.graphics.SpriteSheet;
import level.Level;

/*
 * Brokovsky Jobs, founder of some company in the game
 */
public class Jobs extends NPC {

	private static final long serialVersionUID = -7655486644422463084L;

	// dimensions Jobs
	private static final int WIDTH = 16, HEIGHT = 16;

	public Jobs(Level level, int x, int y) {
		super(level, "Brokovsky", x, y, 1, WIDTH, HEIGHT, 500, null, 10, 0, "", 0);
		setSpriteSheet(SpriteSheet.characters);
	}

	/**
	 * Dialogue options for Jobs
	 */
	public void doDialogue() {

		switch (random.nextInt(13)) {
		case 0: {
			ChatHandler.displayText(getName() + ": I'm richer than you. In fact I'm rich than everyone, except"
					+ " for that bastar William Fence in Washington", Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(getName() + ": Is that any way to dress in my city?", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(getName() + ": I'm Sascha Brovosky.", Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(getName() + ": Get out of my sight.", Color.white);
			return;
		}
		case 4: {
			ChatHandler.displayText(getName() + ": I lost my patents!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(getName() + ": If Jugle thinks they can compete with me in the mobile business"
					+ " they can go straight to HELL!", Color.white);
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
			ChatHandler.displayText(
					getName() + ": Uggh, I see the lower class has found an entrance into our beloved city",
					Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(getName() + ": Hello!", Color.white);
			return;
		}
		}

	}

}
