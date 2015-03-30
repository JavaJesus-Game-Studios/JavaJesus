package ca.javajesus.game;

import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHandler implements java.io.Serializable{

	public boolean muted = false;

	public static SoundHandler sound = new SoundHandler();

	public Clip sheathe, background1, chest, chimpanzee, click, demon,
			fireball, footsteps1, footstepsDirt, footstepsFarmland,
			footstepsGrass, footstepsRoad, footstepsWood, gunshot, gunshot2,
			gunshot3, levelup, shortSword1, shortSword2, swimming, walking,
			background2, explosion;

	public Clip[] list = { background1, background2 };

	@SuppressWarnings("unused")
	public SoundHandler() {

		AudioInputStream sheathe, background1, chest, chimpanzee, click, demon, fireball, footsteps1, footstepsDirt, footstepsFarmland, footstepsGrass, footstepsRoad, footstepsWaterSand, footstepsWood, gunshot, gunshot2, gunshot3, levelup, shortSword1, shortSword2, swimming, walking, background2, explosion;

		try {

			sheathe = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Sword_Sounds/Short_Sword_Sound_v2.wav"));

			background2 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Freaks.wav"));

			background1 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/8-bit_Sounds/Background1_v2.wav"));

			chest = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/8-bit_Sounds/Chest_Opening_v2.wav"));

			chimpanzee = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Enemy-Mob_Sounds/Chimpanzee_Voice_v2.wav"));

			click = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/8-bit_Sounds/Click_v2.wav"));

			demon = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Enemy-Mob_Sounds/Demon_Updated_v2.wav"));

			fireball = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Projectile_Sounds/Fireball_v2.wav"));

			footsteps1 = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Footstep_Sounds/Footsteps_Alternate_v2.wav"));

			footstepsDirt = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Footstep_Sounds/Footsteps_Dirt.wav"));

			footstepsFarmland = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Footstep_Sounds/Footsteps_Farmland_v2.wav"));

			footstepsGrass = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Footstep_Sounds/Footsteps_Grass.wav"));

			footstepsRoad = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Footstep_Sounds/Footsteps_Road.wav"));

			footstepsWood = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Footstep_Sounds/Footsteps_Wood.wav"));

			gunshot = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Projectile_Sounds/Gunshot2_Sidd_v2.wav"));

			gunshot2 = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Projectile_Sounds/Gun_Shot_v2.wav"));

			gunshot3 = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Projectile_Sounds/Gunshot_Sidd_v2.wav"));

			explosion = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Projectile_Sounds/Gunshot_Sidd_v2.wav"));

			levelup = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/8-bit_Sounds/Level_Up_v2.wav"));

			shortSword1 = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Sword_Sounds/Short_Sword_Swoosh_1.wav"));

			shortSword2 = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Sword_Sounds/Short_Sword_Swoosh_2.wav"));

			swimming = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Footstep_Sounds/Swimming.wav"));

			this.sheathe = AudioSystem.getClip();
			this.background1 = AudioSystem.getClip();
			this.background2 = AudioSystem.getClip();
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
			this.footstepsWood = AudioSystem.getClip();
			this.gunshot = AudioSystem.getClip();
			this.gunshot2 = AudioSystem.getClip();
			this.gunshot3 = AudioSystem.getClip();
			this.levelup = AudioSystem.getClip();
			this.shortSword1 = AudioSystem.getClip();
			this.shortSword2 = AudioSystem.getClip();
			this.swimming = AudioSystem.getClip();
			this.walking = AudioSystem.getClip();

			this.chimpanzee.open(chimpanzee);
			this.click.open(click);
			this.demon.open(demon);
			this.fireball.open(fireball);
			this.gunshot.open(gunshot);
			this.gunshot3.open(gunshot3);
			this.background1.open(background1);
			this.background2.open(background2);
			this.sheathe.open(sheathe);
			this.swimming.open(swimming);

			this.chest.open(chest);
			this.levelup.open(levelup);

			this.footstepsDirt.open(footstepsDirt);
			this.footstepsGrass.open(footstepsGrass);
			this.footstepsRoad.open(footstepsRoad);
			this.footstepsWood.open(footstepsWood);

			this.shortSword1.open(shortSword1);
			this.shortSword2.open(shortSword2);

		} catch (Exception e) {
			e.printStackTrace();
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

	public void playShortSword() {
		if (!muted) {
			Clip clip;
			Random random = new Random();
			switch (random.nextInt(2)) {
			case 0:
				clip = shortSword1;
				break;
			default:
				clip = shortSword2;
				break;
			}
			if (clip.isRunning()) {
				clip.stop();
			}
			clip.setFramePosition(0);
			clip.start();
		}
	}

	public void playLoop(Clip clip) {
		if (!muted) {
			if (clip.isRunning()) {
				clip.stop();
			}
			clip.setFramePosition(0);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	public void fire(Clip clip) {
		if (!muted) {
			clip.setFramePosition(0);
			clip.start();
		}
	}

	public void stopBackgrounds() {
		background1.stop();
		background2.stop();
	}

}
