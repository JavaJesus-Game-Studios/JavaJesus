package javajesus.entities.solid.buildings.hippyville;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.UCGrizzlyInterior;

/*
 * Better than UC berkeley
 */
public class UCGrizzly extends Building {

	/**
	 * Creates a school
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public UCGrizzly(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFFFFFFB2, 0xFF6D6D61 }, Sprite.grizzly);

		if (level != null) {
		level.add(new Transporter(level, x + 82, y + 45, new UCGrizzlyInterior(new Point(x + 88, y + 57), level)));
		level.add(new Transporter(level, x + 106, y + 45, new UCGrizzlyInterior(new Point(x + 112, y + 57), level)));
		}
	}
	
	@Override
    public byte getId(){
        return Entity.UC_GRIZZLY;
    }
}
