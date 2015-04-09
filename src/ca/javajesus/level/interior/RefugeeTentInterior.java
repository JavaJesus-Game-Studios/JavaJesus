package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.level.Level;

public class RefugeeTentInterior extends Interior {

	private Point exitPoint;

	public RefugeeTentInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Refugee_Tent_Interior.png", new Point(288,
				264), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 288, 264, nextLevel,
				exitPoint));
	}

}
