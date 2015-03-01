package ca.javajesus.game.entities.npcs;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.monsters.Monster;
import ca.javajesus.game.entities.projectiles.Bullet;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.JJFont;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Policeman extends NPC {

	protected Ellipse2D.Double standRange;
	protected Mob mob;
	protected double scaledSpeed = 0.35;
	protected Ellipse2D.Double aggroRadius;
	protected final int RADIUS = 32 * 8;
	protected boolean cooldown = true;
	protected int tickCount = 0;
	protected boolean isShooting = false;
	protected int shootTickCount = 0;

	public Policeman(Level level, double x, double y, double defaultHealth,
			String walkPath, int walkDistance) {
		super(level, "SWAT Officer", x, y, 1, 16, 16, defaultHealth, Colors
				.get(-1, 000, Colors.fromHex("#000046"), 543), 0, 10, walkPath,
				walkDistance, 0);
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		standRange = new Ellipse2D.Double(x - RADIUS / 4, y - RADIUS / 4,
				RADIUS / 2, RADIUS / 2);
		checkRadius();
		//this.bar = new HealthBar(level, 0 + 2 * 32, this.x, this.y, this);
		//if (level != null)
			//level.addEntity(bar);
	}

	public Policeman(Level level, double x, double y) {
		super(level, "SWAT Officer", x, y, 1, 16, 16, 200, Colors.get(-1, 000,
				Colors.fromHex("#000046"), 543), 0, 10, "", 0, 8);
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		standRange = new Ellipse2D.Double(x - RADIUS / 4, y - RADIUS / 4,
				RADIUS / 2, RADIUS / 2);
		checkRadius();
	}

	private void checkRadius() {

		if (mob != null && mob.isDead) {
			mob = null;
			movingToOrigin = true;
		}

		if (mob == null)
			for (Mob mob : level.getMobs()) {
				if (mob instanceof Monster) {
					if (this.aggroRadius.intersects(mob.hitBox)) {
						// if (!mob.isTargeted) {
						this.mob = mob;
						mob.isTargeted = true;
						return;
						// }
					}
				}
			}
	}

	public void tick() {
		
		if (isDead)
			return;

		checkRadius();
		
		if (isHit) {
			isHitTicks++;
			if (isHitTicks > 20) {
				isHitTicks = 0;
				isHit = false;
			}
		}
		
		if (isTalking) {
			talkCount++;
			if (talkCount > 350) {
				talkCount = 0;
				isTalking = false;
			}
		}

		if (isShooting) {
			shootTickCount++;
			if (shootTickCount > 20) {
				shootTickCount = 0;
				isShooting = false;
			}
		}

		if (isMobCollision()) {
			moveAroundMobCollision();
			return;
		}
		int xa = 0;
		int ya = 0;
		if (mob != null && this.aggroRadius.intersects(mob.hitBox)) {
			if (!cooldown) {
				isShooting = true;
				level.addEntity(new Bullet(level, this.x + 5, (this.y - 7),
						mob.x, mob.y -4, this));
			}
			if (!this.standRange.intersects(mob.hitBox) && !this.standBox.intersects(mob.hitBox)) {

				if ((int) mob.x > (int) this.x) {
					xa++;
				}
				if ((int) mob.x < (int) this.x) {
					xa--;
				}
				if ((int) mob.y > (int) this.y) {
					ya++;
				}
				if ((int) mob.y < (int) this.y) {
					ya--;
				}
			}

			if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya) && !isMobCollision(xa, ya)) {
				move(xa, ya);
				isMoving = true;
			} else {
				isMoving = false;
			}

		} else {
			if (movingToOrigin)
				findOrigin();
			else {
				for (Mob mob : level.getMobs()) {
					if (mob == this)
						continue;
					if (this.standBox.intersects(mob.hitBox))
						return;
				}
				findPath();
			}
		}

		tickCount++;

		if (tickCount % 100 == 0) {
			cooldown = false;
		} else {
			cooldown = true;
		}

	}

	public void render(Screen screen) {

		this.hitBox.setLocation((int) this.x - 8, (int) this.y - 16);
		this.standBox.setLocation((int) this.x - 10, (int) this.y - 18);
		this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
				RADIUS);
		this.standRange.setFrame(x - RADIUS / 4, y - RADIUS / 4, RADIUS / 2,
				RADIUS / 2);
		int xTile = 0;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		if (movingDir == 0) {
			xTile += 10;
		}
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
		}

		int modifier = 8 * scale;
		double xOffset = x - modifier / 2;
		double yOffset = (y - modifier / 2 - 4) - modifier;
		
		if(isDead)
			xTile = 12;

		if (isShooting) {

			xTile = 14;
			if (movingDir == 0) {
				xTile += 2;
			}
			if (movingDir == 1) {
				xTile += 4;
			} 

			// Upper body 1
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, this.color, flipTop, scale, sheet);

			// Upper body 2
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, this.color, flipTop, scale, sheet);

			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom),
					yOffset + modifier, xTile + (yTile + 1) * 32, this.color,
					flipBottom, scale, sheet);

			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, this.color,
					flipBottom, scale, sheet);
		} else {

			// Upper body
			screen.render(xOffset + (modifier * flipTop), yOffset, xTile
					+ yTile * 32, this.color, flipTop, scale, sheet);

			// Upper body
			screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
					(xTile + 1) + yTile * 32, this.color, flipTop, scale, sheet);

			// Lower Body
			screen.render(xOffset + (modifier * flipBottom),
					yOffset + modifier, xTile + (yTile + 1) * 32, this.color,
					flipBottom, scale, sheet);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, this.color,
					flipBottom, scale, sheet);
		}
		
		if (currentQuest != null && !isTalking) {
			JJFont.render("?", screen, (int) xOffset + 4, (int) yOffset - 10, Colors.get(-1, -1, -1, Colors.fromHex("#FFCC00")),
					1);
		}
		if (isTalking) {
			JJFont.render(name, screen, (int) xOffset
					- ((name.length() - 1) / 2 * 8), (int) yOffset - 10, Colors.get(-1, -1, -1, Colors.fromHex("#FFCC00")),
					1);
		}
		
		if (isHit) {
			JJFont.render(damageTaken, screen, (int) xOffset + isHitX, (int) yOffset - 10 + isHitY,
					isHitColor, 1);
		}

	}
	
	public void speak(Player player) {
		isTalking = true;
		ChatHandler.sendMessage(name + ": Stop right there, Criminal Scum!", Color.red);
		return;
	}
}
