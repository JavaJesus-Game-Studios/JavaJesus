package ca.javajesus.items;

import java.awt.Rectangle;
import java.util.ArrayList;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.Mob.Direction;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;

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
	private SpriteSheet sheet = SpriteSheet.swords;
	private boolean isLong;

	private int[] powerPoints;
	private int startPos;

	private boolean powerSwinging = false;
	private int powerSwingTicks = 0;
	private int powerSwingModifier = 0;
	private int powerOffset;

	public Sword(String name, int id, int xTile, int yTile, int swordX,
			int swordY, int[] color, String description, int swordType,
			int cooldown, int damage, int[] powerPoints) {
		super(name, id, xTile, yTile, color, description);
		this.swordType = swordType;
		this.COOLDOWN_TIME = cooldown;
		this.damage = damage;
		this.hitBox = new Rectangle(8, 8);
		this.swordX = swordX;
		this.swordY = swordY;
		if (swordY > 0 && swordY < 20) {
			isLong = true;
		}
		this.powerPoints = powerPoints;
	}

	public void addPlayer(Player player) {
		this.player = player;
		hitBox.setLocation(player.getBounds().x, player.getBounds().y);
	}

	public void tick() {

		if (player != null && isSwinging) {
			for (Mob m : player.getLevel().getMobs()) {
				if (m != player && !mobsHit.contains(m)
						&& this.hitBox.intersects(m.getBounds())) {
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

		if (powerSwinging) {
			powerSwingTicks++;
			if (powerSwingTicks % (COOLDOWN_TIME / 5) == 0) {
				powerSwingModifier += 2;
				if (player.isLatitudinal(player.getDirection())) {
					powerSwingModifier++;
				}
				if (powerSwingTicks == COOLDOWN_TIME) {
					powerSwingModifier = 0;
					powerSwingTicks = 0;
					powerSwinging = false;
				}
			}
		}

	}

	public void swing() {
		if (!isCoolingDown) {
			isCoolingDown = true;
			isSwinging = true;
			mobsHit.clear();
			SoundHandler.sound.playShortSword();
			if (player.input.shift.isPressed()) {
				if (player.stamina > 20) {
					player.stamina -= 20;
				} else {
					powerSwinging = false;
					return;
				}
				powerSwinging = true;
				switch (player.getDirection()) {
				case NORTH:
					startPos = powerPoints[3];
					break;
				case SOUTH:
					startPos = powerPoints[1];
					break;
				case EAST:
					startPos = powerPoints[2];
					break;
				default:
					startPos = powerPoints[0];
					break;
				}
			} else {
				powerSwinging = false;
			}
		}
	}

	public void render(Screen screen) {
		if (!isSwinging)
			return;
		if (player != null) {
			switch (player.getDirection()) {
			case NORTH:
				hitBox.setLocation(player.getBounds().x,
						player.getBounds().y - 10);
				break;
			case SOUTH:
				hitBox.setLocation(player.getBounds().x,
						player.getBounds().y + 10);
				break;
			case WEST:
				hitBox.setLocation(player.getBounds().x - 10,
						player.getBounds().y);
				break;
			case EAST:
				hitBox.setLocation(player.getBounds().x + 10,
						player.getBounds().y);
				break;
			default:
				hitBox.setLocation(player.getBounds().x, player.getBounds().y);
				break;
			}

		}
		int xTile = swordX;
		int yTile = swordY;
		int[] color = player.getColor();
		if (player.getSpeed() == 3) {
			player.changeSteps(1);
		}
		int flip = 0;
		int modifier = 8 * player.getScale();
		int xOffset = player.getX() - modifier;
		int yOffset = player.getY() - modifier;

		if (player.getDirection() == Direction.NORTH) {
			xTile += 2;
		} else if (player.isLatitudinal(player.getDirection())) {
			xTile += 4 + flip * 3;
			if (player.getDirection() == Direction.WEST) {
				flip = 1;
			} else {
				flip = 0;
			}
		}

		if (powerSwinging) {
			if (startPos + powerSwingModifier > powerPoints[3] + 3) {
				startPos = powerPoints[0];
				powerSwingModifier = 0;
			}
			xTile = startPos + powerSwingModifier;
			flip = 0;
			if (xTile == powerPoints[0]) {
				player.setDirection(Direction.WEST);
			} else if (xTile == powerPoints[2]) {
				player.setDirection(Direction.EAST);
			} else if (xTile < powerPoints[2]) {
				player.setDirection(Direction.SOUTH);
			} else {
				player.setDirection(Direction.NORTH);
			}
		}

		if (player.isLatitudinal(player.getDirection())) {

			if (player.getDirection() == Direction.WEST && !powerSwinging) {
				xOffset -= modifier;
			}

			for (int i = 0; i < 2; i++) {

				screen.render(xOffset + (2 * modifier * flip), yOffset
						+ (modifier * i), xTile + (yTile + i) * sheet.boxes,
						color, flip, player.getScale(), sheet);

				screen.render(xOffset + modifier, yOffset + (modifier * i),
						(xTile + 1) + (yTile + i) * sheet.boxes, color, flip,
						player.getScale(), sheet);

				screen.render(xOffset + 2 * modifier - (2 * modifier * flip),
						yOffset + (modifier * i), (xTile + 2) + (yTile + i)
								* sheet.boxes, color, flip, player.getScale(),
						sheet);
			}

		} else {
			// Upper Body 1
			screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile
					* 32, color, flip, player.getScale(), SpriteSheet.swords);
			// Upper Body 2
			screen.render(xOffset + modifier - (modifier * flip), yOffset,
					(xTile + 1) + yTile * 32, color, flip, player.getScale(),
					SpriteSheet.swords);

			// Lower Body 1
			screen.render(xOffset + (modifier * flip), yOffset + modifier,
					xTile + (yTile + 1) * 32, color, flip, player.getScale(),
					SpriteSheet.swords);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flip), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, color, flip,
					player.getScale(), SpriteSheet.swords);

			if (player.getDirection() == Direction.SOUTH) {

				// Lower Body 1
				screen.render(xOffset + (modifier * flip), yOffset + 2
						* modifier, xTile + (yTile + 2) * 32, color, flip,
						player.getScale(), SpriteSheet.swords);

				// Lower Body 2
				screen.render(xOffset + modifier - (modifier * flip), yOffset
						+ 2 * modifier, (xTile + 1) + (yTile + 2) * 32, color,
						flip, player.getScale(), SpriteSheet.swords);

			} else if (isLong && player.getDirection() == Direction.NORTH) {

				screen.render(xOffset + (modifier * flip), yOffset - modifier,
						xTile + (yTile - 1) * 32, color, flip,
						player.getScale(), SpriteSheet.swords);

				screen.render(xOffset + modifier - (modifier * flip), yOffset
						- modifier, (xTile + 1) + (yTile - 1) * 32, color,
						flip, player.getScale(), SpriteSheet.swords);

			}
		}

	}

}
