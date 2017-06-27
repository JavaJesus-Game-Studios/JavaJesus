package javajesus.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.filechooser.FileSystemView;

import javajesus.entities.Player;
import javajesus.level.Level;
import javajesus.level.story.BautistasDomain;
import javajesus.level.story.EdgeOfTheWoods;
import javajesus.level.story.EdgeOfTheWoodsTop;
import javajesus.level.story.LordHillsboroughsDomain;
import javajesus.level.story.OrchardValley;
import javajesus.level.story.SanCisco;
import javajesus.level.story.SanJuan;
import javajesus.level.story.TechTopia;

/*
 * This class manages the saving of files
 */
public class GameData {

	// player that will be saved
	private static Player player;

	// gets the home directory
	private static final String DIR = FileSystemView.getFileSystemView()
			.getDefaultDirectory().getPath()
			+ "/My Games/JavaJesus/";
	
	// player data
	private static String[] playerData = new String[2];
	
	// constants for saving and loading
	public static final int NAME = 0, LEVEL = 1;

	/**
	 * @param object
	 *            The object to serialize
	 * @return True if the object is saved successfully
	 */
	public static boolean save(String name, Object object) {

		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(DIR + name, false))) {

			out.writeObject(object);
			return true;

			// create the directory if it doesnt exist
		} catch (FileNotFoundException e) {

			File dir = new File(DIR);

			System.err.println("Creating directory " + dir);

			// if succesful, then resave
			if (dir.mkdirs()) {
				return save(name, object);
			} else {
				System.err.println("Could not  create file");
				return false;
			}

		} catch (IOException e) {
			System.err.println("There was a problem saving " + name);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @return the object in memory if loaded successfully null if no object was
	 *         found
	 */
	public static Object load(String name) {

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				DIR + name))) {
			return in.readObject();

		} catch (IOException | ClassNotFoundException e) {
			System.err.println("There was a problem loading" + name);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return Saves the player's level's name
	 */
	public static boolean savePlayerLevelData() {
		playerData[NAME] = player.getName();
		playerData[LEVEL] = player.getLevel().getName();
		return save("Player", playerData);
	}

	public static void setPlayer(Player p) {
		player = p;
	}

	/**
	 * Save all the story mode levels to file
	 */
	public static void saveLevels() {
		save("BautistasDomain", BautistasDomain.level);
		save("EdgeOfTheWoods", EdgeOfTheWoods.level);
		save("EdgeOfTheWoodsTop", EdgeOfTheWoodsTop.level);
		save("LordHillsboroughsDomain", LordHillsboroughsDomain.level);
		save("OrchardValley", OrchardValley.level);
		save("SanCisco", SanCisco.level);
		save("SanJuan", SanJuan.level);
		save("TechTopia", TechTopia.level);
	}

	public static void loadLevels() {
		BautistasDomain.level = (Level) load("BautistasDomain");
		EdgeOfTheWoods.level = (Level) load("EdgeOfTheWoods");
		EdgeOfTheWoodsTop.level = (Level) load("EdgeOfTheWoodsTop");
		LordHillsboroughsDomain.level = (Level) load("LordHillsboroughsDomain");
		OrchardValley.level = (Level) load("OrchardValley");
		SanCisco.level = (Level) load("SanCisco");
		SanJuan.level = (Level) load("SanJuan");
		TechTopia.level = (Level) load("TechTopia");

	}

}
