package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;
import javajesus.level.LevelFactory;

public class LordHillsboroughsDomain extends Level {

	/**
	 * Creates Lord Hillsborough's Domain
	 * @throws IOException 
	 */
	public LordHillsboroughsDomain(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)CITY_LEVELS/Domain of Lord Hillsborough.png", "Lord Hillsborough's Domain", new Point(1366, 1450), slot, LevelFactory.HILLSBOROUGH);

		System.err.println("Creating Lord Hillsborough's Domain");
	}

}
