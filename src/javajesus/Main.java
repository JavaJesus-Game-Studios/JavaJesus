package javajesus;

import engine.GameEngine;
import javajesus.utility.JJStrings;

/**
 * @author Derek
 * 
 * This is the main driver of JavaJesus It uses the JavaJesus external engine to
 * instantiate a game
 */
public class Main {

	// Window width
	public static final int WINDOW_WIDTH = 700 * 12 / 9;

	// Window height
	public static final int WINDOW_HEIGHT = 700;

	/**
	 * Main Method
	 * 
	 * @param args run time arguments
	 */
	public static void main(String[] args) {

		try {

			// initialize a new game
			new GameEngine(JJStrings.NAME, WINDOW_WIDTH, WINDOW_HEIGHT, new JavaJesus())
			.start();

			// report any errors
		} catch (Exception e) {
			System.err.println("Game Failed to Initialize!");
			e.printStackTrace();
		}

	}

}
