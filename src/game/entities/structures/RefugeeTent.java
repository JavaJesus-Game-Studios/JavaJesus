package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;
import level.interior.RefugeeTentInterior;

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
