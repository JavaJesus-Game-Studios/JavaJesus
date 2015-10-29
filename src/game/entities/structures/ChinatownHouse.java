package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.ChinatownHouseInterior;
import level.interior.PoorHouseInterior;

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
