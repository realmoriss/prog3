package me.realmoriss.prog3.nagyhf.entities;
import me.realmoriss.prog3.nagyhf.entities.primitives.Vec2D;

/**
 * Created on 11/29/16.
 */
public class Brick extends GraphicEntity {
	private static final String DEF_CLASSNAME = "prop_brick";
	private static final String BRICK_IMG = "assets/img/brick.png";
	protected boolean destroyed = false;

	public Brick(Vec2D pos, String name) {
		super(pos, name, BRICK_IMG);
		classname = DEF_CLASSNAME;
	}

	public Brick(Vec2D pos, String name, Vec2D size) {
		super(pos, name, BRICK_IMG, size);
		classname = DEF_CLASSNAME;
	}

	public boolean getDestroyed() {
		return destroyed;
	}

	public void destroy() {
		if (!destroyed) {
			destroyed = true;
		}
	}

}
