package ca.javajesus.quests;

import java.io.Serializable;
import java.util.ArrayList;

import ca.javajesus.game.entities.Mob;

@SuppressWarnings("unused")
public abstract class Event implements Serializable {
	
	private static final long serialVersionUID = 776382962177295971L;
	
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
