package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;

import level.Level;
import level.interior.PoorHouseInterior;

public class GreatTree extends SolidEntity {

	private static final long serialVersionUID = -2057500563473932212L;

	public GreatTree(Level level, int x, int y) {
		super(level, x, y, 56, 120);
		level.addEntity(new Transporter(level, x + 22, y + 30,
				new PoorHouseInterior(new Point(x + 28, y + 42), this.level)));
	}
	
	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFF143914, 0xFF522900 }, Sprite.greattree);

	}

}
