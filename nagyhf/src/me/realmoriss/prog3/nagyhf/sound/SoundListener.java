package me.realmoriss.prog3.nagyhf.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import java.io.IOException;

/**
 * Created on 11/30/16.
 */
public class SoundListener implements LineListener {
	private Clip clip;
	private AudioInputStream as;

	public SoundListener(Clip clip, AudioInputStream as) {
		this.clip = clip;
		this.as = as;
	}

	@Override
	public void update(LineEvent event) {
		if (event.getType().equals(LineEvent.Type.STOP)) {
			if (clip != null) {
				clip.stop();
				clip.flush();
				try {
					clip.close();
					System.out.println("clip closed");
				} finally {
					clip = null;
				}
			}
			if (as != null) {
				try {
					as.close();
					System.out.println("as closed");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					as = null;
				}
			}
		}
	}
}
