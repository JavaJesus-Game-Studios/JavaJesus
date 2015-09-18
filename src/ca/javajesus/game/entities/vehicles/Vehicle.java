package ca.javajesus.game.entities.vehicles;

import java.awt.Point;

import ca.javajesus.game.Display;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.JavaRectangle;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.graphics.JJFont;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class Vehicle extends Mob {

	private static final long serialVersionUID = -190937210314016793L;

	public boolean isUsed = false;
	protected Player player;
	public transient InputHandler input;
	protected int tickCount = 0;
	protected Point acceleration = new Point(0, 0);
	protected int DELAY = 10;
	protected final int MAX_ACCELERATION = 5;
	protected boolean isXSlowingDown = true;
	protected boolean isYSlowingDown = true;

	protected boolean showCantExitVehicle = false;
	protected int exitTickCount = 0;

	public Vehicle(Level level, String name, int x, int y, int speed,
			int width, int height, SpriteSheet sheet, int defaultHealth) {
		super(level, name, x, y, speed, width, height, sheet, defaultHealth);
		this.renderOnTop = false;
		this.setHitBox(new JavaRectangle(width, height, this));
	}

	public void addPlayer(Player player) {
		this.player = player;
		this.input = player.input;
	}

	public void remPlayer() {
		this.player = null;
		this.input = null;
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

		if (bar != null)
			bar.tick();

		int xa = 0;
		int ya = 0;
		if (isMobCollision() && this.isMoving()) {
			for (Mob mob : level.getMobs()) {
				if (!(mob == this || mob instanceof Player)) {
					if (mob.getBounds().intersects(this.getBounds()))
						mob.damage(3, 15);
				}
			}
		}

		isXSlowingDown = true;
		isYSlowingDown = true;
		if (this.isUsed) {

			if (input.w.isPressed()) {

				isYSlowingDown = false;
				if (Math.abs(acceleration.y - 1) < MAX_ACCELERATION
						&& tickCount % DELAY == 0) {
					acceleration.y--;
				}
			}

			if (input.s.isPressed()) {
				isYSlowingDown = false;
				if (Math.abs(acceleration.y + 1) < MAX_ACCELERATION
						&& tickCount % DELAY == 0) {
					acceleration.y++;
				}
			}

			if (input.a.isPressed()) {
				isXSlowingDown = false;
				if (Math.abs(acceleration.x - 1) < MAX_ACCELERATION
						&& tickCount % DELAY == 0) {
					acceleration.x--;
				}
			}

			if (input.d.isPressed()) {
				isXSlowingDown = false;
				if (Math.abs(acceleration.x + 1) < MAX_ACCELERATION
						&& tickCount % DELAY == 0) {
					acceleration.x++;
				}
			}

			if (input.i.isPressed()) {
				input.i.toggle(false);
				if (Display.inGameScreen) {
					Display.displayInventory();
				}
			}
			if (input.esc.isPressed()) {
				input.esc.toggle(false);
				if (Display.inGameScreen) {
					Display.displayPause();
				}
			}
			if (input.e.isPressed()) {
				if (!player.isSolidEntityCollision(-6, 0)
						&& !player.hasCollided(-6, 0)) {
					player.setX(player.getX() - 20);
					this.isUsed = false;
					player.isDriving = false;
					input.e.toggle(false);
					remPlayer();
				} else {
					showCantExitVehicle = true;
				}
			}

		}

		if (isDead) {
			return;
		}

		xa += acceleration.x;
		ya += acceleration.y;

		if ((xa != 0 || ya != 0)
				&& !isSolidEntityCollision(xa * (int) speed, ya * (int) speed)) {
			move(xa, ya);
			setMoving(true);
			if (isUsed)
				player.setMoving(true);
		} else {
			if (isSolidEntityCollision(xa * (int) speed, 0)) {
				acceleration.x = 0;
			}
			if (isSolidEntityCollision(0, ya * (int) speed)) {
				acceleration.y = 0;
			}
			setMoving(false);
			if (isUsed)
				player.setMoving(false);
		}

		if (tickCount % DELAY == 0) {
			if (isXSlowingDown) {
				if (acceleration.x > 0) {
					acceleration.x--;
				}
				if (acceleration.x < 0) {
					acceleration.x++;
				}
			}
			if (isYSlowingDown) {
				if (acceleration.y > 0) {
					acceleration.y--;
				}
				if (acceleration.y < 0) {
					acceleration.y++;
				}
			}
		}

		tickCount++;
		if (showCantExitVehicle) {
			exitTickCount++;
			if (exitTickCount >= 60) {
				showCantExitVehicle = false;
				exitTickCount = 0;
			}
		}
	}

	public void kill() {

		isDead = true;
		level.remEntity(this);
		level.addEntity(this, 0);
		level.killList.add(this);
	}

	public void render(Screen screen) {
		if (player != null && showCantExitVehicle) {
			JJFont.render("!", screen, player.getX() - 20, player.getY(),
					new int[] { 0xFF000000, 0xFF000000, 0xFFFFCC00 }, 1);
		}
	}

}
