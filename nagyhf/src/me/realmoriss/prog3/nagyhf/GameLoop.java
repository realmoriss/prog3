package me.realmoriss.prog3.nagyhf;

import java.util.TimerTask;

/**
 * Created on 11/29/16.
 */
public class GameLoop extends TimerTask {
	private GameFrame game;

	public GameLoop(GameFrame gf) {
		game = gf;
	}

	@Override
	public void run() {
		game.step();
	}
}
