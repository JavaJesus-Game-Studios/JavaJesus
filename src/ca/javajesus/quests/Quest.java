package ca.javajesus.quests;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import ca.javajesus.game.entities.Player;
import ca.javajesus.game.entities.npcs.NPC;

public abstract class Quest implements Serializable {
	
	private static final long serialVersionUID = 8675321530565601615L;
	
	protected NPC giver;
	protected ArrayList<Boolean> objectives = new ArrayList<Boolean>();
	protected boolean finished = false;
	protected Player player;
	protected String title;
	protected int phase = 0;
	public Point walkLocation;
	
	public Quest(NPC giver, String title) {
		this.giver = giver;
		this.title = title;
		objectives.add(false);
	}
	
	public Quest(NPC giver, String title, Point point) {
		this.giver = giver;
		this.title = title;
		objectives.add(false);
		this.walkLocation = point;
	}
	
	public int getPhase() {
		return phase;
	}
	
	public void nextPhase() {
		phase++;
	}
	
	public String toString() {
		return title +" by " + giver;
	}
	
	public abstract String preDialogue(); 
	
	public abstract String dialogue();
	
	public abstract String postDialogue();
	
	public abstract boolean condition1();
	
	public void update() {
		checkForCompletion();
		for (Boolean e: objectives) {
			if (!e) {
				return;
			}
		}
		phase = 2;
	}
	
	public abstract void checkForCompletion();
	
	public void addPlayer(Player player) {
		this.player = player;
	}
	

}
