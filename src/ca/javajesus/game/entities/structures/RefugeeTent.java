package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.PoorHouseInterior;
import ca.javajesus.level.interior.RefugeeTentInterior;

public class RefugeeTent  extends SolidEntity {

	public RefugeeTent(Level level, int x, int y) {
		super(level, x, y, 47, 23);
		level.addEntity(new Transporter(level, x + 34, y + 7,
				new RefugeeTentInterior(new Point(x + 40, y + 18), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFFF8FA8F, 0xFF000000 }, Sprite.refugee_tent);

	}

}
