package ca.javajesus.game.entities.structures;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Colors;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;

public class ShantyHouse extends SolidEntity{

	protected int color = Colors.get(-1, 111, Colors.fromHex("#adadad"), -1);
	
	public ShantyHouse(Level level, int x, int y, int width, int height) {
		super(level, x, y, 36, 47);
		level.addEntity(new Transporter(level, x + 12, y + 31, this.level));
	}
	public void render(Screen screen) {

		screen.render((int) x, (int) y, color, Sprite.shantyhouse);

	}

}

