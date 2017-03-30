package javajesus.entities.structures.hippyville;

import java.awt.Point;

import javajesus.entities.SolidEntity;
import javajesus.entities.structures.Building;
import javajesus.entities.structures.transporters.Transporter;
import javajesus.graphics.Sprite;
import level.Level;
import level.interior.PoorHouseInterior;

/*
 * A large tree house
 */
public class GreatTree extends Building {

	private static final long serialVersionUID = -2057500563473932212L;

	public GreatTree(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFF143914, 0xFF522900 }, Sprite.greattree, SolidEntity.TWO_THIRDS);
		
		this.setBounds(getBounds().x + 17, getBounds().y, getBounds().width - 34, getBounds().height);

		
		level.add(
				new Transporter(level, x + 22, y + 30, new PoorHouseInterior(new Point(x + 28, y + 42), getLevel())));
	}

}
