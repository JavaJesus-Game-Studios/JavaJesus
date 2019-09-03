package javajesus.utility.movement;

import java.awt.Point;

import javajesus.entities.Mob;
import javajesus.entities.Player;

/*
 * A Script is a specific action on a specific mob
 * Scripts have the highest priority in determining a mob's direction
 */
public class Script {

	// the mob that has the script
	protected Mob mob;

	// the destination of where to send the mob
	// scaled from Tile to Entity coords automatically
	protected Point destination;
	
	// whether or not to move there instantaneously
	private boolean moveNow;

	/**
	 * @param mob - The targeted mob
	 * @param point - in tile coordinates
	 */
	public Script(Mob mob, Point tileCoord) {
		this.mob = mob;
		tileCoord.x = (int) ((tileCoord.x << 3) + 4 - (mob.getBounds().getWidth() / 2));
		tileCoord.y = (int) ((tileCoord.y << 3) + 8 - mob.getBounds().getHeight());
		destination = tileCoord;
	}
	
	public Script(Player mob, Point entityCoord) {
		this.mob = mob;
		destination = entityCoord;
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
		return destination.x == mob.getX() && destination.y == mob.getY();
	}

}
