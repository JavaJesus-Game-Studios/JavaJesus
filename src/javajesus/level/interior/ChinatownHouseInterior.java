package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

public class ChinatownHouseInterior extends Interior {

	public ChinatownHouseInterior(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/SAN_CISCO/INTERIORS/Chinatown_House_Interior", new Point(864, 744),
		        level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 872, 752, outside) };
	}

}
