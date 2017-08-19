package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

public class RancheroHouseInterior extends Interior {

	public RancheroHouseInterior(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Ranchero_House_Interior", new Point(872, 712),
		        level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 872, 712, outside) };
	}

}
