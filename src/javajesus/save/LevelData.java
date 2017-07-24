package javajesus.save;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Loads and Saves Level data
 */
public class LevelData {
	
	/**
	 * Loads a level file from path into
	 * the tile array tiles
	 * @param File - the file to load
	 * @param tiles - the tile list to be filled
	 * @throws IOException
	 */
	public static final void load(File file, int[] tiles) throws IOException {
		
		// open the output stream
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
		
		// loads all the data into a byte stream
		byte data[] = new byte[tiles.length];
		is.read(data);

		// put the data into level Tiles
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = data[i] & 0x000000FF;
		}

		// close the stream
		is.close();
		
	}
	
	/**
	 * Saves the tile data from tiles into
	 * a file in the specified path
	 * 
	 * @param path - the path to save to
	 * @param tiles - the tile data to save
	 */
	public static final void save(String path, int[] tiles) throws IOException {
		
		// open the tile output stream
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File(path), false));
		
		// fill the data with tile ids
		for (int i = 0; i < tiles.length; i++) {
			
			// save the tile data
			os.write((byte) tiles[i]);
		}
		
		// free resources
		os.close();

	}

}
