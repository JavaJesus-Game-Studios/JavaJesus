package ca.javajesus.game.entities.npcs.aggressive;

import java.awt.Color;

import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.Game;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.JJFont;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class Gorilla extends Shooter {
	
	private static final long serialVersionUID = 5350816667470294053L;
	
	private boolean isAttacking;
	private int coolTicks;
	
	public Gorilla(Level level, int x, int y, int defaultHealth,
			String walkPath, int walkDistance) {
		super(level, "Gorilla", x, y, 1, 16, 16, defaultHealth,
				new int[] { 0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 22,
				walkPath, walkDistance);
		this.strength = 30;
	}

	public Gorilla(Level level, int x, int y) {
		super(level, "Gorilla", x, y, 1, 16, 16, 200, new int[] {
				0xFF111111, 0xFF000046, 0xFFEDC5AB }, 0, 22,"", 0);
		this.strength = 30;
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
			if (mob.getY() > this.y) {
				ya++;
			}
			if (mob.getY() < this.y) {
				ya--;
			}
		}

		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			setMoving(true);
			move(xa, ya);
		} else {
			if (movingToOrigin)
				findOrigin();
			else {
				findPath();
			}
		}

	}
	
	public void render(Screen screen) {
		
		this.getBounds().setLocation(this.x - (this.width / 2),
				this.y - (this.height / 2));
		this.getOuterBounds().setLocation(
				this.x - (int) getBounds().getWidth() / 2 - 2,
				this.y - (int) getBounds().getHeight() / 2 - 2);
		
		this.aggroRadius.setFrame(x - RADIUS / 2, y - RADIUS / 2, RADIUS,
				RADIUS);
		this.standRange.setFrame(x - RADIUS / 4, y - RADIUS / 4, RADIUS / 2,
				RADIUS / 2);
		
		int modifier = 8 * scale;
		int xOffset = (x - modifier - modifier / 2);
		int yOffset = (y - modifier - modifier / 2);

		if (isSwimming) {
			if (isOnFire()) {
				setOnFire(false);
			}
			int[] waterColor = { 0xFF5A52FF, 0xFF000000, 0xFF000000 };
			yOffset += 4;
			if (tickCount % 60 < 15) {
				waterColor[0] = 0xFF5266FF;
				waterColor[1] = 0xFF000000;
				waterColor[2] = 0xFF000000;
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				waterColor[0] = 0xFF3D54FF;
				waterColor[1] = 0xFF5266FF;
				waterColor[2] = 0xFF000000;
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColor[0] = 0xFF3D54FF;
				waterColor[1] = 0xFF000000;
				waterColor[2] = 0xFF000000;
			} else {
				yOffset -= 1;
				waterColor[0] = 0xFF5266FF;
				waterColor[1] = 0xFF5266FF;
				waterColor[2] = 0xFF000000;
			}
			screen.render(xOffset, yOffset + 3, 0 + 10 * sheet.boxes,
					waterColor, 0x00, 1, sheet);
			screen.render(xOffset + 8, yOffset + 3, 0 + 10 * sheet.boxes,
					waterColor, 0x01, 1, sheet);
		}

		// Handles fire animation
		if (isOnFire()) {
			int[] firecolor = { 0xFFF7790A, 0xFFF72808, 0xFF000000 };

			screen.render(xOffset + 3, yOffset, this.level.fireList.get(0)
					.getXTile() + 15 * sheet.boxes, firecolor, 0, 2,
					SpriteSheet.tiles);

		}

		if (!isDead) {

			if (isTalking) {
				JJFont.render(name, screen, (int) xOffset
						- ((name.length() - 1) / 2 * 8), (int) yOffset - 10,
						new int[] { 0xFF000000, 0xFF000000, 0xFFFFCC00 }, 1);
			}

			if (isHit) {
				JJFont.render(damageTaken, screen, (int) xOffset + isHitX,
						(int) yOffset - 10 + isHitY, isHitColor, 1);
			}
		}
		
		int xTile = 0;
		int walkingSpeed = 4;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 15;
		}
		if (getDirection() == Direction.SOUTH) {
			xTile += 3;
		} else if (isLatitudinal(getDirection())) {
			xTile += 6 + flip * 3;
			if (getDirection() == Direction.WEST) {
				flip = 1;
			} else {
				flip = 0;
			}
		}

		if (isAttacking) {
			if (getDirection() == Direction.NORTH) {
				xTile = 27;
			}
			if (getDirection() == Direction.SOUTH) {
				xTile = 21;
			} else if (isLatitudinal(getDirection())) {
				xTile = 24 + flip * 3;
				if (getDirection() == Direction.WEST) {
					flip = 1;
				} else {
					flip = 0;
				}
			}
		}

		if (isDead)
			xTile = 18;

		for (int i = 0; i < 3; i++) {
			
			screen.render(xOffset + (2 * modifier * flip), yOffset + (modifier * i), xTile
					+ (yTile + i) * sheet.boxes, color, flip, scale, sheet);

			screen.render(xOffset + modifier, yOffset + (modifier * i), (xTile + 1) + (yTile + i)
					* sheet.boxes, color, flip, scale, sheet);
			
			screen.render(xOffset + 2 * modifier - (2 * modifier * flip),
					yOffset + (modifier * i), (xTile + 2) + (yTile + i) * sheet.boxes, color,
					flip, scale, sheet);
		}
	}

	public void speak(Player player) {
		isTalking = true;
		ChatHandler.sendMessage(name + ": Gorilla like human.", Color.white);
		return;
	}

}
