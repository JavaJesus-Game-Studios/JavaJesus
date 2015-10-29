package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Screen;
import game.graphics.Sprite;

import java.awt.Point;
import java.awt.Rectangle;

import level.Level;
import level.interior.CastleInterior;

public class Castle extends SolidEntity {

	private static final long serialVersionUID = -5648283117813621970L;

	public Castle(Level level, int x, int y) {
		super(level, x, y, 321, 176);
		this.shadow = new Rectangle(width, (5 * height / 6));
		this.shadow.setLocation(x + 12, y);
		this.bounds = new Rectangle(width, (height / 6) - 8);
		this.bounds.setLocation(x + 12, y + shadow.height);
		level.addEntity(new Transporter(level, x + 154, y + 160,
				new CastleInterior(new Point(x + 43, y + 167), this.level)));

	}

	public void render(Screen screen) {

		screen.render((int) x, (int) y, new int[] { 0xFF111111, 0xFF8D8D8D, 0xFFEEFEFF }, Sprite.castle);

	}

}
