package javajesus.quest.events;

import javajesus.level.Level;

/*
 * An Event consists of a list of specific actions that constitute a situation in the game
 */
public abstract class Event {

	/**
	 * @param level - level where the actions are committed
	 */
	protected Event(Level level) {
		init(level);
	}

	/**
	 * @param level - level where actions are committed
	 */
	protected abstract void init(Level level);

}
