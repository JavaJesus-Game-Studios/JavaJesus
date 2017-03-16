package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.ShantyHouseInterior;

/*
 * a small run down house
 */
public class ShantyHouse extends Building {

	private static final long serialVersionUID = -2605055003316533697L;

	public ShantyHouse(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFADADAD, 0xFF000000 }, Sprite.shantyhouse, SolidEntity.QUARTER);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 12, y + 31, new ShantyHouseInterior(new Point(x + 18, y + 42), level)));
	}
}
