package javajesus.entities.npcs;

import javajesus.ChatHandler;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;

import java.awt.Color;

/*
 * The player's wife
 */
public class Wife extends NPC {

	private static final long serialVersionUID = -3743386069270272125L;

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
			ChatHandler.displayText(getName() + ": Hi sweety.", Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(getName() + ": Did you pick up the kids from Swim Practice?", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(getName() + ": You never do anything right.", Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(getName() + ": Can you help me raise our kids for once?", Color.white);
			return;
		}
		case 4: {
			ChatHandler.displayText(getName() + ": I swear, sometimes I think I should just "
					+ "take the kids and move in with my parents.", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(getName() + ": How was work? You didn't have to shoot anyone did you?",
					Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(getName() + ": I got pulled over today, can you get me out of the ticket?",
					Color.white);
			return;
		}
		case 7: {
			ChatHandler.displayText(getName() + ": You may be a knob, but you're a loveable knob.", Color.white);
			return;
		}
		case 8: {
			ChatHandler.displayText(
					getName() + ": The gang problem get's worse here everyday, can you switch departments? I'm afraid"
							+ "You'll be killed.",
					Color.white);
			return;
		}
		case 9: {
			ChatHandler.displayText(getName() + ": I'm not racist but when you're driving in the East Bay,"
					+ " roll up your windows and lock your doors.", Color.white);
			return;
		}
		case 10: {
			ChatHandler.displayText(
					getName() + ": Have you seen our son? He was playing hide and seek with me and he seems to have"
							+ "literally dissapeared!",
					Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(
					getName() + ": Don't tell the children about the Apes in the North," + " you'll scare them.",
					Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(getName() + ": Can you pick up some food on your way home?", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(getName() + ": Hello!", Color.white);
			return;
		}
		}

	}

}
