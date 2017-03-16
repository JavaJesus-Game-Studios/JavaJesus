package game.entities.structures.techtopia;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.Building;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.RadarDishInterior;

/*
 * A large round dish
 */
public class RadarDish extends Building {

	private static final long serialVersionUID = 2945216436648329178L;

	public RadarDish(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFA3A3C2, 0xFF75758C }, Sprite.radardish, SolidEntity.QUARTER);
		
		this.setBounds(getBounds().x + 10, getBounds().y, getBounds().width - 20, getBounds().height);
		
		level.add(new Transporter(level, x + 37, y + 117,
				new RadarDishInterior(new Point(x + 43, y + 125), level)));
	}
	

}
