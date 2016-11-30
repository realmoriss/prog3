package me.realmoriss.prog3.nagyhf.entities;

import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created on 11/30/16.
 */
public class Paddle extends GraphicEntity implements KeyListener {
	private static final String DEF_CLASSNAME = "weapon_paddle";
	private static final String PADDLE_IMG = "assets/img/paddle.png";
	private static final int DEF_SPEED = 5;
	protected int speed;
	private int isMoving = 0;

	public Paddle(Vec2D pos, String name) {
		super(pos, name, PADDLE_IMG);
		classname = DEF_CLASSNAME;
		speed = DEF_SPEED;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			isMoving = -1;
		} else if (key == KeyEvent.VK_RIGHT) {
			isMoving = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT && isMoving == -1) {
			isMoving = 0;
		} else if (key == KeyEvent.VK_RIGHT && isMoving == 1) {
			isMoving = 0;
		}
	}

	public void step() {
		if (isMoving != 0) {
			setPos(getPos().add(speed * isMoving, 0));
		}
	}
}
