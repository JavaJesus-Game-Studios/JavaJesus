package save;

import level.LevelList;
import game.Game;

/*
 * This class manages the saving of level files
 */
public class GameData {

	/**
	 * Saves the current player's level
	 * @return True if the game is saved successfully
	 */
	public static boolean saveGame() {
		Game.levels.setCurrentLevel(Game.player.getLevel());
		return Game.player.getLevel().saveData.save(Game.levels.playerLevel);
	}

	/**
	 * 
	 * @param file the name of the file
	 * @param o the object to serialize
	 */
	public static void saveObject(String file, Object o) {
		new SaveFile(file).save(o);
	}

	/**
	 * 
	 * @return True if the level was loaded successfully
	 */
	public static boolean load() {
		Object obj = Game.player.getLevel().saveData.load();
		if (obj != null) {
			Game.levels = (LevelList) obj;
			return true;
		}
		return false;
	}

}
