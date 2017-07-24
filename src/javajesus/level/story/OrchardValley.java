package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;

public class OrchardValley extends Level {

	/**
	 * Creates Orchard Valley
	 * @throws IOException 
	 */
	public OrchardValley(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)WILDERNESS_AREA/Orchard_Valley.png", Level.ORCHARD, new Point(136, 1816), slot);

		System.err.println("Creating Orchard Valley");

	}


}
