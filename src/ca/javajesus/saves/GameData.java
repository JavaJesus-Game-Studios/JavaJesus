package ca.javajesus.saves;

import ca.javajesus.game.Game;
import ca.javajesus.game.entities.Player;
import ca.javajesus.level.Level;

public class GameData {

	public static SaveFile level1 = new SaveFile("level1", Level.level1);
	public static SaveFile player = new SaveFile("player", Game.player);

	public static void saveGame() {
		level1.save();
		//player.save();
		System.out.println("SAVED");
	}

	public static void saveObject(SaveFile f) {
		f.save();
	}

	public static void load() {
		if (level1.load() != null) {
			Level.level1 = (Level) level1.load();
			System.out.println("LOADED");
		}
		/*if (player.load() != null) {
			Game.player = (Player) player.load();
			System.out.println("LOADED");
		}*/
	}

}
