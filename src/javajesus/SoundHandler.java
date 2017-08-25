package javajesus;

import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.media.AudioClip;

/*
 * The sound engine for the game
 * Controls all sounds that can be called by any entity
 */
public class SoundHandler {

	// determines if sounds will actually play
	private static boolean muted;

	// background clip that is currently playing
	private static Clip currentBackground;

	// only looping clips will have their own audio thread
	public static Clip alphaTheme, explorationMusic, combatMusic, background2, footstepsDirt,
	        footstepsFarmland, footstepsGrass, footstepsRoad, footstepsWood, carAcceleration, carDriving, carIdle,
	        swimming;

	// audio clips that can be opened as needed
	public static URL sheathe, deathDirge, chest, questAccepted, chimpanzee, click, demon, fireball, assaultRifle,
	        revolver, shotgun, levelup, shortSword1, shortSword2, walking, explosion, carStartUp, laser;

	/**
	 * Initializes the sounds for the game
	 */
	public static void initialize() {

		// all the different audio streams that will be played at some point in
		// the game
		AudioInputStream alphaTheme, explorationMusic, combatMusic, background2, footstepsDirt,
		        footstepsFarmland, footstepsGrass, footstepsRoad, footstepsWood, carAcceleration, carDriving, carIdle,
		        swimming;

		try {

			// initialize all the audio streams
			sheathe = SoundHandler.class.getResource("/AUDIO_DATA/WEAPONS/SWORDS/Short_Sword_Sound_v2.wav");

			background2 = AudioSystem
			        .getAudioInputStream(SoundHandler.class.getResource("/AUDIO_DATA/MUSIC/Freaks.wav"));

			alphaTheme = AudioSystem
			        .getAudioInputStream(SoundHandler.class.getResource("/AUDIO_DATA/MUSIC/AlphaTheme.wav"));
			explorationMusic = AudioSystem
			        .getAudioInputStream(SoundHandler.class.getResource("/AUDIO_DATA/MUSIC/village_roam.wav"));
			combatMusic = AudioSystem
			        .getAudioInputStream(SoundHandler.class.getResource("/Audio_DATA/MUSIC/combat_1.wav"));
			deathDirge = SoundHandler.class.getResource("/AUDIO_DATA/MUSIC/DeathDirge.wav");

			chest = SoundHandler.class.getResource("/AUDIO_DATA/GUI/ChestOpened.wav");

			questAccepted = SoundHandler.class.getResource("/AUDIO_DATA/GUI/QuestAccepted.wav");
			chimpanzee = SoundHandler.class.getResource("/AUDIO_DATA/ACTOR/ENEMY/Chimpanzee_Voice_v2.wav");

			click = SoundHandler.class.getResource("/AUDIO_DATA/GUI/Click_v2.wav");

			demon = SoundHandler.class.getResource("/AUDIO_DATA/ACTOR/ENEMY/Demon_Updated_v2.wav");

			fireball = SoundHandler.class.getResource("/AUDIO_DATA/WEAPONS/FIREARMS/Fireball_v2.wav");

			footstepsDirt = AudioSystem.getAudioInputStream(
			        SoundHandler.class.getResource("/AUDIO_DATA/ACTOR/FOOTSTEPS/Footsteps_Dirt.wav"));

			footstepsFarmland = AudioSystem.getAudioInputStream(
			        SoundHandler.class.getResource("/AUDIO_DATA/ACTOR/FOOTSTEPS/Footsteps_Farmland_v2.wav"));

			footstepsGrass = AudioSystem.getAudioInputStream(
			        SoundHandler.class.getResource("/AUDIO_DATA/ACTOR/FOOTSTEPS/Footsteps_Grass.wav"));

			footstepsRoad = AudioSystem.getAudioInputStream(
			        SoundHandler.class.getResource("/AUDIO_DATA/ACTOR/FOOTSTEPS/Footsteps_Road.wav"));

			footstepsWood = AudioSystem.getAudioInputStream(
			        SoundHandler.class.getResource("/AUDIO_DATA/ACTOR/FOOTSTEPS/Footsteps_Wood.wav"));

			assaultRifle = SoundHandler.class.getResource("/AUDIO_DATA/WEAPONS/FIREARMS/Assault_Rifle_Sound_1.wav");

			revolver = SoundHandler.class.getResource("/AUDIO_DATA/WEAPONS/FIREARMS/Revolver_Sound.wav");

			shotgun = SoundHandler.class.getResource("/AUDIO_DATA/WEAPONS/FIREARMS/Shotgun_Sound.wav");

			explosion = SoundHandler.class.getResource("/AUDIO_DATA/WEAPONS/FIREARMS/Explosion.wav");

			laser = SoundHandler.class.getResource("/AUDIO_DATA/WEAPONS/FIREARMS/Laser_Gun_Sound.wav");

			shortSword1 = SoundHandler.class.getResource("/AUDIO_DATA/WEAPONS/SWORDS/Short_Sword_Swoosh_1.wav");

			shortSword2 = SoundHandler.class.getResource("/AUDIO_DATA/WEAPONS/SWORDS/Short_Sword_Swoosh_2.wav");

			swimming = AudioSystem
			        .getAudioInputStream(SoundHandler.class.getResource("/AUDIO_DATA/ACTOR/FOOTSTEPS/Swimming.wav"));

			carAcceleration = AudioSystem.getAudioInputStream(
			        SoundHandler.class.getResource("/AUDIO_DATA/VEHICLES/CAR/Car_Acceleration.wav"));

			carDriving = AudioSystem
			        .getAudioInputStream(SoundHandler.class.getResource("/AUDIO_DATA/VEHICLES/CAR/Car_Driving.wav"));

			carIdle = AudioSystem
			        .getAudioInputStream(SoundHandler.class.getResource("/AUDIO_DATA/VEHICLES/CAR/Car_Idle.wav"));

			carStartUp = SoundHandler.class.getResource("/AUDIO_DATA/VEHICLES/CAR/Car_Start_up.wav");

			SoundHandler.alphaTheme = AudioSystem.getClip();
			SoundHandler.background2 = AudioSystem.getClip();
			SoundHandler.explorationMusic = AudioSystem.getClip();
			SoundHandler.combatMusic = AudioSystem.getClip();
			SoundHandler.footstepsDirt = AudioSystem.getClip();
			SoundHandler.footstepsFarmland = AudioSystem.getClip();
			SoundHandler.footstepsGrass = AudioSystem.getClip();
			SoundHandler.footstepsRoad = AudioSystem.getClip();
			SoundHandler.footstepsWood = AudioSystem.getClip();
			SoundHandler.swimming = AudioSystem.getClip();

			SoundHandler.carAcceleration = AudioSystem.getClip();
			SoundHandler.carDriving = AudioSystem.getClip();
			SoundHandler.carIdle = AudioSystem.getClip();

			SoundHandler.alphaTheme.open(alphaTheme);
			SoundHandler.background2.open(background2);
			SoundHandler.explorationMusic.open(explorationMusic);
			SoundHandler.combatMusic.open(combatMusic);

			SoundHandler.footstepsDirt.open(footstepsDirt);
			SoundHandler.footstepsFarmland.open(footstepsFarmland);
			SoundHandler.footstepsGrass.open(footstepsGrass);
			SoundHandler.footstepsRoad.open(footstepsRoad);
			SoundHandler.footstepsWood.open(footstepsWood);
			SoundHandler.swimming.open(swimming);

			SoundHandler.carAcceleration.open(carAcceleration);
			SoundHandler.carDriving.open(carDriving);
			SoundHandler.carIdle.open(carIdle);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Plays a simple sound effect
	 * 
	 * @param stream - the audio stream to play
	 */
	public static void play(URL url) {

		// make sure the game is not muted
		if (!muted) {
			try {

				AudioClip clip = new AudioClip(url.toString());
				clip.play();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Plays an audio clip without interrupting it Used for ambience
	 * 
	 * @param clip to play
	 */
	public static void playAmbience(Clip clip) {

		// make sure game is not muted
		if (!muted) {

			// sound already playing
			if (clip.isRunning()) {
				return;
			} else {

				// restart the clip
				clip.setFramePosition(0);
				clip.start();
			}
		}
	}

	/**
	 * Randomly selects a short sword sound to play
	 */
	public static void playShortSword() {

		// make sure game is not muted
		if (!muted) {
			Random random = new Random();
			switch (random.nextInt(2)) {
			case 0:
				play(shortSword1);
				break;
			default:
				play(shortSword2);
				break;
			}
		}
	}

	/**
	 * Loops a certain clip, generally background clips
	 * 
	 * @param clip to play
	 */
	public static void playLoop(Clip clip) {

		// don't change the music if its the same
		if (!muted && currentBackground != clip) {

			// stop the current clip from playing
			if (currentBackground != null && currentBackground.isRunning()) {
				currentBackground.stop();
			}

			// now start the next clip
			currentBackground = clip;
			if (clip.isRunning()) {
				clip.stop();
			}
			clip.setFramePosition(0);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	/**
	 * Toggles mute
	 */
	public static void toggleMute() {
		muted = !muted;

		// toggles background music
		if (currentBackground != null) {

			// toggle the current background music
			if (muted) {
				currentBackground.stop();
			} else {
				currentBackground.setFramePosition(0);
				currentBackground.start();
			}

		}
	}

	/**
	 * Closes all audio clips to free memory
	 */
	public void closeAll() {
		alphaTheme.close();
		explorationMusic.close();
		combatMusic.close();
		background2.close();
		footstepsDirt.close();
		footstepsGrass.close();
		footstepsRoad.close();
		footstepsWood.close();
		carAcceleration.close();
		carDriving.close();
		carIdle.close();
	}

	/**
	 * @return True if the sound is muted
	 */
	public static boolean isMuted() {
		return muted;
	}

}
