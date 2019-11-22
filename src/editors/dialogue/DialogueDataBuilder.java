package editors.dialogue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class DialogueDataBuilder {

	// key values used to parse json
	public static final String KEY_ACTOR = "ActorText", KEY_RESPONSE1 = "Response1", KEY_RESPONSE2 = "Response2",
			KEY_RESPONSE3 = "Response3", KEY_TRIGGERS1 = "Triggers1", KEY_TRIGGERS2 = "Triggers2", KEY_TRIGGERS3 = "Triggers3", 
			KEY_END = "EndText", KEY_END_TRIGGER = "EndTrigger", DIALOGUE_SCENE="DialogueScene", ACTOR_ID = "ActorID", QUEST_ID ="QuestID",
			PREV_NODE_ID = "PrevNodeID", NODE_ID = "NodeID", NEXT_NODE_ID = "OutgoingNodeID";
	
	private JSONObject dialogueScene;
	private JSONArray dialogueArray;

	public DialogueDataBuilder() {
		dialogueScene = new JSONObject();
		dialogueArray = new JSONArray();
	}
	
	public DialogueDataBuilder setActor(int actorID) {
		dialogueScene.put(ACTOR_ID, actorID);
		return this;
	}
	
	public DialogueDataBuilder setQuestID(int questId) {
		dialogueScene.put(QUEST_ID, questId);
		return this;
	}
	
	
	public DialogueDataBuilder setDialogueArray(JSONArray arr) {
		this.dialogueArray = arr;
		return this;
	}
	
	public DialogueDataBuilder removeNodeFromDialogue(String id) {
		for (int i = 0; i < dialogueArray.size(); i++) {
			JSONObject obj = (JSONObject) dialogueArray.get(i);
			if (obj.get(NODE_ID).equals(id)) {
				dialogueArray.remove(i);
				break;
			}
		}
		return this;
	}
	
	public DialogueDataBuilder updateNodeInDialogue(String giverText, String response1, String response2,
			String response3, String triggers1, String triggers2, String triggers3, String endText,
			String endTriggers, String currentNOde, String previousNode, String nextNode) {
		for (int i = 0; i < dialogueArray.size(); i++) {
			JSONObject obj = (JSONObject) dialogueArray.get(i);
			if (obj.get(NODE_ID).equals(currentNOde)) {
				dialogueArray.set(i, wrap(giverText, response1, response2, response3, triggers1, triggers2, triggers3,
						endText, endTriggers, currentNOde, previousNode, nextNode));
				break;
			}
		}
		return this;
	}
	
	public DialogueDataBuilder addNodeToDialogue(String giverText, String response1, String response2,
			String response3, String triggers1, String triggers2, String triggers3, String endText,
			String endTriggers, String currentNode, String previousNode, String nextNode) {
		dialogueArray.add(wrap(giverText, response1, response2, response3, triggers1, triggers2, triggers3,
				endText, endTriggers, currentNode, previousNode, nextNode));
		return this;
	}

	/**
	 * Saves quest data into readable JSON file
	 * 
	 * @param giverText     - the text the giver displays initially
	 * @param response1     - the first possible response
	 * @param response2     - the second possible response
	 * @param response3     - the third possible response
	 */
	private JSONObject wrap(String giverText, String response1, String response2,
			String response3, String triggers1, String triggers2, String triggers3, String endText,
			String endTriggers, String currentNode, String previousNode, String nextNode) {
		
		JSONObject obj = new JSONObject();

		// now save the data
		obj.put(KEY_ACTOR, giverText);
		obj.put(KEY_RESPONSE1, response1);
		obj.put(KEY_RESPONSE2, response2);
		obj.put(KEY_RESPONSE3, response3);
		obj.put(KEY_END, endText);
		obj.put(KEY_TRIGGERS1, triggers1);
		obj.put(KEY_TRIGGERS2, triggers2);
		obj.put(KEY_TRIGGERS3, triggers3);
		obj.put(KEY_END_TRIGGER, endTriggers);
		obj.put(NODE_ID, currentNode);
		obj.put(PREV_NODE_ID, previousNode);
		obj.put(NEXT_NODE_ID, nextNode);

		return obj;

	}
	
	public JSONObject build() {
		dialogueScene.put(DIALOGUE_SCENE, dialogueArray);
		return dialogueScene;
	}

}
