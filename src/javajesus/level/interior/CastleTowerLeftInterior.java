package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

public class CastleTowerLeftInterior extends Interior {

	public CastleTowerLeftInterior(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Castle_Interiors/Castle_Tower_Interior_Left",
		        new Point(1927, 2080), level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] {
		        new TransporterInterior(this, 2104, 2056, new CastleBattlements(new Point(1927, 2080), outside)) };
	}

}
