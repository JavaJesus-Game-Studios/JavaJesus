package game.entities.structures.hippyville;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.Building;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.UCGrizzlyInterior;

/*
 * Better than UC berkeley
 */
public class UCGrizzly extends Building {

	private static final long serialVersionUID = 1258793771122138267L;

	public UCGrizzly(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFB2, 0xFF6D6D61 }, Sprite.grizzly, SolidEntity.HALF);
		
		this.setBounds(getBounds().x + 4, getBounds().y, getBounds().width - 8, getBounds().height);

		level.add(
				new Transporter(level, x + 82, y + 45, new UCGrizzlyInterior(new Point(x + 88, y + 57), level)));
		level.add(
				new Transporter(level, x + 106, y + 45, new UCGrizzlyInterior(new Point(x + 112, y + 57), level)));
	}
}
