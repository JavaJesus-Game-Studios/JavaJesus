package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;
import javajesus.level.LevelFactory;

public class OrchardValley extends Level {

	/**
	 * Creates Orchard Valley
	 * @throws IOException 
	 */
	public OrchardValley(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)WILDERNESS_AREA/Orchard_Valley.png", "Orchard Valley", new Point(136, 1816), slot, LevelFactory.ORCHARD);

		System.err.println("Creating Orchard Valley");

	}


}
