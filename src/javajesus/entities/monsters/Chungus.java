package javajesus.entities.monsters;

import java.awt.event.KeyEvent;

import com.sun.prism.paint.Color;

import engine.Window;
import javajesus.entities.Entity;
import javajesus.entities.Player;
import javajesus.entities.strategies.CollisionStrategy;
import javajesus.entities.strategies.MonsterCollisionStrategy;
import javajesus.entities.strategies.RideableCollisionStrategy;
import javajesus.entities.vehicles.Rideable;
import javajesus.graphics.Screen;
import javajesus.graphics.SpriteSheet;
import javajesus.graphics.render_strategies.Render4x4;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class Chungus extends Monster implements Rideable{

	private Player player;
	
	private SpriteSheet sheet; 
	
	private static final int WALKING_ANIMATION_SPEED = 3;
	
	private final int CHUNGUS_SPEED = 2;
	
	int xCoordinate = 0, yCoordinate = 0;
	
	int xTile = 0, yTile = 0;
	
	boolean step;
	
	boolean staggered = false;
	
	private static final int WIDTH = 32, HEIGHT = 32;
	
	Render4x4 renderChungus = new Render4x4();
	
	private CollisionStrategy originalStrategy;
	private CollisionStrategy rideableStrategy;
	
	public Chungus(Level level, int x, int y, int speed, int health) {
		super(level, "Chungus", x, y, speed, WIDTH, HEIGHT, 0, health, 10);
		setSpriteSheet(SpriteSheet.chungus);
		originalStrategy = this.collisionStrategy;
		rideableStrategy = new RideableCollisionStrategy(this);
		// TODO Auto-generated constructor stub
	}
	
	public Chungus(Level level, int x, int y) {
		this(level, x, y, 1, 200);
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return Entity.CHUNGUS;
	}

	@Override
	protected void setSpriteSheet(SpriteSheet sheet) {
		this.sheet = sheet;
	}
	
	public void render(Screen screen) {
		super.render(screen);
		
		xCoordinate = getX(); 
		yCoordinate = getY();
		
		 int[] color = Chungus.color;

		step = ((numSteps >> WALKING_ANIMATION_SPEED) & 1) == 1;
		
		getTileOffsets(this.isMoving, step);
		
		
		renderChungus.renderNormal(screen, xCoordinate, yCoordinate, xTile, yTile, false, sheet, color);
		
	}
	
	private void getTileOffsets(boolean isMoving, boolean step) {
		
		if (getDirection() == Direction.SOUTH) {
			if (!isMoving) {
				xTile = 0;
				yTile = ((tickCount % 120 <= 60) ? 0 : 4);
			}
			else {
				xTile = (step ? 4 : 8);
				yTile = 0;
			}		
		}
		
		if (getDirection() == Direction.EAST) {
			if (!isMoving) {
				xTile = 12;
				yTile = ((tickCount % 120 <= 60) ? 0 : 4);
			}
			else {
				xTile = (step ? 16 : 20);
				yTile = 0;
			}		
		}
		
		if (getDirection() == Direction.WEST) {
			if (!isMoving) {
				xTile = 24;
				yTile = ((tickCount % 120 <= 60) ? 0 : 4);
			}
			else {
				xTile = (step ? 28 : 32);
				yTile = 0;
			}		
		}
		
		if (getDirection() == Direction.NORTH) {
			if (!isMoving) {
				xTile = 36;
				yTile = ((tickCount % 120 <= 60) ? 0 : 4);
			}
			else {
				xTile = (step ? 40 : 44);
				yTile = 0;
			}		
		}
		
	}
	
	@Override
	public void drive(Player player) {
		this.player = player;
		this.collisionStrategy = rideableStrategy;
		((RideableCollisionStrategy) collisionStrategy).setUsed(true);
		this.setColor(player.getColor());
		xTile = 0;
		this.setSpeed(CHUNGUS_SPEED);
	}
	
	@Override
	public void exit() {
		player.exitVehicle();
		player = null;
		((RideableCollisionStrategy) collisionStrategy).setUsed(false);
		xTile = 0;
		this.setSpeed(1);
		this.collisionStrategy = originalStrategy;
	}
	
	@Override
	public boolean isUsed() {
		return player != null;
	}
	
	@Override
	public void input(Window window) {
		// change in x and y
		int dx = 0, dy = 0;
		isMoving = false;

		
		if (window.isKeyPressed(KeyEvent.VK_W)) {
			dy--;
		}

		if (window.isKeyPressed(KeyEvent.VK_S)) {
			dy++;
		}

		if (window.isKeyPressed(KeyEvent.VK_A)) {
			dx--;
		}

		if (window.isKeyPressed(KeyEvent.VK_D)) {
			dx++;
		}

		if (window.isKeyPressed(KeyEvent.VK_E)) {
			window.toggle(KeyEvent.VK_E);
			exit();
		}

		if (dx != 0 || dy != 0) {
			isMoving = true;
			move(dx, dy);
		}

	}
	/**
	 * Updates the horse
	 */
	public void tick() {
		
		// check for input
		if (!isUsed()) {

			super.tick();
		}
	}
	
	/**
	 * Moves a horse on the level
	 * 
	 * @param dx
	 *            the total change in x
	 * @param dy
	 *            the total change in y
	 */
	public void move(int dx, int dy) {
		
		setBounds(getX(), getY(), WIDTH , HEIGHT);
		setOuterBounds(WIDTH, HEIGHT);

		if(isUsed())
			super.moveSmoothly(dx, dy);
		else
			super.move(dx, dy);
	}
	
	public boolean canRide() {
		return staggered;
	}
}
