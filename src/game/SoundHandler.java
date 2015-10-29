package game;

import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHandler {

	public boolean muted = false;

	public transient static SoundHandler sound = new SoundHandler();

	public transient Clip sheathe, background1, chest, chimpanzee, click, demon,
			fireball, footsteps1, footstepsDirt, footstepsFarmland,
			footstepsGrass, footstepsRoad, footstepsWood, assaultRifle, revolver,
			shotgun, levelup, shortSword1, shortSword2, swimming, walking,
			background2, explosion, carAcceleration, carDriving, carIdle,
			carStartUp, laser;

	public transient Clip[] list = { background1, background2 };

	@SuppressWarnings("unused")
	public SoundHandler() {

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
			this.assaultRifle = AudioSystem.getClip();
			this.revolver = AudioSystem.getClip();
			this.shotgun = AudioSystem.getClip();
			this.levelup = AudioSystem.getClip();
			this.shortSword1 = AudioSystem.getClip();
			this.shortSword2 = AudioSystem.getClip();
			this.swimming = AudioSystem.getClip();
			this.walking = AudioSystem.getClip();
			this.laser = AudioSystem.getClip();
			this.explosion = AudioSystem.getClip();
			
			this.carAcceleration = AudioSystem.getClip();
			this.carDriving = AudioSystem.getClip();
			this.carIdle = AudioSystem.getClip();
			this.carStartUp = AudioSystem.getClip();

			this.chimpanzee.open(chimpanzee);
			this.click.open(click);
			this.demon.open(demon);
			this.fireball.open(fireball);
			this.assaultRifle.open(assaultRifle);
			this.revolver.open(revolver);
			this.shotgun.open(shotgun);
			this.background1.open(background1);
			this.background2.open(background2);
			this.sheathe.open(sheathe);
			this.swimming.open(swimming);
			
			this.explosion.open(explosion);
			this.laser.open(laser);

			this.chest.open(chest);
			this.levelup.open(levelup);

			this.footstepsDirt.open(footstepsDirt);
			this.footstepsGrass.open(footstepsGrass);
			this.footstepsRoad.open(footstepsRoad);
			this.footstepsWood.open(footstepsWood);

			this.shortSword1.open(shortSword1);
			this.shortSword2.open(shortSword2);
			
			this.carAcceleration.open(carAcceleration);
			this.carDriving.open(carDriving);
			this.carIdle.open(carIdle);
			this.carStartUp.open(carStartUp);

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
	
	public void playContinuously(Clip clip) {
		if (!muted) {
			if (clip.isRunning()) {
				return;
			} else {
				clip.setFramePosition(0);
				clip.start();
			}
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

	public void closeAll() {
		this.chimpanzee.close();
		this.click.close();
		this.demon.close();
		this.fireball.close();
		this.assaultRifle.close();
		this.revolver.close();
		this.shotgun.close();
		this.background1.close();
		this.background2.close();
		this.sheathe.close();
		this.swimming.close();
		this.explosion.close();
		this.laser.close();
		this.chest.close();
		this.levelup.close();
		this.footstepsDirt.close();
		this.footstepsGrass.close();
		this.footstepsRoad.close();
		this.footstepsWood.close();
		this.shortSword1.close();
		this.shortSword2.close();
		this.carAcceleration.close();
		this.carDriving.close();
		this.carIdle.close();
		this.carStartUp.close();
	}

}
