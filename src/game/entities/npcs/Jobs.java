package game.entities.npcs;

import game.ChatHandler;
import game.entities.Player;
import game.graphics.SpriteSheet;

import java.awt.Color;

import level.Level;

public class Jobs extends NPC {
	
	private static final long serialVersionUID = -7655486644422463084L;

	public Jobs(Level level, int x, int y) {
		super(level, "Brokovsky", x, y, 1, 16, 16, 500, null, 10, 0, "",
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
			ChatHandler.displayText(name + ": I'm richer than you. In fact I'm rich than everyone, except"
					+ " for that bastar William Fence in Washington",
					Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(name
					+ ": Is that any way to dress in my city?", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(name + ": I'm Sascha Brovosky.",
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
					.displayText(name + ": I lost my patents!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(name + ": If Jugle thinks they can compete with me in the mobile business"
					+ " they can go straight to HELL!", Color.white);
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
					+ ": Uggh, I see the lower class has found an entrance into our beloved city", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(name + ": Hello!", Color.white);
			return;
		}
		}

	}

}
