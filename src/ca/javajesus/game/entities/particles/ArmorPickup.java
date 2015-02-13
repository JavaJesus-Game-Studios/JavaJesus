package ca.javajesus.game.entities.particles;

import java.awt.Rectangle;
import ca.javajesus.game.SoundHandler;
import ca.javajesus.game.entities.Mob;
import ca.javajesus.game.entities.Player;
import ca.javajesus.game.gfx.Colors;
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.level.Level;

public class ArmorPickup extends Particle {

	private static int color = Colors.get(-1, 555, 300, 500);
	private final Rectangle BOX = new Rectangle(10, 10);

	public ArmorPickup(Level level, double x, double y) {
		super(level, 9, color, x, y);

		this.x = x;
		this.y = y;
	}

	public void render(Screen screen) {

		screen.render(this.x, this.y, tileNumber, color, 1, 1, sheet);
		BOX.setLocation((int) this.x, (int) this.y);
		for (Mob mob : level.getMobs()) {

			if (mob instanceof Player && BOX.intersects(mob.hitBox)) {
				((Player) mob).yTile = 12;
				mob.defense = 5;
				sound.play(SoundHandler.sound.click);
				level.remEntity(this);
			}

		}
	}

}
