package javajesus.level;

import java.util.HashMap;

import javajesus.quest.factories.AlphaCaveCharacterFactory;
import javajesus.quest.factories.AlphaCharacterFactory;
import javajesus.quest.factories.CharacterFactory;

public class CharacterFactoryFactory {
	private static HashMap<Integer, CharacterFactory> factoryMap = new HashMap<>();

	public static CharacterFactory make(int levelId) {

		if (factoryMap.get(levelId) != null) {
			return factoryMap.get(levelId);
		}

		CharacterFactory cf = null;
		switch (levelId) {
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
		case LevelFactory.ALPHA_CAVE_INTERIOR:
			cf = new AlphaCaveCharacterFactory();
			break;
		case LevelFactory.ISLAND:
		case LevelFactory.TILE_TESTER:
		case LevelFactory.ROAD_TESTER:
		}

		factoryMap.put(levelId, cf);
		return cf;

	}
}
