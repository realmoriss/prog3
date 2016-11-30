package me.realmoriss.prog3.nagyhf;

import me.realmoriss.prog3.nagyhf.entities.Macska;
import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created on 11/28/16.
 */
public class TestApp {
	private static int MIN_FPS = 1000;

	public static void main(String[] args) {
		System.out.println("Hello World!");

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
