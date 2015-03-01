package ca.javajesus.game.entities.monsters;

import java.awt.Color;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.JJFont;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class Monkey extends Monster {
	
	public Monkey(Level level, String name, double x, double y, int speed,
			int health) {
		super(level, name, x, y, speed, 16, 16, 8, health, Colors.get(-1,
				Colors.fromHex("#2a1609"), Colors.fromHex("#391e0c"),
				Colors.fromHex("#b08162")));
		this.bar = new HealthBar(level, 0 + 2 * 32, this.x, this.y, this, 0);
		if (level != null)
			level.addEntity(bar);
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin) || isWaterTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax) || isWaterTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y) || isWaterTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y) || isWaterTile(xa, ya, xMax, y)) {
				return true;
			}
		}

		return false;
	}

	public void tick() {
		
		if (isDead)
			return;
		
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

		if (random.nextInt(500) == 0) {
			sound.play(SoundHandler.sound.chimpanzee);
		}
		checkRadius();

		if (isMobCollision()) {
			moveAroundMobCollision();
			return;
		}
		int xa = 0;
		int ya = 0;

		if (mob != null && this.aggroRadius.intersects(mob.hitBox)
				&& !this.standBox.intersects(mob.hitBox)) {

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
	}

	public void render(Screen screen) {
		
		this.hitBox.setSize(width, height);
		this.hitBox.setLocation((int) this.x - 8, (int) this.y - 16);
		this.standBox.setLocation((int) this.x - 10, (int) this.y - 18);
		this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
				RADIUS);
		int xTile = 0;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;
		
		if (movingDir == 0) {
			xTile += 10;
		}
		if (movingDir == 1) {
			xTile += 2;
		} 
		else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
		}
		
		int modifier = 8 * scale;
		double xOffset = x - modifier / 2;
		double yOffset = (y - modifier / 2 - 4) - modifier;
		
		if (isDead)
			xTile = 20;
		
		// Upper body
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile
				+ yTile * 32, color, flipTop, scale, sheet);

		// Upper body
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
				(xTile + 1) + yTile * 32, color, flipTop, scale, sheet);

		// Lower Body
		screen.render(xOffset + (modifier * flipBottom),
				yOffset + modifier, xTile + (yTile + 1) * 32, color,
				flipBottom, scale, sheet);

		// Lower Body
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
				+ modifier, (xTile + 1) + (yTile + 1) * 32, color,
				flipBottom, scale, sheet);
		
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
		ChatHandler.sendMessage("oh oh ah ah oh", Color.white);
		return;
	}
}
