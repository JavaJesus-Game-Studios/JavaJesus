package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class ShantyHouseInterior extends Interior {

	private static final long serialVersionUID = -6309788913336065310L;

	private Point exitPoint;

	public ShantyHouseInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Shanty_House_Interior.png", new Point(256, 272), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 256, 278, nextLevel, exitPoint));
	}

}
