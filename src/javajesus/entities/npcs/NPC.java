package javajesus.entities.npcs;

import java.awt.Color;
import java.util.ArrayList;

import javajesus.DialogueHandler;
import javajesus.JavaJesus;
import javajesus.MessageHandler;
import javajesus.SoundHandler;
import javajesus.entities.Entity;
import javajesus.entities.Mob;
import javajesus.entities.Player;
import javajesus.graphics.JJFont;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.level.Level;
import javajesus.quest.Quest;
import javajesus.utility.Direction;

/*
 * Non player characters are friendly mobs that the player can interact with
 */
public abstract class NPC extends Mob {

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

	// whether or not to render the npc
	private boolean customRender;

	// allows npcs to move every other tick
	private int moveTick;

	// whether or not this NPC should follow the player
	private boolean followingPlayer;

	// hit color of the npc
	protected static final int[] mobHitColor = { 0xFF700000, 0xFF700000, 0xFF700000, 0, 0 };
	protected static final int[] questColor = { 0xFFFFFF00, 0xFFFFFF00, 0xFFFFFF00 };

	/**
	 * Creates a NPC that interacts with the environment
	 * 
	 * @param level        - the level to place it on
	 * @param name         - the name of the NPC
	 * @param x            - the X coord
	 * @param y            - the Y coord
	 * @param speed        - the base speed
	 * @param width        - the width in pixels
	 * @param height       - the height in pixels
	 * @param health       - the base health
	 * @param color        - the color set
	 * @param xTile        - the column on the spritesheet
	 * @param yTile        - the row on the spritesheet
	 * @param walkPath     - the type of idle formation
	 * @param walkDistance - the distance of each idle formation
	 * @param customRender - whether or not to render the npc
	 */
	public NPC(Level level, String name, int x, int y, int speed, int width, int height, int health, int[] color,
			int xTile, int yTile, String walkPath, int walkDistance, boolean customRender) {
		super(level, name, x, y, speed, width, height, SpriteSheet.mobFriends, health);

		// instance data
		this.setColor(color);
		this.xTile = xTile;
		this.yTile = yTile;
		this.walkPath = walkPath;
		this.walkDistance = walkDistance;
		this.xPos = x;
		this.yPos = y;
		this.customRender = customRender;
		this.setBounds(getX(), getY(), width, height);

		// create the health bar
		if (level != null) {
			createHealthBar();
		}

	}

	/**
	 * Creates a NPC that interacts with the environment
	 * 
	 * @param level        - the level to place it on
	 * @param name         - the name of the NPC
	 * @param x            - the X coord
	 * @param y            - the Y coord
	 * @param speed        - the base speed
	 * @param width        - the width in pixels
	 * @param height       - the height in pixels
	 * @param health       - the base health
	 * @param color        - the color set
	 * @param xTile        - the column on the spritesheet
	 * @param yTile        - the row on the spritesheet
	 * @param walkPath     - the type of idle formation
	 * @param walkDistance - the distance of each idle formation
	 */
	public NPC(Level level, String name, int x, int y, int speed, int width, int height, int health, int[] color,
			int xTile, int yTile, String walkPath, int walkDistance) {
		this(level, name, x, y, speed, width, height, health, color, xTile, yTile, walkPath, walkDistance, false);
	}

	/**
	 * Updates the NPC 60 times per second
	 */
	public void tick() {
		super.tick();
		
		// NPC's are always moving unless they are just standing
		isMoving = true;
		
		if (followingPlayer) {
			if ((path == null || !path.isNotEmpty()) && !getOuterBounds().intersects(JavaJesus.getPlayer().getBounds())) {
				setPath(JavaJesus.getPlayer()); 
			}
		} else {
			// will move the NPC back to the origin
			if (tickCount % MOVE_TO_ORIGIN_TIMEOUT == 0) {
				movingToOrigin = getX() != xPos || getY() != yPos;
			}

			// move if not colliding
			if (!isCollidingWithMob()) {

				// simple pathfinding for the NPC
				if (movingToOrigin)
					findOrigin();
				else
					findPath();
			} 
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

		// notifies the player this NPC has a quest
		if (currentQuest != null && !isTalking) {
			JJFont.render("?", screen, getX() + 4, getY() - 10, questColor, 1);
		}

		// don't render if class is overriding render
		if (customRender) {
			return;
		}

		// default color
		int[] color = this.color;

		// change color if knockback
		if (isHit) {
			color = mobHitColor;
		}

		// modifier used for rendering in different scales/directions
		int modifier = UNIT_SIZE;

		// no x or y offset, use the upper left corner as absolute
		int xOffset = getX(), yOffset = getY();

		// the horizontal/vertical position on the spritesheet
		int xTile = this.xTile, yTile = this.yTile;

		// whether or not to render backwards
		boolean flip = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;

		// adjust spritesheet offsets
		if (getDirection() == Direction.NORTH) {
			xTile += 8;
			if (isMoving) {
				xTile += 2;
			} else {
				xTile += ((tickCount % 120 <= 60) ? 23 : 0);
			}
		} else if (getDirection() == Direction.SOUTH) {
			if (isMoving) {
				xTile += 2;
			} else {
				xTile += ((tickCount % 120 <= 60) ? 27 : 0);
			}
		} else if (isLatitudinal()) {
			xTile += 4;
			if (isMoving) {
				xTile += (flip ? 2 : 0);
			} else {
				xTile += ((tickCount % 120 <= 60) ? 25 : 0);
			}
			flip = getDirection() == Direction.WEST;
		}

		// dead image has an exact location
		if (isDead()) {
			xTile = 12;
		}

		// depth effect when swimming
		int swimOffset = modifier * (isSwimming ? 1 : 0);

		// Upper body 1
		screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + swimOffset, xTile, yTile, getSpriteSheet(), flip,
				color);

		// Upper Body 2
		screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + swimOffset, (xTile + 1), yTile,
				getSpriteSheet(), flip, color);

