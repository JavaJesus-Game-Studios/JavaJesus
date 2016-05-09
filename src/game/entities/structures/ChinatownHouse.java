package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.ChinatownHouseInterior;

/*
 * A typical house in Chinatown
 */
public class ChinatownHouse extends Building {

	private static final long serialVersionUID = 2403522342845736281L;

	public ChinatownHouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF618249, 0xFF992B2B, 0xFFFFFFFF }, Sprite.chinatown_house, SolidEntity.HALF);
		level.add(new Transporter(level, x + 26, y + 41,
				new ChinatownHouseInterior(new Point(x + 32, y + 53), getLevel())));
	}

}
