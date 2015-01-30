package ca.javajesus.game;

import java.applet.Applet;
import java.applet.AudioClip;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHandler {

	public boolean muted = false;

	public static SoundHandler sound = new SoundHandler();

	public static Clip sheathe, background1, chest, chimpanzee, click, demon,
			fireball, footsteps1, footstepsDirt, footstepsFarmland,
			footstepsGrass, footstepsRoad, footstepsWaterSand, footstepsWood,
			gunshot, gunshot2, gunshot3, levelup, shortSword1, swimming,
			walking;

	public SoundHandler() {

		AudioInputStream sheathe, background1, chest, chimpanzee, click, demon, fireball, footsteps1, footstepsDirt, footstepsFarmland, footstepsGrass, footstepsRoad, footstepsWaterSand, footstepsWood, gunshot, gunshot2, gunshot3, levelup, shortSword1, swimming, walking;

		try {

			sheathe = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Short_Sword_Sound_v2.wav"));

			background1 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Freaks.wav"));

			chest = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Chest_Opening_v2.wav"));

			chimpanzee = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Chimpanzee_voice_v2.wav"));

			click = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Click_v2.wav"));

			demon = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Demon_Updated_v2.wav"));

			fireball = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Fireball_v2.wav"));

			footsteps1 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Footsteps_Alternate_v2.wav"));

			footstepsDirt = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Footsteps_Dirt_v2.wav"));

			footstepsFarmland = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Footsteps_Farmland_v2.wav"));

			footstepsGrass = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Footsteps_Grass_v2.wav"));

			footstepsRoad = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Footsteps_Road_v2.wav"));

			footstepsWaterSand = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Footsteps_Water_Sand_v2.wav"));

			footstepsWood = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Footsteps_Wood_v2.wav"));

			gunshot = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Gunshot2_Sidd_v2.wav"));

			gunshot2 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Gunshot_Sidd_v2.wav"));

			gunshot3 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Gun_Shot_v2.wav"));

			levelup = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Level_Up_v2.wav"));

			shortSword1 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Short_Sword_Sound_v2.wav"));

			swimming = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Swimming_v2.wav"));

			walking = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Walking_v2.wav"));

			SoundHandler.sheathe = AudioSystem.getClip();
			SoundHandler.background1 = AudioSystem.getClip();
			SoundHandler.chest = AudioSystem.getClip();
			SoundHandler.chimpanzee = AudioSystem.getClip();
			SoundHandler.click = AudioSystem.getClip();
			SoundHandler.demon = AudioSystem.getClip();
			SoundHandler.fireball = AudioSystem.getClip();
			SoundHandler.footsteps1 = AudioSystem.getClip();
			SoundHandler.footstepsDirt = AudioSystem.getClip();
			SoundHandler.footstepsFarmland = AudioSystem.getClip();
			SoundHandler.footstepsGrass = AudioSystem.getClip();
			SoundHandler.footstepsRoad = AudioSystem.getClip();
			SoundHandler.footstepsWaterSand = AudioSystem.getClip();
			SoundHandler.footstepsWood = AudioSystem.getClip();
			SoundHandler.gunshot = AudioSystem.getClip();
			SoundHandler.gunshot2 = AudioSystem.getClip();
			SoundHandler.gunshot3 = AudioSystem.getClip();
			SoundHandler.levelup = AudioSystem.getClip();
			SoundHandler.shortSword1 = AudioSystem.getClip();
			SoundHandler.swimming = AudioSystem.getClip();
			SoundHandler.walking = AudioSystem.getClip();

			SoundHandler.chimpanzee.open(chimpanzee);
			SoundHandler.click.open(click);
			SoundHandler.demon.open(demon);
			SoundHandler.fireball.open(fireball);
			SoundHandler.gunshot.open(gunshot);
			SoundHandler.background1.open(background1);
			SoundHandler.sheathe.open(sheathe);
		} catch (Exception e) {

		}

	}

	public void play(Clip clip) {
		if (!muted) {
			if (clip.isRunning()) {
				clip.stop();
			}
			clip.setFramePosition(0);
			clip.start();
		}
	}

}