		if (!isSwimming) {

			// Lower Body 1
			screen.render(xOffset + (modifier * (flip ? 1 : 0)), yOffset + modifier, xTile, (yTile + 1),
					getSpriteSheet(), flip, color);
			// Lower Body 2
			screen.render(xOffset + modifier - (modifier * (flip ? 1 : 0)), yOffset + modifier, (xTile + 1),
					(yTile + 1), getSpriteSheet(), flip, color);

		}
		super.render(screen);

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
	 * @param quest - the quest to add
	 */
	public void addQuest(Quest quest) {
		quests.add(quest);

		// now make it active
		if (currentQuest == null) {
			currentQuest = quest;
		}
	}

	/**
	 * @return the current quest
	 */
	public Quest getCurrentQuest() {
		return currentQuest;
	}

	/**
	 * @param num - the index of the quest to set
	 */
	public void setQuest(int num) {
		this.currentQuest = quests.get(num);
	}

	/**
	 * @param quest - quest to bring to focus
	 */
	public void addQuestAndSet(Quest quest) {
		addQuest(quest);
		setQuest(quests.indexOf(quest));
	}

	/**
	 * Dialogue options that can easily be overridden
	 * 
	 * @param player - initiator of conversation
	 */
	protected String getDialogue(Player player) {

		// choose one at random
		switch (random.nextInt(13)) {
		case 0:
			return "I used to be an adventurer too!";
		case 1:
			return ": Nice shirt!";
		case 2:
			return "Are you Jesus?";
		case 3:
			return "This is some nice weather we've been having.";
		case 4:
			return "You are not from around here are you!";
		case 5:
			return "Hello Officer!";
		case 6:
			return "Who goes there!";
		case 7:
			return "Have you been to San Cisco? I hear they're having lovely weather.";
		case 8:
			return "It's you! It really is! All Hail the Hero of the Bay!";
		case 9:
			return "I'm not racist but when you're driving in the East Bay, roll up your windows and lock your doors.";
		case 10:
			return "Have you seen my friend Bob? He's a peasant and he seems to have" + "literally dissapeared!";
		case 11:
			return "Nasty business it is with those Apes in the North!" + " Nasty business indeed.";
		case 12:
			return "Hola, mi nombre es Esteban Norteruta!";
		default:
			return "Hello!";
		}
	}

	/**
	 * Text to player
	 */
	public void speak(Player player) {

		// set the talking effect
		isTalking = true;

		// generally the NPC will face the opposite direction of the player when talking
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

		// do quest dialogue
		if (currentQuest != null) {

			// continue action dialogue
			if (!currentQuest.isDialogueFinished()) {

				// delegate to dialogue gui
				DialogueHandler.startDialogue(this);

				// do the simple chat
			} else {

				// do normal dialogue
				MessageHandler.displayText(getName() + ": " + currentQuest.getDialogue(), Color.WHITE);
			}

			// move on to next quest if completed
			if (currentQuest.isCompleted()) {

				// quest completed sound
				SoundHandler.play(SoundHandler.chest);

				// update the player
				player.finishQuest(currentQuest);

				// update quest
				nextQuest();
			}
		} else {
			// do normal dialogue
			MessageHandler.displayText(getName() + ": " + getDialogue(player), Color.WHITE);
		}

	}

	/**
	 * Sets the npc to the next quest
	 */
	public void nextQuest() {
		quests.remove(currentQuest);
		currentQuest = null;
		if (quests.size() > 0) {
			currentQuest = quests.get(0);
		}
	}

	/**
	 * Logic on mob death
	 */
	@Override
	public void remove() {
		super.remove();

		// now remove the quest
		if (currentQuest != null) {
			quests.remove(currentQuest);
			currentQuest = null;
		}
	}

	/**
	 * Moves a npc on the level every other tick
	 * 
	 * @param dx - the total change in x
	 * @param dy - the total change in y
	 */
	public void move(int dx, int dy) {

		if (moveTick++ % 2 == 0) {
			super.move(dx * (int) getSpeed(), dy * (int) getSpeed());
		}
	}

	/**
	 * Moves a npc every tick (will cause faster movement)
	 * 
	 * @param dx - the total change in x
	 * @param dy - the total change in y
	 */
	public void moveSmoothly(int dx, int dy) {
		super.move(dx * (int) getSpeed(), dy * (int) getSpeed());
	}

	/**
	 * @return the colorset
	 */
	public int[] getColor() {
		return color;
	}

	/**
	 * @param color the new color
	 */
	protected void setColor(int[] color) {
		if( color == null )
			color = new int[] {0,0,0,0,0};
		if (color.length < 5)
			color = new int[] { color[0], color[1], color[2], 0, 0 };
		this.color = color;
	}

	/**
	 * Gets a random NPC
	 * 
	 * @param level - level to place it on
	 * @param x     - its x coord
	 * @param y     - its y coord
	 * @return the NPC
	 */
	public static Entity getRandomNPC(Level level, int x, int y) {

		// randomly generated a type of peasant
		int type = random.nextInt(4);
		return new Peasant(level, x, y, type);
	}

	/**
	 * @return the xTile
	 */
	public final int getXTile() {
		return xTile;
	}

	/**
	 * @return the yTile
	 */
	public final int getYTile() {
		return yTile;
	}

	/**
	 * @return the path to the NPC dialogue head
	 */
	public String getHeadPath() {
		return "/VISUAL_DATA/GUI/HUD/DIALOGUE/actor_dialogue-m.png";
	}

	public void setFollowingPlayer(boolean follow) {
		this.followingPlayer = follow;
	}

}
