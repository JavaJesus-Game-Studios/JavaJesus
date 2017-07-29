package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Stairs;
import javajesus.entities.transporters.Transporter;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * An interior inside the skyscraper
 */
public class SkyscraperFloor extends Interior {

	// the current initialized floor
	private static int floorNum;

	/**
	 * @param point - the entrance point
	 * @param level - the level the player came from
	 * @param floorNum - which floor number it is
	 * @throws IOException
	 */
	public SkyscraperFloor(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Skyscraper_Interiors/Skyscraper_Floors_1-10",
		        new Point(500, 500), level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		Transporter[] entities = new Transporter[2];

		if (floorNum++ < 10) {
			entities[0] = new Stairs(this, 2112, 1968, new SkyscraperFloor(new Point(2116, 1971), this),
			        Direction.EAST, Stairs.WOOD);
		} else {
			entities[0] = new Stairs(this, 2112, 1968, new SkyscraperPent(new Point(2116, 1971), this), Direction.EAST,
			        Stairs.WOOD);
			floorNum = 0;
		}
		entities[1] = new Stairs(this, 2112, 2008, this, Direction.SOUTH, Stairs.WOOD);

		return entities;
	}

}
