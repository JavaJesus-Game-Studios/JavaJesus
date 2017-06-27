package javajesus.quests;

import javajesus.entities.Player;
import javajesus.entities.npcs.NPC;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * A Quest is given by a NPC that requires a set of specific dialogue and action events completed
 */
public abstract class Quest implements Serializable {

	private static final long serialVersionUID = 8675321530565601615L;

	protected NPC giver;

	// The amount of unique criteria that must be accomplished before being
	// completed
	protected ArrayList<Boolean> objectives = new ArrayList<Boolean>();

	protected boolean finished;
	protected Player player;
	private String title;
	public Point walkLocation;
	private static final int JUST_STARTED = 0, DOING_OBJECTIVES = 1,
			COMPLETED = 2;
	protected int phase = JUST_STARTED;

	/**
	 * @param giver
	 *            The NPC who gives the quest
	 * @param title
	 *            The name of the quest There is one default objective that is
	 *            set to false
	 */
	public Quest(NPC giver, String title) {
		this.giver = giver;
		this.title = title;
		objectives.add(false);
	}

	/**
	 * @param giver
	 *            The NPC who gives the quest
	 * @param title
	 *            The name of the quest
	 * @param point
	 *            The location to which a player must walk to trigger the quest
	 *            There is one default objective that is set to false
	 */
	public Quest(NPC giver, String title, Point point) {
		this.giver = giver;
		this.title = title;
		objectives.add(false);
		this.walkLocation = point;
	}

	/**
	 * @return The current phase of the quest
	 */
	public int getPhase() {
		return phase;
	}

	/**
	 * Signals the next phase of the quest, DOING_OBJECTIVES
	 */
	public void nextPhase() {
		phase = DOING_OBJECTIVES;
	}

	/**
	 * Default name of the quest
	 */
	public String toString() {
		return title + " by " + giver;
	}

	/**
	 * @return The initial pre dialogue of the quest
	 */
	public abstract String preDialogue();

	/**
	 * @return The dialoague during the quest
	 */
	public abstract String dialogue();

	/**
	 * @return The post dialogue after the quest is completed
	 */
	public abstract String postDialogue();

	/**
	 * @return True if the first condition has been satisfied
	 */
	public abstract boolean condition1();

	/**
	 * Checks if each objective has been satisfied and moves to the next
	 * available objective
	 */
	public void update() {
		checkForCompletion();
		for (Boolean e : objectives) {
			if (!e) {
				return;
			}
		}
		phase = COMPLETED;
	}

	/**
	 * Checks for the conditions and properly updates the objectives
	 */
	public abstract void checkForCompletion();

	/**
	 * @param player
	 *            Sets the target player of the quest
	 */
	public void addPlayer(Player player) {
		this.player = player;
	}

}
