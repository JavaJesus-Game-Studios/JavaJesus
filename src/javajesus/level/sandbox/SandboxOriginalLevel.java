package javajesus.level.sandbox;

import java.awt.Point;
import java.io.IOException;

import javax.sound.sampled.Clip;

import javajesus.SoundHandler;
import javajesus.level.Level;
import javajesus.level.LevelFactory;

/*
 * Fixed level for sandbox mode
 */
public class SandboxOriginalLevel extends Level {
		
	/**
	 * SandboxOriginalLevel ctor()
	 * 
	 * Creates a fixed sandbox map
	 * @throws IOException 
	 */
	public SandboxOriginalLevel(int slot) throws IOException {
		super("/WORLD_DATA/SANDBOX_DATA/TEST_LEVELS/original", "Alpha Level", new Point(1472, 24), slot, LevelFactory.ALPHA);
		
	}
	/**
	 * @return a clip of the  background music
	 */
	@Override
	public Clip getBackgroundMusic() {
		return SoundHandler.explorationMusic;
	}


}
