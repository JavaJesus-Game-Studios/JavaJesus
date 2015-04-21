package ca.javajesus.game.entities.particles;

import java.awt.geom.Ellipse2D;
import java.util.Random;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.SpriteSheet;
import ca.javajesus.level.Level;

public class BlackHole extends Particle {

	private static final long serialVersionUID = 2827325538515820858L;
	
	private int posNumber;
	private int tickCount = 1;
	protected Ellipse2D.Double aggroRadius;
	protected final int RADIUS = 32 * 32;
	private boolean darken = true;
	private boolean lighten = false;
	private Random random = new Random();

	public BlackHole(Level level, double x, double y) {
		super(level, 0, new int[] { 0xFF000000, 0xFF000000, 0xFF000000 }, x, y);
		this.sheet = SpriteSheet.explosions;
		this.posNumber = tileNumber;
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		SoundHandler.sound.play(SoundHandler.sound.explosion);
	}

	public void tick() {
		if (this.x > level.width * sheet.boxes || this.x < 0
				|| this.y > level.height * sheet.boxes || this.y < 0) {
			level.remEntity(this);
		}

		if (tickCount % 20 == 0) {
			posNumber += 4;
		}

		if (posNumber > tileNumber + (14 * 4)) {
			level.remEntity(this);
		} else if (posNumber > tileNumber + (13 * 4)) {
			lighten = true;
		}

		if (random.nextInt(2) == 0) {
			level.addEntity(new Explosion(level, random.nextInt(100) - 50
					+ this.x, random.nextInt(100) - 50 + this.y));
		}

		for (Entity e : level.getEntities()) {
			/*
			 * if (e instanceof SolidEntity) { if (e.getX() > this.x) {
			 * e.setX(e.getX() - 1); } if (e.getX() < this.x) { e.setX(e.getX()
			 * + 1); } if (e.getY() > this.y) { e.setY(e.getY() - 1); } if
			 * (e.getY() < this.y) { e.setY(e.getY() + 1); } }
			 */
			if (!(e instanceof Mob)) {
				continue;
			}
			Mob mob = (Mob) e;
			int xa = 0;
			int ya = 0;
			if (this.aggroRadius.intersects(mob.getBounds())) {
				mob.damage(1);
				if (mob.getX() > this.x) {
					xa -= 1;
				}
				if (mob.getX() < this.x) {
					xa += 1;
				}
				if (mob.getY() > this.y) {
					ya -= 1;
				}
				if (mob.getY() < this.y) {
					ya += 1;
				}
			} else if (mob.isDead()) {
				if (mob.getX() > this.x) {
					mob.setX(mob.getX() - 1);
				}
				if (mob.getX() < this.x) {
					mob.setX(mob.getX() + 1);
				}
				if (mob.getY() > this.y) {
					mob.setY(mob.getY() - 1);
				}
				if (mob.getY() < this.y) {
					mob.setY(mob.getY() + 1);
				}
			}

			if ((xa != 0 || ya != 0) && !mob.isSolidEntityCollision(xa, ya)) {
				mob.setMoving(true);
				mob.move(xa, ya);
			}

		}

		tickCount++;
	}

	public void render(Screen screen) {
		if (darken) {
			screen.setShader(983082);
			darken = false;
		}
		if (lighten) {
			screen.setShader(0);
			lighten = false;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				screen.render((int) this.x + (j * 24) - 24, (int) this.y
						+ (i * 24) - 48, posNumber + j + (i * sheet.boxes),
						color, 0, 3, sheet);
			}
		}
	}

}
