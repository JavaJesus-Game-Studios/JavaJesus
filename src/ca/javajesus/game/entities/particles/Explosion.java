package ca.javajesus.game.entities.particles;

import java.awt.Rectangle;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Explosion extends Particle {

	private int posNumber;
	private int tickCount = 1;
	private Rectangle bounds;

	public Explosion(Level level, double x, double y) {
		super(level, 4 * SpriteSheet.explosions.boxes, new int[] { 0xFFFF9900,
				0xFFFF3C00, 0xFFFF0000 }, x, y);
		this.sheet = SpriteSheet.explosions;
		this.posNumber = tileNumber;
		SoundHandler.sound.play(SoundHandler.sound.gunshot3);
		this.bounds = new Rectangle(16, 16);
		bounds.setLocation((int) x, (int) y);
	}

	public void tick() {
		if (this.x > level.width * sheet.boxes || this.x < 0
				|| this.y > level.height * sheet.boxes || this.y < 0) {
			level.remEntity(this);
		}

		if (tickCount % 5 == 0) {
			posNumber += 2;
		}

		if (posNumber > tileNumber + 26) {
			level.remEntity(this);
		}

		for (Mob mob : level.getMobs()) {
			if (mob.getBounds().intersects(this.bounds)) {
				mob.damage(1);
			}
		}

		tickCount++;
	}

	public void render(Screen screen) {
		screen.render((int) this.x, (int) this.y, posNumber, color, 0, 1, sheet);
		screen.render((int) this.x + 8, (int) this.y, posNumber + 1, color, 0,
				1, sheet);
		screen.render((int) this.x, (int) this.y + 8, posNumber + sheet.boxes,
				color, 0, 1, sheet);
		screen.render((int) this.x + 8, (int) this.y + 8, posNumber + 1
				+ sheet.boxes, color, 0, 1, sheet);
	}

}
