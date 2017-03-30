package javajesus.entities.structures.sancisco;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * Triad HQ
 */
public class TriadHQ extends Building {

	private static final long serialVersionUID = 7977338171228988031L;

	public TriadHQ(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF335C33, 0xFF8D1919, 0xFF4D4DFF }, Sprite.triad_HQ, SolidEntity.FIVE_SIXTHS);
		
		this.setBounds(getBounds().x + 19, getBounds().y, getBounds().width - 36, getBounds().height);
		
		level.add(
				new Transporter(level, x + 90, y + 155, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}

}
