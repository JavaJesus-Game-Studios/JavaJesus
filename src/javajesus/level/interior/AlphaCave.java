package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javajesus.JavaJesus;
import javajesus.entities.Mob;
import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;

public class AlphaCave extends Interior {
	
	public AlphaCave(Point point, Level level) throws IOException {
		super("/WORLD_DATA/INTERIOR_DATA/AlphaCaveInterior", new Point(832, 776), level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 832, 776, outside) };
	}
	
}
