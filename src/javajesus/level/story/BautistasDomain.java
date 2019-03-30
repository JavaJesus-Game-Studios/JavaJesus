package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;
import javajesus.level.LevelFactory;

/*
 * The level of Bautistas Domain
 */
public class BautistasDomain extends Level {

	/**
	 * Creates Bautistas Domain
	 * 
	 * @param slot - save file to choose
	 * @throws IOException 
	 */
	public BautistasDomain(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)CITY_LEVELS/Domain_of_Ranchero_Bautista.png", LevelFactory.BAUTISTA, new Point(2896, 64), slot);

		System.err.println("Creating Bautistas Domain");
	}
}
