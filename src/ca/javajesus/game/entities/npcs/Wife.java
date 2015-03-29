package ca.javajesus.game.entities.npcs;

import java.awt.Color;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class Wife extends NPC {
	
	public Wife(Level level, int x, int y) {
		super(level, "Wife", x, y, 1, 16, 16, 500, null, 0, 3, "",
				0);
		this.sheet = SpriteSheet.characters;
	}

	public void speak(Player player) {

		isTalking = true;
		switch (player.getDirection()) {
		case NORTH: {
			setDirection(Direction.SOUTH);
			break;
		}
		case SOUTH: {
			setDirection(Direction.NORTH);
			break;
		}
		case WEST: {
			setDirection(Direction.EAST);
			break;
		}
		case EAST: {
			setDirection(Direction.WEST);
			break;
		}
		}

		if (currentQuest != null) {
			if (!player.activeQuests.contains(currentQuest)) {
				player.activeQuests.add(currentQuest);
			}
			currentQuest.update();
			switch (currentQuest.getPhase()) {
			case 0: {
				ChatHandler.sendMessage(
						name + ": " + currentQuest.preDialogue(), Color.blue);
				sound.play(sound.levelup);
				currentQuest.nextPhase();
				return;
			}
			case 1: {
				ChatHandler.sendMessage(name + ": " + currentQuest.dialogue(),
						Color.blue);
				return;
			}
			case 2: {
				ChatHandler.sendMessage(
						name + ": " + currentQuest.postDialogue(), Color.CYAN);
				sound.play(sound.chest);
				if (!player.completedQuests.contains(currentQuest)) {
					player.completedQuests.add(currentQuest);
					player.activeQuests.remove(currentQuest);
				}
				nextQuest();
				return;
			}
			}
		}

		switch (random.nextInt(13)) {
		case 0: {
			ChatHandler.sendMessage(name + ": Hi sweety.",
					Color.black);
			return;
		}
		case 1: {
			ChatHandler.sendMessage(name
					+ ": Did you pick up the kids from Swim Practice?", Color.white);
			return;
		}
		case 2: {
			ChatHandler.sendMessage(name + ": You never do anything right.",
					Color.white);
			return;
		}
		case 3: {
			ChatHandler.sendMessage(name + ": Can you help me raise our kids for once?",
					Color.white);
			return;
		}
		case 4: {
			ChatHandler
					.sendMessage(name + ": I swear, sometimes I think I should just "
							+ "take the kids and move in with my parents.", Color.white);
			return;
		}
		case 5: {
			ChatHandler.sendMessage(name + ": How was work? You didn't have to shoot anyone did you?", Color.white);
			return;
		}
		case 6: {
			ChatHandler.sendMessage(name + ": I got pulled over today, can you get me out of the ticket?", Color.white);
			return;
		}
		case 7: {
			ChatHandler
					.sendMessage(
							name
									+ ": You may be a knob, but you're a loveable knob.",
							Color.white);
			return;
		}
		case 8: {
			ChatHandler
					.sendMessage(
							name
									+ ": The gang problem get's worse here everyday, can you switch departments? I'm afraid"
									+ "You'll be killed.",
							Color.white);
			return;
		}
		case 9: {
			ChatHandler
					.sendMessage(
							name
									+ ": I'm not racist but when you're driving in the East Bay,"
									+ " roll up your windows and lock your doors.",
							Color.white);
			return;
		}
		case 10: {
			ChatHandler
					.sendMessage(
							name
									+ ": Have you seen our son? He was playing hide and seek with me and he seems to have"
									+ "literally dissapeared!", Color.white);
			return;
		}
		case 11: {
			ChatHandler.sendMessage(name
					+ ": Don't tell the children about the Apes in the North,"
					+ " you'll scare them.", Color.white);
			return;
		}
		case 12: {
			ChatHandler.sendMessage(name
					+ ": Can you pick up some food on you're way home?", Color.white);
			return;
		}
		default: {
			ChatHandler.sendMessage(name + ": Hello!", Color.white);
			return;
		}
		}

	}

}
