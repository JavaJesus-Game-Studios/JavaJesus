package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class RancheroHouseInterior extends Interior {

	private static final long serialVersionUID = 7955092802065217014L;

	private Point exitPoint;

	public RancheroHouseInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Ranchero_House_Interior.png", new Point(256, 304), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 256, 304, nextLevel, exitPoint));
	}

}
