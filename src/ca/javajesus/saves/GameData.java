package ca.javajesus.saves;

import ca.javajesus.game.Game;
import ca.javajesus.level.LevelList;

public class GameData {

	public static boolean saveGame() {
		Game.levels.setCurrentLevel(Game.player.getLevel());
		if (Game.player.getLevel().saveData.save(Game.levels.playerLevel)) {
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
		Object obj = Game.player.getLevel().saveData.load();
		if (obj != null) {
			Game.levels = (LevelList) obj;
			System.out.println("LOADED");
			return true;
		}
		return false;
	}

}
