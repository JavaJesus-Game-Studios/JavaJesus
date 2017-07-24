package javajesus.level;

import java.awt.Point;
import java.io.IOException;

/* Tester level for testing all of our entities */
public class LevelTester extends Level {

	// Name of the level
	private static final String NAME = "Tester Level 1";
	
	/**
	 * Creates  Tester Level
	 * @throws IOException 
	 */
	public LevelTester(int slot) throws IOException {
		super("/WORLD_DATA/TESTER_LEVELS/tile_tester",
				NAME, new Point(150, 100), slot);
	}

}
