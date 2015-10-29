package level.interior;

import game.entities.structures.transporters.TransporterInterior;

import java.awt.Point;

import level.Level;

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
