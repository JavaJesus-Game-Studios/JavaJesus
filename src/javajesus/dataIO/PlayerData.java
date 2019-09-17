package javajesus.dataIO;

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
public class PlayerData {
	
	// IDS for starting weapons and gender
	public static final byte REVOLVER = 0, SHORT_SWORD = 1;
	public static final byte MALE = 0, FEMALE = 1;
	
	// gets the home directory
	private static final String DIR = FileSystemView.getFileSystemView()
			.getDefaultDirectory().getPath()
			+ "/My Games/JavaJesus/File";
	
	// name of individual files
	private static final String NAME = "/Player";
	
	/**
	 * Saves player data to a specified file
	 * 
	 * @param numSlot - which save file clicked (1, 2, or 3)
	 * @param playerName - name of the player
	 * @param skinColor - skin color of the player
	 * @param shirtColor - shirt color of the player
	 * @param startingWeap - starting weapon of the player
	 * @return whether or not it was successfully saved
	 */
	public static boolean save(int numSlot, String playerName, int skinColor, int hairColor, int shirtColor, int pantsColor, byte gender, byte startingWeap) {
		
		// save the data to the file
		try (ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(DIR + numSlot + NAME, false))) {
			
			// 4 ints @ 4 bytes each = 16 + 2 (gender/weapon) + playerName bytes
			byte[] data = ByteBuffer.allocate(18 + playerName.getBytes().length)
					.putInt(skinColor)
					.putInt(hairColor)
					.putInt(shirtColor)
					.putInt(pantsColor)
					.put(gender)
					.put(startingWeap)
					.put(playerName.getBytes())
					.array();
			
			// write the data to a file
			out.write(data);
			return true;
			
			// directory does not exist
		} catch (FileNotFoundException e) {
			
			// create the file
			File dir = new File(DIR + numSlot);

			System.err.println("Creating directory " + dir);

			// if successful then resave
			if (dir.mkdirs()) {
				return save(numSlot, playerName, skinColor, hairColor, shirtColor, pantsColor, gender, startingWeap);
			} else {
				System.err.println("Could not  create file");
				e.printStackTrace();
				return false;
			}
			
			// file could not be saved
		} catch (IOException e) {
			System.err.println("There was a problem saving player save file " + numSlot);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Loads player creation data from a file
	 * 
	 * @param numSlot - save file # (1, 2, or 3)
	 * @return - object data in form:
	 * {skinColor, hairColor, shirtColor, pantsColor, gender, weapon, playerName}
	 * 
	 */
	public static Object[] load(int numSlot) {

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				DIR + numSlot + NAME))) {
			
			// skin color is first 4 bytes
			Integer skinColor = in.readInt();
			
			// hair color is first 4 bytes
			Integer hairColor = in.readInt();
			
			// shirt color is second 4 bytes
			Integer shirtColor = in.readInt();
			
			// pants color is second 4 bytes
			Integer pantsColor = in.readInt();
			
			// next byte is male or female
			byte gender = in.readByte();
			
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
			return new Object[] {skinColor, hairColor, shirtColor, pantsColor, gender, weapon, playerName};
			

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
		File save = new File(DIR + slot + NAME);
		return save.exists();
	}
	
	/**
	 * delete()
	 * Deletes a save file
	 * 
	 * @param slot - slot to delete
	 * @return - whether the delete was successful or not
	 */
	public static boolean delete(int slot) {
		
		// check if it exists
		if (exists(slot)) {
			
			// get instance of the folder
			File save = new File(DIR + slot);
			
			// now delete every file inside
			for (String file: save.list()) {
				new File(save.getPath(), file).delete();
			}
			
			return save.delete();
			
		}
		
		return false;
		
	}


}
