package ca.javajesus.items;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.projectiles.Missile;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Bazooka extends Gun{
	
	private Player player;

	public Bazooka() {
		super("Bazooka", 10, 5, 0, Colors.get(-1,
				Colors.fromHex("000000"), Colors.fromHex("#434343"), Colors.fromHex("#371b09")), 
				"Standard Explosive Artillery", 5, 0, 2,
				10, 20, 200);
	}
	
	public void addPlayer(Player player) {
		this.player = player;
	}
	
	public void fire(Level level, double x, double y, int dir, Player player) {
		if (ammo > 0 && !isReloading && canFire) {
			level.addEntity(new Missile(level, x, y, dir, player, damage));
			ammo--;
			canFire = false;
		}
	}
	
	public void renderGun(Screen screen) {
		
		int xTile = 0;
		int yTile = 8;
		int walkingAnimationSpeed = 4;
		int flipTop = (player.getNumSteps() >> walkingAnimationSpeed) & 1;
		int flipBottom = (player.getNumSteps() >> walkingAnimationSpeed) & 1;
		int modifier = 8 * player.getScale();
		double xOffset = player.getX() - modifier / 2.0;
		double yOffset = player.getY() - modifier / 2.0 - 4;
		int xx = 0;

		if (player.shootingDir == 0) {
			xTile += 16;
			xOffset += 8;
			flipTop = 0;
			flipBottom = 0;
		} else if (player.shootingDir == 1) {
			
			xTile += 6;
			xOffset -=8;
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
		screen.render(xOffset + modifier - (modifier * flipTop) , yOffset,
				(xTile + 1) + yTile * 32, color, flipTop, player.getScale(),
				SpriteSheet.player);
		
		// Upper Body 3
				screen.render(xOffset + 2 * modifier - (modifier * flipTop) - xx * 2, yOffset,
						(xTile + 2) + yTile * 32, color, flipTop, player.getScale(),
						SpriteSheet.player);

		// Lower Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier,
				xTile + (yTile + 1) * 32, color, flipBottom, player.getScale(),
				SpriteSheet.player);

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom,
				player.getScale(), SpriteSheet.player);
		
		// Lower Body 3
				screen.render(xOffset + 2 * modifier - (modifier * flipBottom) - xx * 2, yOffset
						+ modifier, (xTile + 2) + (yTile + 1) * 32, color, flipBottom,
						player.getScale(), SpriteSheet.player);


		
	}

}
