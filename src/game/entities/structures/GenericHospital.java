package game.entities.structures;

import game.entities.SolidEntity;
import game.entities.structures.transporters.Transporter;
import game.graphics.Sprite;
import level.Level;

/*
 * A typical hospital
 */
public class GenericHospital extends Building {

	private static final long serialVersionUID = 1190467540624136913L;

	public GenericHospital(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFFF, 0xFF496787 }, Sprite.generic_hospital, SolidEntity.TWO_THIRDS);
		level.addEntity(new Transporter(level, x + 45, y + 64, getLevel()));
	}

}
