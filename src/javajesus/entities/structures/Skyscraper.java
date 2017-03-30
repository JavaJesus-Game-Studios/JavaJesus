package javajesus.entities.structures;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
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
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 38, y + 234, new SkyscraperLobby(new Point(x + 44, y + 243), level)));
	}

}
