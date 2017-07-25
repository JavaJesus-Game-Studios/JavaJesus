package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

/*
 * The interior to the cafe
 */
public class CafeInterior extends Interior {

	/**
	 * Creates the cafe interior
	 * @param point - the spawn point in the interior
	 * @param level - the outside level
	 * @throws IOException
	 */
	public CafeInterior(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/TECH_TOPIA/INTERIORS/Cafe_Interior.png", new Point(936, 912), level);	
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 944, 920, outside) };
	}

}
