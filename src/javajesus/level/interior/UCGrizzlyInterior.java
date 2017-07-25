package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

public class UCGrizzlyInterior extends Interior {

	public UCGrizzlyInterior(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/HIPPY_VILLE/INTERIORS/UC_Grizzly_Interior.png", new Point(936, 912),
		        level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 944, 920, outside) };
	}

}
