package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;

public class JungleHQ extends SolidEntity {

	public JungleHQ(Level level, int x, int y) {
		super(level, x, y, 148, 96);
		level.addEntity(new Transporter(level, x + 61, y + 80,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
		level.addEntity(new Transporter(level, x + 75, y + 80,
				new PoorHouseInterior(new Point(x + 40, y + 67), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFF248F24, 0xFF4D4DFF }, Sprite.jungle_hq);

	}
	
}
