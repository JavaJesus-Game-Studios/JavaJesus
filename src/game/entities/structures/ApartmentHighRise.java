package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;
import java.awt.Rectangle;

import level.Level;
import level.interior.ApartmentLobby;

public class ApartmentHighRise extends SolidEntity {

	private static final long serialVersionUID = 3895374472641199582L;

	public ApartmentHighRise(Level level, int x, int y) {
		super(level, x, y, 71, 222);
		this.shadow = new Rectangle(width, (2 * height / 3));
		this.shadow.setLocation(x, y);
		this.bounds = new Rectangle(width, (height / 3) - 8);
		this.bounds.setLocation(x, y + shadow.height);
		level.addEntity(new Transporter(level, x + 30, y + 206, new ApartmentLobby(new Point(x + 36, y + 216), level)));
	}

	public void render(Screen screen) {

		screen.render(x, y, new int[] { 0xFF111111, 0xFF673101, 0xFFABD3FF }, Sprite.apartment);

	}

}
