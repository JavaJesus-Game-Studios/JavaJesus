package level.interior;

import game.entities.structures.transporters.TransporterInterior;

import java.awt.Point;

import level.Level;

public class CatholicChurchInterior extends Interior {

	private Point exitPoint;

	public CatholicChurchInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Catholic_Church_Interior.png", new Point(256,
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
		this.addEntity(new TransporterInterior(this, 252, 278, nextLevel,
				exitPoint));
	}

}
