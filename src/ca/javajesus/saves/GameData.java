package ca.javajesus.saves;

import ca.javajesus.game.Game;
import ca.javajesus.level.LevelList;

public class GameData {

	public static SaveFile levels = new SaveFile("levels");

	public static boolean saveGame() {
		Game.levels.setSaveLevel(Game.player.getLevel());
		if (levels.save(Game.levels)) {
			System.out.println("SAVED");
			return true;
		} else {
			return false;
		}
		
	}

	public static void saveObject(String file, Object o) {
		new SaveFile(file).save(o);
	}

	public static boolean load() {
		Object obj = levels.load();
		if (obj != null) {
			Game.levels = (LevelList) obj;
			System.out.println("LOADED");
			return true;
		}
		return false;
	}

}
