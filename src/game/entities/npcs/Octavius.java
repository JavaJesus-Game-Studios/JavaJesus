package game.entities.npcs;

import game.ChatHandler;
import game.entities.Player;
import game.graphics.Screen;
import game.graphics.SpriteSheet;

import java.awt.Color;

import level.Level;

public class Octavius extends NPC {

	private static final long serialVersionUID = -6699489501735991630L;

	public Octavius(Level level, int x, int y) {
		super(level, "Octavius", x, y, 1, 16, 24, 500, null, 0, 12, "",
				0);
		this.sheet = SpriteSheet.characters;
	}
	
	public void render(Screen screen) {
		super.render(screen);
		int xTile = this.xTile;
		int yTile = this.yTile - 1;

		int walkingAnimationSpeed = 4;

		int flip = (numSteps >> walkingAnimationSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 10;
		}
		if (getDirection() == Direction.SOUTH) {
			xTile += 2;
		} else if (isLatitudinal(getDirection())) {
			xTile += 4 + ((numSteps >> walkingAnimationSpeed) & 1) * 2;
			if (getDirection() == Direction.WEST) {
				flip = 1;
			} else {
				flip = 0;
			}
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier;
		int yOffset = y - 2 * modifier;

		// Upper body 1
		screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile
				* sheet.boxes, this.color, flip, scale, sheet);

		// Upper body 2
		screen.render(xOffset + modifier - (modifier * flip), yOffset,
				(xTile + 1) + yTile * sheet.boxes, this.color, flip, scale,
				sheet);
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
			ChatHandler.displayText(name + ": Human must go.",
					Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(name
					+ ": Ape together strong, Ape fighting weak.", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(name + ": Kobe must be punished.",
					Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(name + ": Gorilla loyal, Gorilla not abandon Chimp.",
					Color.white);
			return;
		}
		case 4: {
			ChatHandler
					.displayText(name + ": Bonobo bad, Kobe Bonobo. Human worse, human violent"
							+ "human kill ape.", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(name + ": You should not, be here!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(name + ": My kingdom, is not, for Humans!", Color.white);
			return;
		}
		case 7: {
			ChatHandler
					.displayText(
							name
									+ ": I love the lights in Human City.",
							Color.white);
			return;
		}
		case 8: {
			ChatHandler
					.displayText(
							name
									+ ": Ape is family, You ape to, part of Ape family.",
							Color.white);
			return;
		}
		case 9: {
			ChatHandler
					.displayText(
							name
									+ ": Demon help Kobe, Demon kill Ape! Ape hate Demon.",
							Color.white);
			return;
		}
		case 10: {
			ChatHandler
					.displayText(
							name
									+ ": My son, no listen to me.", Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(name
					+ ": Human raise me, human hurt me, ape attack human city, ape cross human bridge"
					+ "ape live in forest.", Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(name
					+ ": This is ape home, go back to human home!", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(name + ": Hello!", Color.white);
			return;
		}
		}

	}
}
