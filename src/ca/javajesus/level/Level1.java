package ca.javajesus.level;

import ca.javajesus.game.entities.monsters.Monster;
import ca.javajesus.game.entities.npcs.NPC;
import ca.javajesus.game.entities.npcs.Policeman;
import ca.javajesus.game.entities.vehicles.Vehicle;

public class Level1 extends Level {

	public Level1(String imagePath) {
		super(imagePath);
	}

	public void initNPCPlacement() {
		this.addEntity(NPC.npc1);
		this.addEntity(NPC.npc2);
		this.addEntity(NPC.npc3);
		this.addEntity(NPC.npc4);
		this.addEntity(NPC.npc5);
		this.addEntity(NPC.npc6);
		this.addEntity(new Policeman(this, 400, 100, 200, "linear", 20));
		this.addEntity(NPC.npc8);
		this.addEntity(NPC.npc9);
		this.addEntity(NPC.npc10);
		
		this.addEntity(Monster.gang1);
		this.addEntity(Monster.horseThing1);
		//this.addEntity(Monster.man1);
		//this.addEntity(Monster.man2);
				
	}

	@Override
	public void initSpawnerPlacement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initChestPlacement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void otherEntityPlacement() {
		this.addEntity(Vehicle.vehicle1);
		
	}

}
