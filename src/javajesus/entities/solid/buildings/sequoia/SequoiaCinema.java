package javajesus.entities.solid.buildings.sequoia;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * A snazzy cinema
 */
public class SequoiaCinema extends Building {

	/**
	 * Creates a sequoiac cinema
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public SequoiaCinema(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D1919, 0xFF4D4DFF }, Sprite.sequoiaCinema);

		// change the bounds
		setBounds(getBounds().x + 14, getBounds().y, getBounds().width - 14, getBounds().height);

		if (level != null)
		level.add(new Transporter(level, x + 59, y + 99, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
	}
	
	@Override
    public byte getId(){
        return Entity.SEQUOIA_CINEMA;
    }
}
