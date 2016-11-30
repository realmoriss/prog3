package me.realmoriss.prog3.nagyhf.entities;

import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

/**
 * Created on 11/30/16.
 */
public class RedBrick extends Brick {
	private static final String DEF_CLASSNAME = "prop_brick_red";
	public RedBrick(Vec2D pos, String name) {
		super(pos, name, "red");
		classname = DEF_CLASSNAME;
	}
}
