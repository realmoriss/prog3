package me.realmoriss.prog3.nagyhf.entities.primitives;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 11/30/16.
 */
public class Vec2DTest {
	Vec2D vec1;
	Vec2D vec2;

	@Before
	public void init() {
		vec1 = new Vec2D(0, 0);
		vec2 = new Vec2D(1,2);
	}

	@Test
	public void getX() throws Exception {
		double x = vec1.getX();
		Assert.assertEquals(0, x, 0);
		x = vec2.getX();
		Assert.assertEquals(1, x, 0);
	}

	@org.junit.Test
	public void getY() throws Exception {
		double y = vec1.getY();
		Assert.assertEquals(0, y, 0);
		y = vec2.getY();
		Assert.assertEquals(2, y, 0);
	}

	@org.junit.Test
	public void addX() throws Exception {
		vec1.addX(10);
		Assert.assertEquals(10, vec1.getX(), 0);
		Assert.assertEquals(0, vec1.getY(), 0);
	}

	@org.junit.Test
	public void addY() throws Exception {
		vec1.addY(10);
		Assert.assertEquals(0, vec1.getX(), 0);
		Assert.assertEquals(10, vec1.getY(), 0);
	}

	@org.junit.Test
	public void add() throws Exception {
		vec1.add(vec2);
		Assert.assertEquals(1, vec1.getX(), 0);
		Assert.assertEquals(2, vec1.getY(), 0);
	}

	@org.junit.Test
	public void add1() throws Exception {
		vec1.add(10, 11);
		Assert.assertEquals(10, vec1.getX(), 0);
		Assert.assertEquals(11, vec1.getY(), 0);
	}

}