package javajesus.entities.solid.buildings.sequoia;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Door;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * The school in sequoia
 */
public class SequoiaSchool extends Building {

	/**
	 * Creates a sequoia school
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 * @throws IOException 
	 */
	public SequoiaSchool(Level level, int x, int y) throws IOException {
		super(level, x, y, new int[] { 0xFF111111, 0xFF8D1919, 0xFF4D4DFF }, Sprite.sequoiaSchool);

		if (level != null)
		level.add(new Door(level, x + 60, y + 99, new PoorHouseInterior(new Point(x + 40, y + 67), level),0,0));
	}

	@Override
    public byte getId(){
        return Entity.SEQUOIA_SCHOOL;
    }
}
