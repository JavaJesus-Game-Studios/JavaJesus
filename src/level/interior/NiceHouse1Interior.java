package level.interior;

import game.entities.structures.transporters.TransporterInterior;

import java.awt.Point;

import level.Level;

public class NiceHouse1Interior extends Interior {

	private static final long serialVersionUID = -1360325449829006520L;

	private Point exitPoint;

	public NiceHouse1Interior(Point point, Level level) {
		super("/Buildings/Generic Interiors/Nice_House_1_Interiors/Nice_House1_Floor1.png", new Point(252, 272), level);
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
