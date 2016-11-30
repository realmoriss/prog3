package me.realmoriss.prog3.nagyhf.entities;

import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 11/30/16.
 */
public class GraphicEntityTest {
	GraphicEntity ent;

	@Before
	public void init() {
		ent = new GraphicEntity(new Vec2D(10, 11), "TestEntity");
	}

	@Test
	public void getPos() throws Exception {
		Vec2D pos = ent.getPos();
		Assert.assertEquals(10, pos.getX(), 0);
		Assert.assertEquals(11, pos.getY(), 0);
	}

	@Test
	public void setPos() throws Exception {
		Vec2D pos = new Vec2D(12, 13);
		Vec2D pos1 = ent.setPos(pos);
		Assert.assertEquals(12, pos1.getX(), 0);
		Assert.assertEquals(13, pos1.getY(), 0);
	}

	@Test
	public void getName() throws Exception {
		String name = ent.getName();
		Assert.assertEquals("TestEntity", name);
	}

	@Test
	public void setName() throws Exception {
		ent.setName("NewName");
		String name = ent.getName();
		Assert.assertEquals("NewName", name);
	}

}