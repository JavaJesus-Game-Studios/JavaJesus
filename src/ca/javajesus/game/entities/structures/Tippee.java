package ca.javajesus.game.entities.structures;


import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
<<<<<<< HEAD
import ca.javajesus.game.gfx.Screen;
import ca.javajesus.game.gfx.Sprite;
=======
import ca.javajesus.game.graphics.Colors;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
>>>>>>> origin/master
import ca.javajesus.level.Level;

public class Tippee  extends SolidEntity {

	public Tippee(Level level, int x, int y) {
		super(level, x, y, 32, 50);
		level.addEntity(new Transporter(level, x + 10, y + 34, this.level));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF522900, 0xFF977F66, 0xFF335C33 }, Sprite.tippee);

	}

}
