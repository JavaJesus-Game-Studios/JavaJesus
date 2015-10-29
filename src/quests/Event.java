package quests;

import game.entities.Mob;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * An Event consists of a list of mobs and specific actions that constitute a situation in the game
 */
public abstract class Event implements Serializable {

	private static final long serialVersionUID = 776382962177295971L;

	private String name;
	private String description;
	private ArrayList<Mob> people;
	private ArrayList<Script> scripts;
	private boolean isActive;
	private boolean isCompleted;

	/**
	 * @param eventName
	 *            Name of the event
	 * @param description
	 *            Description of the event
	 * @param mobs
	 *            The people involved in the event
	 * @param scripts
	 *            The list of actions for the people in the event
	 */
	public Event(String eventName, String description, ArrayList<Mob> mobs,
			ArrayList<Script> scripts) {
		name = eventName;
		this.description = description;
		this.people = mobs;
		this.scripts = scripts;
		isActive = false;
		init();
	}

	/**
	 * Binds the scripts to the people
	 */
	private void init() {
		for (int i = 0; i < people.size(); i++) {
			people.get(i).script = scripts.get(i);
		}
	}

	/**
	 * @return True if the event has been completed
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**
	 * @return The name of the event
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The description of the event
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return The list of people involved in this event
	 */
	public ArrayList<Mob> getPeople() {
		return people;
	}

	/**
	 * @return True if the event is currently active
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Completes this event
	 */
	protected void finish() {
		this.isActive = false;
		this.isCompleted = true;
	}

}
