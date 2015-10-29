package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.CafeInterior;

public class Cafe extends SolidEntity {

	private static final long serialVersionUID = 1292938033962226188L;

	public Cafe(Level level, int x, int y) {
		super(level, x, y, 100, 59);
		level.addEntity(new Transporter(level, x + 77, y + 43,
				new CafeInterior(new Point(x + 83, y + 55), this.level)));
	}

	public void render(Screen screen) {
		screen.render(x, y, new int[] { 0xFF111111, 0xFFFFFF99, 0xFFFFFFCC }, Sprite.cafe);
	}
}
