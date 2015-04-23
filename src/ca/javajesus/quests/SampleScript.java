package ca.javajesus.quests;

import java.awt.Point;

import ca.javajesus.game.entities.Mob;

public class SampleScript extends Script {

	private static final long serialVersionUID = 1007488080963858189L;

	public SampleScript(Mob mob) {
		super(mob, new Point(1000,1000), 1);
	}
	
	public void tick() {
		this.findPoint();
		if (currentStep == 1) {
			mob.script = null;
			return;
		}
	}
}
