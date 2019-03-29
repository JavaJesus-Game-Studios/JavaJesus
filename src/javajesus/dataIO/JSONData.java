package javajesus.dataIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONData {
	
	/**
	 * Saves the JSON array to a .json file
	 * 
	 * @param path  - the path to the file
	 * @param array - the array to save
	 */
	public static final void save(String path, JSONObject array) {

		// open the output stream
		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))))) {

			// now write the JSON Array
			out.write(array.toJSONString());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Loads a JSON array into memory
	 * 
	 * @param data - the stream of data from the file
	 * @return the JSON array
	 */
	public static final JSONObject load(InputStream data) {

		// open the reader
		try (BufferedReader in = new BufferedReader(new InputStreamReader(data))) {

			// create the parser
			JSONParser parser = new JSONParser();

			// now parse the data
			JSONObject array = (JSONObject) parser.parse(in);

			// return the array
			return array;

			// file could not be loaded into memory
		} catch (IOException e) {
			e.printStackTrace();

			// code could not be parsed
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// there was a problem
		return null;

	}

}
