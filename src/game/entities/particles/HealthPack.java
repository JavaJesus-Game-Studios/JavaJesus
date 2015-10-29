package game.entities.particles;

import game.SoundHandler;
import game.entities.Mob;
import game.entities.Player;
import game.graphics.Screen;

import java.awt.Rectangle;

import level.Level;

public class HealthPack extends Particle {

	private static final long serialVersionUID = -7889833550875856663L;
	
	private final Rectangle BOX = new Rectangle(10, 10);

	public HealthPack(Level level, double x, double y) {
		super(level, 9, new int[] { 0xFFF6F4EE, 0xFFFFFFFF, 0xFFFF0000 }, x, y);

		this.x += random.nextInt(400) - 200;
		this.y += random.nextInt(400) - 200;
	}

	public void render(Screen screen) {

		screen.render((int) this.x, (int) this.y, tileNumber, color, 1, 1,
				sheet);
		BOX.setLocation((int) this.x, (int) this.y);
		for (Mob mob : level.getMobs()) {

			if (BOX.intersects(mob.getBounds())) {
				mob.heal();
				if (mob instanceof Player) {
					screen.setShader(0);
				}
				level.remEntity(this);
				sound.play(SoundHandler.sound.click);
			}

		}
	}

}
