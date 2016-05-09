package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class WarehouseInterior extends Interior {

	private static final long serialVersionUID = 179209523701508203L;

	private Point exitPoint;

	public WarehouseInterior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Warehouse_Interior.png", new Point(264, 272), level);
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
