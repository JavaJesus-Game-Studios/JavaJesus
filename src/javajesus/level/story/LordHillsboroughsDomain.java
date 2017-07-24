package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;

public class LordHillsboroughsDomain extends Level {

	/**
	 * Creates Lord Hillsborough's Domain
	 * @throws IOException 
	 */
	public LordHillsboroughsDomain(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)CITY_LEVELS/Domain of Lord Hillsborough.png", Level.HILLSBOROUGH, new Point(1366, 1450), slot);

		System.err.println("Creating Lord Hillsborough's Domain");
	}

}
