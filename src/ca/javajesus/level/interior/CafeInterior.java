package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.level.Level;

public class CafeInterior extends Interior {

	private Point exitPoint;
	private Entity entity;
	private Spawner spawner;
	
	public CafeInterior(Point point, Level level) {
		super("/Buildings/Unique_TechTopia_Interiors/Cafe_Interior.png", new Point(944, 920), level);	
		this.exitPoint = point;
	}
	
	public CafeInterior(Point point, Level level, Entity entity, Spawner spawner) {
		super("/Buildings/Unique_TechTopia_Interiors/Cafe_Interior.png", new Point(944, 920), level);	
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
		this.spawner = spawner;
		spawner.init(this);
	}
	
	public CafeInterior(Point point, Level level, Entity entity) {
		super("/Buildings/Unique_TechTopia_Interiors/Cafe_Interior.png", new Point(944, 920), level);	
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
	}
	
	protected void initNPCPlacement() {
		
	}

	protected void initSpawnerPlacement() {
		
		if (spawner != null) {
			this.addEntity(spawner);
		}
	
	}

	protected void initChestPlacement() {
		
		if (entity != null) {
			this.addEntity(entity);
		}
		
	}

	protected void otherEntityPlacement() {
		this.addEntity(new TransporterInterior(this, 944, 920, nextLevel, exitPoint));
	}

}
