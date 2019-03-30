package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;
import javajesus.level.LevelFactory;

/*
 * A Parody of San Jose
 */
public class SanJuan extends Level {

	/**
	 * Creates San Juan
	 * @throws IOException 
	 */
	public SanJuan(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)CITY_LEVELS/San_Juan.png", LevelFactory.JUAN, new Point(1680, 2648), slot);

		System.err.println("Creating San Juan");

	}


}
