package javajesus.level;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;

import javajesus.level.interior.AlphaCaveInterior;
import javajesus.level.interior.Interior;
import javajesus.level.sandbox.SandboxIslandLevel;
import javajesus.level.sandbox.SandboxOriginalLevel;

public class LevelFactory {

	public static final int BAUTISTA = 0, EDGE_MAIN = 1,
			EDGE_TOP = 2, HILLSBOROUGH = 3, ORCHARD = 4,
			CISCO = 5, JUAN = 6, TECH = 7, ALPHA = 8, ISLAND = 9,
			TILE_TESTER = 10, ROAD_TESTER = 11, RANDOM = 12, ALPHA_CAVE_INTERIOR = 13;

	private static HashMap<Integer, Level> levelMap = new HashMap<>();

	public static Level make(int id, int playerSlot) {

		try {
			Level level = null;
			switch (id) {
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
			
			if (level != null) {
				levelMap.put(id, level);
			}
			
			return level;
			
		} catch (IOException e) {
			e.printStackTrace();

			System.exit(1);
		}

		return null;
	}
	
	public static Interior make(int id, Point spawnPoint, Level outsideLevel) {
		
		try {
			Interior interior = null;
			switch (id) {
			case ALPHA_CAVE_INTERIOR: interior = new AlphaCaveInterior(spawnPoint, outsideLevel); break;
			}
			
			if (interior != null) {
				levelMap.put(id, interior);
			}
			
			return interior;
			
		} catch (IOException e) {
			e.printStackTrace();

			System.exit(1);
		}

		return null;
	}

	public static Level get(int id) {
		return levelMap.get(id);
	}

}
