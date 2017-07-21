package javajesus.level.sandbox;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.solid.trees.Forest;
import javajesus.entities.transporters.MapTransporter;
import javajesus.level.Level;

/*
 * Fixed level for sandbox mode
 */
public class SandboxIslandLevel extends Level {

	// serialization
	private static final long serialVersionUID = 259027073546330929L;
	
	/**
	 * SandboxOriginalLevel ctor()
	 * 
	 * Creates a fixed island map
	 */
	public SandboxIslandLevel() {
		super("/WORLD_DATA/SANDBOX_DATA/TEST_LEVELS/island", true, "Island Map", new Point(788, 792));
	}

	protected NPC[] getNPCPlacement() {
		return null;
	}

	protected Spawner[] getSpawnerPlacement() {
		return null;
	}

	protected Chest[] getChestPlacement() {
		return null;
	}

	protected Entity[] getOtherPlacement() {
		
		Forest.generateForest(this, 808, 888, 500, 400);
		
		return null;
		
	}

	protected MapTransporter[] getMapTransporterPlacement() {
		return null;
	}

}
