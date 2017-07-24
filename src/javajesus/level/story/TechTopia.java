package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;

public class TechTopia extends Level {

	/**
	 * Creates Tech Topia
	 * @throws IOException 
	 */
	public TechTopia(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)CITY_LEVELS/Tech_Topia.png", Level.TECH, new Point(1512, 584), slot);

		System.err.println("Creating Tech Topia");
	}


}