package game.entities.npcs;

import game.ChatHandler;
import game.entities.Player;
import game.graphics.SpriteSheet;

import java.awt.Color;

import level.Level;

public class Daughter extends NPC {

	private static final long serialVersionUID = -6160414459786768128L;

	public Daughter(Level level, int x, int y) {
		super(level, "Daughter", x, y, 1, 16, 16, 500, null, 0, 5, "",
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
			ChatHandler.displayText(name + ": Hi daddy!",
					Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(name
					+ ": I love you daddy!", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(name + ": Daddy can we watch Chilled?",
					Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(name + ": Daddy why is the sky blue?",
					Color.white);
			return;
		}
		case 4: {
			ChatHandler
					.displayText(name + ": Daddy how many stars are there!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(name + ": Daddy, Mommy said you're immature, what does that mean?", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(name + ": Daddy! He took my dolly!", Color.white);
			return;
		}
		case 7: {
			ChatHandler
					.displayText(
							name
									+ ": I want to see the tall buildings!",
							Color.white);
			return;
		}
		case 8: {
			ChatHandler
					.displayText(
							name
									+ ": Daddy can we watch the Tiger King?",
							Color.white);
			return;
		}
		case 9: {
			ChatHandler
					.displayText(
							name
									+ ": Da da da dum da dum dah duh dum dee doo dum", Color.white);
			return;
		}
		case 10: {
			ChatHandler
					.displayText(
							name
									+ ": Daddy let's go to the park!", Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(name
					+ ": Mommy said we're going on a picnic tomorrow!", Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(name
					+ ": Daddy why is your face so scratchy?", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(name + ": Bye!", Color.white);
			return;
		}
		}

	}
}
