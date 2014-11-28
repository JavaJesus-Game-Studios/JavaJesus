package ca.javajesus.game;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundHandler {

	public AudioClip sheathe;

	public SoundHandler() {
		
		sheathe = Applet.newAudioClip(SoundHandler.class
				.getResource("/Sound/Short_Sword_Sound.wav"));
	}
	
	/**
	public Clip sound;

	public SoundHandler(){

		AudioInputStream sheathe;
		try {
			sheathe = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Short_Sword_Sound.wav"));
			sound = AudioSystem.getClip();
			sound.open(sheathe);
			sound.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

	}**/

}
