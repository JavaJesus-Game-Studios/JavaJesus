package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

public class RussianChurchInterior extends Interior {

	public RussianChurchInterior(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/Russian_Church_Interior", new Point(248, 296),
		        level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 256, 304, outside) };
	}

}
