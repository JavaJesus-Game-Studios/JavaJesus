package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;
import javajesus.level.LevelFactory;

public class EdgeOfTheWoods extends Level {

	/**
	 * Creates Edge Of The Woods level
	 * @throws IOException 
	 */
	public EdgeOfTheWoods(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)WILDERNESS_AREA/Edge_of_the_Woods_Main.png", LevelFactory.EDGE_MAIN, new Point(2704, 552), slot);

		System.err.println("Creating Edge Of The Woods");
	}

}
