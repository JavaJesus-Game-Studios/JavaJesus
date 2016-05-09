package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.SkyscraperLobby;

/*
 * A large skyscraper!
 */
public class Skyscraper extends Building {

	private static final long serialVersionUID = -7981585850251413451L;

	public Skyscraper(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF673101, 0xFFABD3FF }, Sprite.skyscraper,
				SolidEntity.SEVEN_EIGTHS);
		level.add(
				new Transporter(level, x + 38, y + 234, new SkyscraperLobby(new Point(x + 44, y + 243), level)));
	}

}
