package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class PoorHouseInterior extends Interior {

	private static final long serialVersionUID = -4406965337751303583L;

	private Point exitPoint;

	public PoorHouseInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Generic_Poor_House_Interior.png", new Point(256, 272), level);
		this.exitPoint = point;
	}

	protected void initNPCPlacement() {

	}

	protected void initSpawnerPlacement() {

	}

	protected void initChestPlacement() {

	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 252, 278, nextLevel, exitPoint));
	}

}
