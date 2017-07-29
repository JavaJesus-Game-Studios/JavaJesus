package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Stairs;
import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class CastleInterior extends Interior {

	public CastleInterior(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Castle_Interiors/Castle_1_Main_Hall",
		        new Point(496, 456), level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] {

		        new TransporterInterior(this, 504, 472, outside),
		        new Stairs(this, 352, 240, outside, Direction.NORTH,
		                Stairs.STONE),
		        new Stairs(this, 664, 240, outside, Direction.NORTH,
		                Stairs.STONE) };
	}

}
