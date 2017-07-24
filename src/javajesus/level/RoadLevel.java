package javajesus.level;

import java.awt.Point;
import java.io.IOException;

public class RoadLevel extends Level {

	public RoadLevel(int slot) throws IOException {
		super("/WORLD_DATA/TESTER_LEVELS/road_tester", "Road Level", new Point(50, 50), slot);
	}

}
