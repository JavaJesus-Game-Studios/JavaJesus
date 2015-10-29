package game.entities.npcs;

import game.ChatHandler;
import game.entities.Player;
import game.graphics.SpriteSheet;

import java.awt.Color;

import level.Level;

public class Son extends NPC {
	
	private static final long serialVersionUID = -3810366725712991982L;

	public Son(Level level, int x, int y) {
		super(level, "Son", x, y, 1, 16, 16, 500, null, 0, 7, "",
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
			ChatHandler.displayText(name + ": Hey dad.",
					Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(name
					+ ": Can I see your gun?", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(name + ": Dady feel how strong I am.",
					Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(name + ": Dad let's race!",
					Color.white);
			return;
		}
		case 4: {
			ChatHandler
					.displayText(name + ": Can I play your video games?", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(name + ": Let's watch Ultraman!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(name + ": Mom won't let me play with water guns.", Color.white);
			return;
		}
		case 7: {
			ChatHandler
					.displayText(
							name
									+ ": Have you shot any bad guys?",
							Color.white);
			return;
		}
		case 8: {
			ChatHandler
					.displayText(
							name
									+ ": C'mon let me see your gun.",
							Color.white);
			return;
		}
		case 9: {
			ChatHandler
					.displayText(
							name
									+ ": Dad make her give me back my Owlman action figure.",
							Color.white);
			return;
		}
		case 10: {
			ChatHandler
					.displayText(
							name
									+ ": Mark said that his friend Don went into the forest up north and saw a Chimpanzee"
									+ "and then it bit his finger off so now he has a fake finger"
									+ "but it looks just like a real finger!", Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(name
					+ ": Are there really Chimpanzees in the forest?", Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(name
					+ ": Can we go see Lord Hillsborough's castle, Mark said the Knights let you play with their swords!",
					Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(name + ": Hello!", Color.white);
			return;
		}
		}

	}

}
