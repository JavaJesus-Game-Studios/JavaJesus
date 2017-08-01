package javajesus.entities.npcs.characters;

import javajesus.MessageHandler;
import javajesus.entities.Entity;
import javajesus.entities.npcs.NPC;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

import java.awt.Color;

/*
 * The player's wife
 */
public class Wife extends NPC {

	// dimensions Bautista
	private static final int WIDTH = 16, HEIGHT = 16;

	/**
	 * Create the player's wife
	 * 
	 * @param level
	 *            the level she is on
	 * @param x
	 *            the x coord
	 * @param y
	 *            the y coord
	 */
	public Wife(Level level, int x, int y) {
		super(level, "Wife", x, y, 1, WIDTH, HEIGHT, 500, null, 0, 3, "", 0);
		setSpriteSheet(SpriteSheet.characters);
	}

	/**
	 * The Wife's dialogue options
	 */
	public void doDialogue() {

		switch (random.nextInt(13)) {
		case 0: {
			MessageHandler.displayText(getName() + ": Hi sweety.", Color.black);
			return;
		}
		case 1: {
			MessageHandler.displayText(getName() + ": Did you pick up the kids from Swim Practice?", Color.white);
			return;
		}
		case 2: {
			MessageHandler.displayText(getName() + ": You never do anything right.", Color.white);
			return;
		}
		case 3: {
			MessageHandler.displayText(getName() + ": Can you help me raise our kids for once?", Color.white);
			return;
		}
		case 4: {
			MessageHandler.displayText(getName() + ": I swear, sometimes I think I should just "
					+ "take the kids and move in with my parents.", Color.white);
			return;
		}
		case 5: {
			MessageHandler.displayText(getName() + ": How was work? You didn't have to shoot anyone did you?",
					Color.white);
			return;
		}
		case 6: {
			MessageHandler.displayText(getName() + ": I got pulled over today, can you get me out of the ticket?",
					Color.white);
			return;
		}
		case 7: {
			MessageHandler.displayText(getName() + ": You may be a knob, but you're a loveable knob.", Color.white);
			return;
		}
		case 8: {
			MessageHandler.displayText(
					getName() + ": The gang problem get's worse here everyday, can you switch departments? I'm afraid"
							+ "You'll be killed.",
					Color.white);
			return;
		}
		case 9: {
			MessageHandler.displayText(getName() + ": I'm not racist but when you're driving in the East Bay,"
					+ " roll up your windows and lock your doors.", Color.white);
			return;
		}
		case 10: {
			MessageHandler.displayText(
					getName() + ": Have you seen our son? He was playing hide and seek with me and he seems to have"
							+ "literally dissapeared!",
					Color.white);
			return;
		}
		case 11: {
			MessageHandler.displayText(
					getName() + ": Don't tell the children about the Apes in the North," + " you'll scare them.",
					Color.white);
			return;
		}
		case 12: {
			MessageHandler.displayText(getName() + ": Can you pick up some food on your way home?", Color.white);
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
		return Entity.WIFE;
	}

}
