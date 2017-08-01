package javajesus.entities.npcs.characters;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.entities.npcs.NPC;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

import java.awt.Color;

/*
 * Rancher Bautista
 */
public class Bautista extends NPC {

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
			MessageHandler.displayText(getName() + ": I'm richer than you.", Color.black);
			return;
		}
		case 1: {
			MessageHandler.displayText(getName() + ": Is that any way to dress in my lands?", Color.white);
			return;
		}
		case 2: {
			MessageHandler.displayText(getName() + ": I'm Ranchero Bautista, the true inheritor of the peninsula."
					+ "Until that bendejo Hillsborough and his family stole my land! And now these "
					+ "pasty 'techies' threaten my borders from the south, and these peasants threaten my"
					+ " authority from within, these are dark times mi amigo.", Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText(getName() + ": Get out of my sight.", Color.white);
			return;
		}
		case 4: {
			MessageHandler.displayText(
					getName() + ": They are my savages! I have the right to do with " + "them as I please!",
					Color.white);
			return;
		}
		case 5: {
			MessageHandler.displayText(getName() + ": Hola Officer!", Color.white);
			return;
		}
		case 6: {
			MessageHandler.displayText(getName() + ": Mis cabelleros son bueno a guerro!", Color.white);
			return;
		}
		case 7: {
			MessageHandler.displayText(getName() + ": Have you been to San Cisco? It has declined into squalor since "
					+ "the days of Bautista rule.", Color.white);
			return;
		}
		case 8: {
			MessageHandler.displayText(
					getName() + ": My fellow ranchero's have even turned against me! I have enemies everywhere "
							+ "my friend you must help me squash this rebellion and reclaim my own glory!",
					Color.white);
			return;
		}
		case 9: {
			MessageHandler
					.displayText(
							getName() + ": I am not torturing the capture rebels, they are my subjects and I am just"
									+ "however as my subjects they must obey my command, and when they don't"
									+ " I must take action, and physical torment seems to be most persuasive.",
							Color.white);
			return;
		}
		case 10: {
			MessageHandler.displayText(getName() + ": Poor people disgust me, rich people disgust me, the only good thing "
					+ "in life is women. Haha!", Color.white);
			return;
		}
		case 11: {
			MessageHandler.displayText(
					getName() + ": Those apes have spies everywhere, I know the savages are in league with them,"
							+ "the apes will help them to steal my land! And don't try to convince me "
							+ "that thes apes did not influence the rebellion!",
					Color.white);
			return;
		}
		case 12: {
			MessageHandler.displayText(getName() + ": Hola, Esteban Norteruta!", Color.white);
			return;
		}
		default: {
			MessageHandler.displayText(getName() + ": I must become my own Napoleon!", Color.white);
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
		return Entity.BAUTISTA;
	}
}
