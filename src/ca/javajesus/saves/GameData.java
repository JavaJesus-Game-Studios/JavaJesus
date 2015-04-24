package ca.javajesus.saves;

import ca.javajesus.game.Game;
import ca.javajesus.level.LevelList;

public class GameData {

	public static SaveFile levels;

	public static void saveGame() {
		Game.levels.setSaveLevel(Game.player.getLevel());
		levels.save();
		System.out.println("SAVED");
		System.out.println(levels.load());
	}

	public static void saveObject(SaveFile f) {
		f.save();
	}

	public static boolean load() {
		if (levels != null && levels.load() != null) {
			Game.levels = (LevelList) levels.load();
			System.out.println(Game.levels.playerLevel);
			System.out.println("LOADED");
			return true;
		}
		return false;
	}
	
	public static void initFile() {
		levels = new SaveFile("levels", Game.levels);
	}

}
