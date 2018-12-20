package javajesus.entities.npcs.characters;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.entities.npcs.NPC;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

import java.awt.Color;

/*
 * Rancher Storyteller
 */
public class Storyteller extends NPC {

	// dimensions Storyteller
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates Rancher Storyteller
	 * @param level the level he is on
	 * @param x the x coord
	 * @param y the y coord
	 */
	public Storyteller(Level level, int x, int y) {
		super(level, "Storyteller", x, y, 1, WIDTH, HEIGHT, 500, null, 0, 25, "", 0);
		setSpriteSheet(SpriteSheet.mobFriends);
	}

	/**
	 * Storyteller's Dialogue options
	 */
	protected void doDialogue() {
		switch (random.nextInt(4)) {
		case 0: {
			MessageHandler.displayText(getName() + ": Hello!", Color.black);
			return;
		}
		case 1: {
			MessageHandler.displayText(getName() + ": Very Nice!", Color.white);
			return;
		}
		case 2: {
			MessageHandler.displayText(getName() + ": No more stories for you.", Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText(getName() + ": P-please d-d-don't d-doink me officer!", Color.white);
			return;
		}
		default: {
			MessageHandler.displayText(getName() + ": Thejas.", Color.white);
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
