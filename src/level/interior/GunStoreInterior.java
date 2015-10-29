package level.interior;

import game.entities.structures.transporters.TransporterInterior;

import java.awt.Point;

import level.Level;

public class GunStoreInterior extends Interior {

	private Point exitPoint;

	public GunStoreInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Gun_Store_Interior.png", new Point(256,
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
		this.addEntity(new TransporterInterior(this, 256, 272, nextLevel,
				exitPoint));
	}

}
