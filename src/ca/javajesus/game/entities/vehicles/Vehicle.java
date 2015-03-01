package ca.javajesus.game.entities.vehicles;

import ca.javajesus.game.Game;
import ca.javajesus.game.InputHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Vehicle extends Mob {

	public boolean isUsed = false;
	protected Player player;
	protected InputHandler input;
	int vehicleTick = 0;
	CarPhysics physics;

	public static Vehicle vehicle1 = new CenturyLeSabre(Level.level1,
			"Century LeSabre", 300, 300, 3, 200);

	public static Vehicle boat1 = new Boat(Level.level1, "Century LeSabre",
			300, 500, 3, 200);

	public Vehicle(Level level, String name, double x, double y, int speed,
			int width, int height, SpriteSheet sheet, double defaultHealth) {
		super(level, name, x, y, speed, width, height, sheet, defaultHealth);
		this.physics = new CarPhysics(0, 0, 0, 0);
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

	public void tick() {
		int xa = 0;
		int ya = 0;
		if (isMobCollision() && this.isMoving) {
			for (Mob mob : level.getMobs()) {
				if (!(mob == this || mob instanceof Player)) {
					if (mob.hitBox.intersects(this.hitBox))
						mob.damage(2, 8);
				}
			}
		}
		
		if (this.isUsed) {
		    		    
			if (input.w.isPressed()) {

				if (physics.getYAcceleration() > 0) {
					physics.setYAcceleration(0);
				} else
					physics.setYAcceleration(-2);
				//input.w.toggle(false);
			}

			if (input.s.isPressed()) {
				if (physics.getYAcceleration() < 0) {
					physics.setYAcceleration(0);
				} else
					physics.setYAcceleration(2);
				//input.s.toggle(false);
			}

			if (input.a.isPressed()) {
				if (physics.getXAcceleration() > 0) {
					physics.setXAcceleration(0);
				} else
					physics.setXAcceleration(-2);
				//input.a.toggle(false);
			}

			if (input.d.isPressed()) {
				if (physics.getXAcceleration() < 0) {
					physics.setXAcceleration(0);
				} else
					physics.setXAcceleration(2);
				//input.d.toggle(false);
			}
			
			if (input.b.isPressed()) {
                physics.reset();
			    input.b.toggle(false);
            }
			
			/*if(!(input.w.isPressed()||input.s.isPressed()||input.a.isPressed()||input.d.isPressed()))
			{
			    physics.setXAcceleration(0);
			    physics.setYAcceleration(0);
			}*/

			//physics.setTick(vehicleTick / 60);
			
			if (hasCollided(xa, ya))
			{
			    physics.reset();
			}
			
			if(Math.abs(physics.getXVelocity()) > 0)
			{
			    physics.setXFriction(0.25);
			}
			else
			{
			    physics.setXFriction(0);
			    if(vehicleTick%2==0)
			    {
			    physics.xReset();
			    }
			}
			
			if(Math.abs(physics.getYVelocity()) > 0)
            {
                physics.setYFriction(0.25);
            }
            else
            {
                physics.setYFriction(0);
                if(vehicleTick%2==0)
                {
                physics.yReset();
                }
            }
			    
			physics.xFriction(movingDir);
			physics.yFriction(movingDir);
			
			physics.updatePosition();
			xa = (int) physics.x / 10;
			ya = (int) physics.y / 10;
			//xa = (int) physics.getXVelocity();
			//ya = (int) physics.getYVelocity();

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
			
			if ((xa != 0 || ya != 0)
					&& !isSolidEntityCollision(xa * (int) speed, ya
							* (int) speed)) {
				move(xa, ya);
				isMoving = true;
				player.isMoving = true;
			} else {
			 	isMoving = false;
				player.isMoving = false;
			}
			
			if (isSolidEntityCollision(xa * (int) speed, ya * (int) speed)) {
            physics.reset();
            xa = 0;
            ya = 0;
            vehicleTick=0;
			}

			System.out.println(xa + "   " + ya);
			System.out.println(physics.x + "   " + physics.y);
			System.out.println(physics.getXVelocity() + "   " + physics.getYVelocity());
			System.out.println(physics.getXAcceleration() + "   " + physics.getYAcceleration());
		}
		vehicleTick++;
		//physics.incrementTick();
	}

	public void render(Screen screen) {

	}

}
