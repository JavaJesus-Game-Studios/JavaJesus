package game.entities.structures;

import java.awt.Point;
import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * Triad HQ
 */
public class TriadHQ extends Building {

	private static final long serialVersionUID = 7977338171228988031L;

	public TriadHQ(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF335C33, 0xFF8D1919, 0xFF4D4DFF }, Sprite.triad_HQ, SolidEntity.FIVE_SIXTHS);
		level.add(
				new Transporter(level, x + 90, y + 155, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}

}
