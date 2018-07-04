package javajesus;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.SplashScreen;

import javajesus.utility.JJStrings;
import engine.GameEngine;

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
			
			// create a splash loading screen
			/**
			SplashScreen splash = SplashScreen.getSplashScreen();
			
			// error checking
			if (splash == null) {
				System.err.println("Splash is null");
				System.err.println("Add VM Arg in Run Configurations: -splash:res/VISUAL_DATA/GUI/CREDITS/logo.gif");
				return;
			}
			
			// render the splash screen
	        splash.createGraphics();
	        splash.update();
*/
			// start the launcher and sound
			SoundHandler.initialize();
			
			// load the custom font
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/VISUAL_DATA/GUI/FONT/PressStart2P.ttf")));
			
			// initialize a launcher
			new GameEngine(JJStrings.NAME, JavaJesus.WINDOW_WIDTH, JavaJesus.WINDOW_HEIGHT, new Launcher())
			.start();
			
			// close the loading screen
			//splash.close();

			// report any errors
		} catch (Exception e) {
			System.err.println("Launcher Failed to Start!");
			e.printStackTrace();
			
			System.exit(-1);
		}

	}

}
