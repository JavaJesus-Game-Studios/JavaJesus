package game.entities.npcs;

import game.ChatHandler;
import game.entities.Player;
import game.graphics.SpriteSheet;

import java.awt.Color;

import level.Level;

public class Zorra extends NPC {
	
	private static final long serialVersionUID = -4981994300664864597L;

	public Zorra(Level level, int x, int y) {
		super(level, "Zorra", x, y, 1, 16, 16, 500, null, 10, 5, "",
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
			ChatHandler.displayText(name + ": We freed you, now help us!",
					Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(name
					+ ": Bautista is terrorizing my people!", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(name + ": Help me win back my city!",
					Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(name + ": Mierda!",
					Color.white);
			return;
		}
		case 4: {
			ChatHandler
					.displayText(name + ": Bautista es un punto!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(name + ": Hello Officer!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(name + ": Bendejo!", Color.white);
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
					+ ": Hola, mi nombre es Zorra!", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(name + ": Hola!", Color.white);
			return;
		}
		}

	}

}
