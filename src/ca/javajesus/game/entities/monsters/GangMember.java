package ca.javajesus.game.entities.monsters;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.entities.projectiles.Bullet;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class GangMember extends Monster {

	protected Ellipse2D.Double standRange;

	public GangMember(Level level, String name, int x, int y, int speed,
			int health, int type) {
		super(level, name, x, y, speed, 14, 16, 1, health, Colors.get(-1, 111,
				555, 543));
		getType(type);
		standRange = new Ellipse2D.Double(x - RADIUS / 4, y - RADIUS / 4,
				RADIUS / 2, RADIUS / 2);
		this.bar = new HealthBar(level, 0 + 2 * sheet.boxes, this.x, this.y,
				this, 0);
		if (level != null)
			level.addEntity(bar);
	}

	private void getType(int type) {
		switch (type) {
		case 0:
			yTile = 3;
			break;
		case 1:
			yTile = 10;
			break;
		default:
			yTile = 3;
			break;
		}
	}

	public void tick() {

		super.tick();

		if (isShooting) {
			shootTickCount++;
			if (shootTickCount > 20) {
				shootTickCount = 0;
				isShooting = false;
			}
		}
		int xa = 0;
		int ya = 0;
		if (mob != null && this.aggroRadius.intersects(mob.getBounds())) {
			if (!cooldown) {
				isShooting = true;
				level.addEntity(new Bullet(level, this.x + 5, (this.y - 7), mob
						.getX(), mob.getY() - 4, this, 3));
			}
			if (!this.standRange.intersects(mob.getBounds())) {

				if (mob.getX() > this.x) {
					xa++;
				}
				if (mob.getX() < this.x) {
					xa--;
				}
				if (mob.getY() > this.y) {
					ya++;
				}
				if (mob.getY() < this.y) {
					ya--;
				}
			} else {
				if (mob.isMoving()) {
					if (mob.getX() > this.x) {
						xa--;
					}
					if (mob.getX() < this.x) {
						xa++;
					}
					if (mob.getY() > this.y) {
						ya--;
					}
					if (mob.getY() < this.y) {
						ya++;
					}
				}
			}
		}

		if (tickCount % 100 == 0) {
			cooldown = false;
		} else {
			cooldown = true;
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
		this.getBounds().setLocation((int) this.x - 7, (int) this.y - 16);
		this.getOuterBounds().setLocation((int) this.x - 9, (int) this.y - 18);
		this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
				RADIUS);
		this.standRange.setFrame(x - RADIUS / 4, y - RADIUS / 4, RADIUS / 2,
				RADIUS / 2);
		int xTile = 0;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 10;
		}
		if (getDirection() == Direction.SOUTH) {
			xTile += 2;
		} else if (isLatitudinal(getDirection())) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			if (getDirection() == Direction.WEST) {
				flipTop = 1;
				flipBottom = 1;
			} else {
				flipTop = 0;
				flipBottom = 0;
			}
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = (y - modifier / 2 - 4) - modifier;

		if (isDead) {
			isShooting = false;
			xTile = 12;
		}

		if (isShooting) {

			xTile = 14;

			// Upper body 1
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * sheet.boxes, color, flipTop, scale, sheet);

			// Upper body 2
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * sheet.boxes, color, flipTop, scale,
					sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom),
					yOffset + modifier, xTile + (yTile + 1) * sheet.boxes,
					color, flipBottom, scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color,
					flipBottom, scale, sheet);
		} else {

			// Upper body
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * sheet.boxes, color, flipTop, scale, sheet);

			// Upper body
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * sheet.boxes, color, flipTop, scale,
					sheet);

			// Lower Body
			screen.render(xOffset + (modifier * flipBottom),
					yOffset + modifier, xTile + (yTile + 1) * sheet.boxes,
					color, flipBottom, scale, sheet);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color,
					flipBottom, scale, sheet);
		}

	}

	public void speak(Player player) {
		isTalking = true;
		ChatHandler.sendMessage("I'm in charge here.", Color.white);
		return;
	}

}
