package ca.javajesus.game.entities.structures;

import java.awt.Point;

import ca.javajesus.game.entities.SolidEntity;
import ca.javajesus.game.entities.structures.transporters.Transporter;
import ca.javajesus.game.graphics.Screen;
import ca.javajesus.game.graphics.Sprite;
import ca.javajesus.level.Level;
import ca.javajesus.level.interior.ChinatownHouseInterior;
import ca.javajesus.level.interior.PoorHouseInterior;

public class ChinatownHouse extends SolidEntity {

	public ChinatownHouse(Level level, int x, int y) {
		super(level, x, y, 64, 57);
		level.addEntity(new Transporter(level, x + 26, y + 41,
				new ChinatownHouseInterior(new Point(x + 32, y + 53), this.level)));
	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] {0xFF618249, 0xFF992B2B, 0xFFFFFFFF }, Sprite.chinatown_house);

	}

}
