package game.entities.monsters;

import game.ChatHandler;
import game.entities.Player;
import game.entities.particles.HealthBar;
import game.entities.projectiles.Bullet;
import game.graphics.Screen;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import level.Level;
import utility.Direction;

public class GangMember extends Monster {

	private static final long serialVersionUID = 3322532159669147419L;
	
	protected Ellipse2D.Double standRange;

	public GangMember(Level level, String name, int x, int y, int speed,
			int health, int type) {
		super(level, name, x, y, speed, 14, 16, 1, health, new int[] {
				0xFF111111, 0xFFFFFFFF, 0xFFEDC5AB });
		getType(type);
		standRange = new Ellipse2D.Double(x - RADIUS / 4, y - RADIUS / 4,
				RADIUS / 2, RADIUS / 2);
		this.bar = new HealthBar(level, this.x, this.y, this);
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
						.getX(), mob.getY() - 4, this, 3, sound.revolver));
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
		this.standRange.setFrame(x - RADIUS / 4, y - RADIUS / 4, RADIUS / 2,
				RADIUS / 2);
		int xTile = 0;
		int walkingSpeed = 4;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 8;
		} else if (isLatitudinal(getDirection())) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			if (getDirection() == Direction.WEST) {
				flip = 1;
			} else {
				flip = 0;
			}
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier;
		int yOffset = y - modifier;

		if (isDead) {
			isShooting = false;
			xTile = 12;
		}

		if (isShooting) {
			xTile = 14;
			if (getDirection() == Direction.NORTH) {
				xTile += 12;
			}
			if (getDirection() == Direction.SOUTH) {
				xTile += 6;
			}
		}

		// Upper body 1
		screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile
				* sheet.boxes, color, flip, scale, sheet);

		// Upper body 2
		screen.render(xOffset + modifier - (modifier * flip), yOffset,
				(xTile + 1) + yTile * sheet.boxes, color, flip, scale, sheet);

		// Lower Body 1
		screen.render(xOffset + (modifier * flip), yOffset + modifier, xTile
				+ (yTile + 1) * sheet.boxes, color, flip, scale, sheet);

		// Lower Body 2
		screen.render(xOffset + modifier - (modifier * flip), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color,
				flip, scale, sheet);

	}

	public void speak(Player player) {
		isTalking = true;
		ChatHandler.displayText("I'm in charge here.", Color.white);
		return;
	}

}
