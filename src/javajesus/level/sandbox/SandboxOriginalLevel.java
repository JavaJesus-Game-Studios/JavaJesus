package javajesus.level.sandbox;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;

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
		super("/WORLD_DATA/SANDBOX_DATA/TEST_LEVELS/original", "Original Map", new Point(1360, 70), slot);
	}

}
