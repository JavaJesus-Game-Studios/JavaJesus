package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.transporters.Stairs;
import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;
import javajesus.utility.Direction;

/*
 * The Lobby of ApartmentHighRise
 */
public class ApartmentLobby extends Interior {

	/**
	 * Creates the apartment interior
	 * @param point - the spawn point in the interior
	 * @param level - the outside level
	 * @throws IOException
	 */
	public ApartmentLobby(Point point, Level level) throws IOException {
		super("/VISUAL_DATA/STATICS/ARCHITECTURE/GENERIC/INTERIORS/APARTMENT_INTERIORS/Apartment_Lobby.png", new Point(
				1944, 2040), level);
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { 
				new TransporterInterior(this, 1944, 2048, outside),
		        new Stairs(this, 2113, 1710, new SkyscraperFloor(new Point(2113, 1710), this, 1),
		                Direction.NORTH, Stairs.CARPET) };
	}

}
