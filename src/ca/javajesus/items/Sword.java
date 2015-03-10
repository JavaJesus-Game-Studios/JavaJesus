package ca.javajesus.items;

import java.awt.Rectangle;
import java.util.ArrayList;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Sword extends Item {

	public int swordType;
	public boolean isCoolingDown = false;
	private int COOLDOWN_TIME;
	private int cooldownTicks = 0;
	private int damage;
	private Player player;
	public boolean isSwinging = false;
	private Rectangle hitBox;
	ArrayList<Mob> mobsHit = new ArrayList<Mob>();

	public Sword(String name, int id, int xTile, int yTile, int color,
			String description, int swordType, int cooldown, int damage) {
		super(name, id, xTile, yTile, color, description);
		this.swordType = swordType;
		this.COOLDOWN_TIME = cooldown;
		this.damage = damage;
		this.hitBox = new Rectangle(8, 8);

	}

	public void addPlayer(Player player) {
		this.player = player;
		hitBox.setLocation(player.hitBox.x, player.hitBox.y);
	}

	public void tick() {

		if (player != null && isSwinging) {
			for (Mob m : player.getLevel().getMobs()) {
				if (m != player && !mobsHit.contains(m) && this.hitBox.intersects(m.hitBox)) {
					m.damage(damage, damage + 2);
					mobsHit.add(m);
				}
			}
		}

		if (isCoolingDown) {
			cooldownTicks++;
		}

		if (cooldownTicks % COOLDOWN_TIME == 0) {
			cooldownTicks = 0;
			isCoolingDown = false;
			isSwinging = false;
		}

	}

	public void swing() {
		if (!isCoolingDown) {
			isCoolingDown = true;
			isSwinging = true;
			mobsHit.clear();
			SoundHandler.sound.play(SoundHandler.sound.sheathe);
		}
	}

	public void render(Screen screen) {
		if (!isSwinging)
			return;
		if (player != null) {
			switch (player.movingDir) {
			case 0:
				hitBox.setLocation(player.hitBox.x, player.hitBox.y - 10);
				break;
			case 1:
				hitBox.setLocation(player.hitBox.x, player.hitBox.y + 10);
				break;
			case 2:
				hitBox.setLocation(player.hitBox.x - 10, player.hitBox.y);
				break;
			case 3:
				hitBox.setLocation(player.hitBox.x + 10, player.hitBox.y);
				break;
			default:
				hitBox.setLocation(player.hitBox.x, player.hitBox.y);
				break;
			}

		}
		int xTile = this.xTile;
		int walkingAnimationSpeed = 4;
		if (player.speed == 3) {
			player.numSteps++;
		}
		int flipTop = (player.numSteps >> walkingAnimationSpeed) & 1;
		int flipBottom = (player.numSteps >> walkingAnimationSpeed) & 1;
		int modifier = 8 * player.scale;
		double xOffset = player.x - modifier / 2.0;
		double yOffset = player.y - modifier / 2.0 - 4;

		if (player.movingDir == 0) {
			xTile += 2;
		} else if (player.movingDir > 1) {
			xTile += 4;
			flipTop = (player.movingDir - 1) % 2;
			flipBottom = (player.movingDir - 1) % 2;
		}
		// Upper Body 1
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
				* 32, color, flipTop, player.scale, SpriteSheet.swords);
		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
				(xTile + 1) + yTile * 32, color, flipTop, player.scale,
				SpriteSheet.swords);

		// Lower Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier,
				xTile + (yTile + 1) * 32, color, flipBottom, player.scale,
				SpriteSheet.swords);

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom,
				player.scale, SpriteSheet.swords);

		if (player.movingDir < 2) {
			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom), yOffset + 2
					* modifier, xTile + (yTile + 2) * 32, color, flipBottom,
					player.scale, SpriteSheet.swords);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ 2 * modifier, (xTile + 1) + (yTile + 2) * 32, color,
					flipBottom, player.scale, SpriteSheet.swords);
		} else {
			int num = 0;
			if (player.movingDir == 2) {
				num = 16;
			}
			// Upper Body 2
			screen.render(xOffset + 2 * modifier - num - (modifier * flipTop),
					yOffset, (xTile + 2) + yTile * 32, color, flipTop,
					player.scale, SpriteSheet.swords);

			// Lower Body 2
			screen.render(xOffset + 2 * modifier - num
					- (modifier * flipBottom), yOffset + modifier, (xTile + 2)
					+ (yTile + 1) * 32, color, flipBottom, player.scale,
					SpriteSheet.swords);
		}
	}

}
