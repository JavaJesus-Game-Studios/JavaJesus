package javajesus.level.story;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;
import javajesus.level.LevelFactory;

public class EdgeOfTheWoodsTop extends Level {

	/**
	 * Creates Edge Of The Woods Top section
	 * @throws IOException 
	 */
	public EdgeOfTheWoodsTop(int slot) throws IOException {
		super("/WORLD_DATA/STORY_DATA/(X)WILDERNESS_AREA/Edge_of_The_Woods_Top.png", "Edge of the Woods Top", new Point(1832, 1544), slot, LevelFactory.EDGE_TOP);

		System.err.println("Creating Edge Of The Woods Top");
	}

}
