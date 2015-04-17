package ca.javajesus.level.interior;

import java.awt.Point;

import ca.javajesus.game.entities.Entity;
import ca.javajesus.game.entities.Spawner;
import ca.javajesus.game.entities.structures.transporters.TransporterInterior;
import ca.javajesus.level.Level;

public class UCGrizzlyInterior extends Interior {

	private Point exitPoint;
	private Entity entity;
	private Spawner spawner;
	
	public UCGrizzlyInterior(Point point, Level level) {
		super("/Buildings/Unique_HippyVille_Interiors/UC_Grizzly_Interior.png", new Point(944, 920), level);	
		this.exitPoint = point;
	}
	
	public UCGrizzlyInterior(Point point, Level level, Entity entity, Spawner spawner) {
		super("/Buildings/Unique_HippyVille_Interiors/UC_Grizzly_Interior.png", new Point(944, 920), level);	
		this.exitPoint = point;
		this.entity = entity;
		entity.init(this);
		this.spawner = spawner;
		spawner.init(this);
	}
	
	public UCGrizzlyInterior(Point point, Level level, Entity entity) {
		super("/Buildings/Unique_HippyVille_Interiors/UC_Grizzly_Interior.png", new Point(944, 920), level);	
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
