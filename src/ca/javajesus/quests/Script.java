package ca.javajesus.quests;

import java.awt.Point;
import java.io.Serializable;

import ca.javajesus.game.entities.Mob;

public abstract class Script implements Serializable {
	
	private static final long serialVersionUID = -7841027938100196923L;
	
	protected int steps;
	protected Mob mob;
	protected int maxSteps;
	protected int currentStep = 0;
	protected Point destination;
	
	public Script(Mob mob, Point first, int maxSteps) {
		this.mob = mob;
		this.maxSteps = maxSteps;
		destination = first;
	}
	
	public abstract void tick();
	
	protected void findPoint() {

		if (destination.x == mob.getX() && destination.y == mob.getY()) {
			currentStep++;
			return;
		}
		int xa = 0;
		int ya = 0;
		if (destination.x > mob.getX()) {
			xa++;
		}
		if (destination.x < mob.getX()) {
			xa--;
		}
		if (destination.y > mob.getY()) {
			ya++;
		}
		if (destination.y < mob.getY()) {
			ya--;
		}
		if ((xa != 0 || ya != 0) && !mob.isSolidEntityCollision(xa, ya)
				&& !mob.isMobCollision(xa, ya)) {
			mob.setMoving(true);
			mob.move(xa, ya);
		} else {
			mob.setMoving(false);
		}
	}

}
