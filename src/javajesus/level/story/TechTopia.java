package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;
import javajesus.level.LevelFactory;

public class TechTopia extends Level {

	/**
	 * Creates Tech Topia
	 * @throws IOException 
	 */
	public TechTopia(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)CITY_LEVELS/Tech_Topia.png", "Tech Topia", new Point(1512, 584), slot, LevelFactory.TECH);

		System.err.println("Creating Tech Topia");
	}


}