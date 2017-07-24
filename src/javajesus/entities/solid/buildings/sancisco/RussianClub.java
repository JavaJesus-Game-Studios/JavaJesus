package javajesus.entities.solid.buildings.sancisco;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.solid.buildings.Building;
import javajesus.entities.transporters.Transporter;
import javajesus.graphics.Sprite;
import javajesus.level.Level;
import javajesus.level.interior.PoorHouseInterior;

/*
 * A Russian Club
 */
public class RussianClub extends Building {

	/**
	 * Creates a Russian club
	 * 
	 * @param level - the level it is on
	 * @param x - the x coord on the level
	 * @param y - the y coord on the level
	 */
	public RussianClub(Level level, int x, int y) {
		super(level, x, y, new int[] { 0xFFFFEB0A, 0xFF80004B, 0xFFE934F9 }, Sprite.russian_club);

		level.add(new Transporter(level, x + 38, y + 45, new PoorHouseInterior(new Point(x + 40, y + 67), level)));
		level.add(new Transporter(level, x + 51, y + 45, level));
	}
	
	@Override
	public byte getId(){
        return Entity.RUSSIAN_CLUB;
    }
}
