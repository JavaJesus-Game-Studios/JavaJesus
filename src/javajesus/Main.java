package javajesus;

import javajesus.gui.Launcher;

/*
 * Driver class
 */
public class Main {

	/**
	 * @param args
	 *            Run time arguments
	 */
	public static void main(String[] args) {
		SoundHandler.initialize();
		new Launcher().start();
	}

}
