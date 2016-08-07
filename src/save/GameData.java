package save;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * This class manages the saving of files
 */
public class GameData {


	/**
	 * @param object
	 *            The object to serialize
	 * @return True if the object is saved successfully
	 */
	public static boolean save(String name, Object object) {

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name, false))) {

			out.writeObject(object);
			return true;

		} catch (IOException e) {
			System.err.println("There was a problem saving " + name);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @return the object in memory if loaded successfully 
	 * null if no object was found
	 */
	public static Object load(String name) {

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(name))) {
			return in.readObject();

		} catch (IOException | ClassNotFoundException e) {
			System.err.println("There was a problem loading" + name);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @return Saves the main player
	 */
	public static boolean savePlayer() {
		//return save("Player", Game.player); TODO
		return false;
	}

}
