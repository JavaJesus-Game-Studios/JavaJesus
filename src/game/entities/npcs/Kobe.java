package game.entities.npcs;

import game.ChatHandler;
import game.entities.Player;
import game.graphics.SpriteSheet;

import java.awt.Color;

import level.Level;

public class Kobe extends NPC {
	
	private static final long serialVersionUID = 4773669926144737010L;

	public Kobe(Level level, int x, int y) {
		super(level, "Kobe", x, y, 1, 16, 16, 500, null, 0, 14, "",
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
				ChatHandler.displayText(
						name + ": " + currentQuest.preDialogue(), Color.blue);
				sound.play(sound.levelup);
				currentQuest.nextPhase();
				return;
			}
			case 1: {
				ChatHandler.displayText(name + ": " + currentQuest.dialogue(),
						Color.blue);
				return;
			}
			case 2: {
				ChatHandler.displayText(
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
			ChatHandler.displayText(name + ": I'm richer than you.",
					Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(name
					+ ": Is that any way to dress in my court?", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(name + ": I'm Lord Hillsborough.",
					Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(name + ": Get out of my sight.",
					Color.white);
			return;
		}
		case 4: {
			ChatHandler
					.displayText(name + ": I lost my chickens!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(name + ": Hello Officer!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(name + ": Who goes there!", Color.white);
			return;
		}
		case 7: {
			ChatHandler
					.displayText(
							name
									+ ": Have you been to San Cisco? I hear they're having lovely weather.",
							Color.white);
			return;
		}
		case 8: {
			ChatHandler
					.displayText(
							name
									+ ": It's you! It really is! All Hail the Hero of the Bay!",
							Color.white);
			return;
		}
		case 9: {
			ChatHandler
					.displayText(
							name
									+ ": I'm not racist but when you're driving in the East Bay,"
									+ " roll up your windows and lock your doors.",
							Color.white);
			return;
		}
		case 10: {
			ChatHandler
					.displayText(
							name
									+ ": Have you seen my friend Bob? He's a peasant and he seems to have"
									+ "literally dissapeared!", Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(name
					+ ": Nasty business it is with those Apes in the North!"
					+ " Nasty business indeed.", Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(name
					+ ": Hola, mi nombre es Esteban Norteruta!", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(name + ": Hello!", Color.white);
			return;
		}
		}

	}

}
