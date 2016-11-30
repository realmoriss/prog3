package me.realmoriss.prog3.nagyhf;

import javax.swing.*;
import java.util.Timer;

/**
 * Created on 11/28/16.
 */
public class TestApp {
	private static int MIN_FPS = 60;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
		}

		GameFrame gf = new GameFrame();
		GameLoop gl = new GameLoop(gf);

		Timer glTimer = new Timer();
		glTimer.scheduleAtFixedRate(gl, 0, 1000/MIN_FPS);

	}
}
