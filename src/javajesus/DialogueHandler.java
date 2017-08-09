package javajesus;

import javajesus.entities.npcs.NPC;

public class DialogueHandler {
	
	// instance of the layout
	public static JavaJesus main;
	
	/**
	 * @param main - the parent of the dialogue gui
	 */
	public DialogueHandler(JavaJesus main) {
		
		// instance data
		DialogueHandler.main = main;
	}

	/**
	 * @param character - the character the player is speaking to
	 */
	public static void startDialogue(NPC character) {
		main.displayDialogue(character);
	}
	
	/**
	 * Returns to the main game screen
	 */
	public static void endDialogue() {
		main.displayGame();
	}

}
