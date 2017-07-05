package javajesus.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import javax.swing.filechooser.FileSystemView;

import javajesus.items.Item;

/*
 * This class manages the saving of player creation data
 */
public class SaveFile {
	
	// IDS for starting weapons
	public static final byte REVOLVER = 0, SHORT_SWORD = 1;
	
	// gets the home directory
	private static final String DIR = FileSystemView.getFileSystemView()
			.getDefaultDirectory().getPath()
			+ "/My Games/JavaJesus/PlayerData";
	
	// name of individual files
	private static final String NAME = "/Player";
	
	/**
	 * SaveFile ctor()
	 * Saves player creation data to a file to be loaded
	 * 
	 * @param numSlot - which save file clicked (1, 2, or 3)
	 * @param playerName - name of the player
	 * @param skinColor - skin color of the player
	 * @param shirtColor - shirt color of the player
	 * @param startingWeap - starting weapon of the player
	 */
	public SaveFile(int numSlot, String playerName, int skinColor, int shirtColor, byte startingWeap) {
		
		save(numSlot, playerName, skinColor, shirtColor, startingWeap);
		
	}
	
	/**
	 * save()
	 * Saves player creation data to a file
	 * 
	 * @param numSlot - which save file clicked (1, 2, or 3)
	 * @param playerName - name of the player
	 * @param skinColor - skin color of the player
	 * @param shirtColor - shirt color of the player
	 * @param startingWeap - starting weapon of the player
	 * @return whether or not it was successfully saved
	 */
	private boolean save(int numSlot, String playerName, int skinColor, int shirtColor, byte startingWeap) {
		
		// save the data to the file
		try (ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(DIR + NAME + numSlot, false))) {
			
			// 2 ints @ 4 bytes each = 8 + 1 (weapon) + playerName bytes
			byte[] data = ByteBuffer.allocate(9 + playerName.getBytes().length)
					.putInt(skinColor)
					.putInt(shirtColor)
					.put(startingWeap)
					.put(playerName.getBytes())
					.array();
			
			// write the data to a file
			out.write(data);
			return true;
			
			// directory does not exist
		} catch (FileNotFoundException e) {
			
			// create the file
			File dir = new File(DIR);

			System.err.println("Creating directory " + dir);

			// if successful then resave
			if (dir.mkdirs()) {
				return save(numSlot, playerName, skinColor, shirtColor, startingWeap);
			} else {
				System.err.println("Could not  create file");
				e.printStackTrace();
				return false;
			}
			
			// file could not be saved
		} catch (IOException e) {
			System.err.println("There was a problem saving save file" + numSlot);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * load()
	 * Loads player creation data from a file
	 * 
	 * @param numSlot - save file # (1, 2, or 3)
	 * @return - object data in form:
	 * {skinColor, shirtColor, weapon, playerName}
	 * 
	 */
	public static Object[] load(int numSlot) {

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				DIR + NAME + numSlot))) {
			
			// skin color is first 4 bytes
			int skinColor = in.readInt();
			
			// shirt color is second 4 bytes
			int shirtColor = in.readInt();
			
			// next byte is starting weapon
			byte start = in.readByte();
			
			// get the right weapon
			Item weapon = null;
			if (start == REVOLVER) {
				weapon = Item.revolver;
			} else {
				weapon = Item.shortSword;
			}
			
			// last bytes are the player name
			String playerName = "";
			
			// read remaining bytes into chars
			while (in.available() > 0) {
				playerName += (char) in.readByte();
			}
			
			// return the objects
			return new Object[] {new Integer(skinColor), new Integer(shirtColor), weapon, playerName};
			

		} catch (IOException e) {
			System.err.println("There was a problem loading save file" + numSlot);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * exists()
	 * Determines if the file exists
	 * 
	 * @param slot - slot # (1, 2, or 3)
	 * @return - whether or not it exists
	 */
	public static boolean exists(int slot) {
		File save = new File(DIR + NAME + slot);
		return save.exists();
	}


}
