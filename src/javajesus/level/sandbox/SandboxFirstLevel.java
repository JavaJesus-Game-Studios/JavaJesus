package javajesus.level.sandbox;

import java.awt.Point;

import javajesus.entities.Entity;
import javajesus.entities.Spawner;
import javajesus.entities.npcs.NPC;
import javajesus.entities.solid.furniture.Chest;
import javajesus.entities.transporters.MapTransporter;
import javajesus.level.Level;

/*
 * Have comments here about which level it is
 * 
 * 1. extend level
 * 2. add constructor w/ spawn
 * 3. add unimplemented methods
 */
public class SandboxFirstLevel extends Level {

	// serialization
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes the First Level
	 * 
	 * @param imagePath - the level file path
	 * @param loadNow - whether or not to load now // always true for now
	 * @param name - Name of this level
	 * @param spawn - where the player first spawns
	 */
	public SandboxFirstLevel() {
		super("/Levels/Sandbox/test.png", true, "First map", new Point(100, 100));
	}

	/**
	 * Where NPCs are loaded
	 */
	@Override
	protected NPC[] getNPCPlacement() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Where spawners are placed
	 */
	@Override
	protected Spawner[] getSpawnerPlacement() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Where chests are placed
	 */
	@Override
	protected Chest[] getChestPlacement() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Where Transporter Tiles are placed These transport you from one level to
	 * another probably wont use these unless you plan on going inside buildings
	 */
	@Override
	protected MapTransporter[] getMapTransporterPlacement() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Anything else This includes trees, forests, something that wasnt
	 * mentioned above
	 */
	@Override
	protected Entity[] getOtherPlacement() {
		// TODO Auto-generated method stub
		return null;
	}

}
