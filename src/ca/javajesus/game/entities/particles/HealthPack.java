package ca.javajesus.game.entities.particles;

import java.awt.Rectangle;

import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class HealthPack extends Particle {

	private final Rectangle BOX = new Rectangle(10, 10);

	public HealthPack(Level level, double x, double y) {
		super(level, 9, Colors.get(-1, Colors.fromHex("#f6f4ee"),
				Colors.fromHex("#ffffff"), 500), x, y);

		this.x += random.nextInt(400) - 200;
		this.y += random.nextInt(400) - 200;
	}

	public void render(Screen screen) {

		screen.render(this.x, this.y, tileNumber, color, 1, 1, sheet);
		BOX.setLocation((int) this.x, (int) this.y);
		for (Mob mob : level.getMobs()) {

			if (BOX.intersects(mob.getBounds())) {
				mob.heal();
				if (mob instanceof Player) {
					screen.getGame().updateLevel();
				}
				level.remEntity(this);
				sound.play(SoundHandler.sound.click);
			}

		}
	}

}
