package javajesus.entities.npcs;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

import java.awt.Color;

/*
 * Zorra
 */
public class Zorra extends NPC {

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
			MessageHandler.displayText(getName() + ": We freed you, now help us!", Color.black);
			return;
		}
		case 1: {
			MessageHandler.displayText(getName() + ": Bautista is terrorizing my people!", Color.white);
			return;
		}
		case 2: {
			MessageHandler.displayText(getName() + ": Help me win back my city!", Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText(getName() + ": Mierda!", Color.white);
			return;
		}
		case 4: {
			MessageHandler.displayText(getName() + ": Bautista es un punto!", Color.white);
			return;
		}
		case 5: {
			MessageHandler.displayText(getName() + ": Hello Officer!", Color.white);
			return;
		}
		case 6: {
			MessageHandler.displayText(getName() + ": Bendejo!", Color.white);
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
			MessageHandler.displayText(getName() + ": Hola, mi nombre es Zorra!", Color.white);
			return;
		}
		default: {
			MessageHandler.displayText(getName() + ": Hola!", Color.white);
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
		return Entity.ZORRA;
	}

}
