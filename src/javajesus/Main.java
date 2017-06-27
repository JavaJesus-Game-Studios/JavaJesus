package javajesus;

import engine.GameEngine;
import javajesus.utility.JJStrings;

/**
 * @author Derek
 * 
 * This is the main driver of JavaJesus
 * It initializes the sound and launcher
 */
public class Main {

	/**
	 * Main Method
	 * 
	 * @param args run time arguments
	 */
	public static void main(String[] args) {

		try {

			// start the launcher and sound
			SoundHandler.initialize();
			
			// initialize a launcher
			new GameEngine(JJStrings.NAME, JavaJesus.WINDOW_WIDTH, JavaJesus.WINDOW_HEIGHT, new Launcher())
			.start();

			// report any errors
		} catch (Exception e) {
			System.err.println("Launcher Failed to Start!");
			e.printStackTrace();
			
			System.exit(-1);
		}

	}

}
