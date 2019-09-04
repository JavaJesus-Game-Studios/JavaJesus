package javajesus.dataIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JSONData {
	
	/**
	 * Saves the JSON array to a .json file
	 * 
	 * @param path  - the path to the file
	 * @param array - the array to save
	 */
	public static final void save(String path, JSONObject obj) {

		// open the output stream
		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))))) {
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(obj.toJSONString());
			String prettyJsonString = gson.toJson(je);

			// now write the JSON Array
			out.write(prettyJsonString);

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
			JSONObject obj = (JSONObject) parser.parse(in);

			// return the array
			return obj;

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
