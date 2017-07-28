package javajesus.level.sandbox;

import java.awt.Point;
import java.io.IOException;

import javajesus.level.Level;

/*
 * Fixed level for sandbox mode
 */
public class SandboxOriginalLevel extends Level {
	
	// lines of text
	private static final String INS_1 = "WASD - Move", INS_2 = "Arrow Keys - Shoot", INS_3 = "Space - Swing", INS_4 = "I - Inventory",
			INS_5 = "E - Action";
	
	// color set of text
	private static final int[] color = { 0xFF000000, 0xFF000000, 0xFFFFCC00 };

	/**
	 * SandboxOriginalLevel ctor()
	 * 
	 * Creates a fixed sandbox map
	 * @throws IOException 
	 */
	public SandboxOriginalLevel(int slot) throws IOException {
		super("/WORLD_DATA/SANDBOX_DATA/TEST_LEVELS/original", "Original Map", new Point(1472, 24), slot);
		
		// render some text
		addText(INS_1, 1416, 64, color, 1);
		addText(INS_2, 1416, 72, color, 1);
		addText(INS_3, 1416, 80, color, 1);
		addText(INS_4, 1416, 88, color, 1);
		addText(INS_5, 1416, 96, color, 1);
	}

}
