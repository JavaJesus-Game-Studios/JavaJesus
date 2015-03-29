package ca.javajesus.game.entities.npcs;

import java.awt.Color;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class LordHillsborough extends NPC {
	
	public LordHillsborough(Level level, int x, int y) {
		super(level, "Lord Hillsborough", x, y, 1, 16, 18, 500, null, 0, 1, "",
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
			ChatHandler.sendMessage(name + ": I'm richer than you.",
					Color.black);
			return;
		}
		case 1: {
			ChatHandler.sendMessage(name
					+ ": Is that any way to dress in my court?", Color.white);
			return;
		}
		case 2: {
			ChatHandler.sendMessage(name + ": I'm Lord Hillsborough, the domain of Hillsborough has been in my family"
					+ "for generations, when my Great-Grandfather won it in the Mexican American War."
					+ "Of course, 'ol Bautista will give a different version of the story.",
					Color.white);
			return;
		}
		case 3: {
			ChatHandler.sendMessage(name + ": Get out of my sight.",
					Color.white);
			return;
		}
		case 4: {
			ChatHandler
					.sendMessage(name + ": Uggh you bore me with your trifling!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.sendMessage(name + ": Hello, salutations, and welcome to my domain Officer!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.sendMessage(name + ": They may be peasants, but they are my peasants, and I'll be damned"
					+ "if some bow-legged, red-skinned devil men try to take them from me!", Color.white);
			return;
		}
		case 7: {
			ChatHandler
					.sendMessage(
							name
									+ ": Oh how I loathe the cities and their lack royalty, how is one to k"
									+ "now if he is better than everyone else without a title"
									+ "to prove it?",
							Color.white);
			return;
		}
		case 8: {
			ChatHandler
					.sendMessage(
							name
									+ ": Manners maketh man.",
							Color.white);
			return;
		}
		case 9: {
			ChatHandler
					.sendMessage(
							name
									+ ": I'm am racist so when you're driving in the East Bay,"
									+ " roll up your windows and lock your doors. To think those"
									+ "gangsters consider themselves to be on an equal status to me, is "
									+ "utterly disgusting.",
							Color.white);
			return;
		}
		case 10: {
			ChatHandler
					.sendMessage(
							name
									+ ": The only thing more boring than this conversation is the conversation"
									+ "I had with the peasant who was missing a sheep last week."
									+ "You wouldn't think people so poor could be so entitled.", Color.white);
			return;
		}
		case 11: {
			ChatHandler.sendMessage(name
					+ ": Pah! Those 'Officers of the law' with their so called 'superior weaponry'"
					+ " in San Cisco couldn't even stop Zoo animals from "
					+ "causing havoc and escaping into the forest, my Knights could have "
					+ "easily put down that rebellion", Color.white);
			return;
		}
		case 12: {
			ChatHandler.sendMessage(name
					+ ": Oh how I miss the elder days that father spoke of, let me tell you"
					+ "their are a few lords I wouldn't miss the chance to duel with. It has been a long"
					+ "while since I've tested my mettle with a blade, I was once the envy of the land you know,"
					+ " quite a catch.", Color.white);
			return;
		}
		default: {
			ChatHandler.sendMessage(name + ": A gentlemen's weapon is the sabre, these 'firearms' that "
					+ "common rabble use are nothing but a fad, simply a louder and more primitive version of a good"
					+ "crossbow. To think the Saxons have sunk below the savages whose land we aquirred is "
					+ "most troubling.", Color.white);
			return;
		}
		}

	}
}
