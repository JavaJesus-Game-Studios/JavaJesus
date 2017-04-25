package engine.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
	
	/**
	 * Creates a byte string of a specified file
	 * 
	 * @param file the file path
	 * @return the file data as a string
	 */
	public static String loadAsString(String file) {
		
		// builder is buffered
		StringBuilder result = new StringBuilder();
		
		try {
			// create a reader for reading the file
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String buffer = "";
			
			// read in every line in the  file
			while ((buffer = reader.readLine()) != null) {
				result.append(buffer + "\n");
			}
			
			// close the reader once finished
			reader.close();
			
			// report unsuccessful if file was not found
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// return the result
		return result.toString();
	}

}
