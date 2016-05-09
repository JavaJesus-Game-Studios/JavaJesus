package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.TreeHouseInterior;

public class TreeHouse extends Building {

	private static final long serialVersionUID = 1952515854865848495L;

	public TreeHouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF143914, 0xFF522900 }, Sprite.treehouse, SolidEntity.TWO_THIRDS);
		level.add(
				new Transporter(level, x + 18, y + 30, new TreeHouseInterior(new Point(x + 24, y + 42), level)));
	}

}
