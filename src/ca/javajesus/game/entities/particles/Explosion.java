package ca.javajesus.game.entities.particles;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.SpriteSheet;
import ca.javajesus.level.Level;

public class Explosion extends Particle {

	private int posNumber;
	private int tickCount = 1;

	public Explosion(Level level, double x, double y) {
		super(level, 4 * 32, Colors.get(-1, -300, 400, 550), x, y);
		this.sheet = SpriteSheet.explosions;
		this.posNumber = tileNumber;
		SoundHandler.sound.play(SoundHandler.sound.gunshot3);
	}

	public void tick() {
		if (this.x > level.width * 32 || this.x < 0
				|| this.y > level.height * 32 || this.y < 0) {
			level.remEntity(this);
		}

		if (tickCount % 5 == 0) {
			posNumber += 2;
		}

		if (posNumber > tileNumber + 26) {
			level.remEntity(this);
		}

		tickCount++;
	}

	public void render(Screen screen) {
		screen.render(this.x, this.y, posNumber, color, 0, 1, sheet);
		screen.render(this.x + 8, this.y, posNumber + 1, color, 0, 1, sheet);
		screen.render(this.x, this.y + 8, posNumber + 32, color, 0, 1, sheet);
		screen.render(this.x + 8, this.y + 8, posNumber + 33, color, 0, 1,
				sheet);
	}

}
