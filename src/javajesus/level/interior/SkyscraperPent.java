package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Stairs;
import javajesus.entities.transporters.Transporter;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class SkyscraperPent extends Interior {

	public SkyscraperPent(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Skyscraper_Interiors/Skyscraper_Floor_11",
		        new Point(500, 500), level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new Stairs(this, 2112, 2008, this, Direction.SOUTH, Stairs.WOOD) };
	}
}
