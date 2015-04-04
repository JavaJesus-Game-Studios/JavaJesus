package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.structures.furniture.DiningTable;
import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.level.Level;

public class CastleTowerInterior extends Interior {

	private Point exitPoint;

	public CastleTowerInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Castle_Tower_Interiors/Castle_Tower_Interior.png",
				new Point(1935, 2088), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

		this.addEntity(new DiningTable(this, 1900, 1900));

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 1935, 2088, nextLevel,
				exitPoint));
	}

}
