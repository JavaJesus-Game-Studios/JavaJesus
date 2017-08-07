package javajesus.dataIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class QuestData {

	// key values used to parse json
	public static final String KEY_GIVER = "GiverText", KEY_OBJECTIVE = "ObjectiveText", KEY_RESPONSE1 = "Response1",
	        KEY_RESPONSE2 = "Response2", KEY_RESPONSE3 = "Response3";

	/**
	 * Saves quest data into readable JSON file
	 * 
	 * @param giverText - the text the giver displays initially
	 * @param objectiveText - the text that appears in the inventory
	 * @param response1 - the first possible response
	 * @param response2 - the second possible response
	 * @param response3 - the third possible response
	 */
	@SuppressWarnings("unchecked")
	public static final JSONObject wrap(String giverText, String objectiveText, String response1, String response2,
	        String response3) {

		// create the JSON object
		JSONObject obj = new JSONObject();

		// now save the data
		obj.put(KEY_GIVER, giverText);
		obj.put(KEY_OBJECTIVE, objectiveText);
		obj.put(KEY_RESPONSE1, response1);
		obj.put(KEY_RESPONSE2, response2);
		obj.put(KEY_RESPONSE3, response3);

		return obj;

	}

	/**
	 * Saves the JSON array to a .json file
	 * 
	 * @param path - the path to the file
	 * @param array - the array to save
	 */
	public static final void save(String path, JSONArray array) {

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
	 * @param file - file to load into memory
	 * @return the JSON array
	 */
	public static final JSONArray load(File file) {

		// open the reader
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

			// create the parser
			JSONParser parser = new JSONParser();
			
			// now parse the data
			JSONArray array = (JSONArray) parser.parse(in);

			// return the array
			return array;

			// file was not found
		} catch (FileNotFoundException e) {
			System.err.println(file + " not found");
			e.printStackTrace();

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
