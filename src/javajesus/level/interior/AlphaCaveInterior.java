package javajesus.level.interior;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.npcs.aggressive.Orangutan;
import javajesus.entities.transporters.Transporter;
import javajesus.entities.transporters.TransporterInterior;
import javajesus.level.Level;
import javajesus.quest.original.AlphaTurningTheTide;

/*
 * Custom cave for the alpha level
 */
public class AlphaCaveInterior extends Interior {
	
	/**
	 * @param point - spawn point in the cave
	 * @param level - outside level
	 * @throws IOException
	 */
	public AlphaCaveInterior(Point point, Level level) throws IOException {
		super("/WORLD_DATA/INTERIOR_DATA/AlphaCaveInterior", new Point(832, 776), level);
		
		// Adds wise Orangutan with the "Turning the Tide" Quest inside the cave
		Orangutan ken = new Orangutan(this, 1464, 16);
		ken.addQuest(new AlphaTurningTheTide(ken));
	}

	@Override
	public Transporter[] getTransporters() throws IOException {
		return new Transporter[] { new TransporterInterior(this, 832, 776, outside) };
	}
	
}
