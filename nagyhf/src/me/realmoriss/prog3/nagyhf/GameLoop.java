package me.realmoriss.prog3.nagyhf;

import java.util.TimerTask;

/**
 * Created on 11/29/16.
 */
public class GameLoop extends TimerTask {
	private GameFrame game;

	/**
	 * Letrehoz egy jatek loopot, ami a megadott GameFrame step metodusat hivja idozitve
	 * @param gf a vezerelni kivant GameFrame
	 */
	public GameLoop(GameFrame gf) {
		game = gf;
	}

	@Override
	public void run() {
		game.step();
	}
}
