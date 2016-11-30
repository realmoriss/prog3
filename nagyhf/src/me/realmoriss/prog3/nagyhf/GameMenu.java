package me.realmoriss.prog3.nagyhf;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created on 11/30/16.
 */
public class GameMenu implements KeyListener{
	private boolean inMenu = true;

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			inMenu = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public boolean isInMenu() {
		return inMenu;
	}
}
