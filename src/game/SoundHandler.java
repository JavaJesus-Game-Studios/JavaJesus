package game;

import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*
 * The sound engine for the game
 * Controls all sounds that can be called by any entity
 */
public class SoundHandler {

	// determines if sounds will actually play
	private static boolean muted = false;

	// audio clips that can be opened from the sound stream
	public static Clip sheathe, background1, chest, chimpanzee, click, demon,
			fireball, footsteps1, footstepsDirt, footstepsFarmland,
			footstepsGrass, footstepsRoad, footstepsWood, assaultRifle, revolver,
			shotgun, levelup, shortSword1, shortSword2, swimming, walking,
			background2, explosion, carAcceleration, carDriving, carIdle,
			carStartUp, laser;

	public SoundHandler(Clip test) {}

	@SuppressWarnings("unused")
	/**
	 * Initializes the sounds for the game
	 */
	public static void initialize() {

		AudioInputStream sheathe, background1, chest, chimpanzee, click, demon, 
		fireball, footsteps1, footstepsDirt, footstepsFarmland, footstepsGrass, 
		footstepsRoad, footstepsWaterSand, footstepsWood, assaultRifle, revolver, shotgun,
		levelup, shortSword1, shortSword2, swimming, walking, background2, 
		explosion, carAcceleration, carDriving, carIdle, carStartUp, laser;

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

			assaultRifle = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Projectile_Sounds/Assault_Rifle_Sound_1.wav"));

			revolver = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Projectile_Sounds/Revolver_Sound.wav"));

			shotgun = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Projectile_Sounds/Shotgun_Sound.wav"));

			explosion = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Projectile_Sounds/Explosion.wav"));
			
			laser = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Projectile_Sounds/Laser_Gun_Sound.wav"));

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

			carAcceleration = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Car_Sounds/Car_Acceleration.wav"));

			carDriving = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Car_Sounds/Car_Driving.wav"));

			carIdle = AudioSystem
					.getAudioInputStream(SoundHandler.class
							.getResource("/Sound/Car_Sounds/Car_Idle.wav"));

			carStartUp = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/Sound/Car_Sounds/Car_Start_up.wav"));

			SoundHandler.sheathe = AudioSystem.getClip();
			SoundHandler.background1 = AudioSystem.getClip();
			SoundHandler.background2 = AudioSystem.getClip();
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
			SoundHandler.footstepsWood = AudioSystem.getClip();
			SoundHandler.assaultRifle = AudioSystem.getClip();
			SoundHandler.revolver = AudioSystem.getClip();
			SoundHandler.shotgun = AudioSystem.getClip();
			SoundHandler.levelup = AudioSystem.getClip();
			SoundHandler.shortSword1 = AudioSystem.getClip();
			SoundHandler.shortSword2 = AudioSystem.getClip();
			SoundHandler.swimming = AudioSystem.getClip();
			SoundHandler.walking = AudioSystem.getClip();
			SoundHandler.laser = AudioSystem.getClip();
			SoundHandler.explosion = AudioSystem.getClip();
			
			SoundHandler.carAcceleration = AudioSystem.getClip();
			SoundHandler.carDriving = AudioSystem.getClip();
			SoundHandler.carIdle = AudioSystem.getClip();
			SoundHandler.carStartUp = AudioSystem.getClip();

			SoundHandler.chimpanzee.open(chimpanzee);
			SoundHandler.click.open(click);
			SoundHandler.demon.open(demon);
			SoundHandler.fireball.open(fireball);
			SoundHandler.assaultRifle.open(assaultRifle);
			SoundHandler.revolver.open(revolver);
			SoundHandler.shotgun.open(shotgun);
			SoundHandler.background1.open(background1);
			SoundHandler.background2.open(background2);
			SoundHandler.sheathe.open(sheathe);
			SoundHandler.swimming.open(swimming);
			
			SoundHandler.explosion.open(explosion);
			SoundHandler.laser.open(laser);

			SoundHandler.chest.open(chest);
			SoundHandler.levelup.open(levelup);

			SoundHandler.footstepsDirt.open(footstepsDirt);
			SoundHandler.footstepsGrass.open(footstepsGrass);
			SoundHandler.footstepsRoad.open(footstepsRoad);
			SoundHandler.footstepsWood.open(footstepsWood);

			SoundHandler.shortSword1.open(shortSword1);
			SoundHandler.shortSword2.open(shortSword2);
			
			SoundHandler.carAcceleration.open(carAcceleration);
			SoundHandler.carDriving.open(carDriving);
			SoundHandler.carIdle.open(carIdle);
			SoundHandler.carStartUp.open(carStartUp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Plays an audio clip that will restart the same clip if it is already playing
	 * @param clip to play
	 */
	public void play(Clip clip) {
		if (!muted) {
			if (clip.isRunning()) {
				clip.stop();
			}
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
	/**
	 * Plays an audio clip that will play over another audio clip if that is already playing
	 * @param clip to play
	 */
	public void playSmoothly(Clip clip) {
		if (!muted) {
			if (clip.isRunning()) {
				return;
			} else {
				clip.setFramePosition(0);
				clip.start();
			}
		}
	}

	/**
	 * Randomly selects a short sword sound to play
	 */
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

	/**
	 * Loops a certain clip, generally background clips
	 * @param clip to play
	 */
	public void playLoop(Clip clip) {
		if (!muted) {
			if (clip.isRunning()) {
				clip.stop();
			}
			clip.setFramePosition(0);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	/**
	 * Continuously plays a clip, used for bullets
	 * @param clip to play
	 */
	public void fire(Clip clip) {
		if (!muted) {
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
	/**
	 * Toggles mute
	 */
	public void toggleMute() {
		muted = !muted;
	}

	/**
	 * Closes all audio clips
	 */
	public void closeAll() {
		SoundHandler.chimpanzee.close();
		SoundHandler.click.close();
		SoundHandler.demon.close();
		SoundHandler.fireball.close();
		SoundHandler.assaultRifle.close();
		SoundHandler.revolver.close();
		SoundHandler.shotgun.close();
		SoundHandler.background1.close();
		SoundHandler.background2.close();
		SoundHandler.sheathe.close();
		SoundHandler.swimming.close();
		SoundHandler.explosion.close();
		SoundHandler.laser.close();
		SoundHandler.chest.close();
		SoundHandler.levelup.close();
		SoundHandler.footstepsDirt.close();
		SoundHandler.footstepsGrass.close();
		SoundHandler.footstepsRoad.close();
		SoundHandler.footstepsWood.close();
		SoundHandler.shortSword1.close();
		SoundHandler.shortSword2.close();
		SoundHandler.carAcceleration.close();
		SoundHandler.carDriving.close();
		SoundHandler.carIdle.close();
		SoundHandler.carStartUp.close();
	}

}
