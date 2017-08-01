package javajesus.entities.npcs.characters;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.entities.npcs.NPC;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

import java.awt.Color;

/*
 * The player's son
 */
public class Son extends NPC {

	// dimensions Bautista
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Creates the player's son
	 * 
	 * @param level
	 *            the level he is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public Son(Level level, int x, int y) {
		super(level, "Son", x, y, 1, WIDTH, HEIGHT, 500, null, 0, 7, "", 0);
		setSpriteSheet(SpriteSheet.characters);
	}

	/**
	 * The Son's dialogue options
	 */
	public void doDialogue() {

		switch (random.nextInt(13)) {
		case 0: {
			MessageHandler.displayText(getName() + ": Hey dad.", Color.black);
			return;
		}
		case 1: {
			MessageHandler.displayText(getName() + ": Can I see your gun?", Color.white);
			return;
		}
		case 2: {
			MessageHandler.displayText(getName() + ": Dady feel how strong I am.", Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText(getName() + ": Dad let's race!", Color.white);
			return;
		}
		case 4: {
			MessageHandler.displayText(getName() + ": Can I play your video games?", Color.white);
			return;
		}
		case 5: {
			MessageHandler.displayText(getName() + ": Let's watch Ultraman!", Color.white);
			return;
		}
		case 6: {
			MessageHandler.displayText(getName() + ": Mom won't let me play with water guns.", Color.white);
			return;
		}
		case 7: {
			MessageHandler.displayText(getName() + ": Have you shot any bad guys?", Color.white);
			return;
		}
		case 8: {
			MessageHandler.displayText(getName() + ": C'mon let me see your gun.", Color.white);
			return;
		}
		case 9: {
			MessageHandler.displayText(getName() + ": Dad make her give me back my Owlman action figure.", Color.white);
			return;
		}
		case 10: {
			MessageHandler.displayText(
					getName() + ": Mark said that his friend Don went into the forest up north and saw a Chimpanzee"
							+ "and then it bit his finger off so now he has a fake finger"
							+ "but it looks just like a real finger!",
					Color.white);
			return;
		}
		case 11: {
			MessageHandler.displayText(getName() + ": Are there really Chimpanzees in the forest?", Color.white);
			return;
		}
		case 12: {
			MessageHandler.displayText(
					getName()
							+ ": Can we go see Lord Hillsborough's castle, Mark said the Knights let you play with their swords!",
					Color.white);
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
		return Entity.SON;
	}

}
