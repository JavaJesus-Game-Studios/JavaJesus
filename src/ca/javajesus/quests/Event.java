package ca.javajesus.quests;

import java.util.ArrayList;

import ca.javajesus.game.entities.Mob;

public abstract class Event {
	
	private String name;
	private String description;
	protected ArrayList<Mob> people;
	protected ArrayList<Script> scripts;
	protected boolean isActive;
	
	public Event(String eventName, String description, ArrayList<Mob> mobs, ArrayList<Script> scripts) {
		name = eventName;
		this.description = description;
		this.people = mobs;
		this.scripts = scripts;
		isActive = false;
		init();
	}
	
	private void init() {
		for (int i = 0; i < people.size(); i++) {
			people.get(i).script = scripts.get(i);
		}
	}
	
	public abstract boolean isCompleted();
	
}
