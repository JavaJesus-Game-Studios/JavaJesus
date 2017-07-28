package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Stairs;
import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;
import javajesus.utility.Direction;

public class CastleBattlements extends Interior {

	public CastleBattlements(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Castle_Interiors/Castle_1_Battlements", point,
		        level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] {

		        new TransporterInterior(this, 504, 472, outside),
		        new Stairs(this, 376, 304, new CastleInteriorFloor1(new Point(384, 224), this), Direction.SOUTH,
		                Stairs.STONE),
		        new Stairs(this, 648, 304, new CastleInteriorFloor1(new Point(640, 224), this), Direction.SOUTH,
		                Stairs.STONE)

		};
	}

}
