package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * The prison
 */
public class Prison  extends Building {

	private static final long serialVersionUID = 810711005526841075L;

	public Prison(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFAB0, 0xFFABD3FF }, Sprite.prison, SolidEntity.HALF);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(new Transporter(level, x + 77, y + 64,
				new PoorHouseInterior(new Point(x + 40, y + 67), getLevel())));
	}

}
