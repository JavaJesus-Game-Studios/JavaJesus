package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.level.Level;

public class RussianChurchInterior extends Interior {

	private Point exitPoint;

	public RussianChurchInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Russian_Church_Interior.png", new Point(256,
				304), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 256, 304, nextLevel,
				exitPoint));
	}

}
