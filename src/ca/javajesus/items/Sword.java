package ca.javajesus.items;

import java.awt.Rectangle;
import java.util.ArrayList;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.Mob.Direction;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;

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
	private int swordX;
	private int swordY;

	public Sword(String name, int id, int xTile, int yTile, int swordX, int swordY, int color,
			String description, int swordType, int cooldown, int damage) {
		super(name, id, xTile, yTile, color, description);
		this.swordType = swordType;
		this.COOLDOWN_TIME = cooldown;
		this.damage = damage;
		this.hitBox = new Rectangle(8, 8);
		this.swordX = swordX;
		this.swordY = swordY;
	}

	public void addPlayer(Player player) {
		this.player = player;
		hitBox.setLocation(player.getBounds().x, player.getBounds().y);
	}

	public void tick() {

		if (player != null && isSwinging) {
			for (Mob m : player.getLevel().getMobs()) {
				if (m != player && !mobsHit.contains(m) && this.hitBox.intersects(m.getBounds())) {
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
			SoundHandler.sound.playShortSword();
		}
	}

	public void render(Screen screen) {
		if (!isSwinging)
			return;
		if (player != null) {
			switch (player.getDirection()) {
			case NORTH:
				hitBox.setLocation(player.getBounds().x, player.getBounds().y - 10);
				break;
			case SOUTH:
				hitBox.setLocation(player.getBounds().x, player.getBounds().y + 10);
				break;
			case WEST:
				hitBox.setLocation(player.getBounds().x - 10, player.getBounds().y);
				break;
			case EAST:
				hitBox.setLocation(player.getBounds().x + 10, player.getBounds().y);
				break;
			default:
				hitBox.setLocation(player.getBounds().x, player.getBounds().y);
				break;
			}

		}
		int xTile = swordX;
		int yTile = swordY;
		int walkingAnimationSpeed = 4;
		if (player.getSpeed() == 3) {
			player.changeSteps(1);
		}
		int flipTop = (player.getNumSteps() >> walkingAnimationSpeed) & 1;
		int flipBottom = (player.getNumSteps() >> walkingAnimationSpeed) & 1;
		int modifier = 8 * player.getScale();
		int xOffset = player.getX() - modifier / 2;
		int yOffset = player.getY() - modifier / 2 - 4;

		if (player.getDirection() == Direction.NORTH) {
			xTile += 2;
		} else if (player.isLatitudinal(player.getDirection())) {
			xTile += 4;
			if (player.getDirection() == Direction.WEST) {
				flipTop = 1;
				flipBottom = 1;
			} else {
				flipTop = 0;
				flipBottom = 0;
			}
		}
		// Upper Body 1
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
				* 32, color, flipTop, player.getScale(), SpriteSheet.swords);
		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
				(xTile + 1) + yTile * 32, color, flipTop, player.getScale(),
				SpriteSheet.swords);

		// Lower Body 1
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier,
				xTile + (yTile + 1) * 32, color, flipBottom, player.getScale(),
				SpriteSheet.swords);

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * 32, color, flipBottom,
				player.getScale(), SpriteSheet.swords);

		if (player.isLongitudinal(player.getDirection())) {
			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom), yOffset + 2
					* modifier, xTile + (yTile + 2) * 32, color, flipBottom,
					player.getScale(), SpriteSheet.swords);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ 2 * modifier, (xTile + 1) + (yTile + 2) * 32, color,
					flipBottom, player.getScale(), SpriteSheet.swords);
		} else {
			int num = 0;
			if (player.getDirection() == Direction.WEST) {
				num = 16;
			}
			// Upper Body 2
			screen.render(xOffset + 2 * modifier - num - (modifier * flipTop),
					yOffset, (xTile + 2) + yTile * 32, color, flipTop,
					player.getScale(), SpriteSheet.swords);

			// Lower Body 2
			screen.render(xOffset + 2 * modifier - num
					- (modifier * flipBottom), yOffset + modifier, (xTile + 2)
					+ (yTile + 1) * 32, color, flipBottom, player.getScale(),
					SpriteSheet.swords);
		}
	}

}
