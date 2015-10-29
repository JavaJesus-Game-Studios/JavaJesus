package game.entities.npcs;

import game.ChatHandler;
import game.entities.Player;
import game.graphics.SpriteSheet;

import java.awt.Color;

import level.Level;

public class Bautista extends NPC {
	
	private static final long serialVersionUID = -4685371991295485791L;

	public Bautista(Level level, int x, int y) {
		super(level, "Ranchero Bautista", x, y, 1, 16, 16, 500, null, 0, 16, "",
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
					+ ": Is that any way to dress in my lands?", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(name + ": I'm Ranchero Bautista, the true inheritor of the peninsula."
					+ "Until that bendejo Hillsborough and his family stole my land! And now these "
					+ "pasty 'techies' threaten my borders from the south, and these peasants threaten my"
					+ " authority from within, these are dark times mi amigo.",
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
					.displayText(name + ": They are my savages! I have the right to do with "
							+ "them as I please!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(name + ": Hola Officer!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(name + ": Mis cabelleros son bueno a guerro!", Color.white);
			return;
		}
		case 7: {
			ChatHandler
					.displayText(
							name
									+ ": Have you been to San Cisco? It has declined into squalor since "
									+ "the days of Bautista rule.",
							Color.white);
			return;
		}
		case 8: {
			ChatHandler
					.displayText(
							name
									+ ": My fellow ranchero's have even turned against me! I have enemies everywhere "
									+ "my friend you must help me squash this rebellion and reclaim my own glory!",
							Color.white);
			return;
		}
		case 9: {
			ChatHandler
					.displayText(
							name
									+ ": I am not torturing the capture rebels, they are my subjects and I am just"
									+ "however as my subjects they must obey my command, and when they don't"
									+ " I must take action, and physical torment seems to be most persuasive.",
							Color.white);
			return;
		}
		case 10: {
			ChatHandler
					.displayText(
							name
									+ ": Poor people disgust me, rich people disgust me, the only good thing "
									+ "in life is women. Haha!", Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(name
					+ ": Those apes have spies everywhere, I know the savages are in league with them,"
					+ "the apes will help them to steal my land! And don't try to convince me "
					+ "that thes apes did not influence the rebellion!", Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(name
					+ ": Hola, Esteban Norteruta!", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(name + ": I must become my own Napoleon!", Color.white);
			return;
		}
		}

	}

}
