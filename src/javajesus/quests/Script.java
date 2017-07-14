package javajesus.quests;

import java.awt.Point;
import java.io.Serializable;

import javajesus.entities.Mob;

/*
 * A Script is a specific action on a specific mob
 * Scripts have the highest priority in determining a mob's direction
 */
public class Script implements Serializable {

	private static final long serialVersionUID = -7841027938100196923L;

	// the mob that has the script
	private Mob mob;

	// the destination of where to send the mob
	private Point destination;
	
	// whether or not to move there instantaneously
	private boolean moveNow;

	/**
	 * @param mob - The targeted mob
	 * @param first - The first location to which the mob will travel
	 */
	public Script(Mob mob, Point first) {
		this.mob = mob;
		destination = first;
	}
	
	/**
	 * @param mob - The targeted mob
	 * @param dx - the change in x
	 * @param dy - the change in y
	 */
	public Script(Mob mob, int dx, int dy, boolean instant) {
		this.mob = mob;
		destination = new Point(mob.getX() + dx, mob.getY() + dy);
		moveNow = instant;
	}

	/**
	 * @return The destination of the script
	 */
	public Point getDestination() {
		return destination;
	}
	
	/**
	 * @return whether or not to move there right away
	 */
	public boolean isInstantaneous() {
		return moveNow;
	}

	/**
	 * @return True if the script has been completed
	 */
	public boolean isCompleted() {
		return destination.getX() == mob.getX() && destination.getY() == mob.getY();
	}

}
