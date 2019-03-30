package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;
import javajesus.level.LevelFactory;

/*
 * A Parody of San Francisco
 */
public class SanCisco extends Level {

	/**
	 * Creates San Cisco
	 * @throws IOException 
	 */
	public SanCisco(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)CITY_LEVELS/San_Cisco.png", LevelFactory.CISCO, new Point(3400, 2688), slot);

		System.err.println("Creating San Cisco");
	}

}
