package editors.quest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class QuestDataBuilder {

	// key values used to parse json
	public static final String KEY_GIVER = "GiverText", KEY_OBJECTIVE = "ObjectiveText", KEY_RESPONSE1 = "Response1",
			KEY_RESPONSE2 = "Response2", KEY_RESPONSE3 = "Response3", KEY_TRIGGERS1 = "Triggers1", PREV_STATE_ID = "PrevStateID",
			KEY_TRIGGERS2 = "Triggers2", KEY_TRIGGERS3 = "Triggers3", KEY_END = "EndText", QUEST_PARTS="QuestParts",
			KEY_END_TRIGGER = "EndTrigger", NPC_ID = "NPC_ID", INITIAL_QUEST = "InitialQuest", STATE_ID = "StateID",
			FUT_STATE_ID = "OutgoingStateID", LEVEL_ID ="LevelID";
	
	private JSONObject main;
	private JSONArray questParts;

	public QuestDataBuilder() {
		main = new JSONObject();
		questParts = new JSONArray();
	}
	
	public QuestDataBuilder setQuestGiver(int giver) {
		main.put(NPC_ID, giver);
		return this;
	}
	
	public QuestDataBuilder setLevelId(int id) {
		main.put(LEVEL_ID, id);
		return this;
	}
	
	public QuestDataBuilder setNPCFirstQuest(boolean initial) {
		main.put(INITIAL_QUEST, initial);
		return this;
	}
	
	public QuestDataBuilder setQuestPartArray(JSONArray arr) {
		this.questParts = arr;
		return this;
	}
	
	public QuestDataBuilder removeQuestPart(String id) {
		for (int i = 0; i < questParts.size(); i++) {
			JSONObject obj = (JSONObject) questParts.get(i);
			if (obj.get(STATE_ID).equals(id)) {
				questParts.remove(i);
				break;
			}
		}
		return this;
	}
	
	public QuestDataBuilder modifyQuestPart(String giverText, String response1, String response2,
			String response3, String triggers1, String triggers2, String triggers3, String endText,
			String endTriggers, String currentState, String previousState, String objSummary, String futState) {
		for (int i = 0; i < questParts.size(); i++) {
			JSONObject obj = (JSONObject) questParts.get(i);
			if (obj.get(STATE_ID).equals(currentState)) {
				questParts.set(i, wrap(giverText, response1, response2, response3, triggers1, triggers2, triggers3,
						endText, endTriggers, currentState, previousState, objSummary, futState));
				break;
			}
		}
		return this;
	}
	
	public QuestDataBuilder addQuestPart(String giverText, String response1, String response2,
			String response3, String triggers1, String triggers2, String triggers3, String endText,
			String endTriggers, String currentState, String previousState, String objSummary, String futureState) {
		questParts.add(wrap(giverText, response1, response2, response3, triggers1, triggers2, triggers3,
				endText, endTriggers, currentState, previousState, objSummary, futureState));
		return this;
	}

	/**
	 * Saves quest data into readable JSON file
	 * 
	 * @param giverText     - the text the giver displays initially
	 * @param response1     - the first possible response
	 * @param response2     - the second possible response
	 * @param response3     - the third possible response
	 * @param objectiveText - the text that appears in the inventory
	 */
	private JSONObject wrap(String giverText, String response1, String response2,
			String response3, String triggers1, String triggers2, String triggers3, String endText,
			String endTriggers, String currentState, String previousState, String objSummary, String futureState) {
		
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
		obj.put(KEY_OBJECTIVE, objSummary);
		obj.put(FUT_STATE_ID, futureState);

		return obj;

	}
	
	public JSONObject build() {
		main.put(QUEST_PARTS, questParts);
		return main;
	}

}
