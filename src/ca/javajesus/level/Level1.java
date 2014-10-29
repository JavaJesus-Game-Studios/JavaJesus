package ca.javajesus.level;

import ca.javajesus.game.entities.NPC;
import ca.javajesus.level.tile.Tile;

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
		
	}

}
