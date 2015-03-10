package ca.javajesus.items;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;

public class Bazooka extends Gun{
	
	private Player player;

	public Bazooka() {
		super("Bazooka", 8, 10, 0, Colors.get(-1,
				500, 500, Colors.fromHex("#FF0000")), "Standard Firearm", 5, 0, 8,
				10, 20, 2);
	}
	
	public void addPlayer(Player player) {
		this.player = player;
	}
	
	public void renderGun(Screen screen) {
		
		int xTile = 0;
		int yTile = 8;
		int walkingAnimationSpeed = 4;
		int flipTop = (player.numSteps >> walkingAnimationSpeed) & 1;
		int flipBottom = (player.numSteps >> walkingAnimationSpeed) & 1;
		int modifier = 8 * player.scale;
		double xOffset = player.x - modifier / 2.0;
		double yOffset = player.y - modifier / 2.0 - 4;

		if (player.shootingDir == 0) {
			xTile += 6;
		} else if (player.shootingDir > 1) {
			xTile += 15;
			flipTop = (player.shootingDir - 1) % 2;
			flipBottom = (player.shootingDir - 1) % 2;
		}
		// Upper Body 1
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
				* 32, color, flipTop, player.scale, SpriteSheet.player);
		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
				(xTile + 1) + yTile * 32, color, flipTop, player.scale,
				SpriteSheet.player);
		
		// Upper Body 3
				screen.render(xOffset + 2 * modifier - (modifier * flipTop), yOffset,
						(xTile + 2) + yTile * 32, color, flipTop, player.scale,
						SpriteSheet.player);

		// Lower Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier,
				xTile + (yTile + 1) * 32, color, flipBottom, player.scale,
				SpriteSheet.player);

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom,
				player.scale, SpriteSheet.player);
		
		// Lower Body 3
				screen.render(xOffset + 2 * modifier - (modifier * flipBottom), yOffset
						+ modifier, (xTile + 2) + (yTile + 1) * 32, color, flipBottom,
						player.scale, SpriteSheet.player);


		
	}

}
