import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHandler {

	public boolean muted = false;

	public static SoundHandler sound = new SoundHandler();

	public static Clip click, end;
	public SoundHandler() {

		AudioInputStream click, end;

		try {

			click = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/res/Click_v2.wav"));
			end = AudioSystem.getAudioInputStream(SoundHandler.class
					.getResource("/res/Level_Up_v2.wav"));

			SoundHandler.click = AudioSystem.getClip();

			SoundHandler.click.open(click);
			
			SoundHandler.end = AudioSystem.getClip();

			SoundHandler.end.open(end);
			
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
