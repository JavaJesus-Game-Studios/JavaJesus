package level.interior;

import java.awt.Point;

import game.entities.structures.transporters.TransporterInterior;
import level.Level;

public class CafeInterior extends Interior {

	private static final long serialVersionUID = -7327198235470021081L;
	
	private Point exitPoint;
	
	public CafeInterior(Point point, Level level) {
		super("/Buildings/Unique_TechTopia_Interiors/Cafe_Interior.png", new Point(944, 920), level);	
		this.exitPoint = point;
	}
	
	
	protected void initNPCPlacement() {
		
	}

	protected void initSpawnerPlacement() {
		
	
	}

	protected void initChestPlacement() {
		
		
	}

	protected void otherEntityPlacement() {
		add(new TransporterInterior(this, 944, 920, nextLevel, exitPoint));
	}

}
