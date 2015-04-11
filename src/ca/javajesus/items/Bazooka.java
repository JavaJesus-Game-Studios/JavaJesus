package ca.javajesus.items;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;

public class Bazooka extends Gun {

	private Player player;

	public Bazooka() {
		super("Bazooka", 10, 5, 0, new int[] { 0xFF000000, 0xFF434343, 0xFF371B09 },
				"Standard Explosive Artillery", 5, 0, 2, 10, 20, 300,
				Ammo.MISSILE, SoundHandler.sound.explosion);
	}

	public void addPlayer(Player player) {
		this.player = player;
	}

	public void renderGun(Screen screen) {

		int xTile = 0;
		int yTile = 8;
		int walkingAnimationSpeed = 4;
		int flipTop = (player.getNumSteps() >> walkingAnimationSpeed) & 1;
		int flipBottom = (player.getNumSteps() >> walkingAnimationSpeed) & 1;
		int modifier = 8 * player.getScale();
		int xOffset = player.getX() - modifier / 2;
		int yOffset = player.getY() - modifier / 2 - 4;
		int xx = 0;

		if (player.shootingDir == 0) {
			xTile += 16;
			xOffset += 8;
			flipTop = 0;
			flipBottom = 0;
		} else if (player.shootingDir == 1) {

			xTile += 6;
			xOffset -= 8;
			flipTop = 0;
			flipBottom = 0;

		} else if (player.shootingDir > 1) {
			if (player.shootingDir == 2) {
				xx = 8;
			}
			flipTop = (player.shootingDir - 1) % 2;
			flipBottom = (player.shootingDir - 1) % 2;
		}
		// Upper Body 1
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
				* 32, color, flipTop, player.getScale(), SpriteSheet.player);
		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
				(xTile + 1) + yTile * 32, color, flipTop, player.getScale(),
				SpriteSheet.player);

		// Upper Body 3
		screen.render(xOffset + 2 * modifier - (modifier * flipTop) - xx * 2,
				yOffset, (xTile + 2) + yTile * 32, color, flipTop,
				player.getScale(), SpriteSheet.player);

		// Lower Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier,
				xTile + (yTile + 1) * 32, color, flipBottom, player.getScale(),
				SpriteSheet.player);

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom,
				player.getScale(), SpriteSheet.player);

		// Lower Body 3
		screen.render(
				xOffset + 2 * modifier - (modifier * flipBottom) - xx * 2,
				yOffset + modifier, (xTile + 2) + (yTile + 1) * 32, color,
				flipBottom, player.getScale(), SpriteSheet.player);

	}

}
