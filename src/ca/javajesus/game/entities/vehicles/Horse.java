package ca.javajesus.game.entities.vehicles;

import java.util.Random;

import ca.javajesus.game.Game;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class Horse extends Vehicle {

	private static final long serialVersionUID = -3649878712351708546L;
	
	private int yTile;
	private int xTile;

	/** Movement type and the distance they travel */
	protected String walkPath;
	protected int walkDistance;

	/** NPC origin */
	protected double xPos;
	protected double yPos;

	/** Determines direction for NPC movement */
	protected boolean dir1 = true;
	protected boolean dir2;
	protected boolean dir3;
	protected boolean dir4;

	protected boolean movingToOrigin = false;

	public Horse(Level level, int x, int y, int yTile) {
		super(level, "Horse", x, y, 1, 14, 24, SpriteSheet.horses, 300);
		this.yTile = yTile;
		this.xTile = 16;
		this.color = Game.player.getColor();
		this.DELAY = 1;
		this.walkPath = "square";
		this.walkDistance = 8;
		this.xPos = x;
		this.yPos = y;
	}

	public void tick() {
		super.tick();

		if (this.isUsed) {
			xTile = 0;
		} else {
			xTile = 16;
		}

		if ((tickCount % 2 == 0)) {
			if (movingToOrigin)
				findOrigin();
			else {
				findPath();
			}
		}
	}

	public void render(Screen screen) {
		super.render(screen);
		int modifier = 8 * scale;
		int xOffset = (x - 2 * modifier);
		int yOffset = (y - 2 * modifier);
		if (!isDead)
			if (isLongitudinal(getDirection())) {
				xOffset = x - modifier;
				this.width = 14;
				this.height = 24;
				this.getBounds().setSize(width, height);
				// this.getBounds().setLocation(this.x - 7, this.y - 12);
				this.getOuterBounds().setSize(18, height);
				// this.getOuterBounds().setLocation(this.x - 9, this.y - 14);
				this.getBounds().setLocation(this.x - (this.width / 2),
						this.y - (this.height / 2));
				this.getOuterBounds().setLocation(
						this.x - (int) getBounds().getWidth() / 2 - 2,
						this.y - (int) getBounds().getHeight() / 2 - 2);
			} else {
				xOffset = x - 2 * modifier;
				this.width = 24;
				this.height = 24;
				this.getBounds().setSize(width, height);
				// this.getBounds().setLocation(this.x - 12, this.y - 12);
				this.getOuterBounds().setSize(width + 4, height);
				// this.getOuterBounds().setLocation(this.x - 14, this.y - 14);
				this.getBounds().setLocation(this.x - (this.width / 2),
						this.y - (this.height / 2));
				this.getOuterBounds().setLocation(
						this.x - (int) getBounds().getWidth() / 2 - 2,
						this.y - (int) getBounds().getHeight() / 2 - 2);
			}

		int xTile = this.xTile;
		int walkingSpeed = 4;
		int flip = (numSteps >> walkingSpeed) & 1;

		if (getDirection() == Direction.NORTH) {
			xTile += 14;
		}

		if (getDirection() == Direction.SOUTH) {
			xTile += 2;
		} else if (isLatitudinal(getDirection())) {
			xTile += 4 + flip * 4;
			if (getDirection() == Direction.WEST) {
				flip = 1;
			} else {
				flip = 0;
			}
		}

		if (isDead) {
			if (isLongitudinal(getDirection())) {
				setDirection(Direction.WEST);
			}
			xTile = 14;
		}

		if (isLongitudinal(getDirection())) {
			// Upper body
			screen.render(xOffset + (modifier * flip), yOffset, xTile + yTile
					* sheet.boxes, color, flip, scale, sheet);

			// Upper body
			screen.render(xOffset + modifier - (modifier * flip), yOffset,
					(xTile + 1) + yTile * sheet.boxes, color, flip, scale,
					sheet);

			// Middle Body
			screen.render(xOffset + (modifier * flip), yOffset + modifier,
					xTile + (yTile + 1) * sheet.boxes, color, flip, scale,
					sheet);

			// Middle Body
			screen.render(xOffset + modifier - (modifier * flip), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * sheet.boxes, color,
					flip, scale, sheet);

			// Lower Body
			screen.render(xOffset + (modifier * flip), yOffset + 2 * modifier,
					xTile + (yTile + 2) * sheet.boxes, color, flip, scale,
					sheet);

			// Lower Body
			screen.render(xOffset + modifier - (modifier * flip), yOffset + 2
					* modifier, (xTile + 1) + (yTile + 2) * sheet.boxes, color,
					flip, scale, sheet);
		} else {

			for (int i = 0; i < 3; i++) {
				screen.render(xOffset + (3 * modifier * flip), yOffset
						+ (modifier * i), xTile + (yTile + i) * sheet.boxes,
						color, flip, scale, sheet);

				screen.render(xOffset + modifier + (modifier * flip), yOffset
						+ (modifier * i), (xTile + 1) + (yTile + i)
						* sheet.boxes, color, flip, scale, sheet);

				screen.render(xOffset + 2 * modifier - (modifier * flip),
						yOffset + (modifier * i), (xTile + 2) + (yTile + i)
								* sheet.boxes, color, flip, scale, sheet);

				screen.render(xOffset + 3 * modifier - (3 * modifier * flip),
						yOffset + (modifier * i), (xTile + 3) + (yTile + i)
								* sheet.boxes, color, flip, scale, sheet);
			}
		}

	}

	protected void findOrigin() {

		if (xPos == this.x && yPos == this.y) {
			movingToOrigin = false;
			return;
		}
		int xa = 0;
		int ya = 0;
		if (xPos > this.x) {
			xa++;
		}
		if (xPos < this.x) {
			xa--;
		}
		if (yPos > this.y) {
			ya++;
		}
		if (yPos < this.y) {
			ya--;
		}
		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			setMoving(true);
			move(xa, ya);
		} else {
			setMoving(false);
		}
	}

	protected void findPath() {
		switch (walkPath) {
		case "linear": {
			moveLinear();
			break;
		}
		case "triangle": {
			moveTriangle();
			break;
		}
		case "square": {
			moveSquare();
			break;
		}
		case "cross": {
			moveCross();
			break;
		}
		case "circle": {
			moveCircle();
			break;
		}
		default:
			break;
		}
	}

	private void moveLinear() {
		int xa = 0;
		int ya = 0;
		if (dir1) {
			xa++;
			if (this.x > this.walkDistance + xPos) {
				dir1 = false;
				dir2 = true;
			}
		} else if (dir2) {
			xa--;
			if (this.x < xPos - this.walkDistance) {
				dir1 = true;
				dir2 = false;
			}
		}
		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			move(xa, ya);
			setMoving(true);
		} else {
			setMoving(false);
		}

	}

	private void moveTriangle() {
		int xa = 0;
		int ya = 0;
		if (dir1) {
			xa++;
			if (this.x > this.walkDistance + xPos) {
				dir1 = false;
				dir2 = true;
			}
		} else if (dir2) {
			xa--;
			ya--;
			if (this.x < xPos) {
				dir2 = false;
				dir3 = true;
			}
		} else if (dir3) {
			xa--;
			ya++;
			if (this.x < xPos - this.walkDistance) {
				dir3 = false;
				dir1 = true;
			}
		}
		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			move(xa, ya);
			setMoving(true);
		} else {
			setMoving(false);
		}

	}

	private void moveSquare() {
		int xa = 0;
		int ya = 0;
		if (dir1) {
			xa++;
			if (this.x > this.walkDistance + xPos) {
				dir1 = false;
				dir2 = true;
			}
		} else if (dir2) {
			ya++;
			if (this.y > this.walkDistance + yPos) {
				dir2 = false;
				dir3 = true;
			}
		} else if (dir3) {
			xa--;
			if (this.x < xPos - this.walkDistance) {
				dir3 = false;
				dir4 = true;
			}
		} else if (dir4) {
			ya--;
			if (this.y < yPos - this.walkDistance) {
				dir4 = false;
				dir1 = true;
			}
		}
		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			move(xa, ya);
			setMoving(true);
		} else {
			setMoving(false);
		}

	}

	private void moveCross() {
		int xa = 0;
		int ya = 0;
		if (!dir1 && !dir2 && !dir3 && !dir4) {
			Random random = new Random();
			switch (random.nextInt(4)) {
			case 0: {
				dir1 = true;
				break;
			}
			case 1: {
				dir2 = true;
				break;
			}
			case 2: {
				dir3 = true;
				break;
			}
			case 3: {
				dir4 = true;
				break;
			}
			}
		}

		if (dir1) {
			xa++;
			if (this.x > this.walkDistance + xPos) {
				dir1 = false;
			}
		} else if (dir2) {
			ya++;
			if (this.y > this.walkDistance + yPos) {
				dir2 = false;
			}
		} else if (dir3) {
			xa--;
			if (this.x < xPos - this.walkDistance) {
				dir3 = false;
			}
		} else if (dir4) {
			ya--;
			if (this.y < yPos - this.walkDistance) {
				dir4 = false;
			}
		}
		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			move(xa, ya);
			setMoving(true);
		} else {
			setMoving(false);
		}

	}

	private void moveCircle() {

		// Some random code with some random values. Don't ask me how it works.
		double miniTick = tickCount / 20.0;
		int xa = (int) (walkDistance * Math.cos(miniTick / walkDistance));
		int ya = (int) (walkDistance * Math.sin(miniTick / walkDistance));
		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			move(xa, ya);
			setMoving(true);
		} else {
			setMoving(false);
		}

	}

}
