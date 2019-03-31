package javajesus.level;

import java.io.IOException;
import java.util.HashMap;

import javajesus.level.sandbox.SandboxIslandLevel;
import javajesus.level.sandbox.SandboxOriginalLevel;

public class LevelFactory {

	// story level names
	public static final String BAUTISTA = "Bautista's Domain", EDGE_MAIN = "Edge of the Woods",
			EDGE_TOP = "Edge of the Woods Top", HILLSBOROUGH = "Lord Hillsborough's Domain", ORCHARD = "Orchard Valley",
			CISCO = "San Cisco", JUAN = "San Juan", TECH = "Tech Topia", ALPHA = "Alpha Level", ISLAND = "Island",
			TILE_TESTER = "Tile Tester", ROAD_TESTER = "Road Tester", RANDOM = "Random";

	private static HashMap<String, Level> levelMap = new HashMap<>();

	public static Level make(String name, int playerSlot) {

		try {
			Level level = null;
			switch (name) {
			case BAUTISTA:
			case EDGE_MAIN:
			case EDGE_TOP:
			case HILLSBOROUGH:
			case ORCHARD:
			case CISCO:
			case JUAN:
			case TECH:
			case ALPHA:
				level = new SandboxOriginalLevel(playerSlot); break;
			case ISLAND:
				level = new SandboxIslandLevel(playerSlot); break;
			case TILE_TESTER:
				level = new LevelTester(playerSlot); break;
			case ROAD_TESTER:
				level = new RoadLevel(playerSlot); break;
			}
			
			levelMap.put(name, level);
			return level;
			
		} catch (IOException e) {
			e.printStackTrace();

			System.exit(1);
		}

		return null;
	}

	public static Level get(String name) {
		return levelMap.get(name);
	}

}
