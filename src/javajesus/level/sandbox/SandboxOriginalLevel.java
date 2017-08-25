package javajesus.level.sandbox;

import java.awt.Point;
import java.io.IOException;

import javax.sound.sampled.Clip;

import javajesus.SoundHandler;
import javajesus.entities.npcs.Peasant;
import javajesus.level.Level;
import javajesus.quest.original.LiberateFarm;

/*
 * Fixed level for sandbox mode
 */
public class SandboxOriginalLevel extends Level {
	public static SandboxOriginalLevel alpha;
		
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
		add(scaredPeasant);
		
	}
	/**
	 * @return a clip of the  background music
	 */
	@Override
	public Clip getBackgroundMusic() {
		return SoundHandler.explorationMusic;
	}


}
