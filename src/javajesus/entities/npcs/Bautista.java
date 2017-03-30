package javajesus.entities.npcs;

import java.awt.Color;

import javajesus.ChatHandler;
import javajesus.graphics.SpriteSheet;
import level.Level;

/*
 * Rancher Bautista
 */
public class Bautista extends NPC {

	private static final long serialVersionUID = -4685371991295485791L;

	// dimensions Bautista
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates Rancher Bautista
	 * @param level the level he is on
	 * @param x the x coord
	 * @param y the y coord
	 */
	public Bautista(Level level, int x, int y) {
		super(level, "Ranchero Bautista", x, y, 1, WIDTH, HEIGHT, 500, null, 0, 16, "", 0);
		setSpriteSheet(SpriteSheet.characters);
	}

	/**
	 * Bautista's Dialogue options
	 */
	protected void doDialogue() {
		switch (random.nextInt(13)) {
		case 0: {
			ChatHandler.displayText(getName() + ": I'm richer than you.", Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(getName() + ": Is that any way to dress in my lands?", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(getName() + ": I'm Ranchero Bautista, the true inheritor of the peninsula."
					+ "Until that bendejo Hillsborough and his family stole my land! And now these "
					+ "pasty 'techies' threaten my borders from the south, and these peasants threaten my"
					+ " authority from within, these are dark times mi amigo.", Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(getName() + ": Get out of my sight.", Color.white);
			return;
		}
		case 4: {
			ChatHandler.displayText(
					getName() + ": They are my savages! I have the right to do with " + "them as I please!",
					Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(getName() + ": Hola Officer!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(getName() + ": Mis cabelleros son bueno a guerro!", Color.white);
			return;
		}
		case 7: {
			ChatHandler.displayText(getName() + ": Have you been to San Cisco? It has declined into squalor since "
					+ "the days of Bautista rule.", Color.white);
			return;
		}
		case 8: {
			ChatHandler.displayText(
					getName() + ": My fellow ranchero's have even turned against me! I have enemies everywhere "
							+ "my friend you must help me squash this rebellion and reclaim my own glory!",
					Color.white);
			return;
		}
		case 9: {
			ChatHandler
					.displayText(
							getName() + ": I am not torturing the capture rebels, they are my subjects and I am just"
									+ "however as my subjects they must obey my command, and when they don't"
									+ " I must take action, and physical torment seems to be most persuasive.",
							Color.white);
			return;
		}
		case 10: {
			ChatHandler.displayText(getName() + ": Poor people disgust me, rich people disgust me, the only good thing "
					+ "in life is women. Haha!", Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(
					getName() + ": Those apes have spies everywhere, I know the savages are in league with them,"
							+ "the apes will help them to steal my land! And don't try to convince me "
							+ "that thes apes did not influence the rebellion!",
					Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(getName() + ": Hola, Esteban Norteruta!", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(getName() + ": I must become my own Napoleon!", Color.white);
			return;
		}
		}
	}
}
