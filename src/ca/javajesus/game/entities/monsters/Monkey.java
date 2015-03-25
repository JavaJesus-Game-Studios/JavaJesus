package ca.javajesus.game.entities.monsters;

import java.awt.Color;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.Game;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Monkey extends Monster {
	private static int[] color = { 0xFF2A1609, 0xFF391E0C, 0xFFB08162 };
	private boolean isAttacking = false;
	private int coolTicks = 0;

	public Monkey(Level level, String name, int x, int y, int speed, int health) {
		super(level, name, x, y, speed, 12, 16, 8, health, color);
		this.bar = new HealthBar(level, 0 + 2 * sheet.boxes, this.x, this.y,
				this, 0);
		this.strength = 10;
		if (level != null)
			level.addEntity(bar);
	}

	public void tick() {
		super.tick();

		if (this.aggroRadius.intersects(Game.player.getBounds())
				&& random.nextInt(400) == 0) {
			sound.play(SoundHandler.sound.chimpanzee);
		}

		if (mob != null && !cooldown
				&& this.getOuterBounds().intersects(mob.getBounds())) {
			isAttacking = true;
			cooldown = true;
			mob.damage(this.strength);
		}

		if (isAttacking) {
			shootTickCount++;
			if (shootTickCount % 40 == 0) {
				shootTickCount = 0;
				isAttacking = false;
			}
		}

		if (cooldown) {
			coolTicks++;
			if (coolTicks % 100 == 0) {
				coolTicks = 0;
				cooldown = false;
			}
		}

		int xa = 0;
		int ya = 0;

		if (mob != null && !isAttacking
				&& this.aggroRadius.intersects(mob.getBounds())
				&& !this.getOuterBounds().intersects(mob.getBounds())) {

			if (mob.getX() > this.x) {
				xa++;
			}
			if (mob.getX() < this.x) {
				xa--;
			}
			if (mob.getY() > this.y - 8) {
				ya++;
			}
			if (mob.getY() < this.y - 8) {
				ya--;
			}
		}

		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			setMoving(true);
			move(xa, ya);
		} else {
			setMoving(false);
		}
	}

	public void render(Screen screen) {
		super.render(screen);

		if (getDirection() == Direction.WEST) {
			this.getBounds().setLocation(this.x - (this.width / 2) + 8,
					this.y - (this.height / 2));
			this.getOuterBounds().setLocation(
					this.x - (int) getBounds().getWidth() / 2 + 10,
					this.y - (int) getBounds().getHeight() / 2);
		} else {
			this.getBounds().setLocation(this.x - (this.width / 2),
					this.y - (this.height / 2));
			this.getOuterBounds().setLocation(
					this.x - (int) getBounds().getWidth() / 2 - 2,
					this.y - (int) getBounds().getHeight() / 2);
		}

		int xTile = 0;
		int walkingSpeed = 4;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 10;
		}
		if (getDirection() == Direction.SOUTH) {
			xTile += 2;
		} else if (isLatitudinal(getDirection())) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			if (getDirection() == Direction.WEST) {
				flip = 1;
				flip = 1;
			} else {
				flip = 0;
				flip = 0;
			}
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier;
		int yOffset = y - modifier;

		if (isAttacking) {
			if (getDirection() == Direction.NORTH) {
				xTile = 18;
			}
			if (getDirection() == Direction.SOUTH) {
				xTile = 14;
			} else if (isLatitudinal(getDirection())) {
				xTile = 16 + ((numSteps >> walkingSpeed) & 1) * 2;
				if (getDirection() == Direction.WEST) {
					flip = 1;
					flip = 1;
				} else {
					flip = 0;
					flip = 0;
				}
			}
		}

		if (isDead)
			xTile = 12;

		// Upper body
		screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile
				* sheet.boxes, color, flip, scale, sheet);

		// Upper body
		screen.render(xOffset + modifier - (modifier * flip), yOffset,
				(xTile + 1) + yTile * sheet.boxes, color, flip, scale, sheet);

		// Lower Body
		screen.render(xOffset + (modifier * flip), yOffset + modifier,
				xTile + (yTile + 1) * sheet.boxes, color, flip, scale,
				sheet);

		// Lower Body
		screen.render(xOffset + modifier - (modifier * flip), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color,
				flip, scale, sheet);
	}

	public void speak(Player player) {
		isTalking = true;
		ChatHandler.sendMessage("Chimp no speak with human.", Color.white);
		return;
	}
}
