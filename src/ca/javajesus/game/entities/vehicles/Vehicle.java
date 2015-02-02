package ca.javajesus.game.entities.vehicles;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Vehicle extends Mob {

	protected int color;
	protected int scale = 1;
	public boolean isUsed = false;
	protected Player player;
	protected InputHandler input;
	int vehicleTick = 0;

	public static Vehicle vehicle1 = new CenturyLeSabre(Level.level1,
			"Century LeSabre", 300, 300, 3, 200);

	public static Vehicle boat1 = new Boat(Level.level1, "Century LeSabre",
			300, 500, 3, 200);

	public Vehicle(Level level, String name, double x, double y, int speed,
			int width, int height, SpriteSheet sheet, double defaultHealth) {
		super(level, name, x, y, speed, width, height, sheet, defaultHealth);
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
		int xMax = 0;
		int yMin = 0;
		int yMax = 0;
		if (movingDir == 0 || movingDir == 1) {
			xMin = 0;
			xMax = 31;
			yMin = 0;
			yMax = 39;
		} else {
			xMin = 0;
			xMax = 39;
			yMin = 0;
			yMax = 31;
		}
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
	
	int xa = 0;
    int ya = 0;

	public void tick() {
		if (isMobCollision() && this.isMoving) {
			for (Mob mob : level.getMobs()) {
				if (!(mob == this || mob instanceof Player)) {
					if (mob.hitBox.intersects(this.hitBox))
						mob.damage(2, 8);
				}
			}
		}

		CarPhysics car = new CarPhysics(xa, ya, 0, 0);
        
		if (this.isUsed) {
			if (input.w.isPressed()) {
				car.setYAcceleration(-2);
			    //ya--;
				if (isSolidEntityCollision(0, ya)) {
					ya++;
				}
			}
			if (input.s.isPressed()) {
				car.setYAcceleration(2);
			    //ya++;
				if (isSolidEntityCollision(0, ya)) {
					ya--;
				}
			}
			if (input.a.isPressed()) {
			    car.setXAcceleration(-2);
			    //xa--;
				if (isSolidEntityCollision(xa, 0)) {
					xa++;
				}
			}
			if (input.d.isPressed()) {
			    car.setXAcceleration(2);
                //xa++;
				if (isSolidEntityCollision(xa, 0)) {
					xa--;
				}
			}
			
			car.setTick(vehicleTick/60);
			car.position();
	        xa = (int)car.x/30;
	        ya = (int)car.y/30;
	        
		
			if (input.i.isPressed()) {
				input.i.toggle(false);
				if (Game.inGameScreen) {
					Game.displayInventory();
				}
			}
			if (input.esc.isPressed()) {
				input.esc.toggle(false);
				if (Game.inGameScreen) {
					Game.displayPause();
				}
			}
			if (input.e.isPressed()) {
				this.isUsed = false;
				level.addEntity(player.bar);
				player.isDriving = false;
				input.e.toggle(false);
				player.x -= 30;
				remPlayer();
				return;
			}

			if (input.shift.isPressed()) {
				scaledSpeed = 3;
			} else {
				scaledSpeed = 1;
			}
			
			if ((xa != 0 || ya != 0)
					&& !isSolidEntityCollision(xa * (int) scaledSpeed, ya
							* (int) scaledSpeed) && scaledSpeed > 1) {
				move(xa, ya, scaledSpeed);
				isMoving = true;
				player.isMoving = true;
			} else if ((xa != 0 || ya != 0) && !isSolidEntityCollision(xa, ya)) {
				move(xa, ya, 1);
				isMoving = true;
				player.isMoving = true;
			} else {
				isMoving = false;
				player.isMoving = false;
			}
			
			System.out.println(xa + "   " + ya);
			
		}
		vehicleTick++;
	}

	public void render(Screen screen) {

	}

}
