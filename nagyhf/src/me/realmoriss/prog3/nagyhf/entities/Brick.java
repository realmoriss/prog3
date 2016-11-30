package me.realmoriss.prog3.nagyhf.entities;
import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;
import me.realmoriss.prog3.nagyhf.sound.SoundListener;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created on 11/29/16.
 */
public class Brick extends GraphicEntity {
	private static final String DEF_CLASSNAME = "prop_brick";
	private static final String BRICK_IMG = "assets/img/brick.png";
	private static final String BRICK_SND = "assets/sound/brick.wav";
	protected boolean destroyed = false;
	protected AudioInputStream destroySound;
	protected static File destroySoundFile = null;

	public Brick(Vec2D pos, String name) {
		super(pos, name, BRICK_IMG);
		/*
		if (destroySoundFile == null) {
			destroySoundFile = new File(BRICK_SND);
		}
		try {
			destroySound = AudioSystem.getAudioInputStream(destroySoundFile);
		} catch (IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		*/
		classname = DEF_CLASSNAME;
	}

	public Brick(Vec2D pos, String name, Vec2D size) {
		super(pos, name, BRICK_IMG, size);
		/*
		if (destroySoundFile == null) {
			destroySoundFile = new File(BRICK_SND);
		}
		try {
			destroySound = AudioSystem.getAudioInputStream(destroySoundFile);
		} catch (IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		*/
		classname = DEF_CLASSNAME;
	}

	public boolean getDestroyed() {
		return destroyed;
	}

	public void destroy() {
		if (!destroyed) {
			destroyed = true;
			/*
			try {
				Clip clip = AudioSystem.getClip();
				//clip.addLineListener(new SoundListener(clip, destroySound));
				clip.open(destroySound);
				clip.start();
				destroySound.close();
			} catch (IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
			*/
		}
	}

}
