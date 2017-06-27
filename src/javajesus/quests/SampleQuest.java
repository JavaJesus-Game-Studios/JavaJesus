package javajesus.quests;

import javajesus.entities.npcs.NPC;

public class SampleQuest extends Quest {
	
	// This is a sample Quest I will give to Jesus

	private static final long serialVersionUID = 5869993939695932122L;

	public SampleQuest(NPC giver) {
		super(giver, "The Evil Fox");
	}

	public String preDialogue() {
		return "This is a really long test String to test whether or not my code works, let's see what should be typed here, maybe this is long enough, ok this is good.";
	}

	public String dialogue() {
		return "I need you to silence the Fox from spreading his blasphemy.";
	}

	public String postDialogue() {
		return "I can feel God's divine salvation blessing you. Go in Peace.";
	}

	public boolean condition1() {
		// It Was Game.FOX.isDead()
		// This sample quest is impossible to complete
		/*if (Level.getPlayer().isDead()) {
			return true;
		} else {
			return false;
		}*/
		return false;
	}

	public void checkForCompletion() {
		if (condition1()) {
			objectives.set(0, true);
		}
	}

}
