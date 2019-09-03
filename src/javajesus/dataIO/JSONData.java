package javajesus.dataIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import editors.quest.QuestDataBuilder;

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
			
			// this is temporary code to deal with porting over old quests to the new quests
			JSONArray array = (JSONArray) obj.get(QuestDataBuilder.QUEST_PARTS);
			HashMap<String, JSONObject> map = new HashMap<>();
			
			for (int i = 0; i < array.size(); i++) {
				JSONObject next = (JSONObject) array.get(i);
				String parent = (String) next.get(QuestDataBuilder.PREV_STATE_ID);
				String child = (String) next.get(QuestDataBuilder.STATE_ID);
				
				if (parent == null) {
					int prev = 0;
					if (i > 0) {
						prev = (i - 1) / 3;
					}
					next.put(QuestDataBuilder.PREV_STATE_ID, String.valueOf(prev));
				}
				if (child == null) {
					next.put(QuestDataBuilder.STATE_ID, String.valueOf(i));
				}
				
			}
			
			for (int i = 0; i < array.size(); i++) {
				JSONObject next = (JSONObject) array.get(i);
				
				String dialogue = (String) next.get(QuestDataBuilder.KEY_GIVER);
				if (dialogue == null || dialogue.isEmpty()) {
					array.remove(i--);
					continue;
				}
			}

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
