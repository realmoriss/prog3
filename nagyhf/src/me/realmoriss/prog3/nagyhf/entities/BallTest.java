package me.realmoriss.prog3.nagyhf.entities;

import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created on 11/30/16.
 */
public class BallTest {
	Ball ball;

	@Before
	public void init() {
		ball = new Ball(new Vec2D(10, 11), "Ball", 10);
	}

	@Test
	public void getSpeed() throws Exception {
		ball.setSpeed(new Vec2D(1, -1));
		Assert.assertEquals(1, ball.getSpeed().getX(), 0);
		Assert.assertEquals(-1, ball.getSpeed().getY(), 0);
	}

	@Test
	public void doesCollide() throws Exception {
		Rectangle rect = new Rectangle(0, 0, 2, 2);
		Assert.assertEquals(true, ball.doesCollide(rect));
		rect = new Rectangle(-10, -11, 1, 1);
		Assert.assertEquals(false, ball.doesCollide(rect));
	}

}