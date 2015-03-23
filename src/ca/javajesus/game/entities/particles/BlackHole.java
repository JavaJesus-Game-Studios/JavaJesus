package ca.javajesus.game.entities.particles;

import java.awt.geom.Ellipse2D;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class BlackHole extends Particle {

	private int posNumber;
	private int tickCount = 1;
	protected Ellipse2D.Double aggroRadius;
	protected final int RADIUS = 32 * 8;

	public BlackHole(Level level, double x, double y) {
		super(level, 0, Colors.get(-1, 0, 0, 0),
				x, y);
		this.sheet = SpriteSheet.explosions;
		this.posNumber = tileNumber;
		this.aggroRadius = new Ellipse2D.Double(x - RADIUS / 2, y - RADIUS / 2,
				RADIUS, RADIUS);
		SoundHandler.sound.play(SoundHandler.sound.gunshot3);
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
		}

		for (Mob mob : level.getMobs()) {
			int xa = 0;
			int ya = 0;
			if (this.aggroRadius.intersects(mob.getBounds())) {
				mob.damage(1);
				if (mob.getX() > this.x) {
					xa -= 2;
				}
				if (mob.getX() < this.x) {
					xa += 2;
				}
				if (mob.getY() > this.y) {
					ya -= 2;
				}
				if (mob.getY() < this.y) {
					ya += 2;
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
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				screen.render(this.x + (j * 24) - 24, this.y + (i * 24) - 48, posNumber + j
						+ (i * sheet.boxes), color, 0, 3, sheet);
			}
		}
	}

}
