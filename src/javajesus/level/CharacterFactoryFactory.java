package javajesus.level;

import java.util.HashMap;

import javajesus.quest.factories.AlphaCharacterFactory;
import javajesus.quest.factories.CharacterFactory;

public class CharacterFactoryFactory {
	private static HashMap<String, CharacterFactory> factoryMap = new HashMap<>();

	public static CharacterFactory make(String levelName) {

		if (factoryMap.get(levelName) != null) {
			return factoryMap.get(levelName);
		}

		CharacterFactory cf = null;
		switch (levelName) {
		case LevelFactory.BAUTISTA:
		case LevelFactory.EDGE_MAIN:
		case LevelFactory.EDGE_TOP:
		case LevelFactory.HILLSBOROUGH:
		case LevelFactory.ORCHARD:
		case LevelFactory.CISCO:
		case LevelFactory.JUAN:
		case LevelFactory.TECH:
		case LevelFactory.ALPHA:
			cf = new AlphaCharacterFactory();
			break;
		case LevelFactory.ISLAND:
		case LevelFactory.TILE_TESTER:
		case LevelFactory.ROAD_TESTER:
		}

		factoryMap.put(levelName, cf);
		return cf;

	}
}
