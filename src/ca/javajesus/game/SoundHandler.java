package ca.javajesus.game;

import java.applet.Applet;
import java.applet.AudioClip;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHandler {

	public Clip sheathe, background1, chest, chimpanzee, click, demon,
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
					.getResource("/Sound/Background1_v2.wav"));

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
					.getResource("/Sound/Gun_Shot_v2.wav"));

			gunshot2 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Gunshot_Sidd_v2.wav"));

			gunshot3 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Gunshot2_Sidd_v2.wav"));

			levelup = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Level_Up_v2.wav"));

			shortSword1 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Short_Sword_Sound_v2.wav"));

			swimming = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Swimming_v2.wav"));

			walking = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Walking_v2.wav"));

			this.sheathe = AudioSystem.getClip();
			this.background1 = AudioSystem.getClip();
			this.chest = AudioSystem.getClip();
			this.chimpanzee = AudioSystem.getClip();
			this.click = AudioSystem.getClip();
			this.demon = AudioSystem.getClip();
			this.fireball = AudioSystem.getClip();
			this.footsteps1 = AudioSystem.getClip();
			this.footstepsDirt = AudioSystem.getClip();
			this.footstepsFarmland = AudioSystem.getClip();
			this.footstepsGrass = AudioSystem.getClip();
			this.footstepsRoad = AudioSystem.getClip();
			this.footstepsWaterSand = AudioSystem.getClip();
			this.footstepsWood = AudioSystem.getClip();
			this.gunshot = AudioSystem.getClip();
			this.gunshot2 = AudioSystem.getClip();
			this.gunshot3 = AudioSystem.getClip();
			this.levelup = AudioSystem.getClip();
			this.shortSword1 = AudioSystem.getClip();
			this.swimming = AudioSystem.getClip();
			this.walking = AudioSystem.getClip();

			this.chimpanzee.open(chimpanzee);
			this.click.open(click);
			this.demon.open(demon);
			this.fireball.open(fireball);
			this.gunshot.open(gunshot);
			this.background1.open(background1);
			this.sheathe.open(sheathe);
		} catch (Exception e) {

		}

	}
}
