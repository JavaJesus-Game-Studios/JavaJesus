package level.interior;

import game.entities.structures.transporters.TransporterInterior;

import java.awt.Point;

import level.Level;

public class ShantyHouseInterior extends Interior {

	private Point exitPoint;

	public ShantyHouseInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Shanty_House_Interior.png", new Point(256,
				272), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 256, 278, nextLevel,
				exitPoint));
	}

}
