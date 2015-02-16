package ca.javajesus.game.entities.npcs;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import quests.Quest;
import ca.javajesus.game.ChatHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.particles.HealthBar;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class NPC extends Mob {

	public static NPC npc1 = new NPC(Level.level1, "Knight", 200, 100, 1, 16,
			16, 100, Colors.get(-1, 111, Colors.fromHex("#7e7e7e"),
					Colors.fromHex("#FFFFFF")), 0, 2, "linear", 20, 8);
	public static NPC npc2 = new NPC(Level.level1, "Policeman", 160, 250, 1,
			16, 16, 100, Colors.get(-1, Colors.fromHex("#2a2a2a"),
					Colors.fromHex("#000046"), 543), 0, 4, "triangle", 20, 8);
	public static NPC npc3 = new NPC(Level.level1, "Citizen-Female", 200, 400,
			1, 16, 16, 100, Colors.get(-1, 111, 300, 543), 0, 8, "cross", 30, 8);
	public static NPC npc4 = new NPC(Level.level1, "Citizen-Male", 200, 500, 1,
			16, 16, 100, Colors.get(-1, 111, 300, 543), 0, 0, "circle", 2, 8);
	public static NPC npc5 = new NPC(Level.level1, "Fox", 250, 75, 1, 16, 16,
			100, Colors.get(-1, 111, Colors.fromHex("#ffa800"), 555), 0, 14,
			"cross", 50, 8);
	public static NPC npc6 = new NPC(Level.level1, "Tech Warrior", 400, 250, 1,
			16, 16, 100, Colors.get(-1, 000, Colors.fromHex("#42ff00"), 543),
			0, 12, "triangle", 20, 8);
	public static NPC npc7 = new NPC(Level.level1, "Peasant-Male", 2005, 950,
			1, 16, 16, 100,
			Colors.get(-1, 111, Colors.fromHex("#715b17"), 543), 0, 16,
			"square", 100, 8);
	public static NPC npc8 = new NPC(Level.level1, "Peasant-Female", 2025, 950,
			1, 16, 16, 100,
			Colors.get(-1, 111, Colors.fromHex("#715b17"), 543), 0, 18,
			"cross", 0, 8);
	public static NPC npc9 = new NPC(Level.level1, "Peasant-Boychild", 2035,
			950, 1, 16, 16, 9001, Colors.get(-1, 111,
					Colors.fromHex("#715b17"), 543), 14, 16, "square", 0, 8);
	public static NPC npc10 = new NPC(Level.level1, "Peasant-Girlchild", 2045,
			950, 1, 16, 16, 9000, Colors.get(-1, 111,
					Colors.fromHex("#715b17"), 543), 14, 18, "cross", 0, 8);
	protected boolean isSwimming = false;

	/** Range that the NPC can walk */
	protected Ellipse2D.Double walkRadius;
	protected final int RADIUS = 32 * 8;
	protected int color;
	protected int xTile;
	protected int yTile;

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
	protected int tickCount;

	public ArrayList<Quest> quests = new ArrayList<Quest>();
	public Quest currentQuest;

	protected boolean movingToOrigin = false;

	public NPC(Level level, String name, double x, double y, int speed,
			int width, int height, double defaultHealth, int color, int xTile,
			int yTile, String walkPath, int walkDistance, int yChange) {
		super(level, name, x, y, speed, width, height, SpriteSheet.npcs,
				defaultHealth);
		this.walkRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		this.color = color;
		this.xTile = xTile;
		this.yTile = yTile;
		this.walkPath = walkPath;
		this.walkDistance = walkDistance;
		this.xPos = x;
		this.yPos = y;
		this.hitBox = new Rectangle(width, height);
		this.bar = new HealthBar(level, 0 + 2 * 32, this.x, this.y, this,
				yChange);
		if (level != null)
			level.addEntity(bar);
		scaledSpeed = 0.35;
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		return false;
	}

	public void tick() {

		if (hasDied) {
			return;
		}

		tickCount++;
		if (tickCount > 360) {
			tickCount = 0;
			movingToOrigin = true;
		}

		if (isMobCollision()) {
			moveAroundMobCollision();
			return;
		}
		int xx = (int) x;
		int yy = (int) y;
		if (level.getTile(xx >> 3, yy >> 3).getId() == 3) {
			isSwimming = true;
		}
		if (isSwimming && level.getTile(xx >> 3, yy >> 3).getId() != 3) {
			isSwimming = false;
		}

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

	protected void findOrigin() {

		if ((int) xPos == (int) this.x && (int) yPos == (int) this.y) {
			movingToOrigin = false;
		}

		int xa = 0;
		int ya = 0;
		if ((int) xPos > (int) this.x) {
			xa++;
		}
		if ((int) xPos < (int) this.x) {
			xa--;
		}
		if ((int) yPos > (int) this.y) {
			ya++;
		}
		if ((int) yPos < (int) this.y) {
			ya--;
		}
		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			move(xa, ya, scaledSpeed);
			isMoving = true;
		} else {
			isMoving = false;
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

	public void render(Screen screen) {
		this.hitBox.setLocation((int) this.x - (this.width / 2), (int) this.y - (this.height / 2));
		this.standBox.setLocation((int) this.x - (int) hitBox.getWidth() - 2, (int) this.y - (int) hitBox.getHeight() - 2);
		int xTile = this.xTile;
		int yTile = this.yTile;

		int walkingAnimationSpeed = 4;
		if (scaledSpeed == 3) {
			numSteps++;
		}

		int flipTop = (numSteps >> walkingAnimationSpeed) & 1;
		int flipBottom = (numSteps >> walkingAnimationSpeed) & 1;

		if (movingDir == 0) {
			xTile += 10;
		}
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingAnimationSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
			flipBottom = (movingDir - 1) % 2;
		}

		int modifier = 8 * scale;
		double xOffset = x - modifier / 2.0;
		double yOffset = y - modifier / 2.0 - 4;

		if (hasDied)
			xTile = 12;

		if (isSwimming) {
			if (onFire) {
				onFire = false;
			}
			int waterColour = 0;
			yOffset += 4;
			if (tickCount % 60 < 15) {
				waterColour = Colors.get(-1, 225, -1, -1);
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				waterColour = Colors.get(-1, 115, 225, -1);
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColour = Colors.get(-1, 115, -1, -1);
			} else {
				yOffset -= 1;
				waterColour = Colors.get(-1, 225, 225, -1);
			}
			screen.render(xOffset, yOffset + 3, 0 + 10 * 32, waterColour, 0x00,
					1, sheet);
			screen.render(xOffset + 8, yOffset + 3, 0 + 10 * 32, waterColour,
					0x01, 1, sheet);
		}
		if (!isSwimming) {
			// Lower Body 1
			screen.render(xOffset + (modifier * flipBottom),
					yOffset + modifier, xTile + (yTile + 1) * 32, color,
					flipBottom, scale, sheet);
			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset
					+ modifier, (xTile + 1) + (yTile + 1) * 32, color,
					flipBottom, scale, sheet);

		}

		// Upper body 1
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile
				* 32, color, flipTop, scale, sheet);
		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset,
				(xTile + 1) + yTile * 32, color, flipTop, scale, sheet);

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
			if (isMobCollision()) {
				isMoving = false;
				return;
			}
			move(xa, ya, scaledSpeed);
			isMoving = true;
		} else {
			isMoving = false;
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
			if (isMobCollision()) {
				isMoving = false;
				return;
			}
			move(xa, ya, scaledSpeed);
			isMoving = true;
		} else {
			isMoving = false;
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
			if (isMobCollision()) {
				isMoving = false;
				return;
			}
			move(xa, ya, scaledSpeed);
			isMoving = true;
		} else {
			isMoving = false;
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
			if (isMobCollision()) {
				isMoving = false;
				return;
			}
			move(xa, ya, scaledSpeed);
			isMoving = true;
		} else {
			isMoving = false;
		}

	}

	private void moveCircle() {

		// Some random code with some random values. Don't ask me how it works.
		double miniTick = tickCount / 20.0;
		int xa = (int) (walkDistance * Math.cos(miniTick / walkDistance));
		int ya = (int) (walkDistance * Math.sin(miniTick / walkDistance));
		if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)
				&& !isMobCollision(xa, ya)) {
			if (isMobCollision()) {
				isMoving = false;
				return;
			}
			move(xa, ya, scaledSpeed);
			isMoving = true;
		} else {
			isMoving = false;
		}

	}

	public void addQuest(Quest quest) {
		quests.add(quest);
	}

	public void setQuest(int num) {
		this.currentQuest = quests.get(num);
	}

	public void speak(Player player) {

		switch (player.movingDir) {
		case 0: {
			movingDir = 1;
			break;
		}
		case 1: {
			movingDir = 0;
			break;
		}
		case 2: {
			movingDir = 3;
			break;
		}
		case 3: {
			movingDir = 2;
			break;
		}
		}

		if (currentQuest != null) {
			currentQuest.update();
			switch (currentQuest.getPhase()) {
			case 0: {
				ChatHandler.sendMessage(currentQuest.preDialogue(), Color.blue);
				sound.play(sound.levelup);
				currentQuest.nextPhase();
				return;
			}
			case 1: {
				ChatHandler.sendMessage(currentQuest.dialogue(), Color.blue);
				return;
			}
			case 2: {
				ChatHandler
						.sendMessage(currentQuest.postDialogue(), Color.CYAN);
				sound.play(sound.chest);
				nextQuest();
				return;
			}
			}
		}

		switch (random.nextInt(6)) {
		case 0: {
			ChatHandler.sendMessage("I used to be an adventurer too!",
					Color.black);
			return;
		}
		case 1: {
			ChatHandler.sendMessage("Nice shirt!", Color.black);
			return;
		}
		case 2: {
			ChatHandler.sendMessage("Are you Jesus?", Color.black);
			return;
		}
		case 3: {
			ChatHandler
					.sendMessage(
							"This is some nice weather we've been having.",
							Color.black);
			return;
		}
		case 4: {
			ChatHandler.sendMessage("You are not from around here are you!",
					Color.black);
			return;
		}
		default: {
			ChatHandler.sendMessage("Hello!", Color.black);
			return;
		}
		}

	}

	protected void nextQuest() {
		quests.remove(currentQuest);
		currentQuest = null;
		if (quests.contains(0)) {
			currentQuest = quests.get(0);
		}
	}

}
