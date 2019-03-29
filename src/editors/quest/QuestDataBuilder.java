package editors.quest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class QuestDataBuilder {

	// key values used to parse json
	public static final String KEY_GIVER = "GiverText", KEY_OBJECTIVE = "ObjectiveText", KEY_RESPONSE1 = "Response1",
			KEY_RESPONSE2 = "Response2", KEY_RESPONSE3 = "Response3", KEY_TRIGGERS1 = "Triggers1", PREV_STATE_ID = "PrevStateID",
			KEY_TRIGGERS2 = "Triggers2", KEY_TRIGGERS3 = "Triggers3", KEY_END = "EndText", QUEST_PARTS="QuestParts",
			KEY_END_TRIGGER = "EndTrigger", NPC_ID = "NPC_ID", INITIAL_QUEST = "InitialQuest", STATE_ID = "StateID";
	
	private JSONObject main;
	private JSONArray questParts;

	public QuestDataBuilder() {
		main = new JSONObject();
		questParts = new JSONArray();
		main.put(QUEST_PARTS, questParts);
	}
	
	public QuestDataBuilder setQuestGiver(int giver) {
		main.put(NPC_ID, giver);
		return this;
	}
	
	public QuestDataBuilder setNPCFirstQuest(boolean initial) {
		main.put(INITIAL_QUEST, initial);
		return this;
	}
	
	public QuestDataBuilder setObjectiveText(String objective) {
		main.put(KEY_OBJECTIVE, objective);
		return this;
	}
	
	public QuestDataBuilder addQuestPart(String giverText, String response1, String response2,
			String response3, String triggers1, String triggers2, String triggers3, String endText,
			String endTriggers, int currentState, int previousState) {
		questParts.add(wrap(giverText, response1, response2, response3, triggers1, triggers2, triggers3,
				endText, endTriggers, currentState, previousState));
		return this;
	}

	/**
	 * Saves quest data into readable JSON file
	 * 
	 * @param giverText     - the text the giver displays initially
	 * @param objectiveText - the text that appears in the inventory
	 * @param response1     - the first possible response
	 * @param response2     - the second possible response
	 * @param response3     - the third possible response
	 */
	private JSONObject wrap(String giverText, String response1, String response2,
			String response3, String triggers1, String triggers2, String triggers3, String endText,
			String endTriggers, int currentState, int previousState) {
		
		JSONObject obj = new JSONObject();

		// now save the data
		obj.put(KEY_GIVER, giverText);
		obj.put(KEY_RESPONSE1, response1);
		obj.put(KEY_RESPONSE2, response2);
		obj.put(KEY_RESPONSE3, response3);
		obj.put(KEY_END, endText);
		obj.put(KEY_TRIGGERS1, triggers1);
		obj.put(KEY_TRIGGERS2, triggers2);
		obj.put(KEY_TRIGGERS3, triggers3);
		obj.put(KEY_END_TRIGGER, endTriggers);
		obj.put(STATE_ID, currentState);
		obj.put(PREV_STATE_ID, previousState);

		return obj;

	}
	
	public JSONObject build() {
		return main;
	}

}
