package javajesus.level.sandbox;

import java.awt.Point;
import java.io.IOException;

import javajesus.entities.npcs.Peasant;
import javajesus.entities.npcs.aggressive.Orangutan;
import javajesus.level.Level;
import javajesus.level.interior.AlphaCave;
import javajesus.level.interior.Interior;
import javajesus.quest.original.AlphaTurningTheTide;
import javajesus.quest.original.LiberateFarm;

/*
 * Fixed level for sandbox mode
 */
public class SandboxOriginalLevel extends Level {
	
	//Instance of the Souther Cave interior, used in the Liberate Church Quest
	public static Interior churchQuestCave;
	/**
	 * SandboxOriginalLevel ctor()
	 * 
	 * Creates a fixed sandbox map
	 * @throws IOException 
	 */
	public SandboxOriginalLevel(int slot) throws IOException {
		super("/WORLD_DATA/SANDBOX_DATA/TEST_LEVELS/original", "ALPHA Level", new Point(1472, 24), slot);
		
		// creates the scared Peasant the gives the liberate farm quest
		Peasant scaredPeasant = new Peasant(this, 832, 112, 1);
		scaredPeasant.addQuest(new LiberateFarm(scaredPeasant));
		//initializes cave interior to be the Alpha Cave Interior
		churchQuestCave = new AlphaCave(new Point(0,0), this);
		//Adds wise Orangutan with the "Turning the Tide" Quest inside the cave
		Orangutan ken = new  Orangutan(SandboxOriginalLevel.churchQuestCave, 1464, 16);
		ken.addQuest(new AlphaTurningTheTide(ken));
	}

}
