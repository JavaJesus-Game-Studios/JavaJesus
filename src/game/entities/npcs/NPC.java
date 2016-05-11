package game.entities.npcs;

import java.awt.Color;
import java.util.ArrayList;

import game.ChatHandler;
import game.SoundHandler;
import game.entities.Entity;
import game.entities.Mob;
import game.entities.Player;
import game.graphics.JJFont;
import game.graphics.Screen;
import game.graphics.SpriteSheet;
import level.Level;
import quests.Quest;
import utility.Direction;

/*
 * Non player characters are friendly mobs that the player can interact with
 */
public class NPC extends Mob {

	private static final long serialVersionUID = 7279751732700782799L;

	// the coordinates on the spritesheet
	protected int xTile, yTile;

	// Movement type and the distance they travel
	private String walkPath;
	private int walkDistance;

	// NPC origin - spawn location of NPCs
	private int xPos, yPos;

	// quests a NPC might have
	private ArrayList<Quest> quests = new ArrayList<Quest>();

	// active quest
	private Quest currentQuest;

	// whether or not the NPC is moving to its origin
	private boolean movingToOrigin;

	// color of the NPC
	private int[] color;

	// How long a NPC will wait before moving back to the origin
	private static final int MOVE_TO_ORIGIN_TIMEOUT = Entity.secondsToTicks(10);

	// how fast the npcs toggles steps
	private static final int WALKING_ANIMATION_SPEED = 3;

	// determines if the npc is moving in any direction
	protected boolean isMoving;

	// allows npcs to move every other tick
	private int moveTick;

	/**
	 * Creates a NPC that interacts with the environment
	 * 
	 * @param level
	 *            the level to place it on
	 * @param name
	 *            the name of the NPC
	 * @param x
	 *            the X coord
	 * @param y
	 *            the Y coord
	 * @param speed
	 *            the base speed
	 * @param width
	 *            the width in pixels
	 * @param height
	 *            the height in pixels
	 * @param health
	 *            the base health
	 * @param color
	 *            the color set
	 * @param xTile
	 *            the column on the spritesheet
	 * @param yTile
	 *            the row on the spritesheet
	 * @param walkPath
	 *            the type of idle formation
	 * @param walkDistance
	 *            the distance of each idle formation
	 */
	public NPC(Level level, String name, int x, int y, int speed, int width, int height, int health, int[] color,
			int xTile, int yTile, String walkPath, int walkDistance) {
		super(level, name, x, y, speed, width, height, SpriteSheet.npcs, health);

		this.color = color;
		this.xTile = xTile;
		this.yTile = yTile;
		this.walkPath = walkPath;
		this.walkDistance = walkDistance;
		this.xPos = x;
		this.yPos = y;
		this.setBounds(getX(), getY(), width, height);

		createHealthBar();

	}

	/**
	 * Updates the NPC 60 times per second
	 */
	public void tick() {
		super.tick();

		// will move the NPC back to the origin
		if (tickCount % MOVE_TO_ORIGIN_TIMEOUT == 0) {
			movingToOrigin = getX() != xPos || getY() != yPos;
		}

		// NPC's are always moving unless they are just standing
		isMoving = true;

		// move if not colliding
		if (!isCollidingWithMob()) {

			// simple pathfinding for the NPC
			if (movingToOrigin)
				findOrigin();
			else
				findPath();
		}

	}

	/**
	 * Moves a NPC back to the origin
	 */
	private void findOrigin() {

		// the change in x and y
		int dx = 0, dy = 0;
		if (xPos > getX()) {
			dx++;
		} else if (xPos < getX()) {
			dx--;
		}
		if (yPos > getY()) {
			dy++;
		} else if (yPos < getY()) {
			dy--;
		}

		// move the NPC
		move(dx, dy);
		
		movingToOrigin = getX() != xPos || getY() != yPos;
	}

