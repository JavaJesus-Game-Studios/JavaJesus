package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

public class TreeHouseInterior extends Interior {

	public TreeHouseInterior(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/HIPPY_VILLE/INTERIORS/Tree_House_Interior", new Point(384, 456),
		        level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 392, 464, outside) };
	}

}