	/**
	 * Selects the appropriate walk path
	 */
	private void findPath() {
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
		// stand still
		default:
			isMoving = false;
			break;
		}
	}

	/**
	 * Displays the NPC on the screen
	 */
	public void render(Screen screen) {
		super.render(screen);

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE * getScale();

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal/vertical position on the spritesheet
		int xTile = this.xTile, yTile = this.yTile;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile += 8;
			if (isMoving)
				xTile += 2;
		} else if (getDirection() == Direction.SOUTH) {
			if (isMoving)
				xTile += 2;
		} else if (isLatitudinal()) {
			xTile += 4;
			if (isMoving)
				xTile += (flip ? 2 : 0);
			flip = getDirection() == Direction.WEST;
		}

		// dead image has an exact location
		if (isDead()) {
			xTile = 12;
		}

		// depth effect when swimming
		int swimOffset = modifier * (isSwimming ? 1 : 0);

		// Upper body 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + swimOffset,
				xTile + yTile * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + swimOffset,
				(xTile + 1) + yTile * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		if (!isSwimming) {

			// Lower Body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier,
					xTile + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());
			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier,
					(xTile + 1) + (yTile + 1) * getSpriteSheet().boxes, color, flip, getScale(), getSpriteSheet());

		}

		// notifies the player this NPC has a quest
		if (currentQuest != null && !isTalking) {
			JJFont.render("?", screen, xOffset + 4, yOffset - 10, new int[] { 0xFF000000, 0xFF000000, 0xFFFFCC00 }, 1);
		}

	}

	/**
	 * Moves the NPC in a straight line
	 */
	private void moveLinear() {

		// the change in x
		int dx = 0;

		if (getDirection() == Direction.EAST) {
			if (getX() > walkDistance + xPos) {
				dx--;
			} else {
				dx++;
			}
		} else {
			if (getX() < xPos - walkDistance) {
				dx++;
			} else {
				dx--;
			}
		}
		// move the npc
		move(dx, 0);
	}

	/**
	 * Moves the NPC in a triangle
	 */
	private void moveTriangle() {
		int dx = 0, dy = 0;
		if (getDirection() == Direction.EAST) {
			if (getX() > walkDistance + xPos) {
				dx--;
			} else {
				dy++;
			}
		} else if (getDirection() == Direction.NORTH) {
			dx--;
			if (getX() > xPos) {
				dy--;
			} else {
				dy++;
			}
		} else {
			if (getX() < xPos - walkDistance) {
				dx++;
			} else {
				dx--;
				dy++;
			}
		}
		// move the npc
		move(dx, dy);
	}

	/**
	 * Move the NPC in a square
	 */
	private void moveSquare() {
		int dx = 0, dy = 0;
		if (getDirection() == Direction.EAST) {
			if (getX() > walkDistance + xPos) {
				dy++;
			} else {
				dx++;
			}
		} else if (getDirection() == Direction.SOUTH) {
			if (getY() > walkDistance + yPos) {
				dx--;
			} else {
				dy++;
			}
		} else if (getDirection() == Direction.WEST) {
			if (getX() < xPos - walkDistance) {
				dy--;
			} else {
				dx--;
			}
		} else {
			if (getY() < yPos - walkDistance) {
				dx++;
			} else {
				dy--;
			}
		}
		// moves the npc
		move(dx, dy);
	}

	/**
	 * Moves the NPC in a cross
	 */
	private void moveCross() {

		// change in x and y
		int dx = 0, dy = 0;

		// if at origin, randomly get a direction
		if (getX() == xPos && getY() == yPos) {
			switch (random.nextInt(4)) {
			case 0: {
				setDirection(Direction.NORTH);
				break;
			}
			case 1: {
				setDirection(Direction.SOUTH);
				break;
			}
			case 2: {
				setDirection(Direction.EAST);
				break;
			}
			case 3: {
				setDirection(Direction.WEST);
				break;
			}
			}
		}

		if (getDirection() == Direction.EAST) {
			if (getX() > walkDistance + xPos) {
				dx--;
			} else {
				dx++;
			}
		} else if (getDirection() == Direction.WEST) {
			if (getX() < xPos - walkDistance) {
				dx++;
			} else {
				dx--;
			}
		} else if (getDirection() == Direction.NORTH) {
			if (getY() < yPos - walkDistance) {
				dy++;
			} else {
				dy--;
			}
		} else {
			if (getY() > walkDistance + yPos) {
				dy--;
			} else {
				dy++;
			}
		}
		// move the npc
		move(dx, dy);
	}

	/**
	 * Moves the NPC in a circle
	 */
	private void moveCircle() {

		// parametric function
		// x = r cos(ticks)
		// y = r sin (ticks)
		int xa = (int) (walkDistance * Math.cos(Math.toRadians(tickCount % 360)));
		int ya = (int) (walkDistance * Math.sin(Math.toRadians(tickCount % 360)));
		move(xa, ya);
	}

	/**
	 * @param quest
	 *            the quest to add
	 */
	public void addQuest(Quest quest) {
		quests.add(quest);
	}

	/**
	 * @param num
	 *            the index of the quest to set
	 */
	public void setQuest(int num) {
		this.currentQuest = quests.get(num);
	}

	/**
	 * Dialogue options that can easily be overridden
	 */
	protected void doDialogue() {
		switch (random.nextInt(13)) {
		case 0: {
			ChatHandler.displayText(getName() + ": I used to be an adventurer too!", Color.black);
			return;
		}
		case 1: {
			ChatHandler.displayText(getName() + ": Nice shirt!", Color.white);
			return;
		}
		case 2: {
			ChatHandler.displayText(getName() + ": Are you Jesus?", Color.white);
			return;
		}
		case 3: {
			ChatHandler.displayText(getName() + ": This is some nice weather we've been having.", Color.white);
			return;
		}
		case 4: {
			ChatHandler.displayText(getName() + ": You are not from around here are you!", Color.white);
			return;
		}
		case 5: {
			ChatHandler.displayText(getName() + ": Hello Officer!", Color.white);
			return;
		}
		case 6: {
			ChatHandler.displayText(getName() + ": Who goes there!", Color.white);
			return;
		}
		case 7: {
			ChatHandler.displayText(getName() + ": Have you been to San Cisco? I hear they're having lovely weather.",
					Color.white);
			return;
		}
		case 8: {
			ChatHandler.displayText(getName() + ": It's you! It really is! All Hail the Hero of the Bay!", Color.white);
			return;
		}
		case 9: {
			ChatHandler.displayText(getName() + ": I'm not racist but when you're driving in the East Bay,"
					+ " roll up your windows and lock your doors.", Color.white);
			return;
		}
		case 10: {
			ChatHandler.displayText(getName() + ": Have you seen my friend Bob? He's a peasant and he seems to have"
					+ "literally dissapeared!", Color.white);
			return;
		}
		case 11: {
			ChatHandler.displayText(
					getName() + ": Nasty business it is with those Apes in the North!" + " Nasty business indeed.",
					Color.white);
			return;
		}
		case 12: {
			ChatHandler.displayText(getName() + ": Hola, mi nombre es Esteban Norteruta!", Color.white);
			return;
		}
		default: {
			ChatHandler.displayText(getName() + ": Hello!", Color.white);
			return;
		}
		}
	}

	/**
	 * Text to player
	 */
	public void speak(Player player) {

		isTalking = true;

		// generally the NPC will face the opposite direction of the player when
		// talking
		switch (player.getDirection()) {
		case NORTH: {
			setDirection(Direction.SOUTH);
			break;
		}
		case SOUTH: {
			setDirection(Direction.NORTH);
			break;
		}
		case WEST: {
			setDirection(Direction.EAST);
			break;
		}
		default: {
			setDirection(Direction.WEST);
			break;
		}
		}

		if (currentQuest != null) {
			if (!player.getActiveQuests().contains(currentQuest)) {
				player.getActiveQuests().add(currentQuest);
			}
			currentQuest.update();
			switch (currentQuest.getPhase()) {
			case 0: {
				ChatHandler.displayText(getName() + ": " + currentQuest.preDialogue(), Color.blue);
				SoundHandler.play(SoundHandler.levelup);
				currentQuest.nextPhase();
				return;
			}
			case 1: {
				ChatHandler.displayText(getName() + ": " + currentQuest.dialogue(), Color.blue);
				return;
			}
			case 2: {
				ChatHandler.displayText(getName() + ": " + currentQuest.postDialogue(), Color.CYAN);
				SoundHandler.play(SoundHandler.chest);
				if (!player.getCompletedQuests().contains(currentQuest)) {
					player.getCompletedQuests().add(currentQuest);
					player.getActiveQuests().remove(currentQuest);
				}
				nextQuest();
				return;
			}
			}
		}

		doDialogue();

	}

	/**
	 * Sets the npc to the next quest
	 */
	protected void nextQuest() {
		quests.remove(currentQuest);
		currentQuest = null;
		if (quests.contains(0)) {
			currentQuest = quests.get(0);
		}
	}

	/**
	 * Returns a random NPC at the specified location
	 * 
	 * @param level
	 *            the level it is on
	 * @param x
	 *            the desired x coord
	 * @param y
	 *            the desired y coord
	 * @return the random NPC
	 */
	public static NPC getRandomNPC(Level level, int x, int y) {
		switch (random.nextInt(11)) {

		case 0:
			return new Knight(level, x, y, 100, "linear", 20);
		case 1:
			return new NPC(level, "Policeman", x, y, 1, 16, 16, 100, new int[] { 0xFF2A2A2A, 0xFF000046, 0xFFEDC5AB },
					0, 4, "triangle", 20);
		case 2:
			return new NPC(level, "Citizen-Female", x, y, 1, 16, 16, 100,
					new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0, 8, "cross", 30);
		case 3:
			return new NPC(level, "Citizen-Male", x, y, 1, 16, 16, 100,
					new int[] { 0xFF111111, 0xFFA51818, 0xFFEDC5AB }, 0, 0, "circle", 2);
		case 4:
			return new NPC(level, "Fox", x, y, 1, 16, 16, 100, new int[] { 0xFF111111, 0xFFFFA800, 0xFFFFFFFF }, 0, 14,
					"cross", 50);
		case 5:
			return new NPC(level, "Tech Warrior", x, y, 1, 16, 16, 100,
					new int[] { 0xFF000000, 0xFF42FF00, 0xFFEDC5AB }, 0, 12, "triangle", 20);
		case 6:
			return new NPC(level, "Peasant-Male", x, y, 1, 16, 16, 100,
					new int[] { 0xFF111111, 0xFF715B17, 0xFFEDC5AB }, 0, 16, "square", 100);
		case 7:
			return new NPC(level, "Peasant-Female", x, y, 1, 16, 16, 100,
					new int[] { 0xFF111111, 0xFF715B17, 0xFFEDC5AB }, 0, 18, "cross", 0);
		case 8:
			return new NPC(level, "Peasant-Boychild", x, y, 1, 16, 16, 9001,
					new int[] { 0xFF111111, 0xFF715B17, 0xFFEDC5AB }, 14, 16, "square", 0);
		case 9:
			return new NPC(level, "Peasant-Girlchild", x, y, 1, 16, 16, 9000,
					new int[] { 0xFF111111, 0xFF715B17, 0xFFEDC5AB }, 14, 18, "cross", 0);

		default:
			return new Jesus(level, x, y, "stand", 30);

		}
	}

	/**
	 * Moves a npc on the level every other tick
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	public void move(int dx, int dy) {

		if (moveTick++ % 2 == 0) {
			super.move(dx * getSpeed(), dy * getSpeed());
		}
	}

	/**
	 * @return the colorset
	 */
	protected int[] getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the new color
	 */
	protected void setColor(int[] color) {
		this.color = color;
	}

}
